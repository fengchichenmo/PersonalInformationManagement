package loginRegister;

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

import resultMessage.ResultMessage;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		String password=new
		        String(request.getParameter("password").getBytes("ISO-8859-1"),"UTF-8");
		if(("").equals(userName))
		{
			ResultMessage.wrong("信息提示","用户名不能为空");
			response.sendRedirect("login.jsp");
		}
		else if("".equals(password))
		{
			ResultMessage.wrong("信息提示","密码不能为空，登录失败");
			response.sendRedirect("login.jsp");
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
				String sql = "select * from user where userName='"+userName+"'";
				rs = stmt.executeQuery(sql);
				if((rs.next()))
				{
					if(userName.equals(rs.getString("userName")))
					{
						if(password.equals(rs.getString("password")))
						{
							//实例化保存个人信息的JavaBean
							LoginBean jb = new LoginBean();
							jb.setUserName(userName);
							jb.setPassword(password);
							//获取session对象
							HttpSession session=request.getSession();
							ArrayList<LoginBean> login=new ArrayList<LoginBean>();//实例化列表对象
							login.add(jb);//把个人信息保存到列表中
							//把列表保存到session对象中，以便在别的页面中获取个人信息
							session.setAttribute("login", login);
							ArrayList<LoginBean> loginnew = (ArrayList<LoginBean>)session.getAttribute("login");
							response.sendRedirect("main/main.jsp");
						}
						else
						{
							ResultMessage.wrong("信息提示","密码错误，登录失败");
							response.sendRedirect("login.jsp");
						}
					}
					else
					{
						ResultMessage.wrong("信息提示","该用户尚未注册，登录失败");
						response.sendRedirect("login.jsp");
					}
				}
				rs.close();
				stmt.close();
				con.close();
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
