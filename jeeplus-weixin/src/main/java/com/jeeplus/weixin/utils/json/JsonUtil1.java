package com.jeeplus.weixin.utils.json;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jeeplus.weixin.utils.TimeUtil;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

 

public class JsonUtil1 {
	
  private static Logger logger = Logger.getLogger(JsonUtil1.class);
  private static Map primitiveMap = new HashMap();

  static{
    primitiveMap.put(boolean.class, Boolean.class);
    primitiveMap.put(char.class, Character.class);
    primitiveMap.put(byte.class, Byte.class);
    primitiveMap.put(short.class, Short.class);
    primitiveMap.put(int.class, Integer.class);
    primitiveMap.put(long.class, Long.class);
    primitiveMap.put(float.class , Float.class);
    primitiveMap.put(double.class, Double.class);
  }

  public JsonUtil1() {
  }
  

  public static JSONObject toJSONObject(Object bean){
  	return toJSONObject(bean, null);
  }


  public static JSONObject toJSONObject(Object bean, JSONObject json){
    if(bean==null)
      return null;
    if(json==null)
    	json = new JSONObject();
    Class klass = bean.getClass();
    Method[] methods = klass.getMethods();
    Method method;
    String name, key;
    Object obj = null;
    for (int i = 0; i < methods.length; i += 1) {
      try {
        method = methods[i];
        name = method.getName();
        key = "";
        if (name.startsWith("get")) {
          key = name.substring(3);
        } else if (name.startsWith("is")) {
          key = name.substring(2);
        }
        if (key.length() > 0 && !key.equals("Class") && Character.isUpperCase(key.charAt(0)) &&
            method.getParameterTypes().length == 0) {
          key = key.substring(0, 1).toLowerCase() + key.substring(1);
          obj = method.invoke(bean, (Object[])null);

          if (obj == null) {
            json.put(key, JSONObject.NULL);
          } else if (Date.class.isAssignableFrom(obj.getClass())) {
            json.put(key, TimeUtil.formatDateTime((Date) obj));
          } else if (obj.getClass().isPrimitive() || obj.getClass().equals(String.class)
              || obj.getClass().equals(Boolean.class) || Number.class.isAssignableFrom(obj.getClass())) {
            json.put(key, obj);
          }
        }
      } catch (Exception e) {
        /* forget about it */
    	  logger.error("", e);
      }
    }
    return json;
  }

  public static int fillBean(Object bean, JSONObject json) {
    if(bean==null || json==null)
      return 0;
    int ret = 0;
    Class klass = bean.getClass();
    Method[] methods = klass.getMethods();
    Method method;
    String name, key;
    Object obj = null;
    Class[] types = null;
    for (int i = 0; i < methods.length; i += 1) {
      try {
        method = methods[i];
        name = method.getName();
        key = "";
        if (name.startsWith("set")) {
          key = name.substring(3);
          types = method.getParameterTypes();
          if (key.length() > 0 && !key.equals("Class") &&
              Character.isUpperCase(key.charAt(0)) && types.length == 1) {
            key = key.substring(0, 1).toLowerCase() + key.substring(1);
            obj = null;
            Class clazz = types[0];
            if (json.has(key) && (clazz.isPrimitive() || clazz.equals(String.class) || clazz.equals(Boolean.class)
                || Number.class.isAssignableFrom(clazz) || Date.class.isAssignableFrom(clazz))) {
              if (Date.class.isAssignableFrom(clazz)) {
                String tmp = json.optString(key, null);
                if(tmp!=null){
                  if(clazz.equals(java.sql.Date.class))
                    obj = java.sql.Date.valueOf(tmp);
                  else if(clazz.equals(java.sql.Time.class))
                    obj = java.sql.Time.valueOf(tmp);
                  else if(clazz.equals(java.sql.Timestamp.class))
                    obj = java.sql.Timestamp.valueOf(tmp);
                  else {
                    obj = TimeUtil.parseDateTime(json.optString(key));
                    if (obj == null)
                      obj = TimeUtil.parseDate(json.optString(key));
                  }
                }
              }else{
                obj = json.opt(key);
                //System.out.println(" key " + key);
                if(JSONObject.NULL.equals(obj) || obj.equals("") ){
                	obj = null;
                }else if (!clazz.equals(obj.getClass())) {
                  String tmp = json.optString(key, "0");
                  if(clazz.isPrimitive()){
                    clazz = (Class)primitiveMap.get(clazz);
                  }
                  obj = clazz.getConstructor(new Class[] {String.class}).newInstance(new Object[] {tmp});
                }
              }
              method.invoke(bean, new Object[] {obj});
              ret++;
            }
          }
        }
      } catch (Exception e) {
        /* forget about it */
    	logger.error("", e);
      }
    }
    return ret;
  }
  

  public static Object getBean(Class klass, JSONObject json){
    try {
      Object obj = klass.newInstance();
      JsonUtil1.fillBean(obj, json);
      return obj;
    } catch (Exception ex) {
      return null;
    }
  }
  

  public static List getBeanList(Class klass, JSONArray jary){
    List list = new ArrayList();
    JSONObject json = null;
    Object obj = null;
    for(int i=0; i<jary.length(); i++){
      json = jary.optJSONObject(i);
      if(json!=null){
        obj = getBean(klass, json);
        if(obj!=null)
          list.add(obj);
      }
    }
    return list;
  }
  

  public static List toJSONList(List beanList){
  	List list = new ArrayList();
  	if(beanList!=null){
	  	for(int i=0; i<beanList.size(); i++){
	  		Object bean = beanList.get(i);
	  		list.add(toJSONObject(bean));
	  	}
  	}
  	return list;
  }


  public static Map Object2StringMap(Object obj) {
	 // System.out.println("ttt");
		if (obj != null) {
			try {
				Map map = new HashMap();
				Class c = obj.getClass();
				Method[] ms = c.getMethods();
				for (Method m : ms) {
					if (m.getName().startsWith("get")
							&& m.getParameterTypes().length == 0
							&& !m.getName().equals("get")
							&& !m.getName().equals("getClass")) {
						char[] fieldch = m.getName().substring(3).toCharArray();
						fieldch[0] = Character.toLowerCase(fieldch[0]);
						String field = new String(fieldch);
						Object value = m.invoke(obj, null);
						if (value != null) {
							if (value instanceof Date) {
								value = TimeUtil.formatDateTime((Date) value);
							}
						}
						map.put(field, value.toString());
					}
				}
				return map;
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("", e);
			}
		}
		return null;
	}
  // list<map>转json by cj
  public static JSONArray listMapToJson(List list) throws Exception{
		JSONArray ja=new JSONArray();
		for(Object obj:list){
			JSONObject jso=	new JSONObject();
			HashMap<String, Object> hs=	(HashMap<String, Object>)obj;
			for(Map.Entry<String, Object> entry : hs.entrySet()){
				jso.put(entry.getKey(),entry.getValue());
			}
			ja.put(jso);
		}
		return ja;
  }
 
}
