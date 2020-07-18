package com.bjpowernode.crm.web.filter;

import com.bjpowernode.crm.settings.domain.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author LNL
 * @date 2020/7/13 9:47
 */
public class LoginFilter implements Filter {
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

        System.out.println("进入到验证有没有登录过的过滤器");
        /*
            ServletRequest和ServletResponse不能做HttpServletRequest的事情，必须转换为具体的子类
         */
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String path = request.getServletPath();
        //允许通过的资源
        if ("/login.jsp".equals(path) || "/settings/user/login.do".equals(path)) {
            chain.doFilter(req, resp);

            //不允许通过的资源
        } else {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");

            //如果user不为null，说明登录过
            if (user != null) {
                chain.doFilter(req, resp);
            } else {
                //重定向到登录页
            /*
                重定向的路径怎么写？
                关于转发和充电丁香的路径的写法
                转发
                    使用的一种特殊的绝对路径的使用方式，这种绝对路径前面不加/项目名，称为内部路径
                    /login.jsp

                重定向：
                    使用的传统绝对路径的写法，前面必须以/项目名开头，后面跟具体的资源路径
                    /crm/login.jsp

                为什么使用重定向
                    使用转发：路径会停留在老路径上，而不是跳转到之后的最新资源的路径上

                request.getContextPath():取得     /项目名
             */
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            }
        }


    }
}
