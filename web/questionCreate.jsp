<%--
  Created by IntelliJ IDEA.
  User: Xuhan
  Date: 2018/3/30
  Time: 23:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>创建问卷</title>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src='js/questionCreate.js' type='text/javascript'></script>
</head>
<body>
    <div id="main">
        <button>添加div</button>
        <div id="problemType">
            <div>题目类型</div>
            <div id="singleSelect">单选题</div>
            <div id="mutiSelect">多选题</div>
            <div id="textInput">文本框</div>
        </div>
        <div id="questionContent">
            <div id="problem">这里是题目</div>
        </div>
    </div>
</body>
</html>
