package codeswithpankaj.com;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

public class SessionTrackingServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the session or create a new one if it doesn't exist
        HttpSession session = request.getSession(true);
        
        // Get the current visit count from the session or initialize it
        Integer visitCount = (Integer) session.getAttribute("visitCount");
        if (visitCount == null) {
            visitCount = 1;
        } else {
            visitCount++;
        }
        
        // Store the updated visit count in the session
        session.setAttribute("visitCount", visitCount);
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<html><body>");
        out.println("<h1>Session Tracking Example</h1>");
        out.println("<p>You have visited this page " + visitCount + " times.</p>");
        out.println("</body></html>");
    }

}
