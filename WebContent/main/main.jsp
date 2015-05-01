<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="loginRegister.LoginBean" %>
    <%@page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息管理系统——主页面</title>

</head>
<%
	String userName = null;
	//获取LoginServlet.java中保存在session对象中的数据
	ArrayList<LoginBean> login=(ArrayList<LoginBean>)session.getAttribute("login");
	if(null == login|| 0 == login.size())
	{
		response.sendRedirect("../login.jsp");
	}
	else
	{
		for(int i=login.size()-1;i>=0;i--)
		{
			LoginBean lb= (LoginBean)login.get(i);
			userName = lb.getUserName();
		}
	}
%>
<frameset cols="20%,*" framespacing="0" border="0" frameborder="no">
 	<frame src="left.jsp" name="left" scrolling="no">
 	<frameset rows="20%,10%,*">
 		<frame src="top.jsp" name="top" scrolling="no">
 		<frame src="middle.jsp?userName=<%=userName%>" name="toop" scrolling="no">
 		<frame src="bottom.jsp" name="main">
 	</frameset>
</frameset>
</html>