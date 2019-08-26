package pengq.common.response;

/**
 * Created by pengq on 2018/9/12 22:04.
 */
public enum ResponseCode implements Message {
    SUCCESS("200","The operation is finished successfully."),
    ERROR("500","The operation is finished error.");

    private String code;
    private String message;

    ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
