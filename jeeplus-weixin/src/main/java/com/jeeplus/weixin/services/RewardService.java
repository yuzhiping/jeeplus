package com.jeeplus.weixin.services;
 
import com.jeeplus.weixin.common.pagination.Page;
import com.jeeplus.weixin.common.pagination.PageHelper;
import com.jeeplus.weixin.common.pagination.PageInfo;
import com.jeeplus.weixin.dto.RewardModel;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
 
@Service
public class RewardService extends BaseService{
	
	public PageInfo selectRewardInfoPageList(RewardModel rewardModel) {

		
		 
        PageInfo pageInfo = null;
        int pageSize = 15;
        int pageNum = 1;
        if(null != rewardModel && rewardModel.getCurPage() != 0){
            pageNum = rewardModel.getCurPage();
        }
        PageHelper.startPage(pageNum, pageSize, true);
        Page<RewardModel> pages = rewardDetailModelMapper.selectByRewardUser(rewardModel);
        if(null != pages){
            pageInfo = new PageInfo(pages);
        }
        return pageInfo;
	}
	
	     //设置奖励页面数据
		public void setRewardData(PageInfo pageInfo,
				RewardModel rewardModel, Model model) {
			// TODO Auto-generated method stub
 
			rewardModel.setTotal(rewardDetailModelMapper.sumReward(rewardModel));
			
			model.addAttribute("rewardModel",rewardModel);
	        
	        model.addAttribute("pageInfo", pageInfo);
			
		}
}