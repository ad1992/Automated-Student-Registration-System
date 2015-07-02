
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
 * Servlet implementation class CourseUpdate
 */
@WebServlet(urlPatterns ={"/Registrar/CourseUpdate"})
public class CourseUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private  Connection con;
	private  Statement stmt;
	private PreparedStatement pstmt;
	private String sql;
	ResultSet rs;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseUpdate() {
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
			try {
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("Creating connection");
				con = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/Students", "root",  "Password");
				System.out.println("Creating Statemnet");
				stmt = con.createStatement();
				String check=request.getParameter("action");
				String coursename=request.getParameter("hidname");
				String idcur=request.getParameter("hidid");
				System.out.println(check+" "+coursename+" "+idcur);
				RequestDispatcher rd=request.getRequestDispatcher("courseupdated.jsp");
				if(check.equals("Delete this course"))
				{
					System.out.println("Delete clicked");
					sql="delete from Course_details where CourseName='"+coursename+"' and CourseId='"+idcur+"'";
					stmt.executeUpdate(sql);
					request.setAttribute("action","delete");
				}
				else if(check.equals("Update"))
				{
					
					sql="select * from Course_details where  CourseName='"+coursename+"' and CourseId='"+idcur+"'";
					rs=stmt.executeQuery(sql);
					System.out.println(rs.next());
					int oldcap=rs.getInt("Students_Capacity");
					int oldvac=rs.getInt("Vacancy");
					int enroll=rs.getInt("Students_Enrolled");
					int id=rs.getInt("CourseId");
					String duration = request.getParameter("Course_Duration");
					String start = request.getParameter("Start_Date");
					String end = request.getParameter("End_Date");
					String capacity = request.getParameter("Capacity");					
					int diff=(Integer.parseInt(capacity))-oldcap;
					int vacancy=oldvac+diff;					
					System.out.println("name= "+coursename+"duration=  "+duration+"start=  "+start+"end=  "+end+"capacity= "+capacity);	
					sql="update Course_details set  CourseName=?,Duration_weeks=?,Starts=?,Ends=?,Students_Capacity=?,Students_Enrolled=?,Vacancy=? WHERE CourseId=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setString(1,coursename);
					pstmt.setString(2,duration);
					pstmt.setString(3,start);
					pstmt.setString(4,end);
					pstmt.setString(5,capacity);
					pstmt.setInt(6,enroll);
					pstmt.setInt(7, vacancy);
					pstmt.setInt(8, id);
					pstmt.executeUpdate();
					System.out.println("Updated");				
					request.setAttribute("action","update");
					
				}
				request.setAttribute("course",coursename);
				rd.forward(request,response);
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
						if(pstmt!=null)
							pstmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
							
					
				}
			
		
			
		}

	}


