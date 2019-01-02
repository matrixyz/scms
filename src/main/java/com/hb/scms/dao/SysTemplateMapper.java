package com.hb.scms.dao;

import com.hb.scms.model.SysTemplate;
import com.hb.scms.model.SysTemplateDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;
@Mapper
@Repository
public interface SysTemplateMapper {
    @Select("select * from sys_template")
    List<SysTemplateDto>  selectDto(SysTemplateDto SysTemplateDto);

    @SelectProvider(type=SqlProvider.class,method="selectCondition")
    SysTemplate  select(SysTemplate SysTemplate);

    @Select("select * from sys_template")
    List<SysTemplate>  selectList(SysTemplate SysTemplate);

    @UpdateProvider(type=SqlProvider.class,method="updateCondition")
    int  update(SysTemplate SysTemplate);

    @InsertProvider(type=SqlProvider.class,method="insertCondition")
    int  insert(SysTemplate SysTemplate);

    class SqlProvider{
        public String selectCondition(SysTemplate SysTemplate){
            return new SQL(){{
                SELECT("*");
                FROM("sys_template");
                WHERE("tpl_id=#{tplId}");

            }}.toString();
        }
        public String updateCondition(SysTemplate SysTemplate){
            return new SQL(){{
                UPDATE("sys_template");

                if(SysTemplate.getTplId() !=null){
                    SET("tpl_id=#{tplId}");
                }
                if(SysTemplate.getTplTypeid() !=null){
                    SET("tpl_typeid=#{tplTypeid}");
                }
                if(SysTemplate.getTplName() !=null){
                    SET("tpl_name=#{tplName}");
                }
                if(SysTemplate.getTplSource() !=null){
                    SET("tpl_source=#{tplSource}");
                }
                if(SysTemplate.getTplLast() !=null){
                    SET("tpl_last=#{tplLast}");
                }

                 WHERE("tpl_id=#{tplId}");


            }}.toString();
        }
        public String insertCondition(SysTemplate SysTemplate){
            return new SQL(){{
                INSERT_INTO("sys_template");

                if(SysTemplate.getTplId() !=null){
                    SET("tpl_id=#{tplId}");
                    VALUES("tpl_id","#{tplId}");
                }
                if(SysTemplate.getTplTypeid() !=null){
                    SET("tpl_typeid=#{tplTypeid}");
                    VALUES("tpl_typeid","#{tplTypeid}");
                }
                if(SysTemplate.getTplName() !=null){
                    SET("tpl_name=#{tplName}");
                    VALUES("tpl_name","#{tplName}");
                }
                if(SysTemplate.getTplSource() !=null){
                    SET("tpl_source=#{tplSource}");
                    VALUES("tpl_source","#{tplSource}");
                }
                if(SysTemplate.getTplLast() !=null){
                    SET("tpl_last=#{tplLast}");
                    VALUES("tpl_last","#{tplLast}");
                }

            }}.toString();
        }
    }




    }

