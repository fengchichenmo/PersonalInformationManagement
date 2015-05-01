<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<form action="../UpdateFriendServlet" method="post">
		<table border="0" cellspacing="0" cellpadding="0" width="60%" align="center">
			<tr align="center">
				<td align="center" height="130">
					<p>请输入要修改人的姓名</p>
					姓名<input type="text" name="name"><br>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="确定" size="12">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="reset" value="清除" size="12">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>