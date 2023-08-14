# Servlet cookie

A servlet cookie is a small piece of data that a web server sends to a web browser, which is then stored by the browser and sent back to the server with subsequent requests. Cookies are used to maintain state and store information about users between requests, such as user preferences, session data, or tracking information. Here's an overview of how cookies work with Java Servlets:

1. **Creating a Cookie:** In a Java Servlet, you can create a cookie using the `javax.servlet.http.Cookie` class and add it to the response object. For example:

```java
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class CookieServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Create a cookie
        Cookie usernameCookie = new Cookie("username", "john_doe");
        
        // Set cookie properties
        usernameCookie.setMaxAge(3600); // Cookie lasts for 1 hour
        usernameCookie.setPath("/"); // Cookie is accessible from the entire context path
        
        // Add the cookie to the response
        response.addCookie(usernameCookie);
        
        // Write response content
        response.setContentType("text/html");
        response.getWriter().println("Cookie created and sent.");
    }
}
```

2. **Reading Cookies:** When the browser sends subsequent requests to the server, it includes any associated cookies in the request headers. You can read these cookies in your servlet using the `HttpServletRequest.getCookies()` method:

```java
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class ReadCookieServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get cookies from the request
        Cookie[] cookies = request.getCookies();
        
        // Search for a specific cookie
        String username = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("username".equals(cookie.getName())) {
                    username = cookie.getValue();
                    break;
                }
            }
        }
        
        // Write response content
        response.setContentType("text/html");
        response.getWriter().println("Username from cookie: " + username);
    }
}
```

3. **Updating and Deleting Cookies:** You can modify a cookie's value, expiration, or other properties and send it back to the browser. To delete a cookie, you can set its value to empty and set its max age to 0.

Cookies provide a simple way to store user-specific information on the client side. However, they have limitations, such as a maximum size and potential security concerns. For more advanced scenarios, like session management, web developers often use session objects, which can be managed using the HttpSession in servlets.

# Example 

### Working with cookies in a Jakarta EE servlet:

1. **Create a Cookie:**

```java
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/create-cookie")
public class CreateCookieServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        Cookie usernameCookie = new Cookie("username", "john_doe");
        usernameCookie.setMaxAge(3600); // Cookie lasts for 1 hour
        usernameCookie.setPath("/");    // Cookie is accessible from the entire context path
        response.addCookie(usernameCookie);
    }
}
```

2. **Read Cookies:**

```java
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/read-cookie")
public class ReadCookieServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        String username = null;
        
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("username".equals(cookie.getName())) {
                    username = cookie.getValue();
                    break;
                }
            }
        }
    }
}
```

3. **Update and Delete Cookies:**

You can modify or delete cookies using the same methods as before. For example, to update a cookie:

```java
Cookie updatedCookie = new Cookie("username", "new_value");
updatedCookie.setMaxAge(3600);
response.addCookie(updatedCookie);
```

And to delete a cookie:

```java
Cookie deleteCookie = new Cookie("username", "");
deleteCookie.setMaxAge(0); // Set max age to 0 to delete
response.addCookie(deleteCookie);
```

Remember to add the appropriate `@WebServlet` annotations and make sure to deploy these servlets in your Jakarta EE application.
