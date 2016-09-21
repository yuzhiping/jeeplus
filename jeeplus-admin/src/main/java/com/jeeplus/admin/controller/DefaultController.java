package com.jeeplus.admin.controller;

import com.jeeplus.admin.common.controller.BaseController;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yuzp17311 on 2016/9/1.
 */
@Controller
public class DefaultController extends BaseController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(HttpServletRequest request, HttpServletResponse response) {
        return "redirect:/index.html";
    }

}
