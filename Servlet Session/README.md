# Servlet session

Servlet session allows you to maintain user-specific data across multiple requests and responses. Sessions are especially useful for storing information that needs to persist across different pages or interactions on a website. Here's a simple example of how to use Servlet sessions:

Let's say you have a basic web application where users can log in and view their profile information. You want to store their username in a session so that you can display personalized content throughout their session.

## Why is Session Tracking Required?

Because the HTTP protocol is stateless, we require Session Tracking to make the client-server relationship stateful.
Session tracking is important for tracking conversions in online shopping, mailing applications, and E-Commerce applications.
The HTTP protocol is stateless, which implies that each request is treated as a new one. As you can see in the image below.


1. Create a new Java Servlet (e.g., `ProfileServlet`) that will handle the user profile page:

```java
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ProfileServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(); // Get the current session or create a new one
        
        // Check if the user is logged in (you might use a more sophisticated login mechanism)
        String username = (String) session.getAttribute("username");
        if (username == null) {
            response.sendRedirect("login.html"); // Redirect to login page if not logged in
            return;
        }
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<html><body>");
        out.println("<h1>Welcome, " + username + "!</h1>");
        // Display user-specific profile information here
        out.println("</body></html>");
    }
}
```

2. Create a simple login page (`login.html`) where users can enter their username and password:

```html
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h1>Login</h1>
    <form action="LoginServlet" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username"><br><br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password"><br><br>
        <input type="submit" value="Login">
    </form>
</body>
</html>
```

3. Create a Servlet (`LoginServlet`) that handles the login process and sets the username in the session:

```java
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // Perform authentication here (for simplicity, let's assume a valid username/password)
        if ("valid_username".equals(username) && "valid_password".equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username); // Set username in the session
            
            response.sendRedirect("ProfileServlet"); // Redirect to profile page
        } else {
            response.sendRedirect("login.html"); // Redirect back to login page on failed login
        }
    }
}
```
# Servlet – Session Tracking example

1. **Create a Servlet (`SessionTrackingServlet`) to demonstrate session tracking:**

```java
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

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
```

2. **Add the servlet to your `web.xml` (deployment descriptor):**

```xml
<servlet>
    <servlet-name>SessionTrackingServlet</servlet-name>
    <servlet-class>SessionTrackingServlet</servlet-class>
</servlet>
<servlet-mapping>
    <servlet-name>SessionTrackingServlet</servlet-name>
    <url-pattern>/sessionTracking</url-pattern>
</servlet-mapping>
```

3. **Access the servlet via a URL to see session tracking in action:**

Assuming your application is deployed on a server at `http://localhost:8080/your-app-name`, you can access the session tracking servlet using the URL `http://localhost:8080/your-app-name/sessionTracking`.

When you access this URL multiple times, the servlet will keep track of the number of visits for each user's session.


