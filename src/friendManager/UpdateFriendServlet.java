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

/**
 * Servlet implementation class UpdateFriendServlet
 * ���ܴ�updateFriend.jsp���͹��������ݣ�Ҫ�޸ĵ���ϵ�˵�����
 * Ȼ������ݿ��аѸ���ϵ�˵�������Ϣ����ѯ������Ȼ���䱣�浽session�������У�����updateFriendMessageServlet���л�ȡȻ������޸�
 */
public class UpdateFriendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateFriendServlet() {
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
		String name=new
		        String(request.getParameter("name").getBytes("ISO-8859-1"),"UTF-8");
		if(0 == name.length())
		{
			ResultMessage.wrong("��Ϣ��ʾ", "������Ҫ�޸��˵�����");
			response.sendRedirect("friendManager/updateFriend.jsp");
		}
		try{
			//���ݿ�����
			Connection con=null;
			Statement stmt=null;
			ResultSet rs=null;
			String url="jdbc:mysql://localhost:3306/student?useUnicode=true&characterEncoding=gbk";
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con = DriverManager.getConnection(url, "root", "123456");
			stmt = con.createStatement();
			
			//��ȡ��¼��
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
			if(count < 1)//�����ڸ��û�
			{
				ResultMessage.wrong("��Ϣ��ʾ", "�����������ڣ��޷��޸�");
				response.sendRedirect("friendManager/updateFriend.jsp");
			}
			else
			{
				ArrayList<FriendBean> modFriendList = new ArrayList<FriendBean>();
				if(rs.next())
				{
					FriendBean fb = new FriendBean();
					fb.setName(rs.getString("name"));
					fb.setPhone(rs.getString("phone"));
					fb.setEmail(rs.getString("email"));
					fb.setWorkplace(rs.getString("workplace"));
					fb.setPlace(rs.getString("place"));
					fb.setQQ(rs.getString("QQ"));
					modFriendList.add(fb);
				}
				session.setAttribute("modFriendList", modFriendList);
				
				//�ر����ݿ���Դ
				rs.close();
				stmt.close();
				con.close();
				response.sendRedirect("friendManager/updateFriendMessage.jsp");
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
