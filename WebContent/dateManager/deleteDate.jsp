<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
					<a href="lookDate.jsp">查看日程</a>
				</td>
				<td width="25%">
					<a href="updateDate.jsp">修改日程</a>
				</td>
				<td width="25%">
					删除日程
				</td>
			</tr>
		</table>
	</div>
	<hr noshade>
	<br><br>
	<form action="../DeleteDateServlet" method="post">
		<table border="0" cellspacing="0" cellpadding="0" width="60%" align="center">
			<tr>
				<td height="30" width="50%" align="right">日程时间</td>
				<td width="50%">
				20<input type="text" name="year" size="1" value="">年
				  <input type="text" name="month" size="1" value="">月
				  <input type="text" name="day" size="1" value="">日
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