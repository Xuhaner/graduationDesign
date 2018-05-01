<%--
  Created by IntelliJ IDEA.
  User: Xuhan
  Date: 2018/3/19
  Time: 20:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <title>登录注册页面</title>
</head>
<body>
<div></div>
<form action="UserManage?op=Login"  method="post"  style="padding-top:-700px;">
  用户名：<input type="text" name="u_name" value=""><br><br>
  密码：  <input type="password" name="u_pwd" value=""><br><br>
  <input type="submit" value="登录" name="login"><input type="reset" value="重置"><br>
</form>
<form action="register.jsp">
  <input type="submit" value="新用户注册">
</form>
</body>
</html>