# JDBC
|Sn.No| topic|
|----|---|
|1. |Insert Operation of JDBC|
|2. |Select Operation of JDBC|
|3. |Update Operation of JDBC|
|4.| Delete Operation of JDBC|

Simple servlet that demonstrates the basic JDBC operations: Insert, Select, Update, and Delete. However, please note that this example assumes you have a database set up and the appropriate JDBC driver configured.

```java
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class JDBCServlet extends HttpServlet {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/your_database";
    private static final String JDBC_USERNAME = "your_username";
    private static final String JDBC_PASSWORD = "your_password";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            // Establish JDBC connection
            Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
            
            // Perform operations
            String operation = request.getParameter("operation");
            if (operation != null) {
                if (operation.equals("insert")) {
                    insertData(conn);
                } else if (operation.equals("select")) {
                    selectData(conn, out);
                } else if (operation.equals("update")) {
                    updateData(conn);
                } else if (operation.equals("delete")) {
                    deleteData(conn);
                }
            }
            
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("An error occurred while performing the operation.");
        }
    }

    private void insertData(Connection conn) throws SQLException {
        String insertQuery = "INSERT INTO your_table (column1, column2) VALUES (?, ?)";
        PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);
        preparedStatement.setString(1, "value1");
        preparedStatement.setString(2, "value2");
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    private void selectData(Connection conn, PrintWriter out) throws SQLException {
        String selectQuery = "SELECT * FROM your_table";
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(selectQuery);

        while (resultSet.next()) {
            String column1Value = resultSet.getString("column1");
            String column2Value = resultSet.getString("column2");
            out.println("Column1: " + column1Value + ", Column2: " + column2Value + "<br>");
        }

        resultSet.close();
        statement.close();
    }

    private void updateData(Connection conn) throws SQLException {
        String updateQuery = "UPDATE your_table SET column2 = ? WHERE column1 = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(updateQuery);
        preparedStatement.setString(1, "new_value");
        preparedStatement.setString(2, "value_to_update");
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    private void deleteData(Connection conn) throws SQLException {
        String deleteQuery = "DELETE FROM your_table WHERE column1 = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(deleteQuery);
        preparedStatement.setString(1, "value_to_delete");
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
}
```

Remember to replace `your_database`, `your_username`, `your_password`, `your_table`, and the column names in the queries with your actual database information. Also, make sure you have the necessary MySQL JDBC driver in your project's classpath.

Please note that this is a simple example for demonstration purposes. In a real-world application, you would handle exceptions and connection management more robustly. Additionally, consider using a connection pool for improved performance.


