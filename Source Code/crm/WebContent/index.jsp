<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
	StringBuffer url = new StringBuffer("http://").append(request.getServerName());
	int port = request.getServerPort();
	//if (port > 5000) // developer port number starts at 5001
	url.append(":").append(port);
	url.append(request.getContextPath());
	url.append("/view/dashboard/index.jsf");
	if(request.getParameter(".")!=null){
		url.append("?.="+request.getParameter(".")).append("&..=").append(request.getParameter(".."));
	}
	response.sendRedirect(url.toString()); 
%>