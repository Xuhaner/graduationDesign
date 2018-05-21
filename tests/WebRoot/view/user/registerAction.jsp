<%@page contentType="text/html;charset=UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@page import="com.vote.service.*"%>
<%@page import="com.vote.bean.*"%>
<%

 String username = request.getParameter("username");
 String password = request.getParameter("password");
 String teamname = request.getParameter("teamname");
 AdminService as=new AdminService();
 if(as.register(username,password,teamname)) {
    response.sendRedirect("../login.jsp");
 } else {
  String errmsg="error1";
  response.sendRedirect("../resigter.jsp?errmsg="+errmsg);
 }
%>