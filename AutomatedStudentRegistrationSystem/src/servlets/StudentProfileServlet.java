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
 * Servlet implementation class StudentProfileServlet
 */
@WebServlet(urlPatterns ={"/Councilor/StudentProfileServlet"})
public class StudentProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
	Statement stmt;
	String sql;
	ResultSet rs;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentProfileServlet() {
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
							"root",  "Password");
			System.out.println("Creating Statement");
			stmt = con.createStatement();
			sql="select a.Unique_Id,a. Name,b.Course from  Student_details a,Student_Courses b where a.Unique_Id=b. Student_Id"; 
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println(rs);
			List<Data> list = new ArrayList<Data>();			
            String json = null;
			while (rs.next()){
				String id=rs.getString(1);
				String Name=rs.getString(2);
				String Course=rs.getString(3);
				System.out.println(id +Name+Course);
				Data d=new Data(id,Name,Course);
				list.add(d);
				
			}
			 json = new Gson().toJson(list);
	         response.setContentType("application/json");
	         response.getWriter().write(json);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		finally{
			try {
				if(rs!=null)
				rs.close();
				if(stmt!=null)
				stmt.close();
				if(con!=null)
				con.close();
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
	private class Data{
		String id,Name,Course;
		Data(String id1,String Name1,String Course1)
		{
			id=id1;
			Name=Name1;
			Course=Course1;
		}
	}
}
