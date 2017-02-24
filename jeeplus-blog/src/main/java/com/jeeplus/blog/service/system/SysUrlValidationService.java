package com.jeeplus.blog.service.system;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-24 14:41.
 */
public interface SysUrlValidationService {

    /**
     * validate category url sample :// xxx.51so.info/category/keyword
     * @param websiteId
     * @param urlPath
     * @return
     * @throws Exception
     */
    boolean validateCategory(String websiteId,String urlPath);

    /**
     * validate lable url sample :/tags/xxxx
     * @param websiteId
     * @param urlPath
     * @return
     * @throws Exception
     */
    boolean validateLable(String websiteId,String urlPath);

}
