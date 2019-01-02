package com.hb.scms.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


import com.hb.scms.util.*;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.beans.PropertyEditorSupport;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@EnableAutoConfiguration
@MapperScan(basePackages={"com.hb.cms.dao" })//单独从这个controller启动的时候要加这个注解扫描装配Mapper
//单独从这个controller启动的时候要加这个注解扫描装配Service
@ServletComponentScan(basePackages = {"com.hb.cms.listener"})
@RequestMapping("/zxc/content")
public class GContentController {

    @Value( "${file.upload.path}")
    private String fileUploadPath;
    @Autowired
    ServletContext applications;
    @Autowired
    HttpSession session;








    @Autowired
    private ServletContext servletContext;

    public static void main(String argv[]){

        SpringApplication.run(GContentController.class,argv);
    }


    @InitBinder
    public void InitBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            public void setAsText(String value) {
                try {
                    setValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(value));
                } catch(ParseException e) {
                    setValue(null);
                }
            }

            public String getAsText() {
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date) getValue());
            }

        });
    }
}
