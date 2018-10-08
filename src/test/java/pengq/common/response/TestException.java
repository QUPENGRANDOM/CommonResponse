package pengq.common.response;

import pengq.common.response.exception.GenericException;

/**
 * Created by pengq on 2018/9/12 22:05.
 */ public class TestException extends GenericException {
    public TestException(String responseCode, String message) {
        super(responseCode, message);
    }

    public <T extends Message> TestException(T entity) {
        super(entity);
    }
}

