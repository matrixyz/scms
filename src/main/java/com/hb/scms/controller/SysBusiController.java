package com.hb.scms.controller;
import com.hb.scms.dao.SysBusiMapper;
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
@RequestMapping("/SysBusi")
public class SysBusiController {
    @Autowired
    private SysBusiMapper SysBusiMapper;

    @ResponseBody
    @RequestMapping(value="",method= RequestMethod.GET )
    public SysBusi get(Model model,SysBusi sysBusi ){
        SysBusi sysBusiTemp = SysBusiMapper.select(sysBusi) ;
        if (  sysBusiTemp !=null){
            return  sysBusiTemp;
        }

        return null;
    }

    @ResponseBody
    @RequestMapping(value="/list",method= RequestMethod.GET )
    public List<SysBusiDto> getList( SysBusiDto sysBusiDto  ){

        if (sysBusiDto.getPage() == null||"".equals(sysBusiDto.getPage())) {
            sysBusiDto.setPage("1");
        }
        List<SysBusiDto> list=SysBusiMapper.selectDto(sysBusiDto);
        return  list ;
    }

    @RequestMapping(value="/addPrepared",method= RequestMethod.GET )
    public String addPrepared(Model model,SysBusiDto sysBusiDto ){
        sysBusiDto.setBid(UuidUtil.get16UUID());

        model.addAttribute("submitType", "POST");
        model.addAttribute("SysBusiDto", sysBusiDto);
        return "adm/SysBusi-form";
    }

    @ResponseBody
    @RequestMapping(value="",method= RequestMethod.POST )
    public String post(@RequestBody SysBusi sysBusi ){
        int res=SysBusiMapper.insert(sysBusi);
        if (res>0){
            return "添加信息成功!";
        }else {
            return "添加信息失败!";
        }
    }
    @ResponseBody
    @RequestMapping(value="",method= RequestMethod.PUT )
    public String put(@RequestBody SysBusi sysBusi ){
        int res=SysBusiMapper.update(sysBusi);
        if (res>0){
        return "修改信息成功!";
        }else {
        return "修改信息失败!";
        }
    }



    public static void main(String argv[]){
        SpringApplication.run(SysBusiController.class,argv);
    }

}
