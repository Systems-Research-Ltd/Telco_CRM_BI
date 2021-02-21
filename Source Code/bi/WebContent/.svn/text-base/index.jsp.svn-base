<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
	StringBuffer url = new StringBuffer("http://").append(request.getServerName());
	int port = request.getServerPort();
	//if (port > 5000) // developer port number starts at 5001
	
	url.append(":").append(port);
	url.append(request.getContextPath());
	
	if(request.isUserInRole("Administrator"))
		url.append("/view/um/admin/index.jsf");
	else if(request.isUserInRole("AccountManager"))
		url.append("/view/dashboard/dash.jsf");
	else if(request.isUserInRole("User"))
		url.append("/view/dashboard/dash.jsf");
	if(request.getParameter(".")!=null){
		url.append("?.="+request.getParameter(".")).append("&..=").append(request.getParameter(".."));
	}
	response.sendRedirect(url.toString()); 
%>
