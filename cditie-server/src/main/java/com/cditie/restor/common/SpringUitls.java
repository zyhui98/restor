package com.cditie.restor.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author zhuyunhui
 * @since 4/25/2018
 */
@Component
public class SpringUitls implements ApplicationContextAware{

    public static ApplicationContext CONTEXT;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUitls.CONTEXT = applicationContext;
    }

}
