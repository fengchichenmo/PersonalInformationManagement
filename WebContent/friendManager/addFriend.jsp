<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息管理系统——增加通讯录</title>
</head>
<body bgcolor="CCCFFF">
<div align="center">
	<hr noshade>
	<table border="0" cellspacing="0" cellpadding="0" width="100%" align="center">
		<tr>
			<td width="25%">
				增加联系人
			</td>
			<td width="25%">
				<a href="lookFriend.jsp">查看通讯录</a>
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
	<form action="../AddFriendServlet" method="post">
		<table border="0" cellspacing="0" cellpadding="0" width="60%" align="center">
			<tr>
				<td>姓名</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>电话</td>
				<td><input type="text" name="phone"></td>
			</tr>
			<tr>
				<td>邮箱</td>
				<td><input type="text" name="email"></td>
			</tr>
			<tr>
				<td>工作单位</td>
				<td><input type="text" name="workplace"></td>
			</tr>
			<tr>
				<td>家庭住址</td>
				<td>
					<select name="place" size=1>
						<option value="北京">北京</option>
						<option value="上海">上海</option>
						<option value="南京">南京</option>
						<option value="湖南">湖南</option>
						<option value="湖北">湖北</option>
						<option value="镇江">镇江</option>
						<option value="扬州">扬州</option>
						<option value="福建">福建</option>
						<option value="台湾">台湾</option>
						<option value="其他">其他</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>QQ</td>
				<td><input type="text" name="QQ"></td>
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