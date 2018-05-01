
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>用户注册</title>
</head>
<body >
<form name="from1" action="UserManage?op=AddUser" method="post" onSubmit="return Check();">
    输入用户名:<input name="u_name" type="text"><br><br>
    输入密码:<input name="u_pwd" type="password"><br><br>
    输入email:<input name="u_email" type="text"><br><br>
    团队名字（选填）：<input name="u_email" type="text"><br><br>
    <input type="reset" value="重置"><input type="submit" value="注册">
</form>
</body>
</html>