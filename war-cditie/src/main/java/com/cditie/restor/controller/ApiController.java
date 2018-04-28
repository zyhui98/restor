package com.cditie.restor.controller;

import com.cditie.restor.common.BarrageDataCache;
import com.cditie.restor.mapper.BlogMapper;
import com.cditie.restor.service.HappyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhuyunhui
 * @since 4/25/2018
 */

@RestController
public class ApiController {


    @Autowired
    private HappyService happyService;

    @GetMapping(value = "barrage")
    private Object getBarrage(){
        return BarrageDataCache.getCache();
    }

    @GetMapping(value = "happy")
    private Object happy(){
        return happyService.happy();
    }


}
