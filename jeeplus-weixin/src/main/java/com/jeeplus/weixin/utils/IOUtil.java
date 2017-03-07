package com.jeeplus.weixin.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class IOUtil {

	private static String basePath = null ;
	
	public static InputStream toInputStream(BufferedImage bi) {
		InputStream is = null;
		bi.flush();
		ByteArrayOutputStream bs = new ByteArrayOutputStream();
		ImageOutputStream imOut;
		try {
			imOut = ImageIO.createImageOutputStream(bs);
			ImageIO.write(bi, "jpg", imOut);
			is = new ByteArrayInputStream(bs.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return is;
	}
	
	
	public static String GetImageBase64Str(File upload )
	{
		if(null==upload)
		{
			return null;
		}
		
		// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		InputStream in = null;
		byte [] data = null;
		// 读取图片字节数组
		try
		{
			in = new FileInputStream( upload );
			data = new byte[ in.available() ];
			in.read( data );
			in.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode( data );// 返回Base64编码过的字节数组字符串
	}
	
	
	public static 	byte[] GenerateImageBytes(String imgStr) {// 对字节数组字符串进行Base64解码返回bytes
		if (imgStr == null) // 图像数据为空
			return null;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] bytes = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < bytes.length; ++i) {
				if (bytes[i] < 0) {// 调整异常数据
					bytes[i] += 256;
				}
			}
 
			return bytes;
		} catch (Exception e) {
			return null;
		}
		}  
	
	public static byte[] Img2ByteByUrl(String strUrl) {

		ByteArrayOutputStream baos = null;
		try {
			URL u = new URL(strUrl);
			BufferedImage image = ImageIO.read(u);

			// convert BufferedImage to byte array
			baos = new ByteArrayOutputStream();
			ImageIO.write(image, "jpg", baos);
			baos.flush();

			return baos.toByteArray();
		} catch (Exception e) {
		} finally {
			if (baos != null) {
				try {
					baos.close();
				} catch (IOException e) {
				}
			}
		}
		return null;
	}
	
	 public static void inputstreamtofile(InputStream ins,File file){  
	        OutputStream os = null;
			try {
				os = new FileOutputStream(file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
	        int bytesRead = 0;  
	        byte[] buffer = new byte[8192];  
	        try {
				while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {  
				   os.write(buffer, 0, bytesRead);  
				}
				os.close();
				ins.close();  
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
	 
	     }  
	    
		public synchronized static String getPhotoSavePath(String path){
			if(basePath == null){
				basePath = System.getProperty("user.dir");
				basePath = basePath.replaceAll("\\\\", "/");
				basePath = basePath.substring(0,basePath.lastIndexOf("/bin"))+"/webapps";
			}
			String repath = basePath+path;
			File f = new File(repath);
			f = new File(f.getParent());
			if(!f.exists()){
				f.mkdirs();
			}
			return repath;
		}
 
		
		
}
