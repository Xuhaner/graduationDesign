package com.servlet;
import com.dao.DAOFactory;
import com.dao.QuestionDao;
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
        if("Register".equals(op)){
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String teamname = request.getParameter("teamname");
            UserDao dao = DAOFactory.getUserDAO();
            try{
                if(dao.register(username,password,teamname)) {
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                    //response.sendRedirect("/login.jsp");
                } else {
                    String errmsg="error1";
                    //response.sendRedirect("/resigter.jsp?errmsg="+errmsg);
                    request.getRequestDispatcher("/resigter.jsp?errmsg="+errmsg).forward(request, response);
                }
            }catch (Exception e){

            }
        }else if("UpdateUser".equals(op)){

        }else if("DeleteUser".equals(op)){


        }else if("Login".equals(op)) {
            HttpSession session = request.getSession();

            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String teamname = request.getParameter("teamname");
            UserDao dao = DAOFactory.getUserDAO();
            try{
                User user=dao.login(username,password,teamname);
                if(username.equals(user.getUsername())) {
                    String chk="true";
                    session.setAttribute("Enter",chk);
                    session.setAttribute("userName",username);
                    session.setAttribute("teamname",teamname);
                    //response.sendRedirect("/index.jsp");
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                } else {
                    String errmsg="error1";
                    //response.sendRedirect("/login.jsp?errmsg="+errmsg);
                    request.getRequestDispatcher("/login.jsp?errmsg="+errmsg).forward(request, response);
                }
            }catch (Exception e){

            }

        }
    }
}
