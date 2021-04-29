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



public class ContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String name=request.getParameter("name");
		long phoneNumber =Long.parseLong(request.getParameter("phone"));
		String email=request.getParameter("email");
		String message=request.getParameter("message");
		int tokenNumber=(int)(10000.0 * java.lang.Math.random());

		
	    try{
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinemeal","root","root");  
			 
			PreparedStatement stmt=con.prepareStatement("insert into quaries(name,phoneNumber,email,message,tokenNumber) values (?,?,?,?,?)");  
			stmt.setString(1,name);
			stmt.setLong(2,phoneNumber);
			stmt.setString(3,email);
			stmt.setString(4,message);
			stmt.setInt(5,tokenNumber);
			int i=stmt.executeUpdate();
			String [] recepients =new String[]{email};
		    String [] bccRecepients =new String[]{email};
		    String subject ="Hi "+name+" we got your quary";
		    String messageMail ="Hi "+name+" , we received your quary and this is a refenence number for further info. "+tokenNumber;
		
		    new SendEmail().sendMail(recepients, bccRecepients, subject, messageMail);
			RequestDispatcher view = request.getRequestDispatcher("quaryMessage.html");
            view.forward(request, response);
		   con.close();
	  }catch(Exception e){
		    RequestDispatcher view = request.getRequestDispatcher("commonError.html");
            view.forward(request, response);
			System.out.println(e);
			out.println("Error while DB operation");
			
			
		}
			
		
		
	}


}