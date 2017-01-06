package com.jeeplus.weixin.fastweixin.api;

import com.jeeplus.weixin.fastweixin.api.config.ApiConfig;
import com.jeeplus.weixin.fastweixin.api.entity.UserInfo;
import com.jeeplus.weixin.fastweixin.api.enums.ResultType;
import com.jeeplus.weixin.fastweixin.api.response.*;
import com.jeeplus.weixin.fastweixin.exception.WeixinException;
import com.jeeplus.weixin.fastweixin.util.BeanUtils;
import com.jeeplus.weixin.fastweixin.util.CollectionUtils;
import com.jeeplus.weixin.fastweixin.util.JSONUtils;
import com.jeeplus.weixin.fastweixin.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户管理相关API
 *
 * @author peiyu
 * @since 1.2
 */
public class UserAPI extends BaseAPI {

    private static final Logger LOG = LoggerFactory.getLogger(UserAPI.class);

    public UserAPI(ApiConfig config) {
        super(config);
    }

    /**
     * 获取关注者列表
     *
     * @param nextOpenid 下一个用户的ID
     * @return 关注者列表对象
     */
    public GetUsersResponse getUsers(String nextOpenid) {
        GetUsersResponse response;
        LOG.debug("获取关注者列表.....");
        String url = BASE_API_URL + "cgi-bin/user/get?access_token=#";
        if (StringUtils.isNotBlank(nextOpenid)) {
            url += "&next_openid=" + nextOpenid;
        }
        BaseResponse r = executeGet(url);
        String resultJson = isSuccess(r.getErrcode()) ? r.getErrmsg() : r.toJsonString();
        response = JSONUtils.toBean(resultJson, GetUsersResponse.class);
        return response;
    }

    /**
     * 设置关注者备注
     *
     * @param openid 关注者ID
     * @param remark 备注内容
     * @return 调用结果
     */
    public ResultType setUserRemark(String openid, String remark) {
        BeanUtils.requireNonNull(openid, "openid is null");
        LOG.debug("设置关注者备注.....");
        String url = BASE_API_URL + "cgi-bin/user/info/updateremark?access_token=#";
        Map<String, String> param = new HashMap<String, String>();
        param.put("openid", openid);
        param.put("remark", remark);
        BaseResponse response = executePost(url, JSONUtils.toJson(param));
        return ResultType.get(response.getErrcode());
    }

    /**
     * 创建分组
     *
     * @param name 分组名称
     * @return 返回对象，包含分组的ID和名称信息
     */
    public CreateGroupResponse createGroup(String name) {
        CreateGroupResponse response;
        BeanUtils.requireNonNull(name, "name is null");
        LOG.debug("创建分组.....");
        String url = BASE_API_URL + "cgi-bin/groups/create?access_token=#";
        Map<String, Object> param = new HashMap<String, Object>();
        Map<String, Object> group = new HashMap<String, Object>();
        group.put("name", name);
        param.put("group", group);
        BaseResponse r = executePost(url, JSONUtils.toJson(param));
        String resultJson = isSuccess(r.getErrcode()) ? r.getErrmsg() : r.toJsonString();
        response = JSONUtils.toBean(resultJson, CreateGroupResponse.class);
        return response;
    }

    /**
     * 获取所有分组信息
     *
     * @return 所有分组信息列表对象
     */
    public GetGroupsResponse getGroups() {
        GetGroupsResponse response;
        LOG.debug("获取所有分组信息.....");
        String url = BASE_API_URL + "cgi-bin/groups/get?access_token=#";
        BaseResponse r = executeGet(url);
        String resultJson = isSuccess(r.getErrcode()) ? r.getErrmsg() : r.toJsonString();
        response = JSONUtils.toBean(resultJson, GetGroupsResponse.class);
        return response;
    }

    /**
     * 通过关注者ID获取所在分组信息
     *
     * @param openid 关注者ID
     * @return 所在分组信息
     */
    public String getGroupIdByOpenid(String openid) {
        BeanUtils.requireNonNull(openid, "openid is null");
        LOG.debug("通过关注者ID获取所在分组信息.....");
        String result = null;
        String url = BASE_API_URL + "cgi-bin/groups/getid?access_token=#";
        Map<String, String> params = new HashMap<String, String>();
        params.put("openid", openid);
        BaseResponse r = executePost(url, JSONUtils.toJson(params));
        if (isSuccess(r.getErrcode())) {
            result = JSONUtils.toMap(r.getErrmsg()).get("groupid").toString();
        }
        return result;
    }

    /**
     * 修改分组信息
     *
     * @param groupid 分组ID
     * @param name    新名称
     * @return 调用结果
     */
    public ResultType updateGroup(Integer groupid, String name) {
        BeanUtils.requireNonNull(groupid, "groupid is null");
        BeanUtils.requireNonNull(name, "name is null");
        LOG.debug("修改分组信息.....");
        String url = BASE_API_URL + "cgi-bin/groups/update?access_token=#";
        Map<String, Object> param = new HashMap<String, Object>();
        Map<String, Object> group = new HashMap<String, Object>();
        group.put("id", groupid);
        group.put("name", name);
        param.put("group", group);
        BaseResponse response = executePost(url, JSONUtils.toJson(param));
        return ResultType.get(response.getErrcode());
    }

    /**
     * 移动关注者所在分组
     *
     * @param openid    关注者ID
     * @param toGroupid 新分组ID
     * @return 调用结果
     */
    public ResultType moveGroupUser(String openid, String toGroupid) {
        BeanUtils.requireNonNull(openid, "openid is null");
        BeanUtils.requireNonNull(toGroupid, "toGroupid is null");
        LOG.debug("移动关注者所在分组.....");
        String url = BASE_API_URL + "cgi-bin/groups/members/update?access_token=#";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("openid", openid);
        param.put("to_groupid", toGroupid);

        BaseResponse response = executePost(url, JSONUtils.toJson(param));
        return ResultType.get(response.getErrcode());
    }

    /**
     * 移动关注者所在分组
     *
     * @param openids    关注者ID
     * @param toGroupid 新分组ID
     * @return 调用结果
     */
    public ResultType moveGroupUser(String[] openids, String toGroupid) {
        BeanUtils.requireNonNull(openids, "openid is null");
        BeanUtils.requireNonNull(toGroupid, "toGroupid is null");
        LOG.debug("移动关注者所在分组.....");
        String url = BASE_API_URL + "cgi-bin/groups/members/batchupdate?access_token=#";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("openid_list", openids);
        param.put("to_groupid", toGroupid);

        BaseResponse response = executePost(url, JSONUtils.toJson(param));
        return ResultType.get(response.getErrcode());
    }

    /**
     * 获取关注者信息
     *
     * @param openid 关注者ID
     * @return 关注者信息对象
     */
    public GetUserInfoResponse getUserInfo(String openid) {
        BeanUtils.requireNonNull(openid, "openid is null");
        GetUserInfoResponse response;
        LOG.debug("获取关注者信息.....");
        String url = BASE_API_URL + "cgi-bin/user/info?access_token=#&lang=zh_CN&openid=" + openid;
        BaseResponse r = executeGet(url);
        String resultJson = isSuccess(r.getErrcode()) ? r.getErrmsg() : r.toJsonString();
        response = JSONUtils.toBean(resultJson, GetUserInfoResponse.class);
        return response;
    }

    /**
     *批量获取关注者信息
     *
     * @param userInfoList 关注者ID列表
     * @return 关注者信息对象列表
     */
    public GetUserInfoListResponse getUserInfoList(List<UserInfo> userInfoList){
        String url = BASE_API_URL + "cgi-bin/user/info/batchget?access_token=#";
        Map<String, List<UserInfo>> param = new HashMap<String, List<UserInfo>>();
        param.put("user_list", userInfoList);
        BaseResponse r=executePost(url, JSONUtils.toJson(param));
        String resultJson = isSuccess(r.getErrcode()) ? r.getErrmsg() : r.toJsonString();
        GetUserInfoListResponse getUserInfoListResponse= JSONUtils.toBean(resultJson,GetUserInfoListResponse.class);
        return getUserInfoListResponse;
    }

    /**
     * 删除分组
     * @param groupId 分组ID
     * @return 删除结果
     */
    public ResultType deleteGroup(Integer groupId){
        BeanUtils.requireNonNull(groupId, "groupId is null");
        LOG.debug("删除分组.....");
        String url = BASE_API_URL + "cgi-bin/groups/delete?access_token=#";
        Map<String, Object> param = new HashMap<String, Object>();
        Map<String, Integer> groups = new HashMap<String, Integer>();
        groups.put("id", groupId);
        param.put("group", groups);
        BaseResponse response = executePost(url, JSONUtils.toJson(param));
        return ResultType.get(response.getErrcode());
    }

    /**
     * 批量为用户打上标签
     * 标签功能目前支持公众号为用户打上最多三个标签。
     * @param openidList 用户openid列表
     * @param tagId 标签ID
     * @return 结果
     */
    public ResultType batchTagsToUser(List<String> openidList, Integer tagId) {
        BeanUtils.requireNonNull(tagId, "tagId is null");
        if(CollectionUtils.isEmpty(openidList)) {
            throw new WeixinException("openId列表为空");
        }
        LOG.debug("批量为用户打上标签.....");
        String url = BASE_API_URL + "cgi-bin/tags/members/batchtagging?access_token=#";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("openid_list", openidList);
        param.put("tagid", tagId);
        BaseResponse response = executePost(url, JSONUtils.toJson(param));
        return ResultType.get(response.getErrcode());
    }

    /**
     * 批量为用户取消标签
     * @param openidList 用户openid列表
     * @param tagId 标签ID
     * @return 结果
     */
    public ResultType batchDeleteTagsToUser(List<String> openidList, Integer tagId) {
        BeanUtils.requireNonNull(tagId, "tagId is null");
        if(CollectionUtils.isEmpty(openidList)) {
            throw new WeixinException("openId列表为空");
        }
        LOG.debug("批量为用户取消标签.....");
        String url = BASE_API_URL + "cgi-bin/tags/members/batchuntagging?access_token=#";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("openid_list", openidList);
        param.put("tagid", tagId);
        BaseResponse response = executePost(url, JSONUtils.toJson(param));
        return ResultType.get(response.getErrcode());
    }
}