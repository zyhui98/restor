package com.cditie.restor.common;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


/**
 * @author zhuyunhui
 * @since 4/27/2018
 */
public class ApiURL {
    private static RestTemplate restTemplate = new RestTemplate(createFactory());

    public static final String WEATHER_URL = "https://www.sojson.com/open/api/weather/json.shtml?city=${city}";

    public static ClientHttpRequestFactory createFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(3000);
        factory.setReadTimeout(5000);
        return factory;
    }

    public static String getValue(String url,Map<String,Object> params){
        for(Map.Entry<String,Object> entry:params.entrySet()){
            if(entry.getValue() == null){
                return url;
            }
            url = url.replace("${" + entry.getKey() + "}",entry.getValue().toString());
        }
        return url;
    }

    public static JSONObject getJsonResult(String url,Map<String,Object> paramsMust){
        String urlTemp = getValue(url, paramsMust);
        return  restTemplate.getForObject(urlTemp, JSONObject.class, new HashMap<>());
    }
}
