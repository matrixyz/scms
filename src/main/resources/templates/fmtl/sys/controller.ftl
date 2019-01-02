package ${packageName};
import ${packageNameDao}.${className}Mapper;
import ${packageNameModel}.*;
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
@RequestMapping("/${className}")
public class ${className}Controller {
    @Autowired
    private ${className}Mapper ${className}Mapper;

    @ResponseBody
    @RequestMapping(value="",method= RequestMethod.GET )
    public ${className} get(Model model,${className} ${className?uncap_first} ){
        ${className} ${className?uncap_first}Temp = ${className}Mapper.select(${className?uncap_first}) ;
        if (  ${className?uncap_first}Temp !=null){
            return  ${className?uncap_first}Temp;
        }

        return null;
    }

    @ResponseBody
    @RequestMapping(value="/list",method= RequestMethod.GET )
    public List<${className}Dto> getList( ${className}Dto ${className?uncap_first}Dto  ){

        if (${className?uncap_first}Dto.getPage() == null||"".equals(${className?uncap_first}Dto.getPage())) {
            ${className?uncap_first}Dto.setPage("1");
        }
        List<${className}Dto> list=${className}Mapper.selectDto(${className?uncap_first}Dto);
        return  list ;
    }

    @RequestMapping(value="/addPrepared",method= RequestMethod.GET )
    public String addPrepared(Model model,${className}Dto ${className?uncap_first}Dto ){
        ${className?uncap_first}Dto.set${primaryProp?cap_first}(UuidUtil.get16UUID());

        model.addAttribute("submitType", "POST");
        model.addAttribute("${className}Dto", ${className?uncap_first}Dto);
        return "adm/${className }-form";
    }

    @ResponseBody
    @RequestMapping(value="",method= RequestMethod.POST )
    public String post(@RequestBody ${className} ${className?uncap_first} ){
        int res=${className}Mapper.insert(${className?uncap_first});
        if (res>0){
            return "添加信息成功!";
        }else {
            return "添加信息失败!";
        }
    }
    @ResponseBody
    @RequestMapping(value="",method= RequestMethod.PUT )
    public String put(@RequestBody ${className} ${className?uncap_first} ){
        int res=${className}Mapper.update(${className?uncap_first});
        if (res>0){
        return "修改信息成功!";
        }else {
        return "修改信息失败!";
        }
    }



    public static void main(String argv[]){
        SpringApplication.run(${className}Controller.class,argv);
    }

}
