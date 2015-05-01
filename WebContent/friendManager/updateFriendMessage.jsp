<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="friendManager.FriendBean" %>
    <%@page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息管理系统——修改通讯录</title>
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
					<a href="lookFriend.jsp">查看通讯录</a>
				</td>
				<td width="25%">
					修改联系人
				</td>
				<td width="25%">
					<a href="deleteFriend.jsp">删除联系人</a>
				</td>
			</tr>
		</table>
	</div>
	<hr noshade>
	<br><br>
	<form action="../UpdateFriendMessageServlet" method="post">
		<table border="0" cellspacing="0" cellpadding="0" width="60%" align="center">
		<%
			ArrayList<FriendBean> modFriendList = (ArrayList<FriendBean>)session.getAttribute("modFriendList");
			if(null == modFriendList || 0 == modFriendList.size())
			{
				response.sendRedirect("lookFriend.jsp");
			}
			else
			{
				for(int i=modFriendList.size()-1;i>=0;i--)
				{
					FriendBean fb = (FriendBean)modFriendList.get(i);
		%>
			<tr>
				<td>姓名</td>
				<td><input type="text" name="name" value="<%=fb.getName()%>"></td>
			</tr>
			<tr>
				<td>电话</td>
				<td><input type="text" name="phone" value="<%=fb.getPhone()%>"></td>
			</tr>
			<tr>
				<td>邮箱</td>
				<td><input type="text" name="email" value="<%=fb.getEmail()%>"></td>
			</tr>
			<tr>
				<td>工作单位</td>
				<td><input type="text" name="workplace" value="<%=fb.getWorkplace()%>"></td>
			</tr>
			<tr>
				<td>家庭住址</td>
				<td><input type="text" name="place" value="<%=fb.getPlace()%>"></td>
			</tr>
			<tr>
				<td>QQ</td>
				<td><input type="text" name="QQ" value="<%=fb.getQQ()%>"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="确定" size="12">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="reset" value="清除" size="12">
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