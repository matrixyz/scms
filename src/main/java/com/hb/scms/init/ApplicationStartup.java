package com.hb.scms.init;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

//暂时没有用
public class ApplicationStartup implements ApplicationListener< ContextRefreshedEvent>  {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

    }


}