<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.bitguiders.util.security.Encryptor" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/validation.js"></script>
<script>
function validateForm(){
	//alert("here i'm. this is user name :" + document.getElementById("j_username").value + "this is password: " +document.getElementById("j_password").value );
	var signInId = document.getElementById("j_username");
	var password = document.getElementById("j_password");

	if(isEmpty(signInId)){
			//alert('Sign In Id is required');
			signInId.parentNode.getElementsByTagName("span")[0].innerHTML = "Sign In Id is required.";
			return false;
		}
	/*else if(!isValidEmail(signInId)){
		signInId.parentNode.getElementsByTagName("span")[0].innerHTML = "Invalid Email address";
		return false;
		}*/
		/*	if(signInId !=("j_username"))
				{
			signInId.parentNode.getElementsByTagName("span")[0].innerHTML = "Sign In Id is not valid";
			return false;
			}*/
	if(isEmpty(password))
		{
		//alert('Password is required');
		password.parentNode.getElementsByTagName("span")[0].innerHTML = "Password is required.";
		return false;
		}
			if(password){
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
if((signInId != signInId) && (password != password) ){
	out.println("<span class='error'>Invalid Username or Password</span>");
	return false;
}
	return true;
}

	

</script>
 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Login</title>
<link href="<%=request.getContextPath()%>/resources/themes/default/default.css" rel="stylesheet" type="text/css" />

    
</head>

<body class="login-screen">

<div id="header">
<div class="logo">
<a href="#" title="Business Intelligence"><img src="<%=request.getContextPath()%>/resources/themes/default/images/BusinessIntelligence.png" style="float:left" /></a></div>
<!--  <a href="http://www.lmkr.com/" target="new">
<img src="<%=request.getContextPath()%>/themes/${initParam.theme}/images/srpl.png" style="float:right" />
		 </a>-->
</div>
 <!--  <%-->
	<!--if(request.getParameter("loginerror")!=null)-->
	<!--out.println("Invalid Username or Password");-->
<!--%>-->
<div class="login-holder">
  <table border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td class="login-tbg" align="justify"><span class="heading-black">User </span><span class="heading-blue">Login</span></td>
  </tr>
  <tr>
    <td align="center" valign="top" class="login-bg"><table width="353" border="0" align="center" cellpadding="0" cellspacing="0">
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
        
         <form   class="login"   action="j_security_check?loginerror=true" onsubmit="return validateForm();" method="post">
          
          <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td align="left" valign="middle">Login Name / Email</td>
            </tr>
            <tr>
              <td align="left" valign="middle">
              <input type="text" name="j_username" id="j_username" /><br/><span class='error'></span></td>
            </tr>
            <tr>
              <td align="left" valign="middle">&nbsp;</td>
            </tr>
            <tr>
              <td align="left" valign="middle">Password</td>
            </tr>
            <tr>
              <td align="left" valign="middle">
              <input type="password" name="j_password" id="j_password" /><br/><span class='error' ></span>
               <%
		if(request.getParameter("loginerror")!=null)
		out.println("<span class='error'>The username or password you entered is incorrect.</span>");
			%></td>
            </tr>
            <tr>
            <td align="left" valign="middle">&nbsp;</td>
            </tr>
            <tr>
              <td align="left" valign="middle"><input name="button" type="submit" class="login-btn" id="button" value="" />
                <!-- <span class="flt-ryt"><a href="<%=request.getContextPath()%>/themes/${initParam.theme}/ForgotPassword.xhtml">Forgot Password?</a></span> -->
                <span  class="flt-ryt">
                <a href="<%=request.getContextPath()%>/view/public/ForgotPassword.jsf">Forgot Password?</a>
                </span></td>
            </tr>
            <tr>
              <td align="left" valign="middle">&nbsp;</td>
            </tr>
          </table>
        </form></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td class="login-bbg">&nbsp;</td>
  </tr>
</table>
</div> <!-- End login holder -->
<div class="clear"></div>
<div class="footer login-screen-border">[ Developed by:<a href="http://www.systemsresearchltd.com" onclick="window.open(this.href);return false;">SYSTEMS RESEARCH (Pvt.) Ltd</a>. ]</div>

</body>
</html>
