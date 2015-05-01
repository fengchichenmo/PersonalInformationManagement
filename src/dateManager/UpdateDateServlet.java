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

import dateManager.DateBean;
import loginRegister.LoginBean;
import resultMessage.ResultMessage;

/**
 * Servlet implementation class UpdateDateServlet
 */
public class UpdateDateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateDateServlet() {
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
	
	//数据处理函数
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String year=new
		        String(request.getParameter("year").getBytes("ISO-8859-1"),"UTF-8");
		String month=new
		        String(request.getParameter("month").getBytes("ISO-8859-1"),"UTF-8");
		String day=new
		        String(request.getParameter("day").getBytes("ISO-8859-1"),"UTF-8");
		String thing=new
		        String(request.getParameter("thing").getBytes("ISO-8859-1"),"UTF-8");
		
		String date="20"+year+"-"+month+"-"+day;
		
		if(0 == year.length() || 0 == month.length() || 0 == day.length())
		{
			ResultMessage.wrong("信息提示", "请把日期填写完整，添加失败");
			response.sendRedirect("dateManager/updateDate.jsp");
		}
		else if(year.length() != 2 || Integer.parseInt(year)<0 || Integer.parseInt(year)>99
			|| Integer.parseInt(month)>12 || Integer.parseInt(month)<1
			|| Integer.parseInt(day)>31 || Integer.parseInt(day)<1)
		{
			ResultMessage.wrong("信息提示", "请填写正确的日期，添加失败");
			response.sendRedirect("dateManager/updateDate.jsp");
		}
		else if(0 == thing.length())
		{
			ResultMessage.wrong("信息提示", "请填写日期内容，添加失败");
			response.sendRedirect("dateManager/updateDate.jsp");
		}
		else{
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
				
				String sqlQuery = "select * from date where userName='"+userName+"' and date='"+date+"'";
				rs = stmt.executeQuery(sqlQuery);
				rs.last();
				int count = rs.getRow();
				rs.beforeFirst();
				if(count < 1)//不存在该日程记录
				{
					ResultMessage.wrong("信息提示", "此日程记录不存在，无法修改");
					response.sendRedirect("dateManager/updateDate.jsp");
				}
				else
				{
					//数据库更新
					String sqlUpdate = "update date set thing='"+thing+"' where userName='"+userName+"' and date='"+date+"'";
					stmt.executeUpdate(sqlUpdate);
					
					//session数据更新
					String sqlQueryDate = "select * from date where userName = '"+userName+"'";
					stmt.executeQuery(sqlQueryDate);
					ArrayList<DateBean> dateList = null;
					dateList = new ArrayList<DateBean>();
					while(rs.next())
					{
						DateBean dtb = new DateBean();
						dtb.setDate(rs.getString("date"));
						dtb.setDate(rs.getString("thing"));
						dateList.add(dtb);
					}
					session.setAttribute("dateList", dateList);
					
					rs.close();
					stmt.close();
					con.close();
					ResultMessage.right("信息提示", "修改日程成功");
					response.sendRedirect("LookDateServlet");
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
