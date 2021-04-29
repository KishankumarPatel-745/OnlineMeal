import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String name=request.getParameter("myUser");
		String pass=request.getParameter("myPassword");
	
		System.out.println(name+pass);
		
	    try{
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinemeal","root","root");  
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("select * from customers");  
            while(rs.next()){  
               String tempName=rs.getString("userName");
			   String tempPass=rs.getString("password"); 
			   if(name.equalsIgnoreCase(tempName) && pass.equalsIgnoreCase(tempPass)){
				   if(rs.getBoolean("userVerified")==false){
					   RequestDispatcher view = request.getRequestDispatcher("failureLogin.html");
					   view.forward(request, response);
					   
				   }else{
					   RequestDispatcher view = request.getRequestDispatcher("successLogin.html");
					   view.forward(request, response);
				   }
			 }else{
				 RequestDispatcher view = request.getRequestDispatcher("failureLogin.html");
					   view.forward(request, response);
				 
			 }
			}
			con.close();
		}catch(Exception e){
			RequestDispatcher view = request.getRequestDispatcher("commonError.html");
            view.forward(request, response);
			System.out.println(e);
			
			
		}
			
		
		
	}


}