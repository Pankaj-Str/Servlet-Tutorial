# JDBC

## Insert Operation of JDBC using servlet

Certainly! Here's an example of how to perform an insert operation using JDBC in a Java Servlet application. We'll focus on inserting new user records into a database using a simple form.

1. **Create a MySQL Database Table (`users`) to store user information:**

```sql
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50),
    email VARCHAR(100),
    password VARCHAR(100)
);
```

2. **Create a Java Servlet (`InsertUserServlet`) to handle the insert operation:**

```java
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class InsertUserServlet extends HttpServlet {
    private String jdbcUrl = "jdbc:mysql://localhost:3306/yourdb";
    private String jdbcUser = "yourusername";
    private String jdbcPassword = "yourpassword";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try (Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (username, email, password) VALUES (?, ?, ?)")) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);

            preparedStatement.executeUpdate();

            // Redirect to a success page or display a message
            response.sendRedirect("success.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            // Redirect to an error page or display an error message
            response.sendRedirect("error.jsp");
        }
    }
}
```

3. **Create a simple HTML form (`insert-user.html`) to input user data:**

```html
<!DOCTYPE html>
<html>
<head>
    <title>Insert User</title>
</head>
<body>
    <h1>Insert User</h1>
    <form action="InsertUserServlet" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username"><br><br>
        <label for="email">Email:</label>
        <input type="email" id="email" name="email"><br><br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password"><br><br>
        <input type="submit" value="Insert">
    </form>
</body>
</html>
```

4. **Map the `InsertUserServlet` in your `web.xml` (deployment descriptor).**

5. **Access the `insert-user.html` page and submit the form to insert a new user record.**

Make sure to replace `yourdb`, `yourusername`, and `yourpassword` with your actual database connection details.

In this example, the `InsertUserServlet` receives the user input from the HTML form and inserts the new user record into the `users` table in the database. If the insertion is successful, it redirects to a success page; otherwise, it redirects to an error page.
