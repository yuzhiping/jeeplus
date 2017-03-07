package com.jeeplus.weixin.utils;

import com.jeeplus.weixin.entities.OrderHistoryModel;
import com.jeeplus.weixin.entities.OrderInfoModel;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

 

public class ObjectUtil {
	/**
	 * 根据属性名获取属性值
	 * */
    public static Object getFieldValueByName(String fieldName, Object o) {
        try {  
        	 String getter = "";
        	Field field=getDeclaredField(o,fieldName);
        	if(field.getGenericType().toString().equals(  
                    "class java.lang.Boolean")||field.getGenericType().toString().equals("boolean")){
        		//考虑有些boolean字段是is开头的
        		if(fieldName.indexOf("is")==0){
        			getter =fieldName;
        		}else{
      	 		  String firstLetter = fieldName.substring(0, 1).toUpperCase();  
        		  getter = "is" + firstLetter + fieldName.substring(1);  
        		}
        		
       
        		
        	}else{
                String firstLetter = fieldName.substring(0, 1).toUpperCase();  
                getter = "get" + firstLetter + fieldName.substring(1);  
           
        	}
            Method method = o.getClass().getMethod(getter, new Class[] {});  
            Object value = method.invoke(o, new Object[] {});  
            return value;  
        } catch (Exception e) {  
            e.printStackTrace(); 
            return null;  
        }  
    } 
    
    /**
     * 获取属性名数组
     * */
    public static String[] getFiledName(Object o){
    	Field[] fields=o.getClass().getDeclaredFields();
       	String[] fieldNames=new String[fields.length];
    	for(int i=0;i<fields.length;i++){
    		System.out.println(fields[i].getType());
    		fieldNames[i]=fields[i].getName();
    	}
    	return fieldNames;
    }
    
    /**
     * 获取属性类型(type)，属性名(name)，属性值(value)的map组成的list
     * */
    public static List getFiledsInfo(Object o){
    	Field[] fields=o.getClass().getDeclaredFields();
       	String[] fieldNames=new String[fields.length];
       	List list = new ArrayList();
       	Map infoMap=null;
    	for(int i=0;i<fields.length;i++){
    		infoMap = new HashMap();
    		infoMap.put("type", fields[i].getType().toString());
    		infoMap.put("name", fields[i].getName());
    		infoMap.put("value", getFieldValueByName(fields[i].getName(), o));
    		list.add(infoMap);
    	}
    	return list;
    }
    
 
    
    /**
     * 获取对象的所有属性值，返回一个对象数组
     * */
    public static Object[] getFiledValues(Object o){
    	String[] fieldNames= getFiledName(o);
    	Object[] value=new Object[fieldNames.length];
    	for(int i=0;i<fieldNames.length;i++){
    		value[i]= getFieldValueByName(fieldNames[i], o);
    	}
    	return value;
    }	

    
    /**
	 * 循环向上转型, 获取对象的 DeclaredField
	 * @param object : 子类对象
	 * @param fieldName : 父类中的属性名
	 * @return 父类中的属性对象
	 */
	
	public static Field getDeclaredField(Object object, String fieldName){
		Field field = null ;
		
		Class<?> clazz = object.getClass() ;
		
		for(; clazz != Object.class ; clazz = clazz.getSuperclass()) {
			try {
				field = clazz.getDeclaredField(fieldName) ;
				return field ;
			} catch (Exception e) {
				//这里甚么都不要做！并且这里的异常必须这样写，不能抛出去。
				//如果这里的异常打印或者往外抛，则就不会执行clazz = clazz.getSuperclass(),最后就不会进入到父类中了
				
			} 
		}
	
		return null;
	}	
	
