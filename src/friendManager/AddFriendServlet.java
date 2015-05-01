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
 * Servlet implementation class AddFriendServlet
 */
public class AddFriendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddFriendServlet() {
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
		String name=new
		        String(request.getParameter("name").getBytes("ISO-8859-1"),"UTF-8");
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
		
		if(0 == name.length() || 0 == phone.length() || 0 == email.length()
		&& 0 == workplace.length() || 0 == QQ.length())
		{
			ResultMessage.wrong("信息提示", "不允许有空，添加失败");
			response.sendRedirect("frinedManager/addFriend.jsp");
		}
		else
		{
			try{
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
				
				String sqlQuery = "select * from friends where userName = '"
				       +userName+"' and name = '"+name+"'";
				rs = stmt.executeQuery(sqlQuery);
				rs.last();
				int count = rs.getRow();
				rs.beforeFirst();
				if(count>0)
				{
					ResultMessage.wrong("信息提示", "该联系人记录已经存在，添加失败");
					response.sendRedirect("frinedManager/addFriend.jsp");
				}
				else
				{
					String sqlInsert = "insert into friends(userName,name,phone,email,workplace,place,QQ) values('"
							+userName+"','"+name+"','"+phone+"','"+email+"','"+workplace+"','"+place+"','"+QQ+"')";
					stmt.executeUpdate(sqlInsert);
				}
				//更新session中的friendList 的值
				
				//构建查询语句
				String sqlQuery2 = "select * from friends where userName='"+userName+"'";
				rs=stmt.executeQuery(sqlQuery2);
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
				
				//关闭数据库资源
				rs.close();
				stmt.close();
				con.close();
				ResultMessage.right("信息提示", "添加信息成功");
				response.sendRedirect("friendManager\\lookFriend.jsp");
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
