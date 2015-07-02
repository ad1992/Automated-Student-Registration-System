
package servlets;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GenerateUnique
 */
@WebServlet(urlPatterns ={"/GenerateUnique"})
public class GenerateUnique extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private Connection con;
	private Statement stmt;
	private PreparedStatement pstmt;
	private String sql;
	private String uniqueid;
	private String fname,lname,name;
	private String address;
	private String pincode;
	private String contact;
	private String email;
	private String course;
	private String gender;
	private String year,month,day;
	private String BirthDate;
	private String Degree;
	private String stream;
	private String cgpa;
	private int cid;
    public GenerateUnique() {
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
		 try
         {
         	Class.forName("com.mysql.jdbc.Driver");
         	System.out.println("Creating connection");
         	con=DriverManager.getConnection("jdbc:mysql://localhost:3306/","root", "Password");
         	System.out.println("Creating Statemnet");
         	stmt=con.createStatement();			
         }
         catch(SQLException e)
         {
         	System.out.println(e.getMessage());
         }
         catch(Exception e)
         {
         	System.out.println(e.getMessage());
         }
		  fname=request.getParameter("FirstName");
		  lname=request.getParameter("LastName");
		  name=fname+" "+lname;
		  address=request.getParameter("Address");
		  contact=request.getParameter("Contact");
		  email=request.getParameter("Email");	
		  course=request.getParameter("Course");		 
		  gender=request.getParameter("Gender");
		  pincode=request.getParameter("Pincode");
		  year=request.getParameter("year");
		  month=request.getParameter("month");
		  day=request.getParameter("day");
		  System.out.println("brithdate "+year+" "+month+" "+day);
		  if(year.equals("Select Year")&&day.equals("Select Day"))
		  {
			  BirthDate="N.A";
		  }
		  else
		  {
			  int m=Integer.parseInt(month)+1;
			  month=String.valueOf(m);		 
			  BirthDate=day+"/"+month+"/"+year;
		  }
		  System.out.println(BirthDate);
		  cgpa=request.getParameter("CGPA");
		  stream=request.getParameter("Stream");		  
		  Degree=request.getParameter("Degree");
		  
		  
		 try {
				sql="use Students";
				stmt.executeUpdate(sql);
				sql="select max(Id) from Student_details";
				ResultSet rs=stmt.executeQuery(sql);int id=0;
				if(rs==null)
				{
					id=1;
									
				}
				else
				{
					rs.next();					
					id=rs.getInt(1)+1;
				
				}	
				sql="Select * from Course_details where CourseName='"+course+"'";
				rs=stmt.executeQuery(sql);
				rs.next();
				int e=rs.getInt("Students_Enrolled");
				 e++;
				int v=rs.getInt("Vacancy");	
				v--;
				cid=rs.getInt(1);
				
				uniqueid=contact.substring(contact.length()-4)+id;
				sql="Insert into Student_details values(?,?,?,?,?,?,?,?,?)";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, id);
				pstmt.setString(2,name);
				pstmt.setString(3,address);
				pstmt.setString(4,pincode);
				pstmt.setString(5,contact);
				pstmt.setString(6,email);
				pstmt.setString(7,gender);
				pstmt.setString(8,BirthDate);
				pstmt.setString(9,uniqueid);
				pstmt.executeUpdate();
				sql="Insert into Student_Education values(?,?,?,?)";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1,uniqueid);
				pstmt.setString(2,Degree);
				pstmt.setString(3,cgpa);
				pstmt.setString(4,stream);
				pstmt.executeUpdate();
				sql="Insert into Student_Courses values(Id,?,?,?)";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1,uniqueid);
				pstmt.setString(2, course);
				pstmt.setInt(3, cid);
				pstmt.executeUpdate();			
				sql="UPDATE Course_details SET Students_Enrolled=?, Vacancy=? WHERE Coursename=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, e);
				pstmt.setInt(2, v);
				pstmt.setString(3,course);
				pstmt.executeUpdate();
				pstmt.close();
				rs.close();			
				stmt.close();
				con.close();
				System.out.println(uniqueid);
				RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/UniqueId.jsp");					 
				 request.setAttribute("UNIQUEID",uniqueid);
				 request.setAttribute("course",course);
				 rd.forward(request,response);
		 }
		 catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		
		 
		 
		 
		 
		 
	}

	
}
