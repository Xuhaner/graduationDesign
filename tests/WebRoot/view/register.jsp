<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	//清IE缓存，防止用户注销后点IE后退进入系统
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<%
	String errmsg = request.getParameter("errmsg");
	request.getSession(true);
	String chk = "false";
	session.setAttribute("Enter", chk);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>用户登录</TITLE>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<LINK href="images/public.css" type=text/css rel=stylesheet>
<LINK href="images/login.css" type=text/css rel=stylesheet>
<script>
    
	function OK_onClick() {
		if (chkForm()) {
			document.all("logform").submit();
			return;
		}
	}

	function chkForm() {
		var chkName = document.all("username").value;
		var chkPassword = document.all("password").value;

		if (chkName == "" || chkName.length == 0) {
			alert("请输入用户名。");
			document.all("username").focus();
			return false;
		} else if (chkPassword == "" || chkPassword.length == 0) {
			alert("请输入密码。");
			document.all("password").focus();
			return false;
		}
		return true;
	}

	//通过点击回车键提交表单
	function entersubmit(e) {
		var charCode = (navigator.appName == "Netscape") ? e.which : e.keyCode;
		if (charCode == 13) {
			OK_onClick();
		}
	}

	//重置
	function resetForm() {
		document.all("logform").reset();
		return;
	}
</script>
<META content="MSHTML 6.00.2900.5848" name=GENERATOR>
</HEAD>
<BODY>
<DIV id=div1>
<form id="logform" action="user/registerAction.jsp" method="post" height="800px" onKeyDown="entersubmit(event)">
  <TABLE id=login height="100%" cellSpacing=0 cellPadding=0 width="200px" align=center>
    <TBODY>
      <TR id=main>
        <TD>
          <TABLE height="300px" cellSpacing=0 cellPadding=0 width="100%">
            <TBODY>
              <TR height=30>
                <TD width=100>&nbsp;</TD>
                <TD>&nbsp;
                <font size=4 style="font-size:12px;color: red;">
                </font>
                </TD>
              </TR>
				<TR height=40>
                <TD>用户名：</TD>
                <TD>
                  <INPUT class=textbox id=txtUserName name=username>
                </TD>
              </TR>
              <TR height=40>
                <TD>密　码：</TD>
                <TD>
                  <INPUT class=textbox id=txtUserPassword type=password name=password>
                </TD>
              </TR>
              <TR height=40>
                <TD>团      队：</TD>
                <TD>
                  <INPUT class=textbox id=txtTeamname name=teamname>
                </TD>
              </TR>
              <TR height=40  align=center >
                <TD colspan ="2">
                  <INPUT class=btnLogin id=btnRegister type=button value="注     册" name=btnRegister onclick="javascript:OK_onClick()">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                  <INPUT class=btnLogin type=reset value="重   置" name=reset>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                </TD>
              </TR>
            </TBODY>
          </TABLE>
        </TD>
      </TR>
    </TBODY>
  </TABLE>
  </form>
</DIV>
<DIV id=div2 style="DISPLAY: none"></DIV>
</CONTENTTEMPLATE>
</BODY>
</HTML>
