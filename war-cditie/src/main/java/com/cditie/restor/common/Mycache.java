package com.cditie.restor.common;

import com.cditie.restor.common.barrage.BarrageQueue;
import com.cditie.restor.model.BarrageVo;
import com.cditie.restor.service.WeatherDataService;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author zhuyunhui
 * @since 5/8/2018
 */
@Slf4j
public class Mycache {
    public final static String KEY_WEATHER = "key_weather";
    public final static String TYPE_HOUR = "TYPE_HOUR";

    public final static LoadingCache<String,Object> CACHE_1_HOURS = CacheBuilder.newBuilder().softValues()
            .maximumSize(5000)
            .expireAfterAccess(1, TimeUnit.HOURS)
            .build(new CacheLoader<String, Object>() {
                @Override
                public Object load(String key) throws Exception {
                    if(KEY_WEATHER.equals(key)){
                        return SpringUitls.CONTEXT.getBean(WeatherDataService.class).getWeather();
                    }
                    return null;
                }
            });

    public static  Object getCache(String type,String key){
        try {
            return CACHE_1_HOURS.get(key);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
