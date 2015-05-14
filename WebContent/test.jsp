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
<%Admin admin = ManagedAdminBean.getById(4);%>
<%=admin.getName() %>

</body>
</html>