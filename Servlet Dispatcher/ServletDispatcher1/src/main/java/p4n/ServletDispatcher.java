package p4n;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class ServletDispatcher
 */
public class ServletDispatcher extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		String path = request.getServletPath();
		if ("/h".equals(path)) {
            request.getRequestDispatcher("/HomeServlet").forward(request, response);
        } else if ("/a".equals(path)) {
            request.getRequestDispatcher("/Aboutus").forward(request, response);
        } else{
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<h1>Error: Page not found</h1>");
        	//request.getRequestDispatcher("/Err").forward(request, response);
            out.close();
        }
        }catch(Exception e) {
        	request.getRequestDispatcher("/Err").forward(request, response);
        }	
		}
	}


