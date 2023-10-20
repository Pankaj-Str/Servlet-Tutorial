package p4n.in;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/create-cookie")
public class CreateCookieServlet extends HttpServlet {
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) {
	        
		    Cookie usernameCookie = new Cookie("F_name", "Sonu");
	        usernameCookie.setMaxAge(3600); // Cookie lasts for 1 hour
	        usernameCookie.setPath("/");    // Cookie is accessible from the entire context path
	        response.addCookie(usernameCookie);
	   }

}
