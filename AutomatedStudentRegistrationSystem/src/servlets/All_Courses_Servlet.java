package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;



/**
 * Servlet implementation class All_Courses_Servlet
 */
@WebServlet(urlPatterns ={"/All_Courses_Servlet"})
public class All_Courses_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
	Statement stmt;
	String sql;
	ResultSet rs;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public All_Courses_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {

			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Creating connection");
			con = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/Students",
							"root", "Password");
			System.out.println("Creating Statemnet");
			stmt = con.createStatement();
			sql="select * from  Course_details ";
			ResultSet rs=stmt.executeQuery(sql);
			List<String> list = new ArrayList<String>();	
			while(rs.next())
			{
				int vacancy=rs.getInt("Vacancy");
				if(vacancy>0){
					list.add(rs.getString("CourseName"));
				}
			}
			String json = null;
	        json = new Gson().toJson(list);
	        response.setContentType("application/json");
	        response.getWriter().write(json);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally{
			try {
				if(rs!=null)
					rs.close();
				if(stmt!=null)
					stmt.close();
				if(con!=null)
					con.close();
				if(stmt!=null)
					stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
			
		}
			
		
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
