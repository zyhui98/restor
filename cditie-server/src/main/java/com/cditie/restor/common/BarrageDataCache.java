package com.cditie.restor.common;

import com.cditie.restor.model.BarrageVo;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;



/**
 * @author zhuyunhui
 * @since 4/25/2018
 */
@Slf4j
public class BarrageDataCache {

    public static  List<BarrageVo> getCache(){
        LoadingCache<String,List<BarrageVo>> cache = CacheBuilder.newBuilder().softValues()
                .maximumSize(5000)
                .expireAfterWrite(1, TimeUnit.HOURS)
                .build(new CacheLoader<String, List<BarrageVo>>() {
                    @Override
                    public List<BarrageVo> load(String id) throws Exception {
                        log.info("id:" + id);
                        List<BarrageVo> list = Arrays.asList(BarrageQueue.queue.toArray(new BarrageVo[0]));
                        return list;
                    }
                });
        try {
            return cache.get("default");
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();

    }


}
