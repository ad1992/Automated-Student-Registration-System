package servlets;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class BirthDayServlet
 */
@WebServlet(urlPatterns ={"/BirthDayServlet"})
public class BirthDayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BirthDayServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String y=request.getParameter("year");
		String m=request.getParameter("month");
		System.out.println("y= "+y+"m= "+m);
		List<String> list = new ArrayList<String>();
		String json = null;	
		if(m==null)
		{
			if(y.equals("Select Year"))
			{
				
				list.add("Select Month");
				json=new Gson().toJson(list);
			}
			
			else if(!y.equals("Select Year"))
			{
				
				String month[]={"Jan","Feb","March","April","May","June","July","Aug","Sept","Oct","Nov","Dec" };
				for(int i=0;i<12;i++)
				{
					list.add(month[i]);
				}
				
				json=new Gson().toJson(list);
			}
		}
		else
		{
			if(m.equals("Select Month")||y.equals("Select Year"))
			{
					
					list.add("Select Day");
					json=new Gson().toJson(list);
			}
			else if(!m.equals("Select Month"))
			{
				
				int year=Integer.parseInt(y);		
				int month=Integer.parseInt(m);
				System.out.println("year= "+year+" month="+month);			
				String day[]={"31","28","31","30","31","30","31","31","30","31","30","31"};	
				if((year%4==0&&year%100!=0)||(year%400==0))
				{
					day[1]="29";
				}
				int range=Integer.parseInt(day[month]);
				for(int i=1;i<=range;i++)
				{
					list.add(String.valueOf(i));
				}
				
				json=new Gson().toJson(list);
			}		
		}
	
	
		response.setContentType("application/json");
		response.getWriter().write(json);
	}		
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
