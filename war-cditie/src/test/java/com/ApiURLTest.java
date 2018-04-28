package com;

import com.cditie.restor.common.ApiURL;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhuyunhui
 * @since 4/27/2018
 */
public class ApiURLTest {

    @Test
    public void test(){
        Map<String, Object> params = new HashMap<>();
        params.put("city", "上海");
        System.out.println(ApiURL.getJsonResult(ApiURL.WEATHER_URL,params));
    }
}
