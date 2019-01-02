package com.hb.scms.model;
import com.hb.scms.util.StringUtil;
public class SysDataType{

        // 数据类型主键
        private String dtyId;
        // 数据类型名称
        private String dtyNam;
        // 数据类型
        private String dtyVal;
        // 数据类型长度
        private int dtyLen;

          public void setDtyId(String dtyId) {

                this.dtyId = StringUtil.filterEmpty(dtyId);

          }
          public String getDtyId() {
            return dtyId;
          }
          public void setDtyNam(String dtyNam) {

                this.dtyNam = StringUtil.filterEmpty(dtyNam);

          }
          public String getDtyNam() {
            return dtyNam;
          }
          public void setDtyVal(String dtyVal) {

                this.dtyVal = StringUtil.filterEmpty(dtyVal);

          }
          public String getDtyVal() {
            return dtyVal;
          }
          public void setDtyLen(int dtyLen) {
                 this.dtyLen = dtyLen;
          }
          public int getDtyLen() {
            return dtyLen;
          }
    }