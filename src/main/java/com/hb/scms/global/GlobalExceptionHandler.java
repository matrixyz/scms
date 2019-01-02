package com.hb.scms.global;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.util.IOUtils;
import com.hb.scms.exception.BusinessException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest request,
                                            HttpServletResponse response, Object handler, Exception ex){
       // response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        System.out.println("=====================全局异常信息捕获=======================");


        ex.printStackTrace();
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.print(ex.toString());
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            IOUtils.close(out);
        }

        return null;
    }
}