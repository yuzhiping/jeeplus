package com.jeeplus.weixin.controller;

 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/monitor")
public class MonitorControl extends BaseController {

    private final String prefix = "monitor/";

  
	    /**
	    * 车辆监控首页
	    * @return
	    */ 
	   @RequestMapping("/carControl")
	   public String carControl(Model model){
 
	       return prefix+"carControl";
	   }
	   
	   
	    /**
	    * 车辆监控详情
	    * @return
	    */ 
	   @RequestMapping("/carCDetails")
	   public String carCDetails(Model model){

	       return prefix+"carCDetails";
	   }
	   
 

}
