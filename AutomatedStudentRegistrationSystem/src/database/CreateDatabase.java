package database;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class CreateDatabase {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con;
		Statement stmt;
		String sql;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Creating connection");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/",
					"root", "Password");
			System.out.println("Creating Statemnet");
			stmt = con.createStatement();
			sql = "create Database Students";
			stmt.executeUpdate(sql);
			System.out.println("Students created");
			sql = "use Students";
			stmt.executeUpdate(sql);
			sql = "create table Student_details(Id int,Name varchar(30),Address varchar(500),Pincode varchar(10),Contact varchar(10),Email Varchar(30),Gender char(1),Birthdate varchar(10),Unique_Id varchar(10) Primary key)";
			stmt.executeUpdate(sql);			
			sql="create table Student_Education(Student_Id varchar(10) not null,Degree varchar(10),CGPA varchar(5),Stream varchar(100),Foreign key(Student_Id) references Student_details(Unique_Id) On delete cascade)";
			stmt.executeUpdate(sql);
			sql = "create table Course_details(CourseId int Auto_Increment Primary key,CourseName varchar(30),Duration_weeks int,Starts Varchar(30),Ends varchar(30),Students_Capacity int,Students_Enrolled int,Vacancy int)";
			stmt.executeUpdate(sql);
			sql="create table Student_Courses(Id int Auto_Increment primary key,Student_Id varchar(10) not null,Course varchar(30),Course_Id int not null,Foreign key(Course_Id) references Course_details(CourseId), Foreign key(Student_Id) references Student_details(Unique_Id) On delete cascade)";
			stmt.executeUpdate(sql);
 			sql = "insert into Course_details values(CourseId,'CORE JAVA',4,'15/01/2014','12/02/2014',60,0,60)";
			stmt.executeUpdate(sql);
			sql = "insert into Course_details values(CourseId,'JEE 7',4 ,'17/01/2014','14/02/2014',60,0,60)";
			stmt.executeUpdate(sql);
			sql = "insert into Course_details values(CourseId,'C',3,'15/01/2014','5/02/2014',50,0,50)";
			stmt.executeUpdate(sql);
			sql = "insert into Course_details values(CourseId,'ANDROID',3,'20/01/2014','10/02/2014',40,0,40)";
			stmt.executeUpdate(sql);
			sql = "insert into Course_details values(CourseId,'C++',3,'17/01/2014','7/02/2014',40,0,40)";
			stmt.executeUpdate(sql);
			sql = "insert into Course_details values(CourseId,'PHP',3,'17/01/2014','7.02.2014',40,0,40)";
			stmt.executeUpdate(sql);
			sql = "create Database d2";// for registrar and councilor
			stmt.executeUpdate(sql);
			sql = "use d2";
			stmt.executeUpdate(sql);			
			sql = "create table t1(Id int,Designation varchar(10),Username varchar(30),Password varchar(30))";
			stmt.executeUpdate(sql);
			sql = "insert into t1 values(1,'Councilor','Coun101','c101')";
			stmt.executeUpdate(sql);
			sql = "insert into t1 values(2,'Councilor','Coun102','c102')";
			stmt.executeUpdate(sql);
			sql = "insert into t1 values(3,'Registrar','Reg191','reg1')";
			stmt.executeUpdate(sql);
			
		} catch (SQLException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
