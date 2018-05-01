package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.UserDao;
import com.dao.UserDaoImpl;
import com.entity.User;

public class LoginServlet extends HttpServlet {  //需要继承HttpServlet  并重写doGet  doPost方法
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);  //将信息使用doPost方法执行   对应jsp页面中的form表单中的method
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        String u_name = request.getParameter("u_name"); //得到jsp页面传过来的参数
        String u_pwd = request.getParameter("u_pwd");

        UserDao ud = new UserDaoImpl();

        if(ud.login(u_name, u_pwd)){

            session.setAttribute("uname", "欢迎用户"+u_name); //向request域中放置信息
            //response.sendRedirect("/myHome.jsp");
            request.getRequestDispatcher("/myHome.jsp").forward(request, response);//转发到成功页面
        }else{
            response.sendRedirect("login.jsp"); //重定向到登录页面
        }
    }

}