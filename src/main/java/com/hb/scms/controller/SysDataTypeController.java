package com.hb.scms.controller;
import com.hb.scms.dao.SysDataTypeMapper;
import com.hb.scms.model.*;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.validation.BindingResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import com.hb.scms.util.UuidUtil;
@Controller
@EnableAutoConfiguration
@RequestMapping("/SysDataType")
public class SysDataTypeController {
    @Autowired
    private SysDataTypeMapper SysDataTypeMapper;

    
    @ResponseBody
    @RequestMapping(value="/list",method= RequestMethod.GET )
    public List<SysDataType> getList(Model model,SysDataType sysDataType  ,BindingResult bindingResult){

        
        List<SysDataType> list=SysDataTypeMapper.selectList( );
        
        model.addAttribute("SysDataTypeList", list);
        
        return list;
    }

    



    public static void main(String argv[]){
        SpringApplication.run(SysDataTypeController.class,argv);
    }

}
