package com.bjpowernode.crm.web.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author LNL
 * @date 2020/7/12 22:11
 */
public class EncodingFilter implements Filter {
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

        System.out.println("进入到过滤字符编码的过滤器");

        //过滤post请求中文参数乱码
        req.setCharacterEncoding("UTF-8");
        //过滤器响应流中文乱码
        resp.setContentType("text/html;charset=utf-8");

        //将骑牛方形
        chain.doFilter(req,resp);

    }
}
