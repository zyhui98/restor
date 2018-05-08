package com.cditie.restor.service;

import com.alibaba.fastjson.JSONObject;
import com.cditie.restor.mapper.BlogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhuyunhui
 * @since 4/28/2018
 */
@Component
public class HappyService {

    @Autowired
    private BlogMapper blogMapper;

    public Object happy(){
        List list = blogMapper.selectAll();
        return JSONObject.toJSONString(list);

    }
}
