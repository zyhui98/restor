package com.cditie.restor.service;

import com.alibaba.fastjson.JSONObject;
import com.cditie.restor.common.ApiURL;
import com.cditie.restor.model.BarrageVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author zhuyunhui
 * @since 4/25/2018
 */
@Component
@Slf4j
public class WeatherDataService implements IBarrage{


    public String getWeather(){
        String result = "无数据";
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("city", "上海");
            JSONObject jsonObject = ApiURL.getJsonResult(ApiURL.WEATHER_URL,params);
            if(jsonObject.containsKey("data")){
                String wendu = jsonObject.getJSONObject("data").getString("wendu");
                String quality = jsonObject.getJSONObject("data").getString("quality");
                String notice = String.format("今天温度%s℃,空气质量%s", wendu, quality);
                log.info(notice);
                return  notice;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return result;
    }

    @Override
    public List<BarrageVo> getData(Map<String,Object> params){
        List<BarrageVo> resultCache = new ArrayList<>();
        //一小时更新一次天气数据，打个比方
        if(params.isEmpty()){
            params.put("city", "上海");
        }
        try {
            JSONObject jsonObject = ApiURL.getJsonResult(ApiURL.WEATHER_URL,params);
            if(jsonObject.containsKey("data")){
                String wendu = jsonObject.getJSONObject("data").getString("wendu");
                String quality = jsonObject.getJSONObject("data").getString("quality");
                String notice = String.format("今天温度%s℃,空气质量%s", wendu, quality);
                log.info(notice);
                BarrageVo barrageVo = new BarrageVo();
                barrageVo.setInfo(notice);
                barrageVo.setSpeed(20);
                barrageVo.setBottom(500 + new Random().nextInt(100));
                resultCache.add(barrageVo);
            }else {
                log.error("error is:" + jsonObject);
                return resultCache;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }

        return resultCache;
    }
}
