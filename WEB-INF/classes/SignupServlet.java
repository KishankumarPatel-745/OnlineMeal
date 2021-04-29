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


public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String userName=request.getParameter("myUserName");
		String name=request.getParameter("myName");
		String pass=request.getParameter("myPassword");
		String phone=request.getParameter("myPhone");
		String email=request.getParameter("myEmail");
		int otp=(int)(10000.0 * java.lang.Math.random());
		System.out.println(name+"  :: "+userName+" :: "+ otp);
		
	    try{
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinemeal","root","root");  
			 
			PreparedStatement stmt=con.prepareStatement("insert into customers(userName,name,password,phone,email,otp) values (?,?,?,?,?,?)");  
			stmt.setString(1,userName);
			stmt.setString(2,name);
			stmt.setString(3,pass);
			stmt.setString(4,phone);
			stmt.setString(5,email);
			stmt.setInt(6,otp);
			int i=stmt.executeUpdate();
			if(i>0){
				String [] recepients =new String[]{email};
		        String [] bccRecepients =new String[]{email};
				String subject ="Hi "+name+", Welcome to OnlineMeal.com";
				String messageMail ="Hi "+name+", we have created your account at OnlineMeal.com, here is a otp to verify your email:---"+otp;
		
				new SendEmail().sendMail(recepients, bccRecepients, subject, messageMail);
				
				// Math.random() -- create a random no of 4 digits and then store it into otp variable
				RequestDispatcher view = request.getRequestDispatcher("verifyEmail.html");
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