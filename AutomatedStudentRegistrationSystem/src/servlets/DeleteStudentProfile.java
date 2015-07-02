package servlets;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteStudentProfile
 */
@WebServlet(urlPatterns ={"/Councilor/DeleteStudentProfile"})
public class DeleteStudentProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private  Connection con;
	private  Statement stmt;
	private String sql;
	PreparedStatement pstmt;
	ResultSet rs;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteStudentProfile() {
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
		// TODO Auto-generated method stub
					try {
						Class.forName("com.mysql.jdbc.Driver");
						System.out.println("Creating connection");
						con = DriverManager.getConnection(
								"jdbc:mysql://localhost:3306/Students", "root", "Password");
						System.out.println("Creating Statemnet");
						stmt = con.createStatement();
						String name=request.getParameter("name");
						String uid=request.getParameter("id");
						String course=request.getParameter("course");
						System.out.println("Name="+name+"id="+uid+"course="+course);
						sql="delete from Student_details where Name='"+name+"' and Unique_Id='"+uid+"'";
						stmt.executeUpdate(sql);
						sql="select * from Course_details where CourseName='"+course+"'";
						rs=stmt.executeQuery(sql);
						System.out.println(rs);
						rs.next();
						int enroll=rs.getInt("Students_Enrolled");
						int vac=rs.getInt("Vacancy");
						enroll--;
						vac++;
						sql="Update Course_details set Students_Enrolled=?,Vacancy=?  where CourseName=?"; 
						pstmt=con.prepareStatement(sql);
						pstmt.setInt(1,enroll);
						pstmt.setInt(2,vac);
						pstmt.setString(3,course);
						pstmt.executeUpdate();
						RequestDispatcher rd = request.getRequestDispatcher("StudentProfileDeleted.jsp");
						request.setAttribute("auth","yes");
						rd.forward(request,response);
						
					}
					catch (ClassNotFoundException e) {
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
							if(pstmt!=null)
								pstmt.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
								
						
					}
	}

}
