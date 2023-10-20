package p4n.in;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.http.Cookie;

@WebServlet("/read-cookie")
public class ReadCookieServlet extends HttpServlet {
	
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
	        Cookie[] cookies = request.getCookies();
	        String username = null;
	        response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            
            out.println("<html><body>");
            out.println("<h1>Cookies</h1>");
	       
            for (int i = 0; i < cookies.length; i++) {
    			Cookie cookie = cookies[i];
    			
    			String cookieName = cookie.getName();
    			String cookieValue = cookie.getValue();
    			out.print("<li>"+cookieName+"="+ cookieValue+"</li>");
            
	        
	    }

	 }
}
