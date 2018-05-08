package com.cditie.restor.service;

import com.cditie.restor.mapper.TFeedDetailMapper;
import com.cditie.restor.model.BarrageVo;
import com.cditie.restor.model.entity.TFeedDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author zhuyunhui
 * @since 4/25/2018
 */
@Component
@Slf4j
public class HistoryTodayService implements IBarrage{

    @Autowired
    private TFeedDetailMapper tFeedDetailMapper;
    @Override
    public List<BarrageVo> getData(Map<String,Object> params){
        TFeedDetail tFeedDetail = new TFeedDetail();
        tFeedDetail.setFeedCode("today");
        List<TFeedDetail> listOrg = tFeedDetailMapper.select(tFeedDetail);
        Collections.shuffle(listOrg);
        List<BarrageVo> list = new ArrayList<>();
        listOrg.forEach(item->{
            BarrageVo barrageVo = new BarrageVo();
            barrageVo.setInfo(item.getTitle());
            barrageVo.setSpeed(20);
            barrageVo.setBottom(500 + new Random().nextInt(100));
            list.add(barrageVo);
        });

        return list;
    }
}
