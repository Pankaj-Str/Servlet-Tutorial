

### Step 1: Setup the Development Environment
Ensure you have Java Development Kit (JDK) and an Integrated Development Environment (IDE) like Eclipse or IntelliJ installed. Also, you need a web server like Apache Tomcat.

### Step 2: Create a Dynamic Web Project
1. Open your IDE.
2. Create a new Dynamic Web Project.

### Step 3: Create a Servlet
1. Right-click on the `src` folder and create a new package, for example, `com.example.servlet`.
2. In this package, create a new servlet class, for example, `ContextExampleServlet`.

```java
package com.example.servlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ContextExampleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ServletContext context;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        context = config.getServletContext();
        context.setAttribute("contextAttribute", "Initial Value");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.getWriter().println("<html><body>");
        response.getWriter().println("<h1>Servlet Context Example</h1>");
        
        String contextAttribute = (String) context.getAttribute("contextAttribute");
        response.getWriter().println("<p>Context Attribute Value: " + contextAttribute + "</p>");
        
        context.setAttribute("contextAttribute", "Updated Value");
        response.getWriter().println("<p>Context Attribute Updated!</p>");
        
        response.getWriter().println("</body></html>");
    }
}
```

### Step 4: Configure the Servlet in `web.xml`
1. Navigate to the `WEB-INF` directory.
2. Open or create `web.xml`.

```xml
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee" 
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee 
                             http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
         version="3.1">
    <servlet>
        <servlet-name>ContextExampleServlet</servlet-name>
        <servlet-class>com.example.servlet.ContextExampleServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>ContextExampleServlet</servlet-name>
        <url-pattern>/contextExample</url-pattern>
    </servlet-mapping>
</web-app>
```

### Step 5: Deploy and Run the Application
1. Deploy your project to the Tomcat server.
2. Start the server.
3. Open a web browser and navigate to `http://localhost:8080/YourProjectName/contextExample`.

### Step 6: Verify the Servlet Context Usage
1. On the first request, the servlet initializes the context attribute with "Initial Value" and displays it.
2. The servlet then updates the context attribute to "Updated Value".
3. Subsequent requests will show the updated value.

### Summary
In this example, the `ServletContext` is used to share data between servlets within the same application. The `init` method initializes a context attribute, and the `doGet` method retrieves and updates this attribute. This demonstrates how servlets can use the `ServletContext` to communicate and share data.