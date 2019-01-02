package com.hb.scms.dao;

import com.hb.scms.model.SysCol;
import com.hb.scms.model.SysColDto;
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
public interface SysColMapper {

    @Select("select * from sys_col")
    List<SysColDto>  selectDto(SysColDto SysColDto);

    @SelectProvider(type=SqlProvider.class,method="selectCondition")
    SysCol  select(SysCol SysCol);

    @Select("select * from sys_col")
    List<SysCol>  selectList(SysCol SysCol);

    @Delete("delete from sys_col where col_id =#{colId}")
    int  del(@Param("colId") String colId);

    @UpdateProvider(type=SqlProvider.class,method="updateCondition")
    int  update(SysCol SysCol);

    @InsertProvider(type=SqlProvider.class,method="insertCondition")
    int  insert(SysCol SysCol);

    class SqlProvider{
        public String selectCondition(SysCol SysCol){
            return new SQL(){{
                SELECT("*");
                FROM("sys_col");
                WHERE("col_id=#{colId}");

            }}.toString();
        }
        public String updateCondition(SysCol SysCol){
            return new SQL(){{
                UPDATE("sys_col");

                if(SysCol.getColId() !=null){
                    SET("col_id=#{colId}");
                }
                if(SysCol.getColNam() !=null){
                    SET("col_nam=#{colNam}");
                }
                if(SysCol.getColTy() !=null){
                    SET("col_ty=#{colTy}");
                }
                if(SysCol.getColCmt() !=null){
                    SET("col_cmt=#{colCmt}");
                }
                if(SysCol.getColDef() !=null){
                    SET("col_def=#{colDef}");
                }
                if(SysCol.getColForeTb() !=null){
                    SET("col_fore_tb=#{colForeTb}");
                }
                if(SysCol.getColForCol() !=null){
                    SET("col_for_col=#{colForCol}");
                }
                if(SysCol.getColTbid() !=null){
                    SET("col_tbid=#{colTbid}");
                }
                if(SysCol.getColIndex() !=null){
                    SET("col_index=#{colIndex}");
                }
                if(SysCol.getColShowFront() !=null){
                    SET("col_show_front=#{colShowFront}");
                }

                 WHERE("col_id=#{colId}");


            }}.toString();
        }
        public String insertCondition(SysCol SysCol){
            return new SQL(){{
                INSERT_INTO("sys_col");

                if(SysCol.getColId() !=null){
                    SET("col_id=#{colId}");
                    VALUES("col_id","#{colId}");
                }
                if(SysCol.getColNam() !=null){
                    SET("col_nam=#{colNam}");
                    VALUES("col_nam","#{colNam}");
                }
                if(SysCol.getColTy() !=null){
                    SET("col_ty=#{colTy}");
                    VALUES("col_ty","#{colTy}");
                }
                if(SysCol.getColCmt() !=null){
                    SET("col_cmt=#{colCmt}");
                    VALUES("col_cmt","#{colCmt}");
                }
                if(SysCol.getColDef() !=null){
                    SET("col_def=#{colDef}");
                    VALUES("col_def","#{colDef}");
                }
                if(SysCol.getColForeTb() !=null){
                    SET("col_fore_tb=#{colForeTb}");
                    VALUES("col_fore_tb","#{colForeTb}");
                }
                if(SysCol.getColForCol() !=null){
                    SET("col_for_col=#{colForCol}");
                    VALUES("col_for_col","#{colForCol}");
                }
                if(SysCol.getColTbid() !=null){
                    SET("col_tbid=#{colTbid}");
                    VALUES("col_tbid","#{colTbid}");
                }
                if(SysCol.getColIndex() !=null){
                    SET("col_index=#{colIndex}");
                    VALUES("col_index","#{colIndex}");
                }
                if(SysCol.getColShowFront() !=null){
                    SET("col_show_front=#{colShowFront}");
                    VALUES("col_show_front","#{colShowFront}");
                }

            }}.toString();
        }
    }




    }

