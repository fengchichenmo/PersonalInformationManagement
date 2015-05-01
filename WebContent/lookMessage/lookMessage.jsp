<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="lookMessage.LookMessageBean" %>
    <%@page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息管理系统——查看个人信息</title>
</head>
<body bgcolor="#CCCFFF">
    <hr noshade>
	<div align="center">
		<table border="0" cellspacing="0" cellpadding="0" width="100%" align="center">
			<tr>
				<td width="33%">
					<a href="updateMessage.jsp">修改个人信息</a>
				</td>
				<td width="33%">
					查看个人信息
				</td>
				<td width="33%">
					<a href="updatePassword.jsp">修改密码</a>
				</td>
			</tr>
		</table>
	</div>
	<hr noshade>
	<br><br>
	<table border="2" cellspacing="0" cellpadding="0" bgcolor="#95BDEF" width="60%" align="center">
	<%
		ArrayList<LookMessageBean> wordList = (ArrayList<LookMessageBean>)session.getAttribute("wordList");
		if(null == wordList || 0 == wordList.size())
		{
			response.sendRedirect("main/bottom.jsp");
		}
		else
		{
			for(int i=wordList.size()-1;i>=0;i--)
			{
				LookMessageBean lmb = (LookMessageBean)wordList.get(i);
	%>
	<tr>
		<td height="30">用户姓名</td>
		<td><%=lmb.getName() %></td>
	</tr>
	<tr>
		<td height="30">用户性别</td>
		<td><%=lmb.getSex() %></td>
	</tr>
	<tr>
		<td height="30">出生日期</td>
		<td><%=lmb.getBirth() %></td>
	</tr>
	<tr>
		<td height="30">用户民族</td>
		<td><%=lmb.getNation() %></td>
	</tr>
	<tr>
		<td height="30">用户学历</td>
		<td><%=lmb.getEdu() %></td>
	</tr>
	<tr>
		<td height="30">用户职称</td>
		<td><%=lmb.getWork() %></td>
	</tr>
	<tr>
		<td height="30">用户电话</td>
		<td><%=lmb.getPhone() %></td>
	</tr>
	<tr>
		<td height="30">家庭住址</td>
		<td><%=lmb.getPlace() %></td>
	</tr>
	<tr>
		<td height="30">邮箱地址</td>
		<td><%=lmb.getEmail() %></td>
	</tr>
	<%
			}
		}
	%>
	</table>
</body>
</html>