package com.cditie.restor.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author zhuyunhui
 * @since 4/25/2018
 */
@Component
@Order(value = 0)
@Slf4j
public class SpringUitls implements ApplicationContextAware{

    public static ApplicationContext CONTEXT;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info("applicationContext:{}" , applicationContext.getDisplayName());
        SpringUitls.CONTEXT = applicationContext;
    }

}
