package com.cditie.restor.common.barrage;

import com.cditie.restor.common.SpringUitls;
import com.cditie.restor.model.BarrageVo;
import com.cditie.restor.service.IBarrage;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhuyunhui
 * @since 4/25/2018
 */
@Data
@Slf4j
//@Component
public class BarrageQueue implements InitializingBean{

    public static final LinkedBlockingQueue<BarrageVo> queue = new LinkedBlockingQueue();

    @Override
    public void afterPropertiesSet() throws Exception{
        create();
    }

    private  void create() {

        ScheduledExecutorService pool = new ScheduledThreadPoolExecutor(1);
        pool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    log.info(">>>create");
                    Long start = System.currentTimeMillis();
                    //获取所有数据
                    if(SpringUitls.CONTEXT == null){
                        Thread.sleep(100);
                    }
                    Map<String, IBarrage> map = SpringUitls.CONTEXT.getBeansOfType(IBarrage.class);
                    if(map == null){
                        log.error("map is null");
                        return;
                    }
                    for (IBarrage iBarrage : map.values()) {
                        List<BarrageVo> list = iBarrage.getData(new HashMap<>());
                        log.info(iBarrage.getClass() + "->" + list);
                        queue.addAll(list);

                    }
                    log.info(">>>create success,cost time is {}ms", (System.currentTimeMillis() - start));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 0,60, TimeUnit.MINUTES);

    }
}


