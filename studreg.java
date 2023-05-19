import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

public class RegistrationServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mydatabase";
    private static final String DB_USERNAME = "your-username";
    private static final String DB_PASSWORD = "your-password";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data from the request
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        
        // Validate form inputs (e.g., check for empty fields, format validation, etc.)
        // ...
        
        // Save student data to the database
        saveStudentToDatabase(name, email);
        
        // Provide registration success message
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>Registration Successful</h2>");
        out.println("<p>Name: " + name + "</p>");
        out.println("<p>Email: " + email + "</p>");
        out.println("</body></html>");
    }

    private void saveStudentToDatabase(String name, String email) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String query = "INSERT INTO students (name, email) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, name);
                statement.setString(2, email);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database connection or insertion error
            // ...
        }
    }
}
