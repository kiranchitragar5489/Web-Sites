

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Register")
public class Register extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	public Register() 
	{
		super();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  

		String userName=request.getParameter("userName");  
		String password=request.getParameter("userPass");  
		String emailId=request.getParameter("userEmail");  
		String address=request.getParameter("userAddress");
		String contact=request.getParameter("userContactNumber");


		String location = request.getParameter("isTitles");
		String marksCard = request.getParameter("marksCard");
		String gasBill = request.getParameter("gasBill");
		String electricityBill = request.getParameter("electricityBill");
		try
		{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/waylon","root","root");  
			
			String query = "SELECT * FROM waylon.registeruser";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			List<Integer> KAList = new ArrayList<Integer>();
			List<Integer> MHList = new ArrayList<Integer>();
			List<Integer> DLList = new ArrayList<Integer>();
			while (rs.next())
		      {
		        String userId = rs.getString("userId");
		        String subString = userId.substring(0, 2);
		        if(subString.equalsIgnoreCase("KA"))
		        {
		        	KAList.add(Integer.parseInt(userId.substring(2,userId.length())));
		        }
		        else if(subString.equalsIgnoreCase("MH"))
		        {
		        	MHList.add(Integer.parseInt(userId.substring(2,userId.length())));
		        }
		        else if(subString.equalsIgnoreCase("DL"))
		        {
		        	DLList.add(Integer.parseInt(userId.substring(2,userId.length())));
		        }
		      }
		      st.close();
			
			PreparedStatement ps=con.prepareStatement("insert into waylon.registeruser values(?,?,?,?,?,?,?,?,?)");
			String userId = null ;
			
			if(location.equalsIgnoreCase("KA"))
			{
				if(KAList.isEmpty())
				{
					userId = location+"1";
				}
				else
				{
					Integer i = Collections.max(KAList);
					i = i+1;
					userId = location+i;
				}
			}
			else if(location.equalsIgnoreCase("MH"))
			{
				if(MHList.isEmpty())
				{
					userId = location+"1";
				}
				else
				{
					Integer i = Collections.max(KAList);
					i = i+1;
					userId = location+i;
				}
			}
			else if(location.equalsIgnoreCase("DL"))
			{
				if(DLList.isEmpty())
				{
					userId = location+"1";
				}
				else
				{
					Integer i = Collections.max(KAList);
					i = i+1;
					userId = location+i;
				}
			}
			ps.setString(1,userId);
			ps.setString(2,userName);  
			ps.setString(3,password);  
			ps.setString(4,emailId);  
			ps.setString(5,contact);
			ps.setString(6,address);
			ps.setString(7,marksCard);
			ps.setString(8,gasBill);
			ps.setString(9,electricityBill);

			int i=ps.executeUpdate();  
			if(i>0)  
			{
				response.sendRedirect("dashboard.html");
			}
			else
			{
				System.out.println("Failed");
			}
			con.close();  
		}
		catch(Exception e1)
		{
			System.out.println(e1);
		}  


	}

}
