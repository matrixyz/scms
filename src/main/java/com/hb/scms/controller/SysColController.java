package com.hb.scms.controller;
import com.hb.scms.dao.SysColMapper;
import com.hb.scms.model.*;
import com.hb.scms.util.ClassUtil;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Stack;

import com.hb.scms.util.UuidUtil;
@Controller
@EnableAutoConfiguration
@MapperScan(basePackages={"com.hb.scms.dao" })//单独从这个controller启动的时候要加这个注解扫描装配Mapper

@RequestMapping("/SysCol")
public class SysColController {
    @Autowired
    private SysColMapper SysColMapper;

    @ResponseBody
    @RequestMapping(value="",method= RequestMethod.GET )
    public SysColDto get(Model model,SysColDto sysCol ){
        SysCol  sysColTemp =  SysColMapper.select(sysCol) ;
        SysColDto SysColDto=new SysColDto();
        if (  sysColTemp !=null){
            try {
                ClassUtil.fatherToChild(sysColTemp,  SysColDto );
            } catch (Exception e) {
                e.printStackTrace();
            }
            SysColDto.setSubmitType("PUT");

            return SysColDto;

        }

        SysColDto.setError("获取数据信息失败!");



        return SysColDto;
    }
    @ResponseBody
    @RequestMapping(value="/primarykey",method= RequestMethod.GET )
    public String getPrimarykey( ){

        return UuidUtil.get16UUID();
    }
    @ResponseBody
    @RequestMapping(value="",method= RequestMethod.DELETE )
    public String del(@Param("colId") String colId){
        int res=SysColMapper.del(colId);

        return "ok";
    }
   @ResponseBody
    @RequestMapping(value="/list",method= RequestMethod.GET )
    public List<SysColDto> getList(    SysColDto sysColDto  ){


        List<SysColDto> list= SysColMapper.selectDto(sysColDto);


        return list;
    }
    @RequestMapping(value="/addPrepared",method= RequestMethod.GET )
    public String addPrepared(Model model,SysColDto sysColDto ){
        sysColDto.setColId(UuidUtil.get16UUID());

        model.addAttribute("submitType", "POST");
        model.addAttribute("SysColDto", sysColDto);
        return "adm/SysCol-form";
    }

    @ResponseBody
    @RequestMapping(value="",method= RequestMethod.POST )
    public String post(@RequestBody SysCol sysCol ){
        int res= SysColMapper.insert(sysCol);
        if (res>0){
            return "添加信息成功!";
        }else {
            return "添加信息失败!";
        }
    }
    @ResponseBody
    @RequestMapping(value="",method= RequestMethod.PUT )
    public String put(@RequestBody SysCol sysCol ){
        int res= SysColMapper.update(sysCol);
        if (res>0){
        return "修改信息成功!";
        }else {
        return "修改信息失败!";
        }
    }



    public static void main(String argv[]){
        SpringApplication.run(SysColController.class,argv);
        Stack a=new Stack();

    }

}
