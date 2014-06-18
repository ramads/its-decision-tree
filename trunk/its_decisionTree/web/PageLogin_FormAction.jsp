<%-- 
    Document   : PageLogin_FormAction
    Created on : Aug 27, 2013, 5:06:38 PM
    Author     : Arin
--%>

<%@page import="javax.jms.Session"%>
<%@page import="controller.*"%>
<%@page import="model.repository.dbconnection.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% String userID=request.getParameter("userID");
           String password=request.getParameter("pass");
           
           DataUser data = new DataUser();

           if(data.checkUser(userID, password)){
               session.setAttribute("useridijpt", userID);
               if(userID.equals("numu")&& password.equals("e2c9")){
                    %>     
                       <jsp:forward page="Admin_welcome.jsp">
                         <jsp:param name="id"  value="<%=userID%>" />
                           </jsp:forward> 
                <%} %>
               <jsp:forward page="PageTutorial.jsp">
                   <jsp:param name="id"  value="<%=userID%>" />
                </jsp:forward>                                         
           <% }else{ %>     
               <jsp:forward page="SignUp_notif.jsp">
                    <jsp:param name="id"  value="<%=userID%>" />
                </jsp:forward>   
          <% }        
        %>
    </body>
</html>
