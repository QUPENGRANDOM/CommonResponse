package com.pengq.response;

import com.pengq.response.exception.GenericException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pengq on 2018/9/12 21:46.
 */
public class Test {
    public static void main(String[] args){
        Response response = RestResponse.create("123","shibai");
        response = RestResponse.create(ResponseCode.SUCCESS);
        Test t = new Test();
        try {
            t.get();
        } catch (TestException e) {
          response = RestResponse.create(e);
        }
        System.currentTimeMillis();
    }

   private String get() throws TestException {
        throw new TestException(ResponseCode.ERROR);
   }

}
