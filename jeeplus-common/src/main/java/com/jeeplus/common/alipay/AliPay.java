package com.jeeplus.common.alipay;

import com.alipay.api.AlipayApiException;
import com.jeeplus.common.alipay.util.AliPayDateUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-01-06 11:36.
 */
public class AliPay extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        // 参数目前都是 写死的 根据业务需求 写活
        Map<String, String> maps = new HashMap<String, String>();
        maps.put("out_trade_no", AliPayDateUtils.getOrderNum());
        maps.put("total_amount", "0.01");
        maps.put("subject", "Iphone6 16G");
        maps.put("body", "Iphone6 16G");
        maps.put("product_code", "QUICK_WAP_PAY");
        // 下面两个 参数的 KEY 不要乱写 要和工具类里面对应
        maps.put("ReturnUrl", "http://domain.com/CallBack/return_url.jsp");
        maps.put("NotifyUrl", "http://domain.com/CallBack/notify_url.jsp");
        try {
            AlipayClientFactory ali = new AlipayClientFactory();
            String form = ali.ydAndPc_Pay(maps);
            if (!form.equals("err")) {
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().write(form);// 直接将完整的表单html输出到页面
                response.getWriter().flush();
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
    }

}
