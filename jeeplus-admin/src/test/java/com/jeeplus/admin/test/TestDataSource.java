package com.jeeplus.admin.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;

/**
 * Created by yuzp17311 on 2016/8/25.
 */
public class TestDataSource {


    @Test
    public void testDataSource(){
        ApplicationContext context=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-admin-dao.xml");
        DataSource dataSource= (DataSource) context.getBean("adminDataSource");
        if(dataSource!=null){
            try {
                dataSource.getConnection();
                System.out.println("数据源配置成功");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
