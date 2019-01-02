package com.hb.scms.dao;

import com.hb.scms.model.SysBusi;
import com.hb.scms.model.SysBusiDto;
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
public interface SysBusiMapper {
    @Select("select * from sys_busi")
    List<SysBusiDto>  selectDto(SysBusiDto SysBusiDto);

    @SelectProvider(type=SqlProvider.class,method="selectCondition")
    SysBusi  select(SysBusi SysBusi);

    @Select("select * from sys_busi")
    List<SysBusi>  selectList(SysBusi SysBusi);

    @UpdateProvider(type=SqlProvider.class,method="updateCondition")
    int  update(SysBusi SysBusi);

    @InsertProvider(type=SqlProvider.class,method="insertCondition")
    int  insert(SysBusi SysBusi);

    class SqlProvider{
        public String selectCondition(SysBusi SysBusi){
            return new SQL(){{
                SELECT("*");
                FROM("sys_busi");
                WHERE("bid=#{bid}");

            }}.toString();
        }
        public String updateCondition(SysBusi SysBusi){
            return new SQL(){{
                UPDATE("sys_busi");

                if(SysBusi.getBid() !=null){
                    SET("bid=#{bid}");
                }
                if(SysBusi.getBusiNam() !=null){
                    SET("busi_nam=#{busiNam}");
                }
                if(SysBusi.getBusiDes() !=null){
                    SET("busi_des=#{busiDes}");
                }
                if(SysBusi.getBusiOptTy() !=null){
                    SET("busi_opt_ty=#{busiOptTy}");
                }
                if(SysBusi.getBusiCtlPath() !=null){
                    SET("busi_ctl_path=#{busiCtlPath}");
                }
                if(SysBusi.getBusiCtl() !=null){
                    SET("busi_ctl=#{busiCtl}");
                }
                if(SysBusi.getBusiServClsPath() !=null){
                    SET("busi_serv_cls_path=#{busiServClsPath}");
                }
                if(SysBusi.getBusiServ() !=null){
                    SET("busi_serv=#{busiServ}");
                }
                if(SysBusi.getBusiDaoClsPath() !=null){
                    SET("busi_dao_cls_path=#{busiDaoClsPath}");
                }
                if(SysBusi.getBusiDao() !=null){
                    SET("busi_dao=#{busiDao}");
                }
                if(SysBusi.getBusiViewFilPath() !=null){
                    SET("busi_view_fil_path=#{busiViewFilPath}");
                }
                if(SysBusi.getBusiView() !=null){
                    SET("busi_view=#{busiView}");
                }
                if(SysBusi.getBusiQuerySqlId() !=null){
                    SET("busi_query_sql_id=#{busiQuerySqlId}");
                }
                if(SysBusi.getBusiMid() !=-1){
                    SET("busi_mid=#{busiMid}");
                }

                 WHERE("bid=#{bid}");


            }}.toString();
        }
        public String insertCondition(SysBusi SysBusi){
            return new SQL(){{
                INSERT_INTO("sys_busi");

                if(SysBusi.getBid() !=null){
                    SET("bid=#{bid}");
                    VALUES("bid","#{bid}");
                }
                if(SysBusi.getBusiNam() !=null){
                    SET("busi_nam=#{busiNam}");
                    VALUES("busi_nam","#{busiNam}");
                }
                if(SysBusi.getBusiDes() !=null){
                    SET("busi_des=#{busiDes}");
                    VALUES("busi_des","#{busiDes}");
                }
                if(SysBusi.getBusiOptTy() !=null){
                    SET("busi_opt_ty=#{busiOptTy}");
                    VALUES("busi_opt_ty","#{busiOptTy}");
                }
                if(SysBusi.getBusiCtlPath() !=null){
                    SET("busi_ctl_path=#{busiCtlPath}");
                    VALUES("busi_ctl_path","#{busiCtlPath}");
                }
                if(SysBusi.getBusiCtl() !=null){
                    SET("busi_ctl=#{busiCtl}");
                    VALUES("busi_ctl","#{busiCtl}");
                }
                if(SysBusi.getBusiServClsPath() !=null){
                    SET("busi_serv_cls_path=#{busiServClsPath}");
                    VALUES("busi_serv_cls_path","#{busiServClsPath}");
                }
                if(SysBusi.getBusiServ() !=null){
                    SET("busi_serv=#{busiServ}");
                    VALUES("busi_serv","#{busiServ}");
                }
                if(SysBusi.getBusiDaoClsPath() !=null){
                    SET("busi_dao_cls_path=#{busiDaoClsPath}");
                    VALUES("busi_dao_cls_path","#{busiDaoClsPath}");
                }
                if(SysBusi.getBusiDao() !=null){
                    SET("busi_dao=#{busiDao}");
                    VALUES("busi_dao","#{busiDao}");
                }
                if(SysBusi.getBusiViewFilPath() !=null){
                    SET("busi_view_fil_path=#{busiViewFilPath}");
                    VALUES("busi_view_fil_path","#{busiViewFilPath}");
                }
                if(SysBusi.getBusiView() !=null){
                    SET("busi_view=#{busiView}");
                    VALUES("busi_view","#{busiView}");
                }
                if(SysBusi.getBusiQuerySqlId() !=null){
                    SET("busi_query_sql_id=#{busiQuerySqlId}");
                    VALUES("busi_query_sql_id","#{busiQuerySqlId}");
                }
                if(SysBusi.getBusiMid() !=-1){
                    SET("busi_mid=#{busiMid}");
                    VALUES("busi_mid","#{busiMid}");
                }

            }}.toString();
        }
    }




    }

