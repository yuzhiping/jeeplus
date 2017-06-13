package com.hexsmith;

import com.alibaba.fastjson.JSONArray;
import com.jeeplus.common.util.ExcelBigDataUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-04-18 10:59.
 */
public class testExcelBigData {


    // 下边是在controller里边调用的案例及十万条数据测试
    @ResponseBody
	@RequestMapping(value = "partExport")
    public void partExport(HttpServletResponse response) throws IOException {
		int count = 10;
        JSONArray ja = new JSONArray();
        for(int i=0;i<10;i++){
            Student s = new Student();
            s.setName("POI"+i);
            s.setAge(i);
            s.setBirthday(new Date());
            s.setHeight(i);
            s.setWeight(i);
            s.setSex(i/2==0?false:true);
            ja.add(s);
        }
        Map<String,String> headMap = new LinkedHashMap<String,String>();
        headMap.put("name","姓名");
        headMap.put("age","年龄");
        headMap.put("birthday","生日");
        headMap.put("height","身高");
        headMap.put("weight","体重");
        headMap.put("sex","性别");

        String title = "测试";

        ExcelBigDataUtils.downloadExcelFile(title,headMap,ja,response);

    }


    class Student {
        private String name;
        private int age;
        private Date birthday;
        private float height;
        private double weight;
        private boolean sex;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public Date getBirthday() {
            return birthday;
        }

        public void setBirthday(Date birthday) {
            this.birthday = birthday;
        }

        public float getHeight() {
            return height;
        }

        public void setHeight(float height) {
            this.height = height;
        }

        public double getWeight() {
            return weight;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }

        public boolean isSex() {
            return sex;
        }

        public void setSex(boolean sex) {
            this.sex = sex;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }


}
