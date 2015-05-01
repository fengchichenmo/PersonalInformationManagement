<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="friendManager.FriendBean" %>
    <%@page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息管理系统——查看通讯录</title>
</head>
<body bgcolor="#CCCFFF">
	<hr noshade>
	<div align="center">
		<table border="0" cellspacing="0" cellpadding="0" width="100%" align="center">
			<tr>
				<td width="25%">
					<a href="addFriend.jsp">增加联系人</a>
				</td>
				<td width="25%">
					查看通讯录
				</td>
				<td width="25%">
					<a href="updateFriend.jsp">修改联系人</a>
				</td>
				<td width="25%">
					<a href="deleteFriend.jsp">删除联系人</a>
				</td>
			</tr>
		</table>
	</div>
	<hr noshade>
	<br><br>
	<table border="0" cellspacing="0" cellpadding="0" width="60%" align="center">
		<tr>
			<th height="30">姓名</th>
			<th height="30">电话</th>
			<th height="30">邮箱</th>
			<th height="30">职称</th>
			<th height="30">家庭住址</th>
			<th height="30">QQ</th>
		</tr>
	<%
		ArrayList<FriendBean> friendList = (ArrayList<FriendBean>)session.getAttribute("friendList");
		if(null == friendList || 0 == friendList.size())
		{
	%>
		<tr>
			<td align="center" colspan="6">您还没有任何联系人</td>
		</tr>
	<%
		}
		else
		{
			for(int i=friendList.size()-1;i>=0;i--)
			{
				FriendBean fb = (FriendBean)friendList.get(i);
	%>
		<tr>
			<td><%=fb.getName() %></td>
			<td><%=fb.getPhone() %></td>
			<td><%=fb.getEmail() %></td>
			<td><%=fb.getWorkplace() %></td>
			<td><%=fb.getPlace() %></td>
			<td><%=fb.getQQ() %></td>
		</tr>
	<%
			}
		}
	%>
	</table>
</body>
</html>