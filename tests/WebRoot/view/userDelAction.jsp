<%@page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="check.jsp"%>
<%@page import="com.vote.service.*"%>
<%
	String getid = request.getParameter("uid");
	int uid = Integer.parseInt(getid.trim());
	boolean flag = AdminService.delUser(uid);
    response.sendRedirect("teamUserList.jsp");
%>