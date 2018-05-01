package com.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserDao;
import com.dao.UserDaoImpl;
import com.entity.User;

public class RegisterServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String u_name = request.getParameter("u_name"); //获取jsp页面传过来的参数
        String u_pwd = request.getParameter("u_pwd");
        String u_team = request.getParameter("u_team");
        String u_email = request.getParameter("u_email");

        if(u_team.equals("")){
            u_team = "none";
        }

        User user = new User(); //实例化一个对象，组装属性
        user.setU_name(u_name);
        user.setU_pwd(u_pwd);
        user.setU_team(u_team);
        user.setU_email(u_email);

        UserDao ud = new UserDaoImpl();

        try {
            if(ud.register(user)){
                request.setAttribute("username", u_name);  //向request域中放置参数
                //request.setAttribute("uname", "注册成功");
                request.getRequestDispatcher("/login.jsp").forward(request, response);  //转发到登录页面
            }else{

                response.sendRedirect("fail.jsp");//定向至错误页面
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}