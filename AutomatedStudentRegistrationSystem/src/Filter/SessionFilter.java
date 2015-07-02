package Filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionFilter implements Filter{
	private ArrayList<String> urlList;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		   HttpServletRequest request = (HttpServletRequest) req;
	       HttpServletResponse response = (HttpServletResponse) res;
	        String url = request.getServletPath();
	        System.out.println(url);
	        boolean allowedRequest = false;
	         
	        if(urlList.contains(url)) {
	            allowedRequest = true;
	        }
	             
	        if (!allowedRequest){
	            HttpSession session = request.getSession(false);
	            System.out.println("sessin="+session);
	            if (session==null) {
	            	response.sendRedirect("/AutomatedStudentRegistrationSystem/LogIn");
	               
	            }
	            else{
	            	String dsg=(String)session.getAttribute("DESG");
	            	System.out.println("designation="+dsg);
	            	if((!url.startsWith("/Registrar")&&dsg.equals("Councilor"))||(dsg.equals("Registrar")))
	            	{		
	            		chain.doFilter(req, res);
	    	        }
	            	else
	            	{
	            		response.sendRedirect("/AutomatedStudentRegistrationSystem/UnauthorizeAccess");
	            	}
	            	
	            }
	        }
	        else{
	        	
	        chain.doFilter(req, res);
	        }
		
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub
		 String urls = config.getInitParameter("avoid-urls");
	        StringTokenizer token = new StringTokenizer(urls, ",");
	 
	        urlList = new ArrayList<String>();
	 
	        while (token.hasMoreTokens()) {
	            urlList.add(token.nextToken());
	 
	        }
	        System.out.println(urlList);
	}

}
