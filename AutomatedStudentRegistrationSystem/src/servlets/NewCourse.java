package servlets;

import java.io.*;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This servlet inserts the details of new course in Course_details table in
 * Students database and forwards to courseupdated.jsp on recieving post request.
 * 
 * @author Aakansha Doshi
 * 
 */
@WebServlet(urlPatterns = { "/Registrar/NewCourse" })
public class NewCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	Connection con;
	Statement stmt;

	public NewCourse() {
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Creating connection");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/Students", "root", "Password");
			System.out.println("Creating Statemnet");
			stmt = con.createStatement();
			String name = request.getParameter("Course_Name");
			String duration = request.getParameter("Course_Duration");
			String start = request.getParameter("Start_Date");
			String end = request.getParameter("End_Date");
			String capacity = request.getParameter("Capacity");
			System.out.println("name= " + name + "duration=  " + duration
					+ "start=  " + start + "end=  " + end + "capacity= "
					+ capacity);
			String sql = "insert into Course_details values(CourseId,'" + name
					+ "','" + duration + "','" + start + "','" + end + "','"
					+ capacity + "','0','" + capacity + "')";
			stmt.executeUpdate(sql);
			RequestDispatcher rd = request
					.getRequestDispatcher("courseupdated.jsp");
			request.setAttribute("course", name);
			request.setAttribute("action", "new");
			rd.forward(request, response);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {

				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
