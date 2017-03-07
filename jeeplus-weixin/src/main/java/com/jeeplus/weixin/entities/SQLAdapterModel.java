package com.jeeplus.weixin.entities;

public class SQLAdapterModel {
	
   private  String sql;  
    
   public SQLAdapterModel(String sql) {  
       this.sql = sql;  
   }  
 
   public String getSql() {  
       return sql;  
   }  
 
   public void setSql(String sql) {  
       this.sql = sql;  
   }  
}
