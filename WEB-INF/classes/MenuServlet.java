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
import java.lang.StringBuffer;


public class MenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String name=request.getParameter("restName");
		String query=String.format("select * from menuLists where restName='%s'",name);
		
	
		System.out.println(name);
		
	    try{
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinemeal","root","root");  
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery(query);  
			
			out.println("<!doctype html><html lang='en'><head><meta charset='utf-8'><meta name='viewport' content='width=device-width, initial-scale=1'>");
            out.println("<style>.foot{background-color:#aca9a9!important;color: white;padding: 10px 20px;font-size: 20px;text-align: center;}</style>");
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6' crossorigin='anonymous'>");
            out.println("<title>Menus</title></head>");
			out.println("<body><nav class='navbar navbar-dark bg-dark'><div class='container-fluid'><a class='navbar-brand' href='/app02/''>OnlineMeal</a>");
            out.println("<form class='d-flex' action='/app02/searchServlet' method='POST'><input class='form-control me-2' type='search' placeholder='Enter Location' aria-label='Search'><button class='btn btn-outline-success' type='submit'>Search</button></form></div></nav>");
            out.println("<h1 class='foot'>Menus of "+name+"</h1>");
			//out.println("<div class='row'>");
			out.println("<form action='/app02/cartServlet' method='POST'");
			out.println("<div class='row'>");
			int i=0;
            while(rs.next()){  
			    
			    out.println("<div class='form-check mx-3'><input class='form-check-input' type='checkbox' name='item"+(++i)+"' value='"+rs.getString(2)+"' id='flexCheckDefault'><label class='form-check-label' for='flexCheckDefault'>Select Item</label></div>");
			   //out.println("<div class='col-sm-6'><div class='card'><div class='card-body'><h5 class='card-title'>"+rs.getString(1)+"</h5><p class='card-text'></p>"+rs.getString(2)+"<a href='#' class='btn btn-primary'>Menu</a></div></div></div>");
               //out.println("<div class='card' style='width: 18rem;'><img class='card-img-top' src='https://source.unsplash.com/1600x900/?food' alt='Card image cap'><div class='card-body'><h5 class='card-title'>"+rs.getString(1)+"</h5><p class='card-text'>"+rs.getString(2)+"</p><button type='Submit' class='btn btn-primary'>Menu</submit></div></div>");
			   //out.println("<div class='col-sm-6><div class='card'><div class='card-body'><h5 class='card-title'>"+rs.getString(2)+"</h5><p class='card-text'>"+rs.getString(4)+"</p><p class='card-text'>Price:- "+rs.getInt(3)+"</p></div></div></div>");
			   out.println("<div class='card' style='width: 18rem;'><img class='card-img-top' src='https://source.unsplash.com/1600x900/?food' alt='Card image cap'><div class='card-body'><h5 class='card-title'>"+rs.getString(2)+"</h5><p class='card-text'>Info:- "+rs.getString(4)+"</p><p class='card-text'>Price :- $"+rs.getString(3)+"</p></div></div>");

			   System.out.println("HI this is running");
			}
			 out.println("</div></form>");
			//out.println("</div>");
			out.println("<footer class='foot'><div class='center'>Copyright &copy; www.myOnlineMeal.com All Right Reserve!</div></footer>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js' integrity='sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf' crossorigin='anonymous'></script></body></html>");
			
			con.close();
		}catch(Exception e){
			
			System.out.println(e);
		}
			
		
		
	}


}