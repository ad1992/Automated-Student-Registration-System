package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * This servlet uses gson library to convert java list to json strings. It
 * returns response back to Edit Course Settings.jsp when JQuery fires an ajax
 * request via get() method in Edit Course Settings.jsp. The response helps in
 * displaying the current details of course in Edit Course Settings.jsp.
 * 
 * @author Aakansha Doshi
 * 
 */
@WebServlet(urlPatterns = { "/Registrar/Edit_Course_Settings_Servlet" })
public class Edit_Course_Settings_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
	PreparedStatement pstmt;
	String sql;
	ResultSet rs;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Edit_Course_Settings_Servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {

			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Creating connection");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/Students", "root", "Password");
			System.out.println("Creating Statemnet");
			String id = request.getParameter("id");
			// System.out.println(id);
			// System.out.println("course=" + coursename);
			sql = "select * from Course_details where CourseId=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			int duration = rs.getInt("Duration_weeks");
			String start_date = rs.getString("Starts");
			String end_date = rs.getString("Ends");
			int cap = rs.getInt("Students_Capacity");
			String coursename = rs.getString("CourseName");
			List<Data> list = new ArrayList<Data>();
			Data d = new Data(start_date, end_date, cap, coursename, duration);
			list.add(d);
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
		// request.setCharacterEncoding("UTF-8");
		finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	private class Data {
		private String start_date;
		private String end_date;
		private int capacity;
		private String coursename;
		private int duration;

		public Data(String start_date, String end_date, int capacity,
				String coursename, int duration) {

			this.start_date = start_date;
			this.end_date = end_date;
			this.capacity = capacity;
			this.coursename = coursename;
			this.duration = duration;
		}

	}
}
