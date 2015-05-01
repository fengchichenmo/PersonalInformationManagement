package lookMessage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import loginRegister.LoginBean;
import resultMessage.ResultMessage;

/**
 * Servlet implementation class UpatePasswordMessage
 */
public class UpdatePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePasswordServlet() {
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
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String password1=new
		        String(request.getParameter("password1").getBytes("ISO-8859-1"),"UTF-8");
		String password2=new
		        String(request.getParameter("password2").getBytes("ISO-8859-1"),"UTF-8");
		
		if(0 == password1.length() || 0 == password2.length())
		{
			ResultMessage.wrong("信息提示", "不允许有空，修改失败");
			response.sendRedirect("lookMessage/updatePassword.jsp");
		}
		else if(!password1.equals(password2))
		{
			ResultMessage.wrong("信息提示", "两次密码不同，修改失败");
			response.sendRedirect("lookMessage/updatePassword.jsp");
		}
		else 
		{
			try{
				//创建数据库连接
				Connection con=null;
				Statement stmt=null;
				String url="jdbc:mysql://localhost:3306/student?useUnicode=true&characterEncoding=gbk";
				DriverManager.registerDriver(new com.mysql.jdbc.Driver());
				con = DriverManager.getConnection(url, "root", "123456");
				stmt = con.createStatement();
				
				//获取登录名
				String userName = "";
				LoginBean lb= null;
				HttpSession session = request.getSession();
				ArrayList<LoginBean> login=(ArrayList<LoginBean>)session.getAttribute("login");
				if(null == login|| 0 == login.size())
				{
					response.sendRedirect("login.jsp");
				}
				else
				{
					for(int i=login.size()-1;i>=0;i--)
					{
						lb= (LoginBean)login.get(i);
						userName = lb.getUserName();
					}
				}
				//构建查询语句
				String sqlUpdate = "update user set password='"+password1+"' where userName='"+userName+"'";
				stmt.executeUpdate(sqlUpdate);
				
				lb.setPassword(password1);
				
				session.setAttribute("login", login);
				
				stmt.close();
				con.close();
				ResultMessage.right("信息提示", "修改信息成功");
				response.sendRedirect("lookMessage\\lookMessage.jsp");
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
	}

}
