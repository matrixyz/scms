package com.hb.scms.model;
import com.hb.scms.util.StringUtil;
public class SysTemplate{

        // 模板主键
        private String tplId;
        // 模板类型外键
        private String tplTypeid;
        // 模板名称
        private String tplName;
        // 模板内容
        private String tplSource;
        // 最后修改时间
        private java.util.Date tplLast;

          public void setTplId(String tplId) {

                this.tplId = StringUtil.filterEmpty(tplId);

          }
          public String getTplId() {
            return tplId;
          }
          public void setTplTypeid(String tplTypeid) {

                this.tplTypeid = StringUtil.filterEmpty(tplTypeid);

          }
          public String getTplTypeid() {
            return tplTypeid;
          }
          public void setTplName(String tplName) {

                this.tplName = StringUtil.filterEmpty(tplName);

          }
          public String getTplName() {
            return tplName;
          }
          public void setTplSource(String tplSource) {

                this.tplSource = StringUtil.filterEmpty(tplSource);

          }
          public String getTplSource() {
            return tplSource;
          }
          public void setTplLast(java.util.Date tplLast) {
                 this.tplLast = tplLast;
          }
          public java.util.Date getTplLast() {
            return tplLast;
          }
    }