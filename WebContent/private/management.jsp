<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="company.model.*,company.model.util.*,javax.persistence.EntityManager"%>
    <!DOCTYPE html>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="generator" content="Bootply" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link href="../css/bootstrap.css" rel="stylesheet">
<link href="../css/bootstrap.min.css" rel="stylesheet">
<link href="../css/styles.css" rel="stylesheet">
<script type="text/javascript" src="../js/bootstrap.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/jquery.js"></script>

<% 		  String path = "";
		  String name = "";
		  String title = "";
		  Manager manager = null;
          if(session.getAttribute("type").equals("Admin")){
        	  Admin admin = ManagedAdminBean.getById(Integer.parseInt(session.getAttribute("user").toString()));
        	  if(admin != null){
        	  path = "./admin";
        	  name=admin.getName();
        	  title="Administriation";
        	  }
          } else if(session.getAttribute("type").equals("Manager")){
          	manager = ManagedManagerBean.getById(Integer.parseInt(session.getAttribute("user").toString()));
          	if(manager != null){
          		path = "./manager";
          		name = manager.getName();
          		title = "Manager";
          	}
          } else { 
  			out.print("<script>alert('No permission!');window.location.href='"+request.getContextPath()+"/login.html';</script>");
          } %>
<title><%=title%></title>
</head>
<body>
<!-- Header -->
<div id="top-nav" class="navbar navbar-inverse navbar-static-top">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href=<%=path+".jsp"%>><%=title%></a>
    </div>
    <div class="navbar-collapse collapse">
      <ul class="nav navbar-nav navbar-right">
        <li class="dropdown">
          <a class="dropdown-toggle" role="button" data-toggle="dropdown" href=<%=path+".jsp"%>><i class="glyphicon glyphicon-user"></i><%=name%></a>
				
        </li>
        <li><a href="../logout"><i class="glyphicon glyphicon-lock"></i> Logout</a></li>
      </ul>
    </div>
  </div><!-- /container -->
</div>
<!-- /Header -->

<!-- Main -->
<div class="container-fluid">
<div class="row">
	<div class="col-sm-3">
      <!-- Left column -->
      <a href="#"><strong><i class="glyphicon glyphicon-wrench"></i> Tools</strong></a>  
      
      <hr>
      
      <ul class="list-unstyled">
        <li class="nav-header"> <a href="#" data-toggle="collapse" data-target="#userMenu">
          <h5>Settings</h5>
          </a>
            <ul class="list-unstyled collapse in" id="userMenu">
                <li class="active"> <a href=<%=path+".jsp"%>><i class="glyphicon glyphicon-home"></i> Home</a></li>
                <li><a href="#"><i class="glyphicon glyphicon-envelope"></i> Messages <span class="badge badge-info">4</span></a></li>
                <li><a href=<%=path+"Profile.jsp"%>><i class="glyphicon glyphicon-user"></i> Profile</a></li>
                <li><a href="#"><i class="glyphicon glyphicon-cog"></i> Management</a></li>
                <li><a href="#"><i class="glyphicon glyphicon-flag"></i> Reports</a></li>
                <li><a href="../logout"><i class="glyphicon glyphicon-off"></i> Logout</a></li>
            </ul>
        </li>
      </ul>
           
      <hr>
      
  	</div><!-- /col-3 -->
    <div class="col-sm-9">
      	
      <!-- column 2 -->	
      <a href=<%=path+".jsp"%>><strong><i class="glyphicon glyphicon-dashboard"></i> Employee Index(<%=name %>)</strong></a>  
      
      	<hr>
      		<%if(title.equals("Administriation")){%>
      			<a href="./managerIndex.jsp"><button class="btn btn-primary" type="button">Manager Index</button></a>
      		<br><br><br>
      		<%} else if(title.equals("Manager")){%>
      			<a href="./employeeIndex.jsp"><button class="btn btn-primary" type="button">Employee Index</button></a>
      			<br><br><br>
      			
      			<a href="./employeeOrientation.jsp"><button class="btn btn-primary" type="button">Orientation</button></a>
				<br><br><br>
      		<%} %>
      		<a href="./signUp.jsp"><button class="btn btn-primary" type="button">Sign Up</button></a>
      		<br><br><br>
	 </div>
 </div>		  
</div>
<!-- /Main -->
</body>
</html>