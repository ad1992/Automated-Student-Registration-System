package servlets;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AuthenticeLogin
 */
@WebServlet("/AuthenticateLogin")
public class AuthenticateLogin extends HttpServlet {
	Connection con;
	Statement stmt;
	String sql;  
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthenticateLogin() {
        super();
        // TODO Auto-generated constructor stub
       con=null;
       stmt=null;
       sql=null;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("/AutomatedStudentRegistrationSystem/LogIn");
		//System.out.println("redirected");
		
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		try {
			System.out.println("posty");
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Creating connection");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/d2","root", "Password");
			System.out.println("Creating Statemnet");
			stmt=con.createStatement();
			String user=request.getParameter("Username");
			String Password=request.getParameter("Password");	
			String Designation=request.getParameter("Post");
			System.out.println(user+" "+Password+" "+Designation);
			String sql="select * from t1 where Username='"+user+"' and Password='"+Password+"' and Designation='"+Designation+"'";
			ResultSet rs=stmt.executeQuery(sql);			
			if(rs.next())
			{
				
				HttpSession session=request.getSession(true);
				System.out.println("match");
		 		//session.setAttribute("LOGGEDIN", "true");
		 		session.setAttribute("USERNAME", user);
		 		session.setAttribute("DESG",Designation);
		 		response.sendRedirect("/AutomatedStudentRegistrationSystem/Welcome");
		 		
				 
			}
			else
			{
				RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/ErrorPage.html");
				rd.forward(request,response);
			}
		
			
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
