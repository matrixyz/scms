package com.hb.scms.controller;
import com.hb.scms.dao.SysTemplateMapper;
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
@RequestMapping("/SysTemplate")
public class SysTemplateController {
    @Autowired
    private SysTemplateMapper SysTemplateMapper;

    @ResponseBody
    @RequestMapping(value="",method= RequestMethod.GET )
    public SysTemplate get(Model model,SysTemplate sysTemplate ){
        SysTemplate sysTemplateTemp = SysTemplateMapper.select(sysTemplate) ;
        if (  sysTemplateTemp !=null){
            return  sysTemplateTemp;
        }

        return null;
    }

    @ResponseBody
    @RequestMapping(value="/list",method= RequestMethod.GET )
    public List<SysTemplateDto> getList( SysTemplateDto sysTemplateDto  ){

        if (sysTemplateDto.getPage() == null||"".equals(sysTemplateDto.getPage())) {
            sysTemplateDto.setPage("1");
        }
        List<SysTemplateDto> list=SysTemplateMapper.selectDto(sysTemplateDto);
        return  list ;
    }

    @RequestMapping(value="/addPrepared",method= RequestMethod.GET )
    public String addPrepared(Model model,SysTemplateDto sysTemplateDto ){
        sysTemplateDto.setTplId(UuidUtil.get16UUID());

        model.addAttribute("submitType", "POST");
        model.addAttribute("SysTemplateDto", sysTemplateDto);
        return "adm/SysTemplate-form";
    }

    @ResponseBody
    @RequestMapping(value="",method= RequestMethod.POST )
    public String post(@RequestBody SysTemplate sysTemplate ){
        int res=SysTemplateMapper.insert(sysTemplate);
        if (res>0){
            return "添加信息成功!";
        }else {
            return "添加信息失败!";
        }
    }
    @ResponseBody
    @RequestMapping(value="",method= RequestMethod.PUT )
    public String put(@RequestBody SysTemplate sysTemplate ){
        int res=SysTemplateMapper.update(sysTemplate);
        if (res>0){
        return "修改信息成功!";
        }else {
        return "修改信息失败!";
        }
    }



    public static void main(String argv[]){
        SpringApplication.run(SysTemplateController.class,argv);
    }

}
