package com.bjpowernode.crm.settings.web.controller;

import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.settings.service.UserService;
import com.bjpowernode.crm.settings.service.impl.UserServiceImpl;
import com.bjpowernode.crm.utils.MD5Util;
import com.bjpowernode.crm.utils.PrintJson;
import com.bjpowernode.crm.utils.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author LNL
 * @date 2020/7/12 10:00
 */
public class UserController extends HttpServlet {



    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("进入到用户控制器");

        String path = request.getServletPath();

        if("/settings/user/login.do".equals(path)){

            login(request,response);

        }else if("/settings/user/xxx.do".equals(path)){

        }

    }

    private void login(HttpServletRequest request,HttpServletResponse response){
        System.out.println("进入验证登录操作");
        //未来业务层开发，同一使用代理类形态的接口对象
        UserService userService = (UserService) ServiceFactory.getService(new UserServiceImpl());

        String loginAct = request.getParameter("loginAct");
        String loginPwd = request.getParameter("loginPwd");

        //将密码的铭文形式转换为MD5的密文形式
        loginPwd = MD5Util.getMD5(loginPwd);

        //接收浏览器的ip地址
        String ip = request.getRemoteAddr();
        System.out.println("ip-----------------------"+ip);



        try {
            User user = userService.login(loginAct,loginPwd,ip);

            request.getSession().setAttribute("user",user);
            //如果程序执行到此处，说明业务层没有为controller抛出任何异常
            //表示登录成功

            /*
                {"success":"true"}
             */
            PrintJson.printJsonFlag(response,true);


        }catch (Exception e){
            e.printStackTrace();
            //一旦程序执行了catch快的信息，说明业务层为我们验证登录失败，为controller抛出了异常
            //表示登录失败
            /*
                {"success":"false","msg",?}
             */
            String msg = e.getMessage();
            /*
                作为controller，需要为ajax请求提供多项 信息
                可以有两种手段
                    (1)将多项信息打包成为map，将map解析为json串
                    (2)创建一个VO
                            private boolean success
                            private String msg;
                    如果对于展现的信息将来还会大量的使用，创建一个vo类
                    如果对于展现的信息只有在这个需求中能够使用，那么使用map
             */
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("success",false);
            map.put("msg",msg);
            PrintJson.printJsonObj(response,map);

        }


    }
}
