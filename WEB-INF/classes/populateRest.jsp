<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
    .foot{
     background-color:#aca9a9!important;
     color: white;
     padding: 10px 20px;
     font-size: 20px;
     text-align: center;
}
    </style>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">

    <title>Restaurants</title>
</head>


<body>
   <nav class="navbar navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="/app02/">OnlineMeal</a>
            <form class="d-flex" action="/app02/searchServlet" method="POST">
                <input class="form-control me-2" type="search" placeholder="Enter Location" aria-label="Search">
                <button class="btn btn-outline-success" type="submit">Search</button>
            </form>
        </div>
    </nav>
	<h1 class="foot">Restaurant in <%= request.getParameter("searchString")%> </h1>
	//Declaration
   <%!
       Statement stmt = null;
       Connection con = null;
       ResultSet rs = null;
       StringBuffer sb = new StringBuffer("");
   %>
      //JDBC Code
    <%
            try{  

Class.forName("com.mysql.cj.jdbc.Driver");  

con=DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinemeal",
"root","root");  


 stmt=con.createStatement();  


 rs=stmt.executeQuery("select * from Employee where city="+request.getParameter("searchString"));  

while(rs.next()){
	sb=sb+"div class='card' style='width: 18rem;'><img class='card-img-top' src='https://source.unsplash.com/1600x900/?food' alt='Card image cap'><div class='card-body'><h5 class='card-title'><%= rs.getString(1)%></h5><p class='card-text'><%= rs.getString(2)%></p><a href='#' class='btn btn-primary'>Menu</a></div></div>"

}

out.println(sb);
con.close();    
} //try block ends
catch(Exception e)
{ System.out.println(e);} 

%>
 <footer class="foot">
        <div class="center">
            Copyright &copy; www.myOnlineMeal.com All Right Reserve!
        </div>
    </footer>




    <!-- Optional JavaScript; choose one of the two! -->

    <!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf"
        crossorigin="anonymous"></script>

    }

</body>

</html>