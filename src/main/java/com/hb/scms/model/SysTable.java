package com.hb.scms.model;
import com.hb.scms.util.StringUtil;
public class SysTable{

        // 主键
        private String tbId;
        // 表名
        private String tbNam;
        // 表注释
        private String tbCmt;
        // mysql引擎类型
        private String tbMysqlTy;
        // 数据库类型
        private String tbDbTy;
        // 数据库外键
        private String tbDbid;

          public void setTbId(String tbId) {

                this.tbId = StringUtil.filterEmpty(tbId);

          }
          public String getTbId() {
            return tbId;
          }
          public void setTbNam(String tbNam) {

                this.tbNam = StringUtil.filterEmpty(tbNam);

          }
          public String getTbNam() {
            return tbNam;
          }
          public void setTbCmt(String tbCmt) {

                this.tbCmt = StringUtil.filterEmpty(tbCmt);

          }
          public String getTbCmt() {
            return tbCmt;
          }
          public void setTbMysqlTy(String tbMysqlTy) {

                this.tbMysqlTy = StringUtil.filterEmpty(tbMysqlTy);

          }
          public String getTbMysqlTy() {
            return tbMysqlTy;
          }
          public void setTbDbTy(String tbDbTy) {

                this.tbDbTy = StringUtil.filterEmpty(tbDbTy);

          }
          public String getTbDbTy() {
            return tbDbTy;
          }
          public void setTbDbid(String tbDbid) {

                this.tbDbid = StringUtil.filterEmpty(tbDbid);

          }
          public String getTbDbid() {
            return tbDbid;
          }
    }