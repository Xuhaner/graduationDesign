package com.servlet;
import com.dao.DAOFactory;
import com.dao.UserDao;
import com.dao.UserDaoImpl;
import com.entity.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class UserManage extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String op = request.getParameter("op");
        if("AddUser".equals(op)){
            String u_name=request.getParameter("u_name");
            String u_pwd=request.getParameter("u_pwd");
            String u_email=request.getParameter("u_email");
            String u_team=request.getParameter("u_team");


            UserDao ud = DAOFactory.getUserDAO();
            User user = new User();

            user.setU_name(u_name);
            user.setU_pwd(u_pwd);
            user.setU_email(u_email);
            user.setU_team(u_team);

            boolean result = false;
            try {
                result = ud.register(user);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if(result){
                response.sendRedirect("login.jsp");
            }else{
                response.sendRedirect("fail.jsp");
            }
        }else if("UpdateUser".equals(op)){
            String u_name=request.getParameter("u_name");
            String u_pwd=request.getParameter("u_pwd");
            String u_email=request.getParameter("u_email");
            String u_team=request.getParameter("u_team");


            UserDao ud = new UserDaoImpl();
            User user = new User();

            user.setU_name(u_name);
            user.setU_pwd(u_pwd);
            user.setU_email(u_email);
            user.setU_team(u_team);

           // boolean result = ud.update(user);

            if(true){
                response.sendRedirect("myHome.jsp");
            }else{
                response.sendRedirect("fail.jsp");
            }
        }else if("DeleteUser".equals(op)){
            int u_id = Integer.parseInt(request.getParameter("u_id"));
            UserDao ud = DAOFactory.getUserDAO();

            boolean result = ud.delete(u_id);

            if(result){
                response.sendRedirect("UserList.jsp");
            }else{
                response.sendRedirect("fail.jsp");
            }

        }else if("Login".equals(op)) {
            HttpSession session = request.getSession();

            String u_name = request.getParameter("u_name"); //得到jsp页面传过来的参数
            String u_pwd = request.getParameter("u_pwd");

            UserDao ud = new UserDaoImpl();

            if (ud.login(u_name, u_pwd)) {

                session.setAttribute("uname", "欢迎用户" + u_name); //向request域中放置信息
                //response.sendRedirect("/myHome.jsp");
                request.getRequestDispatcher("/myHome.jsp").forward(request, response);//转发到成功页面
            } else {
                response.sendRedirect("login.jsp"); //重定向到登录页面
            }
        }
    }
}
