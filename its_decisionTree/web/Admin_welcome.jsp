<%-- 
    Document   : LessonList
    Created on : Aug 27, 2013, 8:11:38 PM
    Author     : Arin
--%>

<%@page import="controller.DataUser"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% 
    String userID = session.getAttribute("useridijpt").toString();
    if(userID==null){
        
    }
%>
<html>
      <head>
        <title>Lesson</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link href="css/templete.css" rel="stylesheet" type="text/css"/>
        <link href="css/page_notif.css" rel="stylesheet" type="text/css"/>     
        <link type='text/css' href='css/stylesheet.css' rel='stylesheet' media='screen' />  
        
      </head>
      <body>       
        <div class="container" >
            <div class="header"> </div>          
            <div class="acces">
                <div class="menu" >
                      <nav>
                          <ul>
                              <li><a href="PageWelcome.jsp">Home</a></li>
                              <li><a href="PageUserAccount.jsp" >My Account</a></li>
                              <li><a href="#">Material List</a></li>
                              <li><a href="#">My Report</a></li>
                          </ul>
                      </nav>                                   
                </div>    
            </div>
            <div class="content">
                <div class="Welcome">  <h1>WELCOME <%=request.getParameter("id")%> </h1> </div>                 
                <div class="Welcome">  <h1>WELCOME session <% session.getAttribute("useridijpt"); %> </h1> </div>                 
                           
                 <div class="Link"> <h1>Data User <a href="Admin_dataUser.jsp"> Here </a></h1></div>
                 <div class="Link"> <h1>Data Login User <a href="Admin_logUser.jsp"> Here </a></h1></div>
            </div>
            <div class="footer">
                Created by siinumu ^_^
            </div>  
      </div>       
    </body>
</html>
