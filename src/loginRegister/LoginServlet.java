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
			ResultMessage.wrong("��Ϣ��ʾ","�û�������Ϊ��");
			response.sendRedirect("login.jsp");
		}
		else if("".equals(password))
		{
			ResultMessage.wrong("��Ϣ��ʾ","���벻��Ϊ�գ���¼ʧ��");
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
							//ʵ�������������Ϣ��JavaBean
							LoginBean jb = new LoginBean();
							jb.setUserName(userName);
							jb.setPassword(password);
							//��ȡsession����
							HttpSession session=request.getSession();
							ArrayList<LoginBean> login=new ArrayList<LoginBean>();//ʵ�����б����
							login.add(jb);//�Ѹ�����Ϣ���浽�б���
							//���б��浽session�����У��Ա��ڱ��ҳ���л�ȡ������Ϣ
							session.setAttribute("login", login);
							ArrayList<LoginBean> loginnew = (ArrayList<LoginBean>)session.getAttribute("login");
							response.sendRedirect("main/main.jsp");
						}
						else
						{
							ResultMessage.wrong("��Ϣ��ʾ","������󣬵�¼ʧ��");
							response.sendRedirect("login.jsp");
						}
					}
					else
					{
						ResultMessage.wrong("��Ϣ��ʾ","���û���δע�ᣬ��¼ʧ��");
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
