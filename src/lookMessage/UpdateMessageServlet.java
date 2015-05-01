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
 * Servlet implementation class UpdateMessageServlet
 */
public class UpdateMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateMessageServlet() {
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
		
		String edu=new
		        String(request.getParameter("edu").getBytes("ISO-8859-1"),"UTF-8");
		String work=new
		        String(request.getParameter("work").getBytes("ISO-8859-1"),"UTF-8");
		String phone=new
		        String(request.getParameter("phone").getBytes("ISO-8859-1"),"UTF-8");
		String email=new
		        String(request.getParameter("email").getBytes("ISO-8859-1"),"UTF-8");
		
		if(0==phone.length()||0==email.length())
		{
			ResultMessage.wrong("信息提示", "不允许有空，修改失败");
			response.sendRedirect("lookMessage\\lookMessage.jsp");
		}
		else 
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
				String sqlUpdate = "Update user set edu='"+edu+"',work='"+work+"',phone='"+phone+"',email='"+email+"' "
						+ "where userName='"+userName+"'";
				stmt.executeUpdate(sqlUpdate);
				
				String sqlQuery = "select * from user where userName='"+userName+"'";
				
				rs = stmt.executeQuery(sqlQuery);
				
				LookMessageBean lmb = new LookMessageBean();
				if(rs.next())
				{
					lmb.setName(rs.getString("name"));
					lmb.setSex(rs.getString("sex"));
					lmb.setBirth(rs.getString("birth"));
					lmb.setNation(rs.getString("nation"));
					lmb.setEdu(rs.getString("edu"));
					lmb.setWork(rs.getString("work"));
					lmb.setPhone(rs.getString("phone"));
					lmb.setPlace(rs.getString("place"));
					lmb.setEmail(rs.getString("email"));
				}
				ArrayList<LookMessageBean> wordList = null;
				wordList = new ArrayList<LookMessageBean>();
				wordList.add(lmb);
				session.setAttribute("wordList", wordList);
				
				rs.close();
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
