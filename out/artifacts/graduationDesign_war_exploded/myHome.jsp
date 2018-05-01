
<%--
  Created by IntelliJ IDEA.
  User: Xuhan
  Date: 2018/3/22
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>我的主页</title>
    <link rel="stylesheet" href="css/myHome.css">
    <script src='js/myHome.js' type='text/javascript'></script>
</head>
<body>
<a href="Searchall">查看所有用户</a>
<div id="topBar">
    <a href="index.jsp">这是一个问卷调查系统</a>
    ${uname}
    <a href="updateMy.jsp">修改个人资料</a>
    <a href="javascript:parent.location.href='logout.jsp'" onClick="return confirm('确定要退出吗？');">安全退出</a>
</div>
<div id="main">
    <div id="leftBar">
        <div id="creatQuestion"><a href="questionCreate.jsp">创建问卷</a></div>
        <div id="manageQuestion">管理问卷</div>
        <div id="myQuestion">我的问卷</div>
        <div id="manageTeam">团队管理</div>
    </div>
    <div id="rightManageQ">
        <a href="analyzeQuestion.jsp">分析问卷</a>
    </div>
    <div id="rightMy">
        <a href="question.jsp">要填的问卷</a>
    </div>
    <div id="rightManageT">
        <a href="teamManage.jsp">这里是团队管理</a>
    </div>
</div>
</body>
</html>