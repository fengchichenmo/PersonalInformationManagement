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

import loginRegister.LoginBean;
import resultMessage.ResultMessage;

/**
 * Servlet implementation class UpdateFriendMessageServlet
 * 接受从updateFriendServlet传来的需要修改的联系人的信息，然后进行修改并存入数据库中
 */
public class UpdateFriendMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateFriendMessageServlet() {
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

	private void process(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		String phone=new
		        String(request.getParameter("phone").getBytes("ISO-8859-1"),"UTF-8");
		String email=new
		        String(request.getParameter("email").getBytes("ISO-8859-1"),"UTF-8");
		String workplace=new
		        String(request.getParameter("workplace").getBytes("ISO-8859-1"),"UTF-8");
		String place=new
		        String(request.getParameter("place").getBytes("ISO-8859-1"),"UTF-8");
		String QQ=new
		        String(request.getParameter("QQ").getBytes("ISO-8859-1"),"UTF-8");
		
		if( 0 == phone.length() || 0 == email.length()
		&& 0 == workplace.length() || 0 == QQ.length())
		{
			ResultMessage.wrong("信息提示", "不允许有空，修改失败");
			response.sendRedirect("frinedManager/updateFriendMessage.jsp");
		}
		else
		{
			try{
				//数据库连接
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
				
				String name = null;
				ArrayList<FriendBean> modFriendList = (ArrayList<FriendBean>) session.getAttribute("modFriendList");
				if(null == modFriendList || 0 == modFriendList.size())
				{
					response.sendRedirect("main/bottom.jsp");
				}
				else
				{
					for(int i=modFriendList.size()-1;i>=0;i--)
					{
						FriendBean fb = modFriendList.get(i);
						name = fb.getName();
					}
				}
				//数据库更新
				String sqlUpdate = "update friends set phone='"+phone+"',email='"+email+"',workplace='"+workplace+"',"
						+ "place='"+place+"',QQ='"+QQ+"' where userName='"+userName+"' and name='"+name+"'";
				stmt.executeUpdate(sqlUpdate);
				
				//session数据更新
				String sqlQuery = "select * from friends where userName = '"
					       +userName+"'";
				rs=stmt.executeQuery(sqlQuery);
				ArrayList<FriendBean> friendList = null;
				friendList = new ArrayList<FriendBean>();
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
				ResultMessage.right("信息提示", "修改信息成功");
				response.sendRedirect("LookFriendServlet");
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}

}
