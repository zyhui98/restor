package com.cditie.restor.service;

import com.alibaba.fastjson.JSONObject;
import com.cditie.restor.common.ApiURL;
import com.cditie.restor.model.BarrageVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhuyunhui
 * @since 4/25/2018
 */
@Component
@Slf4j
public class WeatherDataService implements IBarrage{

    private List<BarrageVo> resultCache = new ArrayList<>();

    @Override
    public List<BarrageVo> getData(Map<String,Object> params){
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
                resultCache.clear();
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
