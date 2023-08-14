# Servlet Dispatcher 


Java web application with multiple servlets to handle different types of requests. To manage these servlets effectively, you can use a Servlet Dispatcher.

1. First, define your servlets by extending the `HttpServlet` class and overriding the `doGet()` and `doPost()` methods to handle specific types of requests.

```java
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class FirstServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Servlet logic for handling GET requests
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Servlet logic for handling POST requests
    }
}
```

2. Create a Servlet Dispatcher servlet, which acts as a central entry point for incoming requests and decides which servlet to route the request to.

```java
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class ServletDispatcher extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        
        if ("/first".equals(path)) {
            // Forward the request to the FirstServlet
            request.getRequestDispatcher("/firstServlet").forward(request, response);
        } else if ("/second".equals(path)) {
            // Forward the request to another servlet
            request.getRequestDispatcher("/secondServlet").forward(request, response);
        } else {
            // Handle other cases or show an error page
        }
    }
}
```

3. Configure your `web.xml` (or use annotations if using a modern servlet container) to map URLs to your servlets and the dispatcher:

```xml
<servlet>
    <servlet-name>FirstServlet</servlet-name>
    <servlet-class>yourpackage.FirstServlet</servlet-class>
</servlet>
<servlet>
    <servlet-name>SecondServlet</servlet-name>
    <servlet-class>yourpackage.SecondServlet</servlet-class>
</servlet>
<servlet>
    <servlet-name>ServletDispatcher</servlet-name>
    <servlet-class>yourpackage.ServletDispatcher</servlet-class>
</servlet>

<servlet-mapping>
    <servlet-name>FirstServlet</servlet-name>
    <url-pattern>/firstServlet</url-pattern>
</servlet-mapping>
<servlet-mapping>
    <servlet-name>SecondServlet</servlet-name>
    <url-pattern>/secondServlet</url-pattern>
</servlet-mapping>
<servlet-mapping>
    <servlet-name>ServletDispatcher</servlet-name>
    <url-pattern>/app/*</url-pattern>
</servlet-mapping>
```

4. When a request comes in, the `ServletDispatcher` will determine the appropriate servlet to forward the request to based on the URL. The `RequestDispatcher.forward()` method is used to hand off the request and response objects to the selected servlet for processing.


# Servlet Dispatcher in a Java web application:

Let's assume you have two servlets, `HomeServlet` and `AboutServlet`, and you want to use a Servlet Dispatcher to route requests based on the URL path.

1. Create the `HomeServlet`:

```java
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class HomeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1>Welcome to the Home Page</h1>");
        out.close();
    }
}
```

2. Create the `AboutServlet`:

```java
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class AboutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1>About Us</h1>");
        out.println("<p>We are a company that...</p>");
        out.close();
    }
}
```

3. Create the `ServletDispatcher`:

```java
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class ServletDispatcher extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        
        if ("/home".equals(path)) {
            request.getRequestDispatcher("/homeServlet").forward(request, response);
        } else if ("/about".equals(path)) {
            request.getRequestDispatcher("/aboutServlet").forward(request, response);
        } else {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<h1>Error: Page not found</h1>");
            out.close();
        }
    }
}
```

4. Configure the `web.xml` to map URLs to servlets:

```xml
<servlet>
    <servlet-name>HomeServlet</servlet-name>
    <servlet-class>yourpackage.HomeServlet</servlet-class>
</servlet>
<servlet>
    <servlet-name>AboutServlet</servlet-name>
    <servlet-class>yourpackage.AboutServlet</servlet-class>
</servlet>
<servlet>
    <servlet-name>ServletDispatcher</servlet-name>
    <servlet-class>yourpackage.ServletDispatcher</servlet-class>
</servlet>

<servlet-mapping>
    <servlet-name>HomeServlet</servlet-name>
    <url-pattern>/homeServlet</url-pattern>
</servlet-mapping>
<servlet-mapping>
    <servlet-name>AboutServlet</servlet-name>
    <url-pattern>/aboutServlet</url-pattern>
</servlet-mapping>
<servlet-mapping>
    <servlet-name>ServletDispatcher</servlet-name>
    <url-pattern>/app/*</url-pattern>
</servlet-mapping>
```

5. Access the URLs in your browser:

- Home Page: http://localhost:8080/yourapp/app/home
- About Page: http://localhost:8080/yourapp/app/about

When you access these URLs, the `ServletDispatcher` will route the requests to the appropriate servlets based on the URL path.


