package com.cditie.restor.controller;

import com.cditie.restor.common.Mycache;
import com.cditie.restor.common.barrage.BarrageDataCache;
import com.cditie.restor.model.BarrageVo;
import com.cditie.restor.service.HappyService;
import com.cditie.restor.service.WeatherDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhuyunhui
 * @since 4/25/2018
 */

@RestController
public class ApiController {


    @Autowired
    private HappyService happyService;

    @Autowired
    private WeatherDataService weatherDataService;

    @GetMapping(value = "barrage")
    private Object getBarrage(){
        return BarrageDataCache.getCache();
    }

    @GetMapping(value = "happy")
    private Object happy(){
        return happyService.happy();
    }

    @RequestMapping(value = "weather")
    private String weather(){
        return (String)Mycache.getCache(Mycache.TYPE_HOUR,Mycache.KEY_WEATHER);
    }


}
