package com.hb.scms.dao;

import com.hb.scms.model.SysTable;
import com.hb.scms.model.SysTableDto;
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
public interface SysTableMapper {
    @Select("select * from sys_table")
    List<SysTableDto>  selectDto(SysTableDto SysTableDto);

    @SelectProvider(type=SqlProvider.class,method="selectCondition")
    SysTable  select(SysTable SysTable);

    @Select("select * from sys_table")
    List<SysTable>  selectList(SysTable SysTable);

    @UpdateProvider(type=SqlProvider.class,method="updateCondition")
    int  update(SysTable SysTable);

    @InsertProvider(type=SqlProvider.class,method="insertCondition")
    int  insert(SysTable SysTable);

    class SqlProvider{
        public String selectCondition(SysTable SysTable){
            return new SQL(){{
                SELECT("*");
                FROM("sys_table");
                WHERE("tb_id=#{tbId}");

            }}.toString();
        }
        public String updateCondition(SysTable SysTable){
            return new SQL(){{
                UPDATE("sys_table");

                if(SysTable.getTbId() !=null){
                    SET("tb_id=#{tbId}");
                }
                if(SysTable.getTbNam() !=null){
                    SET("tb_nam=#{tbNam}");
                }
                if(SysTable.getTbCmt() !=null){
                    SET("tb_cmt=#{tbCmt}");
                }
                if(SysTable.getTbMysqlTy() !=null){
                    SET("tb_mysql_ty=#{tbMysqlTy}");
                }
                if(SysTable.getTbDbTy() !=null){
                    SET("tb_db_ty=#{tbDbTy}");
                }
                if(SysTable.getTbDbid() !=null){
                    SET("tb_dbid=#{tbDbid}");
                }

                 WHERE("tb_id=#{tbId}");


            }}.toString();
        }
        public String insertCondition(SysTable SysTable){
            return new SQL(){{
                INSERT_INTO("sys_table");

                if(SysTable.getTbId() !=null){
                    SET("tb_id=#{tbId}");
                    VALUES("tb_id","#{tbId}");
                }
                if(SysTable.getTbNam() !=null){
                    SET("tb_nam=#{tbNam}");
                    VALUES("tb_nam","#{tbNam}");
                }
                if(SysTable.getTbCmt() !=null){
                    SET("tb_cmt=#{tbCmt}");
                    VALUES("tb_cmt","#{tbCmt}");
                }
                if(SysTable.getTbMysqlTy() !=null){
                    SET("tb_mysql_ty=#{tbMysqlTy}");
                    VALUES("tb_mysql_ty","#{tbMysqlTy}");
                }
                if(SysTable.getTbDbTy() !=null){
                    SET("tb_db_ty=#{tbDbTy}");
                    VALUES("tb_db_ty","#{tbDbTy}");
                }
                if(SysTable.getTbDbid() !=null){
                    SET("tb_dbid=#{tbDbid}");
                    VALUES("tb_dbid","#{tbDbid}");
                }

            }}.toString();
        }
    }




    }

