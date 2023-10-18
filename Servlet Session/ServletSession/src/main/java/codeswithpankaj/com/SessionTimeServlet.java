package codeswithpankaj.com;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalTime;


public class SessionTimeServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the session or create a new one if it doesn't exist
        HttpSession session = request.getSession(true);
        
        // Get session creation time
        Date creationTime = new Date(session.getCreationTime());
        
        // Get session last accessed time
        Date lastAccessedTime = new Date(session.getLastAccessedTime());
        LocalTime mytime = LocalTime.now();
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<html><body>");
        out.println("<h1>Session Time Example</h1>");
        out.println("<p>Session ID: " + session.getId() + "</p>");
        out.println("<p>Session Creation Time: " + creationTime + "</p>");
        out.println("<p>Session Last Accessed Time: " + lastAccessedTime + "</p>");
        out.println("<p>Session Last Accessed only Time: " + mytime + "</p>");
        out.println("</body></html>");
    }
}
