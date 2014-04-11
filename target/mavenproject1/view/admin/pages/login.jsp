<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
	<link rel="stylesheet" href="<html:rewrite page='/view/admin/stylesheet/layout.css'/>" />
	<script type="text/javascript" src="<html:rewrite page='/view/admin/javascript/jquery/jquery-1.3.2.min.js'/>"></script>	
	<script type="text/javascript" src="<html:rewrite page='/view/admin/javascript/jquery/superfish/js/superfish.js'/>"></script>	
	<title>Login</title>
</head>
<body>
<div id="header">Administrator</div>
<div id="menu"></div>
<div id="login">
  <div class="div1">Login</div>
  <div class="div2">
  	 <form action="<html:rewrite action='/actions/admin/adminLogin'/>" id="loginAdmin">
  	 <logic:notEmpty name="warning">
  	 	<div class="warning">Invalid Name or Password</div>
  	 </logic:notEmpty>  	 
      <table>
        <tr>
          <td align="center" rowspan="3">
          <img src="<html:rewrite page='/view/admin/images/login.png'/>" alt="" />
          </td>
        </tr>
        <tr>
          <td>User Name<br />
          	<input type="text" name="name" style="margin-top: 4px;">            
            <br />
            <br />
            Password<br />
            <input type="password" name="password" style="margin-top: 4px;">            
            </td>
        </tr>
        <tr>
          <td align="right"><a href="#" onclick="loginAdmin.submit();" class="button" style="text-decoration:none;">
          <span class="button_left button_login"></span>
          <span class="button_middle">Login</span>
          <span class="button_right"></span></a>
          </td>
        </tr>
      </table>
     </form>   
  </div>
  <div class="div3"></div>
</div>
</body>
</html>