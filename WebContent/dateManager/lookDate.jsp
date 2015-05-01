<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="dateManager.DateBean" %>
    <%@page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息管理系统——查看日程</title>
</head>
<body bgcolor="#CCCFFF">
	<hr noshade>
	<div align="center">
		<table border="0" cellspacing="0" cellpadding="0" width="100%" align="center">
			<tr>
				<td width="25%">
					<a href="addDate.jsp">增加日程</a>
				</td>
				<td width="25%">
					查看日程
				</td>
				<td width="25%">
					<a href="updateDate.jsp">修改日程</a>
				</td>
				<td width="25%">
					<a href="deleteDate.jsp">删除日程</a>
				</td>
			</tr>
		</table>
	</div>
	<hr noshade>
	<br><br>
	<table border="0" cellspacing="0" cellpadding="0" width="60%" align="center">
		<tr>
			<th height="40">日程时间</th>
			<th height="40">日程内容</th>
		</tr>
	<%
		ArrayList<DateBean> dateList = (ArrayList<DateBean>)session.getAttribute("dateList");
		if(null == dateList || 0 == dateList.size())
		{
	%>
		<tr>
			<td align="center" colspan="2">您还没有任何日程安排</td>
		</tr>
	<%
		}
		else
		{
			for(int i=dateList.size()-1;i>=0;i--)
			{
				DateBean dtb = (DateBean)dateList.get(i);
	%>
		<tr>
			<td><%=dtb.getDate() %></td>
			<td><%=dtb.getThing() %></td>
		</tr>
	<%
			}
		}
	%>
	</table>
</body>
</html>