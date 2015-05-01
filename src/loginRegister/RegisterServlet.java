package loginRegister;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import resultMessage.ResultMessage;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		String userName=new
		        String(request.getParameter("userName").getBytes("ISO-8859-1"),"UTF-8");
		String password1=new
		        String(request.getParameter("password1").getBytes("ISO-8859-1"),"UTF-8");
		String password2=new
		        String(request.getParameter("password2").getBytes("ISO-8859-1"),"UTF-8");
		String name=new
		        String(request.getParameter("name").getBytes("ISO-8859-1"),"UTF-8");
		String sex=new
		        String(request.getParameter("sex").getBytes("ISO-8859-1"),"UTF-8");
		String birth=new
		        String(request.getParameter("year")+"-"+request.getParameter("month")+"-"+request.getParameter("day"));
		String nation=new
		        String(request.getParameter("nation").getBytes("ISO-8859-1"),"UTF-8");
		String edu=new
		        String(request.getParameter("edu").getBytes("ISO-8859-1"),"UTF-8");
		String work=new
		        String(request.getParameter("work").getBytes("ISO-8859-1"),"UTF-8");
		String phone=new
		        String(request.getParameter("phone").getBytes("ISO-8859-1"),"UTF-8");
		String place=new
		        String(request.getParameter("place").getBytes("ISO-8859-1"),"UTF-8");
		String email=new
		        String(request.getParameter("email").getBytes("ISO-8859-1"),"UTF-8");
		
		if(0==userName.length()||0==password1.length()||0==password2.length()
		||0==name.length()||0==sex.length()|| 0==phone.length()||0==email.length())
		{
			ResultMessage.wrong("信息提示", "不允许有空，注册失败");
			response.sendRedirect("register/register.jsp");
		}
		else if(!password2.equals(password1))
		{
			ResultMessage.wrong("信息提示", "两次密码不同，注册失败");
			response.sendRedirect("register/register.jsp");
		}
		else
		{
			Connection con=null;
			Statement stmt=null;
			ResultSet rs=null;
			try{
				String url="jdbc:mysql://localhost:3306/student?useUnicode=true&characterEncoding=gbk";
				DriverManager.registerDriver(new com.mysql.jdbc.Driver());
				con = DriverManager.getConnection(url, "root", "123456");
				stmt = con.createStatement();
				String sqlQuery = "select * from user where userName='"+userName+"'";
				rs = stmt.executeQuery(sqlQuery);
				rs.last();
				int count = rs.getRow(); //查询数据库中时候已经有该用户
				if(count>0)
				{
					ResultMessage.wrong("信息提示", "用户名已存在，注册失败");
					response.sendRedirect("register/register.jsp");
				}
				else
				{
					String sqlInsert="insert into user "+"(userName,password,name,sex,birth,nation,edu,work,phone,place,email)"+
				  "values("+"'"+userName+"'"+","+"'"+password1+"'"+","+"'"+name+"'"+","+"'"+sex+"'"
				  +","+"'"+birth+"'"+","+"'"+nation+"'"+","+"'"+edu+"'"+","+"'"+work+"'"+","+"'"+phone+"'"
				  +","+"'"+place+"'"+","+"'"+email+"'"+")";
					stmt.executeUpdate(sqlInsert);
				}
				rs.close();
				stmt.close();
				con.close();
				ResultMessage.right("信息提示","注册成功！");
				response.sendRedirect("login.jsp");
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}

}
