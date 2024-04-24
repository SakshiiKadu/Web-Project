import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet("/Hotel")
public class Hotel extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Hotel() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String no_of_peoples = request.getParameter("no_of_peoples");
        String duration = request.getParameter("duration");
        
        

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "root", "Sakshi@1026");

            // Use explicit column names in the INSERT statement
            PreparedStatement ps = con.prepareStatement("INSERT INTO hotel (name, phone, email, address, no_of_peoples,duration) VALUES (?, ?, ?, ?, ?, ?)");

            ps.setString(1, name);
            ps.setString(2, phone);
            ps.setString(3, email);
            ps.setString(4, address);
            ps.setString(5, no_of_peoples);
            ps.setString(6, duration);
            
            int i = ps.executeUpdate();

            if (i > 0) {
                out.print("You are successfully registered.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}