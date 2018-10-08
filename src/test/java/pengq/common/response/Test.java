package pengq.common.response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by pengq on 2018/9/12 21:46.
 */
public class Test {
    private static Logger logger = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args){
        Response response = RestResponse.create("123","shibai");
        response = RestResponse.create(ResponseCode.SUCCESS);
        Test t = new Test();
        try {
            t.get();
        } catch (TestException e) {
          response = RestResponse.create(e);
          logger.info(response.getMessage());
        }
        System.currentTimeMillis();
    }

   private String get() throws TestException {
        throw new TestException(ResponseCode.ERROR);
   }

}
