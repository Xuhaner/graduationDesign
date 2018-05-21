<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="check.jsp"%>
<%@page import="com.vote.service.*"%>
<%@page import="com.vote.bean.*"%>
<%@page import="java.util.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<%
	String teamname =  (String)session.getAttribute("teamname");
	List userList=AdminService.ListUser(teamname);
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	String contextPath = request.getContextPath();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>问卷管理</title>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">
<script language="javascript">
//全选
function selAll()
{
	var ids = document.getElementsByName("id");
	var len = ids.length;
	for(var i=0;i<len;i++)
	{
		if(!ids[i].checked)
		{
			ids[i].checked=true;
		}
	}
}

//取消全选
function noSelAll()
{
	var ids = document.getElementsByName("id");
	var len = ids.length;
	for(var i=0;i<len;i++)
	{
		if(ids[i].checked)
		{
			ids[i].checked=false;
		}
	}
}

//获取选中的radio
function getCheckedRadio(radioObj)
{
   if(typeof radioObj=="undefined")
      return null;
   else if(typeof radioObj.length=="undefined")
      return radioObj;
   else
   {
      for(var i=0;i<radioObj.length;i++)
      {
         if(radioObj[i].checked)
            return radioObj[i];
      }
   }
   return null;
}

//获取radio值
function getRadioValue(radioObj)
{
   if(typeof radioObj=="undefined")
      return "";
   else if(typeof radioObj.length=="undefined")
      return radioObj.value;
   else
   {
      for(var i=0;i<radioObj.length;i++)
      {
         if(radioObj[i].checked)
            return radioObj[i].value;
      }
   }
   return "";
}

//获取CheckBox值
function getCheckBoxValue(checkboxObj,cnt)
{
   return getCheckBoxProperty(checkboxObj,cnt,'value');
}

//获取CheckBox某个属性值
function getCheckBoxProperty(checkboxObj,cnt,propertyName)
{
   var sValue = null;
   if(typeof cnt=="undefined") cnt=2;
   if(typeof checkboxObj=='undefined')
   {
      alert("没有可以选择的记录！");
      return "";
   }
   else if(typeof checkboxObj.length=="undefined")
   {
      checkboxObj.checked=true;
      return checkboxObj[propertyName];
   }
   else
   {
      for(var i=0;i<checkboxObj.length;i++)
      {
         if(checkboxObj[i].checked)
         {
            if(sValue==null) sValue='';
            sValue += ";"+checkboxObj[i][propertyName];
         }
      }
   }
   if(sValue==null)
   {
   	  alert("请至少选择一条记录！");
   	  return '';
   }
   if(sValue.length>0 && sValue.charAt(0)==';')
      sValue = sValue.substring(1);
   if(cnt==1 && sValue.indexOf(';')>-1)
   {
      alert("只能选择一条记录！");
      return "";
   }

   return sValue;
}

//新增用户
function Add_onClick()
{
	window.self.location="./resigter.jsp";
}

</script>
</head>
<body leftmargin="8" topmargin="8" background='skin/images/allbg.gif'>

<!--  快速转换位置按钮  -->
<table width="98%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
<tr>
 <td height="26" background="skin/images/newlinebg3.gif">
  <table height="98%" border="0" cellspacing="0" cellpadding="0">
  <tr>
  <td align=left>
    <input type='button' class="coolbg np" onClick="Add_onClick();" value='添加用户' />
 </td>
 </tr>
</table>
</td>
</tr>
</table>
  
<!--  内容列表   -->
<form name="fm" action="" method="post">
<input type="hidden" name="doType" value="" />
<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="10" background="skin/images/tbg.gif"> &nbsp;内容列表&nbsp;</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="10%">序号</td>
	<td width="60%">用户名</td>
	<td width="30%">操作</td>
</tr>
<%
if(userList.size()==0){
%>
<tr align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
<td height="24" colspan="10" background="skin/images/tbg.gif">no data&nbsp;</td>
</tr>
<%}%>
<%
if(userList.size()>0){
	for(int i=0;i<userList.size();i++){
		AdminBean user=(AdminBean)userList.get(i);
		int uid=user.getId();
%>
<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
	<td><%=(i+1)%></td>
	<td><%=user.getUsername()%></td>
	<td><a href="userDelAction.jsp?uid=<%=uid%>">删除</a>
</tr>
<%
  }
}
%>

<tr bgcolor="#FAFAF1">
<td height="28" colspan="10">
</td>
</tr>
<tr align="right" bgcolor="#EEF4EA">
	<td height="36" colspan="10" align="center"><!--翻页代码 --></td>
</tr>
</table>
</form>
</body>
</html>