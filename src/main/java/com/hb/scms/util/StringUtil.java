package com.hb.scms.util;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.springframework.transaction.annotation.SpringTransactionAnnotationParser;


import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    /**
     * 下划线转驼峰法
     * @param line 源字符串
     * @param smallCamel 大小驼峰,是否为小驼峰
     * @return 转换后的字符串
     */
    public static String underline2Camel(String line,boolean smallCamel){
        if(line==null||"".equals(line)){
            return "";
        }
        StringBuffer sb=new StringBuffer();
        Pattern pattern=Pattern.compile("([A-Za-z\\d]+)(_)?");
        Matcher matcher=pattern.matcher(line);
        while(matcher.find()){
            String word=matcher.group();
            sb.append(smallCamel&&matcher.start()==0?Character.toLowerCase(word.charAt(0)):Character.toUpperCase(word.charAt(0)));
            int index=word.lastIndexOf('_');
            if(index>0){
                sb.append(word.substring(1, index).toLowerCase());
            }else{
                sb.append(word.substring(1).toLowerCase());
            }
        }
        return sb.toString();
    }
    /**
     * 判断是否是空
     * @param str
     * @return
     */
    public static boolean isEmpty(String str){
        if(null==str  ||"".equals(str.trim())){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 判断是否不是空
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str){
        if((str!=null)&&"".equals(str.trim())==false){
            return true;
        }else{
            return false;
        }
    }
    /**
     * 判断是否是空
     * @param str
     * @return
     */
    public static String filterEmpty(String str){
        if(null==str  ||"".equals(str.trim())){
            return null;
        }else{
            return str.trim();
        }
    }
    public static String filterNullToEmpty(String str){
        if(null==str   ){
            return "";
        }else{
            return str;
        }
    }
    //去掉字符串头部斜线
    public static String removeHeadSlash(String str){
        if(null==str   ){
            return "";
        }else{
            str = str.replaceAll("\\\\", "/");
            return str.replaceFirst("^\\/*","");
        }
    }

    /**
     * 转换字符为整数，默认值为0
     * @param str
     * @return
     */
    public static int getNum(String str){
        if(!isNumInt(str)){
            return 0;
        }else{
            try {
                return Integer.parseInt(str);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    /**
     * 将路径里的斜杠\转为反斜杠/
     * @param tar
     * @return
     */
    public static String unSlash(String tar){
        if (null == tar) {
            return tar;
        }
        return tar.replaceAll("\\\\","/");
    }
    //去掉字符串头部斜线
    public static String removeHeadEndSlash(String str){
        if(null==str   ){
            return "";
        }else{
            str = removeHeadSlash(str);
            return str.replaceFirst("\\/*$","");
        }
    }
    /*
     * 匹配是否包含中文 str 要匹配的字符串 如果包含返回true 否则返回false
     */
    public static boolean isContainChinese(String str) {

        Pattern p=Pattern.compile("[\u4e00-\u9fa5]");
        java.util.regex.Matcher m=p.matcher(str);
        if(m.find())
        {
            return true;
        }
        return false;
    }
    /*
     * 匹配是否为整数 如果是返回true 否则返回false
     */
    public static boolean isNumInt(String str) {

        if(null==str)
            return false;
        Pattern p=Pattern.compile("[0-9]+");
        java.util.regex.Matcher m=p.matcher(str);
        if(m.find())
        {
            return true;
        }
        return false;
    }
    /*
     * 匹配是否为图片路径，如果是返回true 否则返回false
     */
    public static boolean isPicPath(String path) {

        if(null==path)
            return false;
        Pattern p=Pattern.compile(".*\\.(jpg|png|gif|jpeg|PNG|JPG)$");
        java.util.regex.Matcher m=p.matcher(path);
        if(m.matches())
        {
            return true;
        }
        return false;
    }
    /*
     * 匹配是否为合法密码组成，如果是返回true 否则返回false
     */
    public static boolean isLegalPwdStr(String path) {

        if(null==path)
            return false;
        Pattern p=Pattern.compile("^(?=.{8,15})(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$");
        java.util.regex.Matcher m=p.matcher(path);
        if(m.matches())
        {
            return true;
        }
        return false;
    }
    /*
     * 处理输入的路径，如果最后路径是文件，则
     * 返回文件名，如果直接是目录路径则什么都不做，如果不是合法路径，
     * 则直接返回null
     */
    public static String procPath(String str) {

        if(null==str)
            return null;
        str = str.replaceAll("\\\\", "/");
        if(isPathDirectory(str))
        {
            File f = new File(str);
            if (f.exists()){
                if(f.isFile())
                    return f.getParent();
                if (f.isDirectory())
                    return str;
            }
        }
        return null;
    }
    public static boolean isPathDirectory(String str) {

        if(null==str)
            return false;
        str = str.replaceAll("\\\\", "/");
        String patten="[\\u4e00-\\u9fa5\\w-\\(\\)/]+";
        if(getOsType().equals("win"))
            patten="[\\u4e00-\\u9fa5\\w-/:\\.\\(\\)]+";
        Pattern p=Pattern.compile(patten);
        java.util.regex.Matcher m=p.matcher(str);
        if(m.matches())
            return true;
        else
            return false;
    }

    public static String getOsType(){
        if (System.getProperty("os.name").toLowerCase().contains("windows"))
            return "win";
        else
            return "unix";
    }
    public static String getFileNameByPath(String path){
        if (path == null) {
            return  null;
        }
        Pattern p=Pattern.compile("([\\u4e00-\\u9fa5\\w-]+\\.(jpg|png|gif|jpeg|PNG|JPG))$");
        Matcher m =p.matcher(path);
        if (m.find()) {
            return m.group(1) ;

        }
       return null;
    }

    /**
     * 将/sdfslf/asdfas/dfasdf样式的路径分解成
     * /sdfslf/asdfas/dfasdf
       /sdfslf/asdfas
       /sdfslf
     的样式
     * @param path
     * @return
     */
    public static List<String> pathLevel(String path) {
        List<String> res=new ArrayList<>();
        if (path==null){
            res.add("/");
            return  res;
        }
        if(path.startsWith("/")==false)
            path="/"+path;
        while (!path.equals("/")){
            res.add(path);
            if (path.lastIndexOf("/")==0)
                break;
            path = path.substring(0, path.lastIndexOf("/"));
        }
        Collections.reverse(res);
        return  res;
    }

    /**
     * 将gb2312 编码转为utf-8
     * @param target
     * @return String 转码后的结果
     */
    public static String gb2312ConvertUTF8(String target){
        if (target == null) {
            return null;
        }

        byte[] b =null;//编码
        String result=null;
        try {
            b = target.getBytes("GB2312");
            result= new String(b, "utf-8");//解码:用什么字符集编码就用什么字符集解码
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /***
     * 读取文件到String
     * @param path
     * @return
     */
    public static String readFileToStr(String path) {
        File file = new File(path);
        if(file.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        new FileInputStream(file), "gb2312"));
                StringBuilder line = new StringBuilder();
                String temp=null;
                while ((temp= reader.readLine())!= null) {
                    line.append(  temp + "\n");
                }
                reader.close();
                return  line.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /***
     * 写文件到指定路径，路径中包含文件名
     * @param filePath
     */
    public static void WriteStringToFile(String text,String filePath) {
        if(text==null)
            return;
        OutputStreamWriter write = null;
        try {

            FileOutputStream fos = new FileOutputStream(new File(filePath)  );
            write=new OutputStreamWriter(fos,"utf-8");
            write.write(text);
            write.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static String removeEndChar(String str,String chars){
        if (str!=null){
            if (str.endsWith(chars)){
                str = str.substring(0, str.length() - 1);
                System.out.println(str);
            }
        }
        String[] a="asdfasdf".split(",");

            return str;
    }

    public static void convertGB2312ToUTF8(String path) {
        String target = readFileToStr(path);
        //target=gb2312ConvertUTF8(target);
        if (target != null) {
            WriteStringToFile(target,  path );
        }
    }

    public static void main(String[] args) {
        System.out.println(isLegalPwdStr("dfAS56"));

    }
}
