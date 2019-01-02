package com.hb.scms.model;
import com.hb.scms.util.StringUtil;
public class SysCol{

        // 主键
        private String colId;
        // 字段名称
        private String colNam;
        // 字段类型
        private String colTy;
        // 字段注释
        private String colCmt;
        // 字段默认值
        private String colDef;
        // 外键对应表
        private String colForeTb;
        // 外键对应字段
        private String colForCol;
        // 对应表ID
        private String colTbid;
        // 索引类型,none表示非索引
        private String colIndex;
        // 前端显示
        private String colShowFront;

          public void setColId(String colId) {

                this.colId = StringUtil.filterEmpty(colId);

          }
          public String getColId() {
            return colId;
          }
          public void setColNam(String colNam) {

                this.colNam = StringUtil.filterEmpty(colNam);

          }
          public String getColNam() {
            return colNam;
          }
          public void setColTy(String colTy) {

                this.colTy = StringUtil.filterEmpty(colTy);

          }
          public String getColTy() {
            return colTy;
          }
          public void setColCmt(String colCmt) {

                this.colCmt = StringUtil.filterEmpty(colCmt);

          }
          public String getColCmt() {
            return colCmt;
          }
          public void setColDef(String colDef) {

                this.colDef = StringUtil.filterEmpty(colDef);

          }
          public String getColDef() {
            return colDef;
          }
          public void setColForeTb(String colForeTb) {

                this.colForeTb = StringUtil.filterEmpty(colForeTb);

          }
          public String getColForeTb() {
            return colForeTb;
          }
          public void setColForCol(String colForCol) {

                this.colForCol = StringUtil.filterEmpty(colForCol);

          }
          public String getColForCol() {
            return colForCol;
          }
          public void setColTbid(String colTbid) {

                this.colTbid = StringUtil.filterEmpty(colTbid);

          }
          public String getColTbid() {
            return colTbid;
          }
          public void setColIndex(String colIndex) {

                this.colIndex = StringUtil.filterEmpty(colIndex);

          }
          public String getColIndex() {
            return colIndex;
          }
          public void setColShowFront(String colShowFront) {

                this.colShowFront = StringUtil.filterEmpty(colShowFront);

          }
          public String getColShowFront() {
            return colShowFront;
          }
    }