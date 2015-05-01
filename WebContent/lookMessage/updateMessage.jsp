<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="lookMessage.LookMessageBean" %>
    <%@page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息管理系统——查看</title>
</head>
<body bgcolor="#CCCFFF">
	<hr noshade>
	<div align="center">
		<table border="0" cellspacing="0" cellpadding="0" width="100%" align="center">
			<tr>
				<td width="33%">修改个人信息</td>
				<td width="33%">
					<a href="lookMessage.jsp">查看个人信息</a>
				</td>
				<td width="33%">
					<a href="updatePassword.jsp">修改密码</a>
				</td>
			</tr>
		</table>
	</div>
	<hr noshade>
	<br><br>
	<form action="../UpdateMessageServlet" method="post">
		<table border="2" cellspacing="0" cellpadding="0" width="60%" align="center" bgcolor="#95BDEF">
		<%
		ArrayList<LookMessageBean> wordList = (ArrayList<LookMessageBean>)session.getAttribute("wordList");
		if(null == wordList || 0 == wordList.size())
		{
			response.sendRedirect("../main/bottom.jsp");
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
		<td>
			<select name="edu" size="1">
				<%
				//博士 
				if("博士".equals(lmb.getEdu()))
				{
				%>
				<option value="博士" selected>博士</option>
				<%
				}
				else
				{ 
				%>
				<option value="博士">博士</option>
				<%
				}
				%>
				
				<%
				//硕士
				if("硕士".equals(lmb.getEdu()))
				{
				%>
				<option value="硕士" selected>硕士</option>
				<%
				}
				else
				{ 
				%>
				<option value="硕士">硕士士</option>
				<%	
				} 
				%>
				
				<% 
				//本科
				if("本科".equals(lmb.getEdu()))
				{
				%>
				<option value="本科" selected>本科</option>
				<%
				}
				else
				{ %>
				<option value="本科">本科</option>
				<%	
				} %>
				
				<% 
				//专科
				if("专科".equals(lmb.getEdu()))
				{
				%>
				<option value="专科" selected>专科</option>
				<%
				}
				else
				{ %>
				<option value="专科">专科</option>
				<%	
				} %>
				
				<% 
				//高中
				if("高中".equals(lmb.getEdu()))
				{
				%>
				<option value="高中" selected>高中</option>
				<%
				}
				else
				{ %>
				<option value="高中">高中</option>
				<%	
				} %>
				
					<% 
				//初中
				if("初中".equals(lmb.getEdu()))
				{
				%>
				<option value="初中" selected>初中</option>
				<%
				}
				else
				{ %>
				<option value="初中">初中</option>
				<%	
				} %>
				
				<% 
				//小学
				if("小学".equals(lmb.getEdu()))
				{
				%>
				<option value="小学" selected>小学</option>
				<%
				}
				else
				{ %>
				<option value="小学">小学</option>
				<%	
				} %>
				
				<% 
				//其他
				if("其他".equals(lmb.getEdu()))
				{
				%>
				<option value="其他" selected>其他</option>
				<%
				}
				else
				{ %>
				<option value="其他">其他</option>
				<%	
				} %>
			</select>
		</td>
	</tr>
	<tr>
		<td height="30">用户职称</td>
		<td>
			<select name="work" size="1">
				<% 
				//软件开发工程师
				if("软件开发工程师".equals(lmb.getWork()))
				{
				%>
				<option value="软件开发工程师" selected>软件开发工程师</option>
				<%
				}
				else
				{ %>
				<option value="软件开发工程师">软件开发工程师</option>
				<%	
				} %>
				
				<% 
				//软件测试工程师
				if("软件测试工程师".equals(lmb.getWork()))
				{
				%>
				<option value="软件测试工程师" selected>软件测试工程师</option>
				<%
				}
				else
				{ %>
				<option value="软件测试工程师">软件测试工程师</option>
				<%	
				} %>
				
				<% 
				//教师
				if("教师".equals(lmb.getWork()))
				{
				%>
				<option value="教师" selected>教师</option>
				<%
				}
				else
				{ %>
				<option value="教师">教师</option>
				<%	
				} %>
				
				<% 
				//学生
				if("学生".equals(lmb.getWork()))
				{
				%>
				<option value="学生" selected>学生</option>
				<%
				}
				else
				{ %>
				<option value="学生">学生</option>
				<%	
				} %>
				
				<% 
				//职员
				if("职员".equals(lmb.getWork()))
				{
				%>
				<option value="职员" selected>职员</option>
				<%
				}
				else
				{ %>
				<option value="职员">职员</option>
				<%	
				} %>
				
				<% 
				//经理
				if("经理".equals(lmb.getWork()))
				{
				%>
				<option value="经理" selected>经理</option>
				<%
				}
				else
				{ %>
				<option value="经理">经理</option>
				<%	
				} %>
				
				<% 
				//老板
				if("".equals(lmb.getWork()))
				{
				%>
				<option value="老板" selected>老板</option>
				<%
				}
				else
				{ %>
				<option value="老板">老板</option>
				<%	
				} %>
				
				<% 
				//公务员
				if("公务员".equals(lmb.getWork()))
				{
				%>
				<option value="公务员" selected>公务员</option>
				<%
				}
				else
				{ %>
				<option value="公务员">公务员</option>
				<%	
				} %>
				
				<% 
				//其他
				if("其他".equals(lmb.getWork()))
				{
				%>
				<option value="其他" selected>其他</option>
				<%
				}
				else
				{ %>
				<option value="其他">其他</option>
				<%	
				} %>
				
			</select>
		</td>
	</tr>
	<tr>
		<td height="30">用户电话</td>
		<td><input type="text" name="phone" value="<%=lmb.getPhone()%>"></td>
	</tr>
	<tr>
		<td height="30">家庭住址</td>
		<td><%=lmb.getPlace() %></td>
	</tr>
	<tr>
		<td height="30">邮箱地址</td>
		<td><input type="text" name="email" value="<%=lmb.getEmail()%>"></td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<input type="submit" value="确定" size="12">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="reset" value="清除" value="清除" size="12">
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