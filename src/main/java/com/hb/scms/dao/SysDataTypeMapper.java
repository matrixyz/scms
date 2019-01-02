package com.hb.scms.dao;

import com.hb.scms.model.SysDataType;
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
public interface SysDataTypeMapper {


    @Select("select * from sys_data_type")
    List<SysDataType>  selectList( );
}
