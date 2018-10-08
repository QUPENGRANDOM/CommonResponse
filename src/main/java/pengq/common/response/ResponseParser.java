package pengq.common.response;

public class ResponseParser extends GenericResponse {
    private String code;
    private String message;

    ResponseParser(String code, String message){
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
