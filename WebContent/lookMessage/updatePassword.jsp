<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="loginRegister.LoginBean" %>
    <%@page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Page</title>
</head>
<body bgcolor="#CCCFFF">
	<hr noshade>
	<div align="center">
		<table border="0" cellspacing="0" cellpadding="0" width="100%" align="center">
			<tr>
				<td width="33%">
					<a href="lookMessage.jsp">查看个人信息</a>
				</td>
				<td width="33%">
					<a href="updageMessage.jsp">修改个人信息</a>
				</td>
				<td width="33%">
					修改密码
				</td>
			</tr>
		</table>
	</div>
	<hr noshade>
	<br><br>
	<form action="../UpdatePasswordServlet" method="post">
		<table border="2" cellspacing="0" cellpading="0" bgcolor="#CCCFFF" width="60%" align="center">
			<%
				ArrayList<LoginBean> login=(ArrayList<LoginBean>)session.getAttribute("login");
				if(null == login || 0 == login.size())
				{
					response.sendRedirect("../main/bottom.jsp");
				}
				else
				{
					for(int i=login.size()-1;i>=0;i--)
					{
						LoginBean lb= login.get(i);
			%>
			<tr>
				<td height="30"> 用户密码</td>
				<td><input type="password" name="password1" value="<%=lb.getPassword()%>"></td>
			</tr>
			<tr>
				<td height="30"> 重复密码</td>
				<td><input type="password" name="password2" value="<%=lb.getPassword()%>"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="确定" size=12>&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="reset" value="清除" size=12>
				</td>
			</tr>
			<%
					}
				}
			%>
		</table>
	</form>
</body>
</html>