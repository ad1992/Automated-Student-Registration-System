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
 * Servlet implementation class Check_Statistics_Servlet
 */
@WebServlet(urlPatterns ={"/Check_Statistics_Servlet"})
public class Check_Statistics_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
	Statement stmt;
	String sql;
	ResultSet rs; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Check_Statistics_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Creating connection");
			con = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/Students",
							"root",  "Password");
			stmt=con.createStatement();
			System.out.println("Creating Statemnet");
			sql = "Select * from Course_details";
			ResultSet rs = stmt.executeQuery(sql);
			List<Data> list = new ArrayList<Data>();			
            String json = null;
			while (rs.next()){
				String C = rs.getString("CourseName");
				int cap = rs.getInt("Students_Capacity");
				int v = rs.getInt("Vacancy");
				int r = rs.getInt("Students_Enrolled");
				int id=rs.getInt("CourseId");				
				Data d=new Data(C,cap,v,r,id);
				list.add(d);		
				
			}
			System.out.println("list="+list);
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
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	private class Data{
		String course;
		int capacity;
		int vacancy;
		int enroll;
		int cid;
		Data(String c,int cap,int v,int e,int id )
		{
			course=c;
			capacity=cap;
			vacancy=v;
			enroll=e;
			cid=id;
			
		}
	}

}
