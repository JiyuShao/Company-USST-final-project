<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="company.model.*,company.model.util.*,javax.persistence.EntityManager,java.util.*"%>
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
		  Integer pageSize = 3;
		  try{
			  pageNum =Integer.parseInt(request.getParameter("pageNum"));
		  }catch(Exception e){}
		  
		  
		  Admin admin = null;
          if(session.getAttribute("type").equals("Admin")){
        	  admin = ManagedAdminBean.getById(Integer.parseInt(session.getAttribute("user").toString()));
        	  if(admin != null){
        	  path = "./admin";
        	  name=admin.getName();
        	  title="Administriation";
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
      <a href=<%=path+".jsp"%>><strong><i class="glyphicon glyphicon-dashboard"></i> Manager Index(<%=name %>)</strong></a>  
      
      	<hr>
      	<form>
		<div class="row">
            <!-- center left-->	
         	<div class="col-md-11">
	         	<table class="table table-striped table-hover" data-height="299" data-sort-name="name" data-sort-order="desc">
				    <thead>
					    <tr>
					        <th class="col-md-2">ID</th>
					        <th class="col-md-2">Name</th>
					        <th class="col-md-3">Email</th>
					        <th class="col-md-3">Admin</th>
					        <th class="col-md-2">Status</th>
					        
					    </tr>
				    </thead>
				    <tbody>
			<% 
			EntityManager em = JPAResourceBean.getEMF().createEntityManager();
			admin = em.find(Admin.class, Integer.parseInt(session.getAttribute("user").toString()));
			if(admin != null){
				List<Manager> managers = new ArrayList<Manager>(admin.getManagers());
				Integer i=0;
				totalPages = admin.getManagers().size()/pageSize+1;
				for(i=(pageNum-1)*pageSize;i<pageSize*pageNum && i<admin.getManagers().size();i++) { 
				%>
				<tr>
					 <td><input type="radio" name="employees"  value=<%=managers.get(i).getManagerId()%>><%=managers.get(i).getManagerId()%></td>
					<td><%=managers.get(i).getName() %></td>
					<td><%=managers.get(i).getEmail() %></td>
					<td><%=ManagedManagerBean.getAdminName(managers.get(i).getManagerId()) %></td>
					<td><%=managers.get(i).getStatus() %></td>
				</tr>
				<% }
			} else { %>
				<tr>
				     <td><input type="radio" name="employees" value="null"></td>
				     <td>null</td>
				     <td>null</td>
				     <td>null</td>
				</tr>
			<% } %>					    	
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
					    	<li><a href=<%="./managerIndex.jsp?pageNum="+i%>><%=i%></a></li>
					    <% }%>
					    <li>
					      <a href="#" aria-label="Next">
					        <span aria-hidden="true">&raquo;</span>
					      </a>
					    </li>
					  </ul>
					</nav>				
			<button type="submit" class="btn btn-primary">Update</button>
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