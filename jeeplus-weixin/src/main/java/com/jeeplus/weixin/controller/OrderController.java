package com.jeeplus.weixin.controller;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.btkj.entity.CommonModel;
import cn.btkj.entity.OrderInfoModel;
import cn.btkj.extendentity.OrderDetailModel;
import cn.btkj.pagination.PageInfo;
import cn.btkj.service.OrderService;


 

/**
 * Created by lzh  
 */
@Controller
@RequestMapping("/order")
public class OrderController extends BaseController {

    private final String prefix = "order/";

    @Autowired
    private OrderService orderService;

	     /**
	     * 我的订单
	     * @return
	     */ 
	    @RequestMapping("/myOrder")
	    public String myOrder(OrderDetailModel myOrderModel,Model model){
	     
	    	 orderService.setOrderDataList(myOrderModel,model);
            
	         return prefix+"myOrder";
	    }
	    
	    /**
	     * 下拉加载更多
	     * @param myOrderModel
	     * @return
	     */
	    @RequestMapping("/laod_more")
	    @ResponseBody
	    public PageInfo laod_more(OrderDetailModel myOrderModel){
	     
	    	PageInfo pageInfo = orderService.getOrderPageList(myOrderModel);
            
	        return pageInfo;
	    }
	    
	    
		  
	     /**
	     * 订单确认
	     * @return
	     */ 
	    @RequestMapping("/orderConfirm")
	    public String orderConfirm(OrderDetailModel orderDetailModel,Model model){
	     
	    	orderService.setorderConfirmData(orderDetailModel,model);
	    	
           
	        return prefix+"orderConfirm";
	    }
	    
	    
		  
	     /**
	     * 订单确认提醒
	     * @return
	     */ 
	    @RequestMapping("/orderRemind")
	    public String orderRemind(@RequestParam(value = "orderno",required = false)String orderno,Model model){
	     
            model.addAttribute("orderno", orderno);
	    	
	        return prefix+"orderRemind";
	    }

	    
	    /**
	     * 保存订单确认
	     * @param myOrderModel
	     * @return
	     */
	    @RequestMapping("/saveOrderConfirm")
	    @ResponseBody
	    public CommonModel saveOrderConfirm(OrderDetailModel  orderDetailModel){
	 
	        return  orderService.saveOrderConfirm(orderDetailModel);
	    }
		  
	     /**
	     * 订单详情
	     * @return
	     */ 
	    @RequestMapping("/orderDetails")
	    public String orderDetails(OrderDetailModel orderDetailModel,Model model){
	    
	    	
	    	orderDetailModel=orderService.setOrderByPrimKey(orderDetailModel.getRecordid(),model);
	    	
 
		    orderService.setUserAccount(orderDetailModel.getOrderuser(),model);
 
			
	    	//待付款订单详情
	    	if(new Byte("2").equals(orderDetailModel.getOrderstatus())){
	    		
	    	 	return prefix+"orderDetails-unPay";
	    	
	    	}else{
	    	//订单详情
	    		return prefix+"orderDetails-now";
	    	}
	    	 
	       
	    }

	    
	    /**
	     * 订单支付
	     * @param myOrderModel
	     * @return
	     */
	    @RequestMapping("/pay_order")
	    @ResponseBody
	    public CommonModel pay_order(OrderDetailModel  orderDetailModel){
 
	        return  orderService.pay_order(orderDetailModel);
	    }
	    
	    /**
	     * 取消订单
	     * @param orderInfoModel
	     * @return
	     */
	    @RequestMapping("/cancel_order")
	    @ResponseBody
	    public Boolean cancel_order(OrderInfoModel orderInfoModel){
	    	
	    	orderInfoModel.setOrderstatus(new Byte("5"));
	    	
	        return  orderService.cancel_order(orderInfoModel);
	    }
	    

}
