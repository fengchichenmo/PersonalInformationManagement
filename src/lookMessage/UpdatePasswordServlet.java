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
			ResultMessage.wrong("��Ϣ��ʾ", "�������пգ��޸�ʧ��");
			response.sendRedirect("lookMessage/updatePassword.jsp");
		}
		else if(!password1.equals(password2))
		{
			ResultMessage.wrong("��Ϣ��ʾ", "�������벻ͬ���޸�ʧ��");
			response.sendRedirect("lookMessage/updatePassword.jsp");
		}
		else 
		{
			try{
				//�������ݿ�����
				Connection con=null;
				Statement stmt=null;
				String url="jdbc:mysql://localhost:3306/student?useUnicode=true&characterEncoding=gbk";
				DriverManager.registerDriver(new com.mysql.jdbc.Driver());
				con = DriverManager.getConnection(url, "root", "123456");
				stmt = con.createStatement();
				
				//��ȡ��¼��
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
				//������ѯ���
				String sqlUpdate = "update user set password='"+password1+"' where userName='"+userName+"'";
				stmt.executeUpdate(sqlUpdate);
				
				lb.setPassword(password1);
				
				session.setAttribute("login", login);
				
				stmt.close();
				con.close();
				ResultMessage.right("��Ϣ��ʾ", "�޸���Ϣ�ɹ�");
				response.sendRedirect("lookMessage\\lookMessage.jsp");
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
	}

}
