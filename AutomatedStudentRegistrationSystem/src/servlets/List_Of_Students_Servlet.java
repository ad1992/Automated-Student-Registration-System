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
 * Servlet implementation class List_Of_Students_Servlet
 */
@WebServlet("/List_Of_Students_Servlet")
public class List_Of_Students_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
	Statement stmt, stmt1;
	String sql;
	ResultSet rs;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public List_Of_Students_Servlet() {
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
			System.out.println("Creating Statemnet");
			stmt = con.createStatement();
			stmt1 = con.createStatement();
			String id = request.getParameter("id");
			System.out.println("id=" + id);
			sql = "select * from Student_Courses where Course_Id='" +id+ "'";
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println(rs);
			ResultSet rs1 = null;
			List<Data> list = new ArrayList<Data>();			
            String json = null;
			while (rs.next()){
				String sid = rs.getString(2);
				String course=rs.getString("Course");			
				System.out.println(id+" "+course);
				rs1 = stmt1.executeQuery("select Name from Student_details where Unique_Id='"
								+ sid + "'");
				System.out.println(rs1);
				rs1.next();
				String name = rs1.getString("Name");
				
				System.out.println(name);
				rs1.close();
				Data d=new Data(sid,name,course);
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
			e.printStackTrace();
		}
		finally
		{
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
		String Id;
		String Name;
		String CourseName;
		Data(String id1,String Name1,String c)
		{
			Id=id1;
			Name=Name1;
			CourseName=c;
			
		}
	}
}
