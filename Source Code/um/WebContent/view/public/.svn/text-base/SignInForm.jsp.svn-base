<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.bitguiders.util.security.Encryptor" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/validation.js"></script>
<script>
	function validateForm() {
		//alert("here i'm. this is user name :" + document.getElementById("j_username").value + "this is password: " +document.getElementById("j_password").value );
		var signInId = document.getElementById("j_username");
		var password = document.getElementById("j_password");
		var application = document.getElementById("application");

		if (isEmpty(application)) {
			//alert('Sign In Id is required');
			application.parentNode.getElementsByTagName("span")[0].innerHTML = "Application is required.";
			return false;
		}
		
		if (isEmpty(signInId)) {
			//alert('Sign In Id is required');
			signInId.parentNode.getElementsByTagName("span")[0].innerHTML = "Sign In Id is required.";
			return false;
		}
		else if(!isValidEmail(signInId)){
			signInId.parentNode.getElementsByTagName("span")[0].innerHTML = "Enter valid email [ xxx@xxx.xxx ]";
			return false;
			}
		/*	if(signInId !=("j_username"))
				{
			signInId.parentNode.getElementsByTagName("span")[0].innerHTML = "Sign In Id is not valid";
			return false;
			}*/
		if (isEmpty(password)) {
			//alert('Password is required');
			password.parentNode.getElementsByTagName("span")[0].innerHTML = "Password is required.";
			return false;
		}
		if (password) {
		}
		/* if(password !=("j_password"))
		{
		signInId.parentNode.getElementsByTagName("span")[0].innerHTML = "Password is not valid";
		return false;
		}*/
		/*
		else if(!isValidPassword(password)){
		password.parentNode.getElementsByTagName("span")[0].innerHTML = "Invalid Password";
		return false;
		}*/
		if ((signInId != signInId) && (password != password)) {
			out
					.println("<span class='error'>Invalid Username or Password</span>");
			return false;
		}
	/*var changeCase = signInId.toLowerCase();
	document.getElementById("j_username").value = changeCase;*/
		return true;
	}
</script>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Login</title>
<link
	href="<%=request.getContextPath()%>/resources/themes/${initParam.theme}/${initParam.theme}.css"
	rel="stylesheet" type="text/css" />


</head>

<body class="login-screen">

	<div id="header">
		<div class="logo">
			<a href="#" title="Customer Relationship Management"><img
				src="<%=request.getContextPath()%>/resources/themes/${initParam.theme}/images/tbaaslogo.png"
				style="float:left;padding-bottom:0px;" /></a>
		</div>
		<!--  <a href="http://www.lmkr.com/" target="new">
<img src="<%=request.getContextPath()%>/themes/${initParam.theme}/images/srpl.png" style="float:right" />
		 </a>-->
	</div>
	<!--  <%-->
	<!--if(request.getParameter("loginerror")!=null)-->
	<!--out.println("Invalid Username or Password");-->
