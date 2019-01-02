package com.hb.scms.model;
import com.hb.scms.util.StringUtil;
public class SysBusi{

        // 主键
        private String bid;
        // 业务名称
        private String busiNam;
        // 业务描述
        private String busiDes;
        // 模型数据操作类型（添加，删除，修改，查询）
        private String busiOptTy;
        // 业务controller方法所在全路径类名
        private String busiCtlPath;
        // 模型的controller方法模板
        private String busiCtl;
        // 业务服务层所在类全路径类名
        private String busiServClsPath;
        // 模型的service方法模板
        private String busiServ;
        // 业务持久层所在接口全路径类名或接口名
        private String busiDaoClsPath;
        // 模型的持久化方法
        private String busiDao;
        // 视图层代码所在文件全路径
        private String busiViewFilPath;
        // 模型的模板表ID
        private String busiView;
        // 查询SQLid
        private String busiQuerySqlId;
        // 对应的模型ID
        private int busiMid;

          public void setBid(String bid) {

                this.bid = StringUtil.filterEmpty(bid);

          }
          public String getBid() {
            return bid;
          }
          public void setBusiNam(String busiNam) {

                this.busiNam = StringUtil.filterEmpty(busiNam);

          }
          public String getBusiNam() {
            return busiNam;
          }
          public void setBusiDes(String busiDes) {

                this.busiDes = StringUtil.filterEmpty(busiDes);

          }
          public String getBusiDes() {
            return busiDes;
          }
          public void setBusiOptTy(String busiOptTy) {

                this.busiOptTy = StringUtil.filterEmpty(busiOptTy);

          }
          public String getBusiOptTy() {
            return busiOptTy;
          }
          public void setBusiCtlPath(String busiCtlPath) {

                this.busiCtlPath = StringUtil.filterEmpty(busiCtlPath);

          }
          public String getBusiCtlPath() {
            return busiCtlPath;
          }
          public void setBusiCtl(String busiCtl) {

                this.busiCtl = StringUtil.filterEmpty(busiCtl);

          }
          public String getBusiCtl() {
            return busiCtl;
          }
          public void setBusiServClsPath(String busiServClsPath) {

                this.busiServClsPath = StringUtil.filterEmpty(busiServClsPath);

          }
          public String getBusiServClsPath() {
            return busiServClsPath;
          }
          public void setBusiServ(String busiServ) {

                this.busiServ = StringUtil.filterEmpty(busiServ);

          }
          public String getBusiServ() {
            return busiServ;
          }
          public void setBusiDaoClsPath(String busiDaoClsPath) {

                this.busiDaoClsPath = StringUtil.filterEmpty(busiDaoClsPath);

          }
          public String getBusiDaoClsPath() {
            return busiDaoClsPath;
          }
          public void setBusiDao(String busiDao) {

                this.busiDao = StringUtil.filterEmpty(busiDao);

          }
          public String getBusiDao() {
            return busiDao;
          }
          public void setBusiViewFilPath(String busiViewFilPath) {

                this.busiViewFilPath = StringUtil.filterEmpty(busiViewFilPath);

          }
          public String getBusiViewFilPath() {
            return busiViewFilPath;
          }
          public void setBusiView(String busiView) {

                this.busiView = StringUtil.filterEmpty(busiView);

          }
          public String getBusiView() {
            return busiView;
          }
          public void setBusiQuerySqlId(String busiQuerySqlId) {

                this.busiQuerySqlId = StringUtil.filterEmpty(busiQuerySqlId);

          }
          public String getBusiQuerySqlId() {
            return busiQuerySqlId;
          }
          public void setBusiMid(int busiMid) {
                 this.busiMid = busiMid;
          }
          public int getBusiMid() {
            return busiMid;
          }
    }