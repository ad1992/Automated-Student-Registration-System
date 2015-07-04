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
 * This servlet uses gson library to convert java list to json strings.It
 * returns the response back to StudentsBioData.jsp when the Jquery fires an
 * ajax request via get() method in StudentsBioData.jsp.The response is based on
 * value of parameter name and id which refers to Name and Unique_id in
 * Students_details table.
 * 
 * @author Aakansha Doshi
 * 
 */
@WebServlet(urlPatterns = { "/Councilor/StudentBioDataServlet" })
public class StudentBioDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
	PreparedStatement pstmt1, pstmt2, pstmt3;
	String sql;
	ResultSet rs, rs1, rs2;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentBioDataServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {

			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Creating connection");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/Students", "root", "Password");
			System.out.println("Creating Statemnet");
			String name = request.getParameter("name");
			String id = request.getParameter("id");
			System.out.println("name=" + name + " id=" + id);
			// System.out.println("course=" + coursename);
			sql = "select * from Student_details where Name=? and Unique_Id=?";
			pstmt1 = con.prepareStatement(sql);
			pstmt1.setString(1, name);
			pstmt1.setString(2, id);
			rs = pstmt1.executeQuery();
			System.out.println(rs);
			rs.next();
			String address = rs.getString("Address");
			String Contact = rs.getString("Contact");
			String Email = rs.getString("Email");
			String Gender = rs.getString("Gender");
			String pincode = rs.getString("Pincode");
			String Birthdate = rs.getString("Birthdate");

			sql = "select Course from Student_Courses where  Student_Id=?";
			pstmt2 = con.prepareStatement(sql);
			pstmt2.setString(1, id);
			rs1 = pstmt2.executeQuery();
			System.out.println("rs1=" + rs1);
			rs1.next();
			String course = rs1.getString(1);
			sql = "select* from Student_Education where Student_Id=? ";
			pstmt3 = con.prepareStatement(sql);
			pstmt3.setString(1, id);
			rs2 = pstmt3.executeQuery();
			rs2.next();
			System.out.println("rs2=" + rs2);
			String cgpa = rs2.getString("CGPA");
			String stream = rs2.getString("Stream");
			String Degree = rs2.getString("Degree");
			List<String> list = new ArrayList<String>();
			String json = null;
			list.add(name);
			list.add(id);
			list.add(course);
			list.add(address);
			list.add(pincode);
			list.add(Contact);
			list.add(Email);
			list.add(Birthdate);
			list.add(Degree);
			list.add(stream);
			list.add(cgpa);
			list.add(Gender);
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
				rs2.close();
				rs1.close();
				rs.close();
				pstmt1.close();
				pstmt2.close();
				pstmt3.close();
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

}
