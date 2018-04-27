package com.cditie.restor.controller;

import com.cditie.restor.common.BarrageDataCache;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhuyunhui
 * @since 4/25/2018
 */

@RestController
public class ApiController {

    @GetMapping(value = "barrage")
    private Object getBarrage(){
        return BarrageDataCache.getCache();
    }

}
