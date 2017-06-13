package com.jeeplus.weixin.service;

 
import java.util.List;

import com.jeeplus.weixin.dto.FoundDetailModel;
import com.jeeplus.weixin.dto.MyFoundModel;
import com.jeeplus.weixin.entities.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.ui.Model;
 

@Service

public class FundService extends BaseService{
	
  
	public void setMyFundData(Integer orderuser, Model model) {
		// TODO Auto-generated method stub
		MyFoundModel myFoundModel=userAccountModelMapper.selectmyFoundByUserid(orderuser);
		
		model.addAttribute("myFoundModel", myFoundModel);
	}

 
	public CommonModel savePrePayment(UserAccountDetailModel userAccountDetailModel) {
		// TODO Auto-generated method stub
		 
      DefaultTransactionDefinition def = new DefaultTransactionDefinition();//事务定义类
      def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
      TransactionStatus status = transactionManager.getTransaction(def);//返回事务对象

		
		CommonModel commonModel=new  CommonModel();
		commonModel.setFlag(false);
		
		try {

			//添加账户充值历史数据
			userAccountDetailModelMapper.insert(userAccountDetailModel);
			
			//更新账户信息
			UserAccountModel userAccountModel=userAccountModelMapper.selectByUserid(userAccountDetailModel.getAccountuser());
			userAccountModel.setTotalamount(userAccountModel.getTotalamount()+userAccountDetailModel.getOptamount());
			userAccountModelMapper.updateByPrimaryKeySelective(userAccountModel);
	 
			
			commonModel.setMessage("充值成功");
			commonModel.setFlag(true);
			transactionManager.commit(status);
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			commonModel.setMessage("充值失败");
			transactionManager.rollback(status);
		}  
		
		return commonModel;
		
 
	}

	public void setFundDetailsData(Integer accountuser, Model model) {
		// TODO Auto-generated method stub
		
	    List<FoundDetailModel>  list=userAccountDetailModelMapper.selectListByUserid(accountuser);
	    
	    model.addAttribute("list",list);

	}

	public CommonModel withDrawals(UserAccountDetailModel userAccountDetailModel) {
		// TODO Auto-generated method stub

		
		CommonModel commonModel=new  CommonModel();
		commonModel.setFlag(false);
		
		try {

			UserAccountModel userAccountModel=userAccountModelMapper.selectByUserid(userAccountDetailModel.getAccountuser());
			
	 
			
			SQLAdapterModel sqlAdapterModel1=new SQLAdapterModel(
					" select count(1)\n" +
					" from tmk_user_account_detail tusd\n" +
					" where (tusd.accountopt=7 or tusd.accountopt=8) " +
					" and tusd.del=0 and tusd.accountuser="+userAccountDetailModel.getAccountuser());
			
			SQLAdapterModel sqlAdapterModel2=new SQLAdapterModel(
					" select count(1)\n" +
					" from tmk_order_info " +
					" where orderuser="+userAccountDetailModel.getAccountuser()+"" +
					" and orderstatus not in(4,5)");
			
			
		    if(dictModelMapper.selectCount(sqlAdapterModel1)>0){
		    	
		    	commonModel.setMessage("您还存在审核中的提现申请,暂时无法执行该操作");
		    	
		    }else if(dictModelMapper.selectCount(sqlAdapterModel2)>0){
				
				commonModel.setMessage("您还存在未完成的订单,暂时无执行该操作");
				
			}else if(userAccountModel.getTotalamount()<(-userAccountDetailModel.getOptamount())){
				
				commonModel.setMessage("提现失败:余额不足");
			}else if (new Byte("8").equals(userAccountDetailModel.getAccountopt()) && -userAccountDetailModel.getOptamount()<100) {
 
					commonModel.setMessage("提现失败:微信提现额度最少为1元");
				
			}else{
 				//添加提现申请历史数据
				userAccountDetailModel.setWxopenid(getLoginUser().getOpenid());
				userAccountDetailModelMapper.insert(userAccountDetailModel);
 
				commonModel.setMessage("提现申请提交成功,请等待审核");
				commonModel.setFlag(true);
			}
			
			


			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			commonModel.setMessage("提现失败");
		}  
		
		return commonModel;
	}

	public void fundPropose(UserInfoModel userInfoModel, Model model) {
		// TODO Auto-generated method stub
		UserAccountModel userAccountModel=userAccountModelMapper.selectByUserid(userInfoModel.getRecordid());
		model.addAttribute("userAccountModel",userAccountModel);
	}

}
