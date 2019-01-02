package com.hb.scms.util;


import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.expression.spel.ast.NullLiteral;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.ServletOutputStream;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.io.*;
import java.util.Arrays;
import java.util.Iterator;

public class ImgUtil {





    /**
     * 根据裁剪尺寸,居中裁剪图片,并未压缩图片大小
     * @param src
     * @param w
     * @param h

     */
    public static BufferedImage cutCenterImage(String src, int w,int h  )  {
        Iterator iterator = ImageIO.getImageReadersByFormatName("jpg");
        ImageReader reader = (ImageReader)iterator.next();


        try {
            InputStream in=new FileInputStream(src);
            ImageInputStream iis = ImageIO.createImageInputStream(in);
            reader.setInput(iis, true);
            ImageReadParam param = reader.getDefaultReadParam();
            int imageIndex = 0;
            if(w>reader.getWidth(imageIndex)) w = reader.getWidth(imageIndex);
            int y=0;
            if(h<reader.getHeight(imageIndex))
                y = (reader.getHeight(imageIndex)-h)/2;

            Rectangle rect = new Rectangle((reader.getWidth(imageIndex)-w)/2, y, w, h);
            param.setSourceRegion(rect);
            BufferedImage bi = reader.read(0,param);
            in.close();
            iis.close();
            reader.dispose();
            return bi;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }

    /**
     * 图片缩放，如果高度参数等于0，则只根据用参数中的宽度w 按照原始宽高比例缩小
     * ，如果高度参数不为0，则按照指定宽度高度缩小，如果宽度高度大于原始图片宽度高度，
     * 则原始图片被拉伸处理。
     * @param w
     * @param h
     * @return
     * @throws Exception
     */
    public static BufferedImage zoomImages(File f , int w,int h )   {
        //File f=new File(DirRoot );
        BufferedImage bufImg=null;
        if(f.exists()){
            try {
                bufImg= ImageIO.read(f );
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            return null;
        }

        int sourceW=0,sourceH=0;
        sourceW=bufImg.getWidth();
        sourceH=bufImg.getHeight();

        if(w< 0)w=0;
        if(h<0)h=0;
        if(w>sourceW)w=sourceW;
        if(h>sourceH)h=sourceH;
        double wr=0,hr=0;
        if(w==0){
            w=sourceW;
            h=sourceH;
        }
        if (h==0)
            h=(int)(sourceH*w*1.0/sourceW);
        bufImg.getScaledInstance(w, h, bufImg.SCALE_SMOOTH);
        Image ImageTemp ;
        wr=w*1.0/sourceW;
        hr=h*1.0 / sourceH;
        AffineTransformOp ato = new AffineTransformOp(AffineTransform.getScaleInstance(wr, hr), null);
        ImageTemp = ato.filter(bufImg, null);
         return (BufferedImage) ImageTemp;

    }

    /**
     * 图片缩放，如果高度参数等于0，则只根据用参数中的宽度w 按照原始宽高比例缩小
     * ，如果高度参数不为0，则按照指定宽度高度缩小。
     * 另外加入了抗锯齿的方法处理，
     * 生成的图片的清晰度，主要取决于生成的图片的高度和宽度的大小，而不是取决于图片生成的算法；
       如果要想达到肉眼无法分别的效果，生成图片的高宽最好不要小于500，一定不要小于400；
       并且这样缩小的图片体积已经大大减小了，比原来的图片体积小了80%
     * @param imgFile
     * @param w
     * @param h
     * @return
     */
    public static BufferedImage thumbnailImage(File imgFile, int w, int h ) {
        try {
            BufferedImage srcImage=ImageIO.read(imgFile);
            int sourceH=srcImage.getHeight();
            int sourceW=srcImage.getWidth();

            if (h<2)
                h=(int)(sourceH*w*1.0/sourceW);
            BufferedImage distImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
// 创建新图
            Graphics2D graphics2d = distImage.createGraphics();

// 设置“抗锯齿”的属性
            graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            graphics2d.drawImage(srcImage.getScaledInstance(w, h, Image.SCALE_SMOOTH), 0, 0, null);
            graphics2d.dispose();
            return distImage;
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }
    /**
     * 获取图片文件类型
     * @param o
     * @return
     */
    public static String getFormatName(File o) {
        try {
            // Create an image input stream on the image
            ImageInputStream iis = ImageIO.createImageInputStream(o);

            // Find all image readers that recognize the image format
            Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
            // No readers found
            if (!iter.hasNext()) return null;

            // Use the first reader
            ImageReader reader = iter.next();

            // Close stream
            iis.close();

            // Return the format name
            return reader.getFormatName();
        } catch (IOException e) {
            //
        }

        // The image could not be read
        return null;
    }


    /**
     * cutImgByWidthHeight 的包装方法，从x=0 ,y=0 的地方开始截取图片
     * @param srcImageFile
     * @param destWidth
     * @param destHeight
     */
    public static BufferedImage cutImgByWidthHeight(File srcImageFile, int destWidth, int destHeight ) {
        BufferedImage bi=cutImgByWidthHeight(srcImageFile, 0, 0, destWidth, destHeight );
        if (bi != null) {
            return bi;
        }
        return null;
    }

    /**
     * 从图片的指定位置裁剪指定大小的区域
     * @param srcImageFile 图片源文件
     * @param x 开始X
     * @param y 开始Y
     * @param destWidth 裁剪的宽度
     * @param destHeight 裁剪的高度
     * @return
     */
    public static BufferedImage cutImgByWidthHeight(File srcImageFile, int x, int y, int destWidth, int destHeight ) {

        try {
            Image img;
            ImageFilter cropFilter;
            // 读取源图像
            BufferedImage bi = ImageIO.read(srcImageFile);
            int srcWidth = bi.getWidth(); // 源图宽度
            int srcHeight = bi.getHeight(); // 源图高度

            if(x<0||(x+destWidth)>srcWidth) x=0;
            if(y<0||(y+destHeight)>srcHeight) y=0;

            if (srcWidth >= destWidth && srcHeight >= destHeight) {
                Image image = bi.getScaledInstance(srcWidth, srcHeight,
                        Image.SCALE_DEFAULT);

                cropFilter = new CropImageFilter(x, y, destWidth, destHeight);
                img = Toolkit.getDefaultToolkit().createImage(
                        new FilteredImageSource(image.getSource(), cropFilter));
                BufferedImage tag = new BufferedImage(destWidth, destHeight,
                        BufferedImage.TYPE_INT_RGB);
                Graphics g = tag.getGraphics();
                g.drawImage(img, 0, 0, null); // 绘制截取后的图
                g.dispose();
                return tag;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        File file=new File("d:\\test\\a.jpg");

        try {
            long startTime=System.nanoTime();
             //thumbnail(file,500,200,new File("d:\\test\\ab.jpg"));
            //zoomImages("d:\\test\\a.jpg" , 500,200 );
           //cut_w_h(file, 10, 10, 500, 200, new File("d:\\test\\ab.jpg"));
            //thumbnailImage(file, 500, 200, true);
            //cutCenterImage("d:\\test\\a.jpg", 500,300 ,new File("d:\\test\\ab.jpg"));+


            long endTime=System.nanoTime();
            System.out.println("程序运行时间： "+(endTime-startTime)+"ns");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
//------------------------------分割线以下的方法暂时都没有使用----------------------------------

    /**
     * <p>Title: thumbnailImage</p>
     * <p>Description: 根据图片路径生成缩略图 ,产生的新图会覆盖掉老的图</p>
     * @param  imgFile    原图片路径
     * @param w            缩略图宽
     * @param h            缩略图高

     * @param force        是否强制按照宽高生成缩略图(如果为false，则生成最佳比例缩略图)
     */
    public static BufferedImage thumbnailImage(File imgFile, int w, int h,   boolean force) {
        try {
            Image img = ImageIO.read(imgFile);
            if (!force) {
                // 根据原图与要求的缩略图比例，找到最合适的缩略图比例
                int width = img.getWidth(null);
                int height = img.getHeight(null);
                if ((width * 1.0) / w < (height * 1.0) / h) {
                    if (width > w)
                        h = Integer.parseInt(new java.text.DecimalFormat("0").format(height * w / (width * 1.0)));
                } else if (height > h)
                    w = Integer.parseInt(new java.text.DecimalFormat("0").format(width * h / (height * 1.0)));
            }
            BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            Graphics g = bi.getGraphics();
            g.drawImage(img, 0, 0, w, h, Color.LIGHT_GRAY, null);
            g.dispose();
            String p = imgFile.getPath();
            // 将图片保存在原目录并加上前缀
           // ImageIO.write(bi, "JPEG", new File(p.substring(0, p.lastIndexOf(File.separator)) + File.separator  + imgFile.getName()));
            return bi;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 按照固定宽高原图压缩
     *
     * @param img
     * @param width
     * @param height
     * @param out
     * @throws IOException
     */
    public static void thumbnail(File img, int width, int height,
                                 File out) throws IOException {
        BufferedImage BI = ImageIO.read(img);
        Image image = BI.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        BufferedImage tag = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics g = tag.getGraphics();
        g.setColor(Color.RED);
        g.drawImage(image, 0, 0, null); // 绘制处理后的图
        g.dispose();
        ImageIO.write(tag, "JPEG", out);
    }
    /**
     * 缩小或放大图像,按照原始图片的宽高比
     * @param srcImageFile 源图像文件地址
     * @param result       缩放后的图像地址
     * @param scale        缩放比例
     * @param flag         缩放选择:true 放大; false 缩小;
     */
    public static void scale(String srcImageFile, String result, int scale, boolean flag)
    {
        try
        {
            BufferedImage src = ImageIO.read(new File(srcImageFile)); // 读入文件
            int width = src.getWidth(); // 得到源图宽
            int height = src.getHeight(); // 得到源图长
            if (flag)
            {
                // 放大
                width = width * scale;
                height = height * scale;
            }
            else
            {
                // 缩小
                width = width / scale;
                height = height / scale;
            }
            Image image = src.getScaledInstance(width, height, Image.SCALE_DEFAULT);
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            g.drawImage(image, 0, 0, null); // 绘制缩小后的图
            g.dispose();
            ImageIO.write(tag, "JPEG", new File(result));// 输出到文件流
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 图片裁剪，从左上角开始 以指定宽度高度裁剪图片买，并未压缩图片
     * @param srcImageFile 图片裁剪地址

     * @param destWidth 图片裁剪宽度
     * @param destHeight 图片裁剪高度
     */
    public   static void cutImage(String srcImageFile,
                                  int destWidth, int destHeight,File targetFile) {
        try {
            Iterator iterator = ImageIO.getImageReadersByFormatName("JPEG");/*PNG,BMP*/
            ImageReader reader = (ImageReader)iterator.next();/*获取图片尺寸*/
            InputStream inputStream = new FileInputStream(srcImageFile);
            ImageInputStream iis = ImageIO.createImageInputStream(inputStream);
            reader.setInput(iis, true);
            ImageReadParam param = reader.getDefaultReadParam();
            Rectangle rectangle = new Rectangle(0,0, destWidth, destHeight);/*指定截取范围*/
            param.setSourceRegion(rectangle);
            BufferedImage bi = reader.read(0,param);

            ImageIO.write(bi, "JPEG", targetFile);
        } catch (Exception e) {
            System.out.println("图片裁剪出现异常:"+e);
        }
    }

    /**
     * 图像类型转换 GIF->JPG GIF->PNG PNG->JPG PNG->GIF(X)
     */
    public static void convert(String source, String result)
    {
        try
        {
            File f = new File(source);
            f.canRead();
            f.canWrite();
            BufferedImage src = ImageIO.read(f);
            ImageIO.write(src, "JPG", new File(result));
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /**
     * 彩色转为黑白
     * @param source
     * @param result
     */
    public static void gray(String source, String result)
    {
        try
        {
            BufferedImage src = ImageIO.read(new File(source));
            ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
            ColorConvertOp op = new ColorConvertOp(cs, null);
            src = op.filter(src, null);
            ImageIO.write(src, "JPEG", new File(result));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

