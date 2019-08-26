
package pengq.common.response;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

@JsonSerialize(using = Response.Builder.class)
@JsonDeserialize(using = Response.Parser.class)
public interface Response extends Message {
    Optional<Map<String, Object>> getFields();

    Optional<Object> getData();

    class Builder extends JsonSerializer<Response> {
        private static final Logger logger = LoggerFactory.getLogger(Builder.class);
        private static final String CODE_KEY = "code";
        private static final String MESSAGE_KEY = "message";
        private static final String DATA_KEY = "data";

        public Builder() {
        }

        @Override
        public void serialize(Response response, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            logger.debug("Start serialize Response: {}", response);
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField(CODE_KEY, response.getCode());
            jsonGenerator.writeStringField(MESSAGE_KEY, response.getMessage());
            if (response.getData().isPresent()) {
                jsonGenerator.writeObjectField(DATA_KEY, response.getData().get());
            }

            if (response.getFields().isPresent()) {
                Map<String, Object> fields = response.getFields().get();
                fields.forEach((k, v) -> {
                    try {
                        jsonGenerator.writeObjectField(k, v);
                    } catch (IOException e) {
                        logger.error("write object field fail,key:[{}],value:[{}]", k, v);
                    }
                });
            }

            jsonGenerator.writeEndObject();
            logger.debug("Finished serialize Response：{}", response);
        }
    }

    class Parser extends JsonDeserializer<Response> {
        private static final String CODE_KEY = "code";
        private static final String MESSAGE_KEY = "message";
        private static final String DATA_KEY = "data";

        public Parser() {
        }

        @Override
        public Response deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            ObjectNode nodes = jsonParser.getCodec().readTree(jsonParser);
            Response response = null;
            ResponseParser parser = new ResponseParser(nodes.get(CODE_KEY).asText(), nodes.get(MESSAGE_KEY).asText());
            if (nodes.has(DATA_KEY)) {
                response = parser.withData(nodes.get(DATA_KEY).toString());
            }
            ObjectNode jsonNodes = nodes.remove(Arrays.asList(CODE_KEY, MESSAGE_KEY, DATA_KEY));
            int size = jsonNodes.size();
            if (size != 0) {
                Iterator<Map.Entry<String, JsonNode>> iterator = jsonNodes.fields();
                iterator.forEachRemaining(x-> parser.appendField(x.getKey(),x.getValue()));
            }

            return response;
        }
    }
}
