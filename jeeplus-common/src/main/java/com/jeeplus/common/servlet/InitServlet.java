package com.jeeplus.common.servlet;

import com.jeeplus.common.util.ConfigUtils;

import javax.servlet.http.HttpServlet;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-07-14 16:56.
 */
public class InitServlet extends HttpServlet {

    public void init(){
        Properties config = new Properties();
        try{
            String path= (getClass().getClassLoader().getResource("").toURI()).getPath();
            FileInputStream inputStream = new FileInputStream(path+"config.properties");
            config.load(inputStream);
            ConfigUtils.setConfig(config);
        }catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
