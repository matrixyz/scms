package com.hb.scms.listener;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import com.hb.scms.dao.*;

import java.util.HashMap;

@WebListener
@MapperScan(basePackages = {"com.hb.scms.dao","com.hb.scms.service"})
public class Listerner implements HttpSessionListener, ServletContextListener {


    @Autowired
    ApplicationContext context;
    @Autowired
    SysDataTypeMapper SysDataTypeMapper;
    @Override

    public void contextInitialized(ServletContextEvent servletContextEvent) {

        servletContextEvent.getServletContext().setAttribute("SysDataTypeLs",SysDataTypeMapper.selectList());

    }









    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {


    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

    }
}
