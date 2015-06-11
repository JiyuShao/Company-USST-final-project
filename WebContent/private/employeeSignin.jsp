<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="company.model.*,company.model.util.*,javax.persistence.EntityManager,java.util.*,java.text.*"%>
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
		  Integer pageNum = 1;
		  Integer totalPages = 0;
		  Integer pageSize = 6;
		  String date = request.getParameter("date");
		  
		  try{
			  pageNum =Integer.parseInt(request.getParameter("pageNum"));
		  }catch(Exception e){}
		  
		  
		  Manager manager = null;
          if(session.getAttribute("type").equals("Manager")){
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
                <li><a href="./messageIndex.jsp"><i class="glyphicon glyphicon-envelope"></i> Messages <span class="badge badge-info">
                <% 
			  		List<Message> messages = ManagedMessageBean.getByToTypeIdStatus(session.getAttribute("type").toString(),
			  				Integer.parseInt(session.getAttribute("user").toString()),"YES");
			  	%>
			  	<%=messages.size()%>
                </span></a></li>
                <li><a href=<%=path+"Profile.jsp"%>><i class="glyphicon glyphicon-user"></i> Profile</a></li>
                <li><a href="./management.jsp"><i class="glyphicon glyphicon-cog"></i> Management</a></li>
                <li><a href="#"><i class="glyphicon glyphicon-flag"></i> Reports</a></li>
                <li><a href="../logout"><i class="glyphicon glyphicon-off"></i> Logout</a></li>
            </ul>
        </li>
      </ul>
           
      <hr>
      
  	</div><!-- /col-3 -->
    <div class="col-sm-9">
      	
      <!-- column 2 -->	
      <a href=<%=path+".jsp"%>><strong><i class="glyphicon glyphicon-dashboard"></i> Employee Sign In(<%=name %>)</strong></a>  
      
      	<hr>
      	<%
      		if(date==""||date==null){
      			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
      			date = df.format(new Date());
      		}
      	%>
      	<form action="../updateDate" method="post">
      		<input name="date" type="date" value=<%=date%>>
      		<input name="submit" class="btn btn-default" type="submit" value="Update" >
      	</form>
      	
      	<form action="123" method="post">
		<div class="row">
            <!-- center left-->	
         	<div class="col-md-11">
	         	<table class="table table-striped table-hover" data-height="299" data-sort-name="name" data-sort-order="desc">
				    <thead>
					    <tr>
					        <th class="col-md-3">ID</th>
					        <th class="col-md-3">Employee</th>
					        <th class="col-md-3">Time</th>
					        <th class="col-md-3">Status</th>
					        
					    </tr>
				    </thead>
				    <tbody>
			<% 
			try{
				List<Signin> signins = ManagedSigninBean.getTodayList(Integer.parseInt(session.getAttribute("user").toString()), date);
				totalPages = signins.size()/pageSize+1;
				Integer i = 0;
				for(i=(pageNum-1)*pageSize;i<pageSize*pageNum && i<signins.size();i++) { 
					%>
					<tr>
						 <td>&nbsp;<%=signins.get(i).getSigninId()%></td>
						 <td><%=ManagedSigninBean.getEmployeeName(signins.get(i).getSigninId()) %></td>
						<td><%=signins.get(i).getTime() %></td>
						<td><%=signins.get(i).getStatus() %></td>
					</tr>
					<% }
			}catch(Exception e){
				%>
				<tr>
				     <td>null</td>
				     <td>null</td>
				     <td>null</td>
				     <td>null</td>
				</tr>
			<%}%>					    	
		    		</tbody>	
				</table>
					<nav>
					  <ul class="pagination">
					    <li>
					      <a href="#" aria-label="Previous">
					        <span aria-hidden="true">&laquo;</span>
					      </a>
					    </li>
					    <%for(int i=1;i<=totalPages;i++){%>
					    	<li><a href=<%="./employeeSignin.jsp?pageNum="+i%>><%=i%></a></li>
					    <% }%>
					    <li>
					      <a href="#" aria-label="Next">
					        <span aria-hidden="true">&raquo;</span>
					      </a>
					    </li>
					  </ul>
					</nav>				
			</div>
		</div>
		</form>
	 </div>
 </div>		  
</div>
<%
%>
<!-- /Main -->
</body>
</html>