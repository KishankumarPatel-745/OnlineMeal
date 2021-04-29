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


public class EmailVerify extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String uID=request.getParameter("userID");
		int otp1=Integer.parseInt(request.getParameter("otp"));
	
	    String query=String.format("select * from customers where userName ='%s';",uID);
		System.out.println(uID+"::"+otp1);
		int verified=0;
		
		String updateQuery=String.format("update customers set userVerified=true where userName = '%s';",uID);
		
	    try{
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinemeal","root","root");
            Statement stmt=con.createStatement();  
			
			
   
			
            ResultSet rs=stmt.executeQuery(query); 
            System.out.println("after rs");			
            while(rs.next()){  
               
			   int dbOtp=rs.getInt("otp"); 
			   System.out.println(dbOtp);
			   if(dbOtp==otp1){
				   //stmt.executeUpdate(updateQuery);
				   //System.out.println("email verified");
				   verified=1;
			
				   }else{
					   RequestDispatcher view = request.getRequestDispatcher("failureLogin.html");
					   view.forward(request, response);
				   }			   
			 } 
			 Statement st=con.createStatement();
			 if(verified==1){
			 int count=st.executeUpdate(updateQuery);
			 System.out.println("Update Query run"+count);
			 }
			 RequestDispatcher view = request.getRequestDispatcher("login.html");
					   view.forward(request, response);
			con.close();
			
		}catch(Exception e){
			RequestDispatcher view = request.getRequestDispatcher("commonError.html");
            view.forward(request, response);
			System.out.println(e);
			
			
		}
			
		
		
	}


}