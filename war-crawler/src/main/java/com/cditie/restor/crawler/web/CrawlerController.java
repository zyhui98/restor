package com.cditie.restor.crawler.web;

import cn.wanghaomiao.seimi.core.Seimi;
import com.alibaba.fastjson.JSONObject;
import com.cditie.restor.crawler.model.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhuyunhui
 * @since 4/27/2018
 */
@RestController
@RequestMapping("crawler")
@Slf4j
public class CrawlerController {

    private Seimi seimi = new Seimi();

    @RequestMapping("run")
    public Object run(@RequestBody JSONObject jsonObject){
        String code = jsonObject.getString("code");
        new Thread(){
            @Override
            public void run() {
                seimi.goRun("today");
            }
        }.start();
        ResponseDto responseDto = new ResponseDto(true);

        return responseDto;
    }

}
