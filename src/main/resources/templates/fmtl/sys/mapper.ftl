package ${packageNameDao};

import ${packageNameModel}.${className};
import ${packageNameModel}.${className}Dto;
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
public interface ${className}Mapper {
    @Select("select * from ${tableName}")
    List<${className}Dto>  selectDto(${className}Dto ${className}Dto);

    @SelectProvider(type=SqlProvider.class,method="selectCondition")
    ${className}  select(${className} ${className});

    @Select("select * from ${tableName}")
    List<${className}>  selectList(${className} ${className});

    @UpdateProvider(type=SqlProvider.class,method="updateCondition")
    int  update(${className} ${className});

    @InsertProvider(type=SqlProvider.class,method="insertCondition")
    int  insert(${className} ${className});

    class SqlProvider{
        public String selectCondition(${className} ${className}){
            return new SQL(){{
                SELECT("*");
                FROM("${tableName}");
                WHERE("${primaryColumn}=${r'#{'}${primaryProp}${r'}'}");

            }}.toString();
        }
        public String updateCondition(${className} ${className}){
            return new SQL(){{
                UPDATE("${tableName}");

                <#list attrs as attr>
                if(${className}.get${attr.name?cap_first}() !=<#if (attr.type == "java.util.Date"|| attr.type == "String"||attr.type?index_of("byte")!=-1)>null<#else>-1</#if>){
                    SET("${attr.column}=${r'#{'}${attr.name}${r'}'}");
                }
                </#list>

                 WHERE("${primaryColumn}=${r'#{'}${primaryProp}${r'}'}");


            }}.toString();
        }
        public String insertCondition(${className} ${className}){
            return new SQL(){{
                INSERT_INTO("${tableName}");

                <#list attrs as attr>
                if(${className}.get${attr.name?cap_first}() !=<#if (attr.type == "java.util.Date"|| attr.type == "String"||attr.type?index_of("byte")!=-1)>null<#else>-1</#if>){
                    SET("${attr.column}=${r'#{'}${attr.name}${r'}'}");
                    VALUES("${attr.column}","${r'#{'}${attr.name}${r'}'}");
                }
                </#list>

            }}.toString();
        }
    }




    }

