<%--
  Created by IntelliJ IDEA.
  User: Xuhan
  Date: 2018/3/30
  Time: 23:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>修改个人资料</title>
</head>
<body>
<form action="UpdateMyServlet" method="post" style="padding-top:-700px;">
    输入用户名:<input name="name" type="text"><br><br>
    输入密码:<input name="pwd" type="password"><br><br>
    输入email:<input name="email" type="text"><br><br>
    输入team名字:<input name="team" type="text"><br><br>
    <input type="reset" value="重置"><input type="submit" value="修改">
    <a href="UpdateMyServlet?u_id=2">删除</a>
</form>
</body>
</html>
