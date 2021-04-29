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


public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String name=request.getParameter("restName");
		String address=request.getParameter("restAddress");
		double fee=Double.parseDouble(request.getParameter("restFee"));
		String city=request.getParameter("restCity");
		String phone=request.getParameter("restPhone");
		String email=request.getParameter("restEmail");
		
		
		System.out.println(name+"  :: "+fee+" :: "+ email);
		
	    try{
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinemeal","root","root");  
			 
			PreparedStatement stmt=con.prepareStatement("insert into foodShops(name,address,deliveryFee,city,phoneNumber,email) values (?,?,?,?,?,?)");  
			stmt.setString(1,name);
			stmt.setString(2,address);
			stmt.setDouble(3,fee);
			stmt.setString(4,city);
			stmt.setString(5,phone);
			stmt.setString(6,email);
			int i=stmt.executeUpdate();
			if(i>0){
				String [] recepients =new String[]{email};
		        String [] bccRecepients =new String[]{email};
				String subject ="Hi "+name+", Welcome to OnlineMeal.com";
				String messageMail ="Hi "+name+", we have created your account at OnlineMeal.com";
		
				new SendEmail().sendMail(recepients, bccRecepients, subject, messageMail);
				
				// Math.random() -- create a random no of 4 digits and then store it into otp variable
				RequestDispatcher view = request.getRequestDispatcher("registerComplete.html");
				view.forward(request, response);	
			}
			con.close();
		}catch(Exception e){
			RequestDispatcher view = request.getRequestDispatcher("commonError.html");
            view.forward(request, response);
			System.out.println(e);
			
		}
			
		
		
	}


}