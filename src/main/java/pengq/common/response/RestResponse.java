package pengq.common.response;

import pengq.common.response.exception.GenericException;

public class RestResponse extends GenericResponse{
    private  String code;
    private  String message;
    private  <T extends Message> RestResponse(T  response) {
        this.code = response.getCode();
        this.message = response.getMessage();
    }

    private RestResponse(String code,String message) {
        this.code = code;
        this.message = message;
    }

    private RestResponse(GenericException e) {
        this.code = e.getResponseCode();
        this.message = e.getMessage();
    }

    public static <T extends Message>  RestResponse create(T response) {
        return new RestResponse(response);
    }
    
    public static RestResponse create(String code,String message) {
        return new RestResponse(code,message);
    }

    public static RestResponse create(GenericException e) {
        return new RestResponse(e);
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}