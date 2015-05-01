package friendManager;

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
import loginRegister.LoginBean;
import lookMessage.LookMessageBean;

/**
 * Servlet implementation class LookFriendServlet
 */
public class LookFriendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LookFriendServlet() {
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

	@SuppressWarnings("unchecked")
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
			String sqlQuery = "select * from friends where userName='"+userName+"'";
			rs=stmt.executeQuery(sqlQuery);
			
			ArrayList<FriendBean> friendList = null;
			friendList = (ArrayList<FriendBean>) session.getAttribute("friendlist");
			if(null == friendList)
			{
				friendList = new ArrayList<FriendBean>();
			}
			while(rs.next())
			{
				FriendBean fb = new FriendBean();
				fb.setName(rs.getString("name"));
				fb.setPhone(rs.getString("phone"));
				fb.setEmail(rs.getString("email"));
				fb.setWorkplace(rs.getString("workplace"));
				fb.setPlace(rs.getString("place"));
				fb.setQQ(rs.getString("QQ"));
				friendList.add(fb);
			}
			session.setAttribute("friendList", friendList);
			
			rs.close();
			stmt.close();
			con.close();
			response.sendRedirect("friendManager\\lookFriend.jsp");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