	public static void setFieldValue2(Object object, String fieldName, Object value) throws SecurityException, NoSuchMethodException, Exception{
		
		Field field = getDeclaredField(object, fieldName) ;
		if(field!=null){

            Method get_Method = object.getClass().getMethod("get" + getMethodName(field.getName()));  //获取getMethod方法
            Method set_Method = (Method) object.getClass().getMethod(  
                    "set" + getMethodName(field.getName()),get_Method.getReturnType()); 
			
			// 如果类型是String  
	        if (field.getGenericType().toString().equals(  
	                "class java.lang.String")) { // 如果type是类类型，则前面包含"class "，后面跟类名  
 
	            /** 
	             * 这里需要说明一下：他是根据拼凑的字符来找你写的getter方法的 
	             * 在Boolean值的时候是isXXX（默认使用ide生成getter的都是isXXX） 
	             * 如果出现NoSuchMethod异常 就说明它找不到那个settet方法 需要做个规范 
	             */  

 

	        	set_Method.invoke(object,value);// 调用setter方法获取属性值  
	       

	        }  

	        // 如果类型是Integer  
	        if (field.getGenericType().toString().equals(  
	                "class java.lang.Integer")||field.getGenericType().toString().equals(  
	                "int")) {  
 
	            set_Method.invoke(object, StringUtils.isEmpty(value.toString())? null: Integer.valueOf(value.toString()));
	          

	        }  

	        
	        // 如果类型是Long  
	        if (field.getGenericType().toString().equals(  
	                "class java.lang.Long")||field.getGenericType().toString().equals(  
	                "long")) {  
 
	            set_Method.invoke(object, StringUtils.isEmpty(value.toString())? null: Long.valueOf(value.toString()));
	          

	        }  
	        
 
	        
	        
	        // 如果类型是Double  
	        if (field.getGenericType().toString().equals(  
	                "class java.lang.Double")||field.getGenericType().toString().equals(  
	                "double")) {  
 
	            set_Method.invoke(object,StringUtils.isEmpty(value.toString())? null: Double.valueOf(value.toString()));
	        

	        }  

	        // 如果类型是Boolean 是封装类  
	        if (field.getGenericType().toString().equals(  
	                "class java.lang.Boolean")) {  
 
	        	set_Method.invoke(object,StringUtils.isEmpty(value.toString())? null: Boolean.valueOf(value.toString()));
	            

	        }  

	        // 如果类型是boolean 基本数据类型不一样 这里有点说名如果定义名是 isXXX的 那就全都是isXXX的  
	        // 反射找不到getter的具体名  
	        if (field.getGenericType().toString().equals("boolean")) {  
 
	            set_Method.invoke(object,StringUtils.isEmpty(value.toString())? null: Boolean.valueOf(value.toString()).booleanValue());
	          

	        }  
	        // 如果类型是Date  
	        if (field.getGenericType().toString().equals(  
	                "class java.util.Date")) {  
 
	            set_Method.invoke(object,StringUtils.isEmpty(value.toString())? null: DateUtil.parseDate(value.toString(), "yyyy-MM-dd hh:mm:ss"));
 

	        }  
	        // 如果类型是Short  
	        if (field.getGenericType().toString().equals(  
	                "class java.lang.Short")) {  
 
	        	set_Method.invoke(object,StringUtils.isEmpty(value.toString())? null: Short.valueOf(value.toString()));
	    

	        }  
	        // 如果类型是Short  
	        if (field.getGenericType().toString().equals(  
	                "class java.math.BigDecimal")) {  
 
	        	set_Method.invoke(object,StringUtils.isEmpty(value.toString())? null: new BigDecimal(value.toString()));
	    

	        }  
	        
	       
		}
		
		
	}
	
	
	
	
    // 把一个字符串的第一个字母大写、效率是最高的、  
    private static String getMethodName(String fildeName) throws Exception{  
        byte[] items = fildeName.getBytes();  
        items[0] = (byte) ((char) items[0] - 'a' + 'A');  
        return new String(items);  
    }  
	
	
    /**
	 * 直接设置对象属性值, 忽略 private/protected 修饰符, 也不经过 setter
	 * @param object : 子类对象
	 * @param fieldName : 父类中的属性名
	 * @param value : 将要设置的值
	 */
	
	public static void setFieldValue(Object object, String fieldName, Object value){
	
		//根据 对象和属性名通过反射 调用上面的方法获取 Field对象
		Field field = getDeclaredField(object, fieldName) ;
		if(field!=null){
 
				//抑制Java对其的检查
				field.setAccessible(true) ;
				
				try {
					//将 object 中 field 所代表的值 设置为 value
 	
					 field.set(object, value) ;
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				
				
		}
				
	    
	}
	
 

	/**
	 * 把一个类中赋值的变量传递到另一个类中使用
	 * @param src
	 * @param dest
	 */
	
    public static void copy(Object src, Object dest) {
    	 
    	 Map<String, Object> srcMap = new HashMap<String, Object>();
    	 Field[] srcFields = null;
    	 ArrayList<Field> srcFieldsList=new ArrayList<>();
    	 
    	
    	 if( "java.lang.Object".equals(src.getClass().getSuperclass().getName())){
        	  srcFields = src.getClass().getDeclaredFields();
        	  for (int i = 0; i < srcFields.length; i++) {
            		srcFieldsList.add(srcFields[i]);
    		  }
         }else{
        	//获取父类和自己的所有属性
         
        	srcFields = src.getClass().getDeclaredFields();
        	for (int i = 0; i < srcFields.length; i++) {
        		srcFieldsList.add(srcFields[i]);
			}
        	
        	srcFields = src.getClass().getSuperclass().getDeclaredFields();
        	for (int i = 0; i < srcFields.length; i++) {
        		srcFieldsList.add(srcFields[i]);
			}
        	
        
         }
         
         for (Field fd : srcFieldsList) {
         	
         	fd.setAccessible(true) ;
             try {
                 srcMap.put(fd.getName(), fd.get(src)); //获取属性值
             } catch (Exception e) {
                 e.printStackTrace();
             }
         }
         Field[] destFields = dest.getClass().getDeclaredFields();
         for (Field fd : destFields) {
         	
         	fd.setAccessible(true) ;
             if (srcMap.get(fd.getName()) == null||"".equals(srcMap.get(fd.getName()))) {
                 continue;
             }
             try {
                 fd.set(dest, srcMap.get(fd.getName())); //给属性赋值
             } catch (Exception e) {
                 e.printStackTrace();
             }
         }
     }
    
    public static void main(String[] args) {
		
    	OrderInfoModel orderInfoModel=new OrderInfoModel();
    	orderInfoModel.setRecordid(1);
    	orderInfoModel.setActualusetime("2222");
    	orderInfoModel.setContractno("222");
    	
		OrderHistoryModel orderHistoryModel=new OrderHistoryModel();
		
		ObjectUtil.copy(orderInfoModel, orderHistoryModel);
		
		System.out.println("aaa");
	}
	
}
