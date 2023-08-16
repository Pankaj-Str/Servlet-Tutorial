# Servlet – Event and Listener

Example that demonstrates the use of servlet events and listeners in a Java Servlet application. We'll create a simple scenario where a user's login count is tracked using `HttpSessionListener`.

1. **Create a class (`LoginCounterListener`) that implements `HttpSessionListener` to track login counts:**

```java
import javax.servlet.http.*;

public class LoginCounterListener implements HttpSessionListener {
    public void sessionCreated(HttpSessionEvent event) {
        // Initialize login count for new session
        event.getSession().setAttribute("loginCount", 0);
    }

    public void sessionDestroyed(HttpSessionEvent event) {
        // No action needed in this example
    }
}
```

2. **Create a servlet (`LoginServlet`) to simulate user logins:**

```java
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);

        // Increment the login count for the user
        Integer loginCount = (Integer) session.getAttribute("loginCount");
        if (loginCount == null) {
            loginCount = 0;
        }
        loginCount++;
        session.setAttribute("loginCount", loginCount);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h1>Login Count Example</h1>");
        out.println("<p>Your login count: " + loginCount + "</p>");
        out.println("</body></html>");
    }
}
```

3. **Add the listener and servlet to your `web.xml` (deployment descriptor):**

```xml
<listener>
    <listener-class>LoginCounterListener</listener-class>
</listener>

<servlet>
    <servlet-name>LoginServlet</servlet-name>
    <servlet-class>LoginServlet</servlet-class>
</servlet>
<servlet-mapping>
    <servlet-name>LoginServlet</servlet-name>
    <url-pattern>/login</url-pattern>
</servlet-mapping>
```

4. **Access the `LoginServlet` via a URL to simulate user logins:**

When you access the URL `http://localhost:8080/your-app-name/login`, it will increment the login count for each session.

In this example, the `LoginCounterListener` listens for session creation events using the `HttpSessionListener` interface. When a new session is created, it initializes the login count attribute in the session.

The `LoginServlet` simulates user logins. Each time you access the servlet, it increments the login count for the user's session and displays it.

Please replace `your-app-name` with the actual name of your web application.
