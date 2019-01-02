package com.hb.scms.model;
import com.hb.scms.util.StringUtil;
public class SysBusiTy{

        // 操作类型主键
        private String btid;
        // 操作类型名称
        private String busiTyNam;
        // 操作类型描述
        private String busiTyDes;

          public void setBtid(String btid) {

                this.btid = StringUtil.filterEmpty(btid);

          }
          public String getBtid() {
            return btid;
          }
          public void setBusiTyNam(String busiTyNam) {

                this.busiTyNam = StringUtil.filterEmpty(busiTyNam);

          }
          public String getBusiTyNam() {
            return busiTyNam;
          }
          public void setBusiTyDes(String busiTyDes) {

                this.busiTyDes = StringUtil.filterEmpty(busiTyDes);

          }
          public String getBusiTyDes() {
            return busiTyDes;
          }
    }