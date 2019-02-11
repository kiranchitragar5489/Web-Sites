

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/LoginUser")
public class Login extends HttpServlet 
{
    public Login() 
    {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  
		try 
		{
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");
			
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/waylon","root","root");  
			
			String query = "SELECT * FROM waylon.registeruser where userName = '"+userName+"' and userpassword = '"+password+"'";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			if(!rs.next()) 
			{
				out.print("Wrong Username and Password.");  
	        }
			else
			{
				response.sendRedirect("dashboard.html");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

}
