package com.jeeplus.common.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 图片工具类
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-13 12:53.
 */
public class ImageUtils {

    /**
     * 图片格式
     */
    private static final String PICTURE_FORMAT_JPG="jpg";

    /**
     * 透明度(0.0 -- 1.0, 0.0为完全透明，1.0为完全不透明)
     */
    private static final float ALPHA=0.5f;

    /**
     * 添加图片水印
     * @param sourceImagePath 源图片路径
     * @param destImagePath 目标图片路径
     * @param watermarkImagePath 水印图片路径
     * @param positionWidth 水印图片距离目标图片左侧的偏移量，如果x<0, 则在正中间
     * @param positionHeight 水印图片距离目标图片上侧的偏移量，如果y<0, 则在正中间
     */
    public static void imageWatermark(String sourceImagePath,String destImagePath,String watermarkImagePath,int positionWidth,int positionHeight){
        OutputStream outputStream=null;
        try {
            File file=new File(sourceImagePath);
            Image image= ImageIO.read(file);
            int width=image.getWidth(null);
            int height=image.getHeight(null);
            BufferedImage bufferedImage=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
            Graphics2D g=bufferedImage.createGraphics();
            g.drawImage(image,0,0,width,height,null);

            //水印文件
            Image waterImage=ImageIO.read(new File(watermarkImagePath));
            int watermarkWidth=waterImage.getWidth(null);
            int watermarkHeight=waterImage.getHeight(null);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,ALPHA));
            int widthDiff=width-watermarkWidth;
            int heigthDiff=height-watermarkHeight;
            if(positionWidth<0){
                positionWidth=widthDiff/2;
            }else if(positionWidth>widthDiff){
                positionWidth=widthDiff;
            }
            if(positionHeight<0){
                positionHeight=heigthDiff/2;
            }else if(positionHeight>heigthDiff){
                positionHeight=heigthDiff;
            }
            g.drawImage(waterImage,positionWidth,positionHeight,watermarkWidth,watermarkHeight,null);
            //水印文件结束
            g.dispose();
            outputStream=new FileOutputStream(destImagePath);
            ImageIO.write(bufferedImage,PICTURE_FORMAT_JPG,outputStream);

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(outputStream!=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 添加图片水印
     * @param sourceImagePath 源图片路径
     * @param destImagePath 目标图片路径
     * @param watermarkImagePath 水印图片路径
     * @param positionWidth 水印图片距离目标图片左侧的偏移量，如果x<0, 则在正中间
     * @param positionHeight 水印图片距离目标图片上侧的偏移量，如果y<0, 则在正中间
     * @param degree 水印图片旋转角度
     */
    public static void imageWatermark(String sourceImagePath,String destImagePath,String watermarkImagePath,int positionWidth,int positionHeight,int degree){
        OutputStream outputStream=null;
        try {
            File file=new File(sourceImagePath);
            Image image= ImageIO.read(file);
            int width=image.getWidth(null);
            int height=image.getHeight(null);
            BufferedImage bufferedImage=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
            Graphics2D g=bufferedImage.createGraphics();
            //设置对线段的锯齿状边缘处理
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.drawImage(image,0,0,width,height,null);

            //设置水印旋转
            g.rotate(Math.toRadians(degree),
                    bufferedImage.getWidth()/2,
                    bufferedImage.getHeight()/2);


            //水印文件
            Image waterImage=ImageIO.read(new File(watermarkImagePath));
            int watermarkWidth=waterImage.getWidth(null);
            int watermarkHeight=waterImage.getHeight(null);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,ALPHA));
            int widthDiff=width-watermarkWidth;
            int heigthDiff=height-watermarkHeight;
            if(positionWidth<0){
                positionWidth=widthDiff/2;
            }else if(positionWidth>widthDiff){
                positionWidth=widthDiff;
            }
            if(positionHeight<0){
                positionHeight=heigthDiff/2;
            }else if(positionHeight>heigthDiff){
                positionHeight=heigthDiff;
            }
            g.drawImage(waterImage,positionWidth,positionHeight,watermarkWidth,watermarkHeight,null);
            //水印文件结束
            g.dispose();
            outputStream=new FileOutputStream(destImagePath);
            ImageIO.write(bufferedImage,PICTURE_FORMAT_JPG,outputStream);

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(outputStream!=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 添加文字水印
     * @param sourceImagePath 源图片路径
     * @param destImagePath 目标图片路径
     * @param text 水印文字
     * @param fontName 字体名称
     * @param fontStyle 字体样式 粗体和斜体(Font.BOLD|Font.ITALIC)
     * @param fontSize 字体大小，单位为像素
     * @param color 字体颜色
     * @param positionWidth 水印文字距离目标图片左侧的偏移量，如果x<0, 则在正中间
     * @param positionHeight 水印文字距离目标图片上侧的偏移量，如果y<0, 则在正中间
     */
    public static void textWatermark(String sourceImagePath,String destImagePath, String text, String fontName, int fontStyle, int fontSize, Color color, int positionWidth, int positionHeight) {
        OutputStream outputStream=null;
        try {
            File file = new File(sourceImagePath);

            Image image = ImageIO.read(file);
            int width = image.getWidth(null);
            int height = image.getHeight(null);
            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufferedImage.createGraphics();
            g.drawImage(image, 0, 0, width, height, null);
            g.setFont(new Font(fontName, fontStyle, fontSize));
            g.setColor(color);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, ALPHA));

            int width_1 = fontSize * getLength(text);
            int height_1 = fontSize;
            int widthDiff = width - width_1;
            int heightDiff = height - height_1;
            if(positionWidth < 0){
                positionWidth = widthDiff / 2;
            }else if(positionWidth > widthDiff){
                positionWidth = widthDiff;
            }
            if(positionHeight < 0){
                positionHeight = heightDiff / 2;
            }else if(positionHeight > heightDiff){
                positionHeight = heightDiff;
            }

            g.drawString(text, positionWidth, positionHeight + height_1);
            g.dispose();
            outputStream=new FileOutputStream(destImagePath);
            ImageIO.write(bufferedImage, PICTURE_FORMAT_JPG, outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(outputStream!=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 添加文字水印
     * @param sourceImagePath 源图片路径
     * @param destImagePath 目标图片路径
     * @param text 水印文字
     * @param fontName 字体名称
     * @param fontStyle 字体样式 粗体和斜体(Font.BOLD|Font.ITALIC)
     * @param fontSize 字体大小，单位为像素
     * @param color 字体颜色
     * @param positionWidth 水印文字距离目标图片左侧的偏移量，如果x<0, 则在正中间
     * @param positionHeight 水印文字距离目标图片上侧的偏移量，如果y<0, 则在正中间
     * @param degree 水印文字旋转角度
     */
    public static void textWatermark(String sourceImagePath,String destImagePath, String text, String fontName, int fontStyle, int fontSize, Color color, int positionWidth, int positionHeight,int degree) throws IOException {
        OutputStream outputStream=null;
        try {
            File file = new File(sourceImagePath);

            Image image = ImageIO.read(file);
            int width = image.getWidth(null);
            int height = image.getHeight(null);
            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufferedImage.createGraphics();
            //设置对线段的锯齿状边缘处理
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.drawImage(image, 0, 0, width, height, null);
            // 4、设置水印旋转
            g.rotate(Math.toRadians(degree),
                    (double) bufferedImage.getWidth() / 2,
                    (double) bufferedImage.getHeight() / 2);

            g.setFont(new Font(fontName, fontStyle, fontSize));
            g.setColor(color);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, ALPHA));

            int width_1 = fontSize * getLength(text);
            int height_1 = fontSize;
            int widthDiff = width - width_1;
            int heightDiff = height - height_1;
            if(positionWidth < 0){
                positionWidth = widthDiff / 2;
            }else if(positionWidth > widthDiff){
                positionWidth = widthDiff;
            }
            if(positionHeight < 0){
                positionHeight = heightDiff / 2;
            }else if(positionHeight > heightDiff){
                positionHeight = heightDiff;
            }

            g.drawString(text, positionWidth, positionHeight + height_1);
            g.dispose();
            outputStream=new FileOutputStream(destImagePath);
            ImageIO.write(bufferedImage, PICTURE_FORMAT_JPG, outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (outputStream!=null){
                outputStream.close();
            }
        }
    }


    /**
     * 获取字符长度，一个汉字作为 1 个字符, 一个英文字母作为 0.5 个字符
     * @param text
     * @return 字符长度，如：text="中国",返回 2；text="test",返回 2；text="中国ABC",返回 4.
     */
    public static int getLength(String text) {
        int textLength = text.length();
        int length = textLength;
        for (int i = 0; i < textLength; i++) {
            if (String.valueOf(text.charAt(i)).getBytes().length > 1) {
                length++;
            }
        }
        return (length % 2 == 0) ? length / 2 : length / 2 + 1;
    }

    /**
     * 图片缩放
     * @param filePath 图片路径
     * @param height 高度
     * @param width 宽度
     * @param bb 比例不对时是否需要补白
     */
    public static void imageZoom(String filePath, int height, int width, boolean bb) {
        try {
            double ratio = 0.0; //缩放比例
            File file = new File(filePath);
            BufferedImage bufferedImage = ImageIO.read(file);
            Image itemp = bufferedImage.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH);
            //计算比例
            if ((bufferedImage.getHeight() > height) || (bufferedImage.getWidth() > width)) {
                if (bufferedImage.getHeight() > bufferedImage.getWidth()) {
                    ratio = (new Integer(height)).doubleValue() / bufferedImage.getHeight();
                } else {
                    ratio = (new Integer(width)).doubleValue() / bufferedImage.getWidth();
                }
                AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(ratio, ratio), null);
                itemp = op.filter(bufferedImage, null);
            }
            if (bb) {
                BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                Graphics2D g = image.createGraphics();
                g.setColor(Color.white);
                g.fillRect(0, 0, width, height);
                if (width == itemp.getWidth(null))
                    g.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2, itemp.getWidth(null), itemp.getHeight(null), Color.white, null);
                else
                    g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0, itemp.getWidth(null), itemp.getHeight(null), Color.white, null);
                g.dispose();
                itemp = image;
            }
            ImageIO.write((BufferedImage) itemp, "jpg", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
