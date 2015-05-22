<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="company.model.*,company.model.util.*,java.util.*,java.text.DateFormat"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="generator" content="Bootply" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link href="../css/bootstrap.min.css" rel="stylesheet">
<link href="../css/styles.css" rel="stylesheet">
<% 		  String path = "";
		  String name = "";
		  String title = "";
          if(session.getAttribute("type").equals("Employee")){
        	  Employee employee = ManagedEmployeeBean.getById(Integer.parseInt(session.getAttribute("user").toString()));
        	  if(employee != null){
        	  path = "./employee";
        	  name=employee.getName();
        	  title="Employee";
        	  }
          }else { 
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
                <li><a href="#"><i class="glyphicon glyphicon-flag"></i> Reports</a></li>
                <li><a href="../logout"><i class="glyphicon glyphicon-off"></i> Logout</a></li>
            </ul>
        </li>
      </ul>
           
      <hr>
      
  	</div><!-- /col-3 -->
    <div class="col-sm-9">
      	
      <!-- column 2 -->	
      <a href=<%=path+".jsp"%>><strong><i class="glyphicon glyphicon-dashboard"></i> My Dashboard</strong></a>  
      
      	<hr>
      
		<div class="row">
            <!-- center left-->	
         	<div class="col-md-6">
         	<form action="../signout" method="post">
			  <div class="well">Sign In Status: 
			  <%	Date date = new Date();
				DateFormat df = DateFormat.getDateInstance();
				Signin signin=null;
				String status = "NULL";
				try{
					signin = ManagedSigninBean.getByIdDate(Integer.parseInt(session.getAttribute("user").toString()), df.format(date),"YES");
					try{
						signin = ManagedSigninBean.getByIdDate(Integer.parseInt(session.getAttribute("user").toString()), df.format(date),"NO");
						status = "&nbsp;&nbsp;You Have Signed Out!!";
					}catch(Exception e){
						status = "&nbsp;&nbsp;<button type='submit' class='btn btn-primary'>You Have Signed In!!</button>";
					}
				}catch(Exception e){
					status = "&nbsp;&nbsp;<button type='submit' class='btn btn-primary'>Please Sign In!!</button>";
				}
			  %>
			  <%=status %>
			  </div>
			  </form>
              <hr>
              <div class="panel panel-default">
                    
                  <div class="panel-heading"><h4>Notices(<%=df.format(date) %>)</h4></div>
                  <div class="panel-body">
                  	<%
                    try{
                    	Notice notice = ManagedNoticeBean.getByDate(df.format(date));
                    	%><%=notice.getContent() %><%
                    }catch(Exception e){
                    	%><%="0 Notice Today :)" %><%
                    }
                    
                    %>
                  <br><br>
                  </div>
              	</div>          
              
          	</div><!--/col-->
        	<div class="col-md-6">
				    <a href="#"><strong><i class="glyphicon glyphicon-comment"></i> Discussions</strong></a>  
      
			      <hr>
			      
			      <div class="row">
			        <div class="col-md-12">
			          <ul class="list-group">
			            <li class="list-group-item"><a href="#"><i class="glyphicon glyphicon-flash"></i> <small>(3 mins ago)</small> The 3rd page reports don't contain any links. Does anyone know why..</a></li>
			            <li class="list-group-item"><a href="#"><i class="glyphicon glyphicon-flash"></i> <small>(1 hour ago)</small> Hi all, I've just post a report that show the relationship betwe..</a></li>
			            <li class="list-group-item"><a href="#"><i class="glyphicon glyphicon-heart"></i> <small>(2 hrs ago)</small> Paul. That document you posted yesterday doesn't seem to contain the over..</a></li>
			            <li class="list-group-item"><a href="#"><i class="glyphicon glyphicon-heart-empty"></i> <small>(4 hrs ago)</small> The map service on c243 is down today. I will be fixing the..</a></li>
			            <li class="list-group-item"><a href="#"><i class="glyphicon glyphicon-heart"></i> <small>(yesterday)</small> I posted a new document that shows how to install the services layer..</a></li>
			            <li class="list-group-item"><a href="#"><i class="glyphicon glyphicon-flash"></i> <small>(yesterday)</small> ..</a></li>
			          </ul>
			        </div>
			      </div>
			</div><!--/col-span-6-->      
  	</div><!--/col-span-9-->
</div>
</div>
</div>
</div>
</div>
<!-- /Main -->

</body>
</html>