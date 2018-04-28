package com.cditie.restor.service;

import com.cditie.restor.model.BarrageVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zhuyunhui
 * @since 4/25/2018
 */
@Component
@Slf4j
public class HistoryTodayService implements IBarrage{

    @Override
    public List<BarrageVo> getData(Map<String,Object> params){
        List<BarrageVo> list = new ArrayList<>();
        BarrageVo barrageVo = new BarrageVo();
        barrageVo.setInfo("历史上的今天是个什么鬼？");

        list.add(barrageVo);
        return list;
    }
}
