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

/**
 * Servlet implementation class LookMessageServlet
 */
public class LookMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LookMessageServlet() {
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
		String userName = request.getParameter("userName");
		try{
			Connection con=null;
			Statement stmt=null;
			ResultSet rs=null;
			String url="jdbc:mysql://localhost:3306/student?useUnicode=true&characterEncoding=gbk";
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con = DriverManager.getConnection(url, "root", "123456");
			stmt = con.createStatement();
			String sql = "select * from user where userName='"+userName+"'";
			rs=stmt.executeQuery(sql);
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
			HttpSession session = request.getSession();
			ArrayList<LookMessageBean> wordList = new ArrayList<LookMessageBean>();
			wordList.add(lmb);
			session.setAttribute("wordList", wordList);
			rs.close();
			stmt.close();
			con.close();
			response.sendRedirect("lookMessage/lookMessage.jsp");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
