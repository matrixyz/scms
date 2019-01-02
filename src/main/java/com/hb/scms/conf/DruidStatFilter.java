package com.hb.scms.conf;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import com.alibaba.druid.support.http.WebStatFilter;


@WebFilter(filterName="druidWebStatFilter",urlPatterns="/zxc/*",
        initParams={
                @WebInitParam(name="exclusions",value="*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/zxc/druid/*")// 忽略资源
        })
public class DruidStatFilter extends WebStatFilter {

}