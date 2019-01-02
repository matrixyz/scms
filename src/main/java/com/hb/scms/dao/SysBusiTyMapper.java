package com.hb.scms.dao;

import com.hb.scms.model.SysBusiTy;
import com.hb.scms.model.SysBusiTyDto;
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
public interface SysBusiTyMapper {
    @Select("select * from sys_busi_ty")
    List<SysBusiTyDto>  selectDto(SysBusiTyDto SysBusiTyDto);

    @SelectProvider(type=SqlProvider.class,method="selectCondition")
    SysBusiTy  select(SysBusiTy SysBusiTy);

    @Select("select * from sys_busi_ty")
    List<SysBusiTy>  selectList(SysBusiTy SysBusiTy);

    @UpdateProvider(type=SqlProvider.class,method="updateCondition")
    int  update(SysBusiTy SysBusiTy);

    @InsertProvider(type=SqlProvider.class,method="insertCondition")
    int  insert(SysBusiTy SysBusiTy);

    class SqlProvider{
        public String selectCondition(SysBusiTy SysBusiTy){
            return new SQL(){{
                SELECT("*");
                FROM("sys_busi_ty");
                WHERE("btid=#{btid}");

            }}.toString();
        }
        public String updateCondition(SysBusiTy SysBusiTy){
            return new SQL(){{
                UPDATE("sys_busi_ty");

                if(SysBusiTy.getBtid() !=null){
                    SET("btid=#{btid}");
                }
                if(SysBusiTy.getBusiTyNam() !=null){
                    SET("busi_ty_nam=#{busiTyNam}");
                }
                if(SysBusiTy.getBusiTyDes() !=null){
                    SET("busi_ty_des=#{busiTyDes}");
                }

                 WHERE("btid=#{btid}");


            }}.toString();
        }
        public String insertCondition(SysBusiTy SysBusiTy){
            return new SQL(){{
                INSERT_INTO("sys_busi_ty");

                if(SysBusiTy.getBtid() !=null){
                    SET("btid=#{btid}");
                    VALUES("btid","#{btid}");
                }
                if(SysBusiTy.getBusiTyNam() !=null){
                    SET("busi_ty_nam=#{busiTyNam}");
                    VALUES("busi_ty_nam","#{busiTyNam}");
                }
                if(SysBusiTy.getBusiTyDes() !=null){
                    SET("busi_ty_des=#{busiTyDes}");
                    VALUES("busi_ty_des","#{busiTyDes}");
                }

            }}.toString();
        }
    }




    }

