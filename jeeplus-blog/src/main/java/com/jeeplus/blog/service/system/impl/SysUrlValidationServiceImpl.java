package com.jeeplus.blog.service.system.impl;

import com.jeeplus.blog.service.system.SysUrlValidationService;
import org.springframework.stereotype.Service;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-24 14:44.
 */
@Service
public class SysUrlValidationServiceImpl implements SysUrlValidationService {
    /**
     * validate category url sample :// xxx.51so.info/category/keyword
     *
     * @param websiteId
     * @param urlPath
     * @return
     * @throws Exception
     */
    @Override
    public boolean validateCategory(String websiteId, String urlPath) {
        return false;
    }

    /**
     * validate lable url sample :/tags/xxxx
     *
     * @param websiteId
     * @param urlPath
     * @return
     * @throws Exception
     */
    @Override
    public boolean validateLable(String websiteId, String urlPath) {
        return false;
    }
}
