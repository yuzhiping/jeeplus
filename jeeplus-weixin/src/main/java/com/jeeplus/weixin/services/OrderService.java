package com.jeeplus.weixin.services;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.jeeplus.weixin.common.pagination.Page;
import com.jeeplus.weixin.common.pagination.PageHelper;
import com.jeeplus.weixin.common.pagination.PageInfo;
import com.jeeplus.weixin.dto.IndexModel;
import com.jeeplus.weixin.dto.OrderDetailModel;
import com.jeeplus.weixin.entities.*;
import com.jeeplus.weixin.utils.DateUtil;
import com.jeeplus.weixin.utils.SendsmsUtil;
import org.apache.commons.beanutils.PropertyUtils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
 

@Service
public class OrderService extends BaseService{

    
	public void setOrderDataList(OrderDetailModel orderDetailModel, Model model) {
		// TODO Auto-generated method stub
 
        
        if(!StringUtils.isEmpty(orderDetailModel.getOrderstatus().toString())){
        	  model.addAttribute("orderstatus_name",dictUtil.getDictNameByValue("OrderStatus",orderDetailModel.getOrderstatus()));
        }
      
		
         model.addAttribute("orderStatusList", dictUtil.getDictListBycategory("OrderStatus"));
		 model.addAttribute("pageInfo", getOrderPageList(orderDetailModel));
	}

	
	public PageInfo getOrderPageList(OrderDetailModel orderDetailModel) {
		
		// TODO Auto-generated method stub
        PageInfo pageInfo = null;
        int pageSize = 15;
        int pageNum = 1;
        if(null != orderDetailModel && orderDetailModel.getCurPage() != 0){
            pageNum = orderDetailModel.getCurPage();
        }
        PageHelper.startPage(pageNum, pageSize, true);
        Page<OrderDetailModel> pages =orderInfoModelMapper.selectMyOrderPageList(orderDetailModel);
        if(null != pages){
            pageInfo = new PageInfo(pages);
        }
		
        return pageInfo;
    
	}


	public OrderDetailModel setOrderByPrimKey(Integer recordid,Model model) {
		// TODO Auto-generated method stub
		OrderDetailModel myOrderModel=orderInfoModelMapper.selectByPrimaryKey(recordid);
		model.addAttribute("orderDetailModel", myOrderModel);
		
		return myOrderModel;
	}


	public void setUserAccount(Integer orderuser, Model model) {
		// TODO Auto-generated method stub
		UserAccountModel userAccountModel=userAccountModelMapper.selectByUserid(orderuser);
		model.addAttribute("userAccountModel", userAccountModel);
		
		
	}
	
	public Boolean cancel_order(OrderInfoModel orderInfoModel) {
		// TODO Auto-generated method stub
		
		try {

			//先向历史表里备份数据
			OrderInfoModel orderInfoModel2=orderInfoModelMapper.selectByPrimaryKey(orderInfoModel.getRecordid());
			OrderHistoryModel orderHistoryModel=new OrderHistoryModel();
			PropertyUtils.copyProperties(orderHistoryModel, orderInfoModel2);
			orderHistoryModel.setRecordid(null);
			orderHistoryModelMapper.insert(orderHistoryModel);
			
			
			
			orderInfoModelMapper.updateByPrimaryKeySelective(orderInfoModel);
			
 
			
			return true;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}

 
	 
	}


    /**
     * 设置确认订单数据	
     * @param orderDetailModel
     * @param model
     */
	public void setorderConfirmData(OrderDetailModel orderDetailModel,
			Model model) {
		// TODO Auto-generated method stub
 
		
		    UserInfoModel userInfoModel = userInfoModelMapper.selectByPrimaryKey(getLoginUser().getRecordid());
		 
		    model.addAttribute("userInfoModel",userInfoModel);
		
			IndexModel indexModel = supplyCarModelMapper.selectByPrimaryKey(orderDetailModel.getRecordid());
			
			model.addAttribute("indexModel", indexModel);
		
	}


	public CommonModel saveOrderConfirm(OrderDetailModel  orderDetailModel) {
		
		// TODO Auto-generated method stub
		OrderInfoModel orderInfoModel = new OrderInfoModel();
		OrderHistoryModel orderHistoryModel=new OrderHistoryModel();
		CommonModel commonModel=new CommonModel();
		commonModel.setFlag(false);
	 
		try {
			
			PropertyUtils.copyProperties(orderInfoModel, orderDetailModel);
			orderInfoModel.setDepositstatus(new Byte("0"));
			orderInfoModel.setOrderno(getLoginUser().getPhoneno()+ DateUtil.formatDate(new Date(), "yyyymmddhhmmss"));
			orderInfoModel.setOrderpay(0);
			orderInfoModel.setOrderstatus(new Byte("1"));
		 
			//添加订单
			orderInfoModelMapper.insertSelective(orderInfoModel);
			
 
			//添加订单历史数据
			PropertyUtils.copyProperties(orderHistoryModel, orderInfoModel);
			orderHistoryModelMapper.insert(orderHistoryModel);
			
	         
			  UserInfoModel userInfoModel= userInfoModelMapper.selectByPrimaryKey(orderDetailModel.getOrderuser());
			
			  //判断是否需要更新推荐人信息
			  String sql="select count(0) from tmk_user_info where phoneNo ='"+getLoginUser().getPhoneno() +"' and refferNo ='"+orderDetailModel.getRefferno()+"'";
			  int count=dictModelMapper.selectCount(new SQLAdapterModel(sql));
			  if(count==0){
                 //更新用户的推荐人手机号
				userInfoModel.setRefferno(orderDetailModel.getRefferno());
				userInfoModelMapper.updateByPrimaryKeySelective(userInfoModel);
				//添加推荐人历史记录
				ReffererInfoModel reffererInfoModel=new ReffererInfoModel();
				reffererInfoModel.setRefferno(userInfoModel.getRefferno());
	            reffererInfoModel.setPhoneno(getLoginUser().getPhoneno());
	        	reffererInfoModelMapper.insert(reffererInfoModel);
			 
 
			}
			//向未注册的推推荐人用户发送短信提醒(目前不能发,模板待申请)
		    sql="select count(*) from tmk_user_info where phoneNo='"+userInfoModel.getRefferno()+"'";
			
			if(dictModelMapper.selectCount(new SQLAdapterModel(sql))==0){
			   SendsmsUtil.Send(userInfoModel.getRefferno(), "温馨提示，您已被"+getLoginUser().getPhoneno()+"设置为推荐人，请及时注册领取奖励http://www.botann.com/btkjsite/front/login");
			}
				
				
			  
			commonModel.setMessage(orderInfoModel.getOrderno());
			commonModel.setFlag(true);
			
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			commonModel.setMessage("提交订单失败");
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			commonModel.setMessage("提交订单失败");
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
			commonModel.setMessage("提交订单失败");
		}
		
		return commonModel;
	
	}


	public CommonModel pay_order(OrderDetailModel orderDetailModel) {
		// TODO Auto-generated method stub
	
		CommonModel commonModel=new  CommonModel();
		commonModel.setFlag(false);
		
		
		//防止重复扣款的情况发生
		OrderDetailModel orderDetailModel2 = orderInfoModelMapper.selectByPrimaryKey(orderDetailModel.getRecordid());
		if(!new Byte("3").equals(orderDetailModel2.getOrderstatus())){
			//修改订单状态
			orderDetailModel.setDepositstatus(new Byte("1"));
			orderDetailModel.setOrderstatus(new Byte("3"));
		 
			orderInfoModelMapper.updateByPrimaryKeySelective(orderDetailModel);
			
			OrderHistoryModel orderHistoryModel=new OrderHistoryModel();
 
			try {
 
				//添加订单历史数据
				OrderDetailModel orderDetailModel3= orderInfoModelMapper.selectByPrimaryKey(orderDetailModel.getRecordid());
				PropertyUtils.copyProperties(orderHistoryModel, orderDetailModel3);
				orderHistoryModel.setRecordid(null);
				orderHistoryModelMapper.insert(orderHistoryModel);
				
			 
				//添加账户充值历史数据
				UserAccountDetailModel userAccountDetailModel=new UserAccountDetailModel();
				if(orderDetailModel.getPaytype()!=2){ 	//可用余额不够付款
					userAccountDetailModel.setAccountuser(orderDetailModel.getOrderuser());
					userAccountDetailModel.setAccountopt(new Byte(orderDetailModel.getPaytype().toString()));
					userAccountDetailModel.setOptamount(orderDetailModel.getOrderpay());
					userAccountDetailModelMapper.insert(userAccountDetailModel);
				}

 
				UserAccountModel userAccountModel=userAccountModelMapper.selectByUserid(orderDetailModel.getOrderuser());
				
				//更新账户信息
				if(orderDetailModel.getPaytype()!=2){  	//可用余额不够付款
					userAccountModel.setTotalamount(userAccountModel.getTotalamount()+orderDetailModel.getOrderpay());
				}
			
			 
				
				//可用余额要扣除冻结的押金
				userAccountModel.setTotalamount(userAccountModel.getTotalamount()-orderDetailModel.getDeposit());
				userAccountModel.setDeposit((userAccountModel.getDeposit()==null? 0:userAccountModel.getDeposit())+orderDetailModel.getDeposit());
				userAccountModel.setDepositfrozenstatus(new Byte("1"));
				userAccountModelMapper.updateByPrimaryKeySelective(userAccountModel);
		 
				//添加账户扣除押金历史数据
				if(orderDetailModel.getDeposit()!=0){
					userAccountDetailModel.setAccountopt(new Byte("11"));
					userAccountDetailModel.setOptamount(-orderDetailModel.getDeposit());
					userAccountDetailModelMapper.insert(userAccountDetailModel);
				}

				
				commonModel.setMessage("付款成功");
				commonModel.setFlag(true);
				
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				commonModel.setMessage("付款失败");
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				commonModel.setMessage("付款失败");
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();	
				commonModel.setMessage("付款失败");
			}
		}else{
			commonModel.setFlag(true);
			commonModel.setMessage("付款成功");
		}
		
		
		return commonModel;
	}




 
}
