# Automated-Student-Registration-System
<b>My first Java EE Web Application developed during college days</b>

>#### It is  a simple Java EE web application with two types of users namely Registrar and Councilor to automate the process of registration of a student in a particular course(C,C++,Java,J2EE,Mysql etc).

<b>Role of a Councilor:</b>

* Fills in the details of the of student and registers the student in the desired course(s).
* View the details of students registered in each course.
* Delete Student Profile

<b>Role of a Registrar:</b>

* Increase/Decrease the capacity of students registered in a particular course.
* Add/Delete/Edit a course.
* View the details of students registered in each course.

<b>Libraries required</b>
* mysql-connector-java-5.1.16.jar
* gson-2.3.1.jar

<b>Server used :</b>J
* Jboss 7.1

###<b> Instructions </b>

1. Run `/AutomatedStudentRegistrationSystem/src/database/createDatabase.java` as java application to create the database and required tables.

2. The username is `root` and password is `Password` for the database.Please change the password in all files inside servlets folder and database  folder in case the password differs.
    ``` 
    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/","root", "Password");
    ```
  Replace `root` and `Password` with your own username and password
	

3. Once the database is created.Start the server and paste this url in the browser  
`http://localhost:8080/AutomatedStudentRegistrationSystem/LogIn` and run.

4. The login page will show up.The username and password of councilor and registrar is given in the **/src/database/createDatabase.java**.

  Designation	|	Username	|	Password
  ---------   |  ---------|   ---------
  Councilor 	|	 Coun101	|	c101
  Councilor	  |  Coun102	|	c102
  Registrar	  |	 Reg191		| reg1


Once Logged in,the welcome page will be displayed with several options depending upon the designation of the user

I have also added **screen shots** which will give a better view of the project.

This project might contain errors as this is my first web app.I will be adding more features to the project.




