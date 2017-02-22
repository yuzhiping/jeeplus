package com.jeeplus.common.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.commons.codec.binary.Base64;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-22 9:44.
 */
public class Base64ImageUtils {

    /**
     * 将图片进行Base64编码
     * @param imageUrl 图片的url路径，如http://.....xx.jpg
     * @return
     */
    public static String encodeImageToBase64(URL imageUrl){
        ByteArrayOutputStream outputStream=null;
        try {
            BufferedImage bufferedImage= ImageIO.read(imageUrl);
            outputStream=new ByteArrayOutputStream();
            ImageIO.write(bufferedImage,"png",outputStream);
        }  catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        Base64 encoder=new Base64();
        // 返回Base64编码过的字节数组字符串
        return String.format("%s%s","data:image/png;base64,", new String(encoder.encode(outputStream.toByteArray())));

    }
    /**
     * 将本地图片进行Base64位编码
     *
     * @param imageFile
     *			图片文件
     * @return
     */
    public static String encodeImgageToBase64(File imageFile) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        ByteArrayOutputStream outputStream = null;
        try {
            BufferedImage bufferedImage = ImageIO.read(imageFile);
            outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", outputStream);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        Base64 encoder = new Base64();
        return String.format("%s%s","data:image/png;base64,", new String(encoder.encode(outputStream.toByteArray())));// 返回Base64编码过的字节数组字符串
    }

    /**
     * 将Base64位编码的图片进行解码，并保存到指定目录
     * @param base64 base64编码的图片信息
     * @param path 保存路径
     * @param imageName 图片名
     */
    public static void decodeBase64ToImage(String base64,String path,String imageName){
        Base64 decoder=new Base64();
        try {
            FileOutputStream outputStream=new FileOutputStream(new File(path+imageName));
            byte[] decoderByte=decoder.decode(base64);
            outputStream.write(decoderByte);
            outputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
