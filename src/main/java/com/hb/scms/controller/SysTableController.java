package com.hb.scms.controller;
import com.hb.scms.dao.SysTableMapper;
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
@RequestMapping("/SysTable")
public class SysTableController {
    @Autowired
    private SysTableMapper SysTableMapper;

    @RequestMapping(value="",method= RequestMethod.GET )
    public String get(Model model,SysTable sysTable ){
        SysTable sysTableTemp = SysTableMapper.select(sysTable) ;
        if (  sysTableTemp !=null){

            model.addAttribute("SysTableDto", sysTableTemp);
            return "adm/SysTable-form";

        }

        model.addAttribute("SysTableDto",new SysTable());
        model.addAttribute("error", "未查询到该信息");


        return "adm/SysTable-form";
    }
    @ResponseBody
    @RequestMapping(value="/list",method= RequestMethod.GET )
    public List<SysTableDto> getList(Model model,SysTableDto sysTableDto   ){


        List<SysTableDto> list=SysTableMapper.selectDto(sysTableDto);



        return list;
    }

    @RequestMapping(value="/addPrepared",method= RequestMethod.GET )
    public String addPrepared(Model model,SysTableDto sysTableDto ){
        sysTableDto.setTbId(UuidUtil.get16UUID());

        model.addAttribute("submitType", "POST");
        model.addAttribute("SysTableDto", sysTableDto);
        return "adm/SysTable-form";
    }

    @ResponseBody
    @RequestMapping(value="",method= RequestMethod.POST )
    public String post(@RequestBody SysTable sysTable ){
        int res=SysTableMapper.insert(sysTable);
        if (res>0){
            return "添加信息成功!";
        }else {
            return "添加信息失败!";
        }
    }
    @ResponseBody
    @RequestMapping(value="",method= RequestMethod.PUT )
    public String put(@RequestBody SysTable sysTable ){
        int res=SysTableMapper.update(sysTable);
        if (res>0){
        return "修改信息成功!";
        }else {
        return "修改信息失败!";
        }
    }



    public static void main(String argv[]){
        SpringApplication.run(SysTableController.class,argv);
    }

}