<!--%>-->
	<div class="login-holder">
	<table border="0">
	<tr>
	<td></td>
	</tr>
	
	<tr>
	<td style="width:50%;height:50%;">
	<span class="heading-grey" > User <span class="login-heading-blue"> Management<span class="subscript-align" >
	<sub><font  face="verdana" color="grey" size="2">version-1.1.0</font></sub>
	</span></span>
	 </span>
	
	</br>
	
	</td>
	</tr>
	<tr><td></td>
	</tr>
	<tr>
	<td style="width:50%;height:50%;">
	<img src="<%=request.getContextPath()%>/resources/themes/${initParam.theme}/images/security.png" style="float:left;padding-right:20px;" />
	<span class="paragraph-grey ">
	<p>
	<span class="sub-heading-grey">Security </span></br>
	Application level dynamic service registration. Easy and flexible Security
	implementation. Single Sign On feature.<a href="<%=request.getContextPath()%>/wiki/um-wiki-v2.0.htm#_Toc365902827" onclick="window.open(this.href);return false;"><span class="blue-link"><b>Learn More..</b></span></a>
	</p>
	</br>
	</span>
	<img src="<%=request.getContextPath()%>/resources/themes/${initParam.theme}/images/company.png" style="float:left;padding-right:20px;"  />
	<span class="paragraph-grey ">
	<p>
	<span class="sub-heading-grey">Company</span></br>
	Manage companies & franchises. Set company specific parameters.<a href="<%=request.getContextPath()%>/wiki/um-wiki-v2.0.htm#_Toc365902804" onclick="window.open(this.href);return false;"><span class="blue-link"><b>Learn More..</b></span></a>
	</p>
	</br>
	</span>
	<img src="<%=request.getContextPath()%>/resources/themes/${initParam.theme}/images/groups.png" style="float:left;padding-right:28px;"  />
	<span class="paragraph-grey ">
	<p>
	<span class="sub-heading-grey">Groups</span></br>
	Manage groups set permissions and add users into groups.Define group roles. </br>
	<a href="<%=request.getContextPath()%>/wiki/um-wiki-v2.0.htm#_Toc365902822" onclick="window.open(this.href);return false;"><span class="blue-link"><b>Learn More..</b></span></a>
	</p>
	</br>
	</span>
	<img src="<%=request.getContextPath()%>/resources/themes/${initParam.theme}/images/users.png" style="float:left;padding-right:19px;" />
	<span class="paragraph-grey ">
	<p>
	<span class="sub-heading-grey">Users</span></br>
	User level services and security control. Manage users and add 
	into groups. Activate and In Activate user profiles.<a href="<%=request.getContextPath()%>/wiki/um-wiki-v2.0.htm#_Toc365902816" onclick="window.open(this.href);return false;"><span class="blue-link"><b>Learn More..</b></span></a>
	</p>
	</span>

	
	
	
	
	</td>
	
	
	
	<td style="width:50%;height:50%;">
	<table border="0" align="right" cellpadding="0" cellspacing="0">
			<tr>
				<td  align="justify" class="login-tbg" > <span class="heading-black"> User </span><span
								class="heading-blue">Login</span> </td>
			</tr>
			<tr>
				<td align="center" valign="top" class="login-bg">
				<table	 border="0" align="center" cellpadding="0"
						cellspacing="0">
						
						<tr>
							<td>
							
							
							
							
							
		<%
		
		Encryptor enc = new Encryptor();
		String str = request.getParameter(".");
		String spilitter = request.getParameter("..");
		if(str!=null){
       		String values[] = enc.decrypt(str,spilitter);
	            response.sendRedirect("j_security_check?j_username="+values[0]+"&j_password="+values[1]);
		}
       	%>
							
								<form class="login" action="/um/ApplicationServlet"
									onsubmit="return validateForm();" method="post">

									<table width="100%" border="0" align="center" cellpadding="0"
										cellspacing="0">
										<tr>
											<td align="left" valign="middle">Choose Application</td>
										</tr>
										<tr>
											<td align="left" valign="middle" >
											<select name="application" id="application" style="width:100%;">
													<option value="" title="Select">Select</option>
													<option value="crm" title="crm">CRM</option>
													<option value="bi" title="bi">BI</option>
											</select><br />
											<span class='error'></span> 
											</td>
										</tr>
										<tr>
											<td align="left" valign="middle">&nbsp;</td>
										</tr>
										<tr>
											<td align="left" valign="middle">Email</td>
										</tr>
										<tr>
											<td align="left" valign="middle">
											<input type="text"	name="j_username" id="j_username" /><br />
											<span class='error'></span></td>
										</tr>
										<tr>
											<td align="left" valign="middle">&nbsp;</td>
										</tr>
										<tr>
											<td align="left" valign="middle">Password</td>
										</tr>
										<tr>
											<td align="left" valign="middle"><input type="password"
												name="j_password" id="j_password" /><br />
											<span class='error'></span> <%
 	if (request.getParameter("loginerror") != null)
 		out.println("<span class='error-message'>The username or password you entered is incorrect.</span>");
 %></td>

										</tr>
										<tr>

											<td align="left" valign="middle">&nbsp;</td>
										</tr>
										<tr>
											<td align="left" valign="middle"><input name="button"
												type="submit" class="login-btn" id="button" value="" /> <!-- <span class="flt-ryt"><a href="<%=request.getContextPath()%>/themes/${initParam.theme}/ForgotPassword.xhtml">Forgot Password?</a></span> -->
												<span class="flt-ryt"> <a
													href="<%=request.getContextPath()%>/../crm/view/public/ForgotPassword.jsf">Forgot
														Password?</a>
											</span></td>
										</tr>
										<tr>
											<td align="left" valign="middle">&nbsp;</td>
										</tr>
									</table>
								</form>
							</td>
						</tr>
					</table></td>
			</tr>
			<tr>
				<td class="login-bbg">&nbsp;</td>
			</tr>
		</table>
	
	</td>
	
	
	
	
	</tr>
	

	
	</table>
	
		
	</div>
	<!-- End login holder -->
	<div class="clear"></div>
	<div class="footer login-screen-border">
		[ Developed by: <a href="http://www.systemsresearchltd.com"
			onclick="window.open(this.href);return false;">SYSTEMS RESEARCH
			(Pvt.) Ltd</a>. ]
	</div>
</body>
</html>
