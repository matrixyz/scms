package com.hb.scms.controller;
import com.hb.scms.dao.SysBusiTyMapper;
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
@RequestMapping("/SysBusiTy")
public class SysBusiTyController {
    @Autowired
    private SysBusiTyMapper SysBusiTyMapper;

    @ResponseBody
    @RequestMapping(value="",method= RequestMethod.GET )
    public SysBusiTy get(Model model,SysBusiTy sysBusiTy ){
        SysBusiTy sysBusiTyTemp = SysBusiTyMapper.select(sysBusiTy) ;
        if (  sysBusiTyTemp !=null){
            return  sysBusiTyTemp;
        }

        return null;
    }

    @ResponseBody
    @RequestMapping(value="/list",method= RequestMethod.GET )
    public List<SysBusiTyDto> getList( SysBusiTyDto sysBusiTyDto  ){

        if (sysBusiTyDto.getPage() == null||"".equals(sysBusiTyDto.getPage())) {
            sysBusiTyDto.setPage("1");
        }
        List<SysBusiTyDto> list=SysBusiTyMapper.selectDto(sysBusiTyDto);
        return  list ;
    }

    @RequestMapping(value="/addPrepared",method= RequestMethod.GET )
    public String addPrepared(Model model,SysBusiTyDto sysBusiTyDto ){
        sysBusiTyDto.setBtid(UuidUtil.get16UUID());

        model.addAttribute("submitType", "POST");
        model.addAttribute("SysBusiTyDto", sysBusiTyDto);
        return "adm/SysBusiTy-form";
    }

    @ResponseBody
    @RequestMapping(value="",method= RequestMethod.POST )
    public String post(@RequestBody SysBusiTy sysBusiTy ){
        int res=SysBusiTyMapper.insert(sysBusiTy);
        if (res>0){
            return "添加信息成功!";
        }else {
            return "添加信息失败!";
        }
    }
    @ResponseBody
    @RequestMapping(value="",method= RequestMethod.PUT )
    public String put(@RequestBody SysBusiTy sysBusiTy ){
        int res=SysBusiTyMapper.update(sysBusiTy);
        if (res>0){
        return "修改信息成功!";
        }else {
        return "修改信息失败!";
        }
    }



    public static void main(String argv[]){
        SpringApplication.run(SysBusiTyController.class,argv);
    }

}
