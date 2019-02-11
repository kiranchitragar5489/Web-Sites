

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/dashboard")
public class Dashboard extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
    public Dashboard() 
    {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  

		String userId =request.getParameter("userId");
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		System.out.println("UserId :: "+ userId);
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");  
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/waylon","root","root");  
			
			String query = "SELECT * FROM waylon.registeruser where userId = '"+userId+"'";
			st = con.createStatement();
			rs = st.executeQuery(query);
		while (rs.next())
		      {
				String userName = rs.getString("userName");
				String emailId = rs.getString("userEmailId");
				String contactNumber = rs.getString("userContactNumber");
				String address = rs.getString("userAddress");
				String marksCard = rs.getString("10MarksCard");
				String gasBill = rs.getString("gasbill");
				String electricityBill = rs.getString("electricitybill");
				
				out.print("<html><body>");
				out.print("userName:: " + userName);
				out.print("<br>");
				out.print("emailId:: " + emailId);
				out.print("<br>");
				out.print("contactNumber:: " + contactNumber);
				out.print("<br>");
				out.print("address:: " + address);
				out.print("<br>");
				out.print("<a href='"+marksCard+"' download>Marks Card</a>");
				out.print("<br>");
				out.print("<a href='"+gasBill+"' download>Gas Bill</a>");
				out.print("<br>");
				out.print("<a href='"+electricityBill+"' download>Electricity Bill</a>");
				
		      }
	} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
			 try {
				con.close();
				st.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

}
