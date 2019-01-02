package com.hb.scms.util;

import java.awt.*;

import java.awt.image.*;
public class LetterPrint {
    //效果不咋样
    public LetterPrint(String target){
        BufferedImage image = new BufferedImage(78,20,BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        g.setFont(new Font("宋体", Font.PLAIN, 16));
        g.drawString(target,2,image.getHeight()-2);
        int[] p = image.getRGB(0,0,image.getWidth(),image.getHeight(),new int[image.getWidth()*image.getHeight()],0,image.getWidth());
        for(int i=0;i<image.getHeight();i++)
            for(int j=0;j<image.getWidth();j++)
                System.out.print(p[i*image.getWidth()+j]==-1?(i%2==0?"*":"*"):" "+(j==image.getWidth()-1?"\n":""));
    }
    public static void main(String args[]){
        new LetterPrint("这个不错");
    }
}
