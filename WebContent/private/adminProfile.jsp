<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="company.model.*,company.model.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Administration</title>
<meta name="generator" content="Bootply" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link href="../css/bootstrap.min.css" rel="stylesheet">
<link href="../css/styles.css" rel="stylesheet">
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
      <a class="navbar-brand" href="./admin.jsp">Administration</a>
    </div>
    <div class="navbar-collapse collapse">
      <ul class="nav navbar-nav navbar-right">
        <li class="dropdown">
          <a class="dropdown-toggle" role="button" data-toggle="dropdown" href="./admin.jsp"><i class="glyphicon glyphicon-user"></i>
          <%Admin admin = ManagedAdminBean.getById(Integer.parseInt(session.getAttribute("user").toString()));
          if( admin != null  && session.getAttribute("type").equals("Admin")){ %>
          <%=admin.getName()%>
          <%	} else { %>
				<h2>NULL</h2>
		<%	} %>
          
          </a>
				
        </li>
        <li><a href="../logout"><i class="glyphicon glyphicon-lock"></i> Logout</a></li>
      </ul>
    </div>
  </div><!-- /container -->
</div>
<!-- /Header -->

<!-- Main -->
	<div class="col-sm-3">
      <!-- Left column -->
      <a href="#"><strong><i class="glyphicon glyphicon-wrench"></i> Tools</strong></a>  
      
      <hr>
      
      <ul class="list-unstyled">
        <li class="nav-header"> <a href="#" data-toggle="collapse" data-target="#userMenu">
          <h5>Settings </h5>
          </a>
            <ul class="list-unstyled collapse in" id="userMenu">
                <li class="active"> <a href="./admin.jsp"><i class="glyphicon glyphicon-home"></i> Home</a></li>
                <li><a href="#"><i class="glyphicon glyphicon-envelope"></i> Messages <span class="badge badge-info">4</span></a></li>
                <li><a href="./adminProfile.jsp"><i class="glyphicon glyphicon-user"></i> Profile</a></li>
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
      <a href="./adminProfile.jsp"><strong><i class="glyphicon glyphicon-dashboard"></i> My Profile</strong></a>  
      
      	<hr>
      
		<div class="row">
            <!-- center left-->	
         	<div class="col-md-6">
				<div class="form-group">
					<form class="form-horizontal" action="../updateAdmin" method="post">
						<div class="form-group">
					    	<label for="user" class="col-sm-2 control-label">ID</label>
					    	<div class="col-sm-10">
					      	<input type="text" class="form-control" name="user" placeholder="ID" value="<%=admin.getAdminId()%>" disabled="true">
					   		</div>
					  	</div>
					  	<div class="form-group">
					    	<label for="name" class="col-sm-2 control-label">Name</label>
					    	<div class="col-sm-10">
					      	<input type="text" class="form-control" name="name" placeholder="Name" value="<%=admin.getName()%>">
					  		</div>
					  	</div>
					  	<div class="form-group">
					   		<label for="birthday" class="col-sm-2 control-label">Birthday</label>
					    	<div class="col-sm-10">
					      	<input type="text" class="form-control" name="birthday" placeholder="Birthday" value="<%=admin.getBirthday()%>">
					    	</div>
					  	</div>
					  	<div class="form-group">
					   		<label for="email" class="col-sm-2 control-label">Email</label>
					    	<div class="col-sm-10">
					      	<input type="text" class="form-control" name="email" placeholder="Email" value="<%=admin.getEmail()%>">
					    	</div>
					  	</div>
					  	<div class="form-group">
					   		<label for="gender" class="col-sm-2 control-label">Gender</label>
					    	<div class="col-sm-10">
					      	<input type="text" class="form-control" name="gender" placeholder="Gender" value="<%=admin.getGender()%>">
					    	</div>
					  	</div>
					  	<div class="form-group">
					   		<label for="phone" class="col-sm-2 control-label">Phone</label>
					    	<div class="col-sm-10">
					      	<input type="text" class="form-control" name="phone" placeholder="Phone" value="<%=admin.getPhone()%>">
					    	</div>
					  	</div>
					  	<div class="form-group">
					   		<label for="password" class="col-sm-2 control-label">Password</label>
					    	<div class="col-sm-10">
					      	<input type="password" class="form-control" name="password" placeholder="Password">
					    	</div>
					  	</div>
					  	<div class="form-group">
					    	<div class="col-sm-offset-2 col-sm-10">
					      	<button type="submit" class="btn btn-default">Submit</button>
					    	</div>
					  	</div>
					  	
					</form>

				</div>
  	</div><!--/col-span-9-->
</div>
</div>
<!-- /Main -->

</body>
</html>