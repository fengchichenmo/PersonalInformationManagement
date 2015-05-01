<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息管理系统——注册页面</title>
</head>
<body bgcolor="CCCFFFF">
<table align="center">
	<tr>
		<td colspan="3" align="center">
		<h3><font color="red">请填写以下注册信息</font></h3>
		</td>
	</tr>
	<tr>
		<td>
			<form action="../RegisterServlet" method="post">
				<table border="2" cellspacing="0" cellpadding="0" bgcolor="#AAABBB">
					<tr>
						<td>登录名称</td>
						<td><input type="text" name="userName" size=20></td>
					</tr>
					<tr>
						<td>登录密码</td>
						<td><input type="password" name="password1" size=20></td>
					</tr>
					<tr>
						<td>重复密码</td>
						<td><input type="password" name="password2" size=20></td>
					</tr>
					<tr>
						<td>用户姓名</td>
						<td><input type="text" name="name" size=20></td>
					</tr>
					<tr>
						<td>用户性别</td>
						<td>
							<input type="radio" name="sex" value="男" checked>男
							<input type="radio" name="sex" value="女">女
						</td>
					</tr>
					<tr>
						<td>出生日期</td>
						<td>
							<select name="year" size="1">
							<%
								int beginYear=1980;
							    int endYear=Calendar.getInstance().get(Calendar.YEAR);
							    for(int indexYear=beginYear;indexYear<=endYear;indexYear++)
							    {
							    	
							%>
									<option value="<%=indexYear%>"><%=indexYear %></option>
							<%
							    }
							%>
							</select>年
							<select name="month" size="1">
							<%
								int beginMonth=1;
							    int endMonth=12;
							    for(int indexMonth=beginMonth;indexMonth<=endMonth;indexMonth++)
							    {
							    	
							%>
									<option value="<%=indexMonth%>"><%=indexMonth %></option>
							<%
							    }
							%>
							</select>月
							<select name="day" size="1">
							<%
								int beginDay=1;
							    int endDay=31;
							    for(int indexDay=beginDay;indexDay<=endDay;indexDay++)
							    {
							    	
							%>
									<option value="<%=indexDay%>"><%=indexDay %></option>
							<%
							    }
							%>
							</select>日
						</td>
					</tr>
					<tr>
						<td>用户民族</td>
						<td>
							<input type="radio" name="nation" value="汉族" checked>汉族
							<input type="radio" name="nation" value="苗族" checked>苗族
							<input type="radio" name="nation" value="土家族" checked>土家族
							<input type="radio" name="nation" value="其他" checked>其他							
						</td>
					</tr>
					<tr>
						<td>用户学历</td>
						<td>
							<select name="edu" size="1">
								<option value="博士">博士</option>
								<option value="硕士">硕士</option>
								<option value="本科">本科</option>
								<option value="专科">专科</option>
								<option value="高中">高中</option>
								<option value="初中">初中</option>
								<option value="小学">小学</option>
								<option value="其他">其他</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>用户职称</td>
						<td>
							<select name="work" size="1">
								<option value="软件开发工程师">软件开发工程师</option>
								<option value="软件测试工程师">软件测试工程师</option>
								<option value="教师">教师</option>
								<option value="学生">学生</option>
								<option value="经理">经理</option>
								<option value="职员">职员</option>
								<option value="老板">老板</option>
								<option value="公务员">公务员</option>
								<option value="其他">其他</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>用户电话</td>
						<td>
							<input type="text" name="phone" size="20">
						</td>
					</tr>
					<tr>
						<td>家庭住址</td>
						<td>
							<select name="place" size="1">
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
						<td>邮箱地址</td>
						<td>
							<input type="text" name="email" size="20">
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
		</td>
	</tr>
</table>

</body>
</html>