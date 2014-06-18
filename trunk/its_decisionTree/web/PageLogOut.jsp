<%-- 
    Document   : PageLogOut
    Created on : Oct 26, 2013, 7:20:05 AM
    Author     : Arin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% 
    String userID = (String) session.getAttribute("useridijpt");
    if(userID==null){
%>
    <jsp:forward page="SignUp_notif.jsp"></jsp:forward>
<%
    } 
%>
<html>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
   <body>
       <div class="header" style="margin-left: 200px; margin-top: 200px;">
            <image src="images/background/headernew.png">
       </div>
       
        <script type="text/javascript">
            <% session.removeAttribute("useridijpt"); %>
             alert("Terima kasih telah menggunakan IJPT (^_^)");  
             window.location= "index.jsp";
        </script>  
    </body>
</html>
