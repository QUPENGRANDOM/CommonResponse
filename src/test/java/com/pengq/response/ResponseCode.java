package com.pengq.response;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pengq on 2018/9/12 22:04.
 */
public enum ResponseCode implements Message {
    SUCCESS("200"),
    ERROR("500");

    private String code;

    ResponseCode(String code) {
        this.code = code;
    }

    private static final Map<ResponseCode, String> messages = new HashMap<ResponseCode, String>() {{
        put(SUCCESS, "The operation is finished successfully.");  put(ERROR, "The operation is finished error.");

    }};

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return messages.get(this);
    }
}
