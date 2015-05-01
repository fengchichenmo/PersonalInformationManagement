package dateManager;

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
import dateManager.DateBean;

/**
 * Servlet implementation class LookDateServlet
 */
public class LookDateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LookDateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request,response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try{
			//创建数据库连接
			Connection con=null;
			Statement stmt=null;
			ResultSet rs=null;
			String url="jdbc:mysql://localhost:3306/student?useUnicode=true&characterEncoding=gbk";
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con = DriverManager.getConnection(url, "root", "123456");
			stmt = con.createStatement();
			
			//获取登录名
			String userName = "";
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
					LoginBean lb= (LoginBean)login.get(i);
					userName = lb.getUserName();
				}
			}
			
			//构建查询语句
			String sqlQuery = "select * from date where userName='"+userName+"'";
			rs=stmt.executeQuery(sqlQuery);
			
			ArrayList<DateBean> dateList = null;
			dateList = new ArrayList<DateBean>();
			while(rs.next())
			{
				DateBean dtb = new DateBean();
				dtb.setDate(rs.getString("date"));
				dtb.setThing(rs.getString("thing"));
				dateList.add(dtb);
			}
			session.setAttribute("dateList", dateList);
			
			rs.close();
			stmt.close();
			con.close();
			response.sendRedirect("dateManager\\lookDate.jsp");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
