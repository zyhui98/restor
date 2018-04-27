package com.cditie.restor.service;

import com.cditie.restor.model.BarrageVo;

import java.util.List;
import java.util.Map;

/**
 * @author zhuyunhui
 * @since 4/25/2018
 */
public interface IBarrage {
    public List<BarrageVo> getData(Map<String,Object> params);
}
