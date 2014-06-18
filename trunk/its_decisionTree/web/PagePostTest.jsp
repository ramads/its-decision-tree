<%-- 
    Document   : PagePostTest
    Created on : Aug 27, 2013, 8:11:38 PM
    Author     : Arin
--%>

<%@page import="controller.Pedagogik"%>
<%@page import="controller.PostTest"%>
<%@page import="controller.Pretest"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% 
    String userID = (String) session.getAttribute("useridijpt");
    if(userID==null){
%>
    <jsp:forward page="SignUp_notif.jsp"></jsp:forward>
<%
    } 
    
   //String idl= "m00";
   String idlearn= request.getParameter("idlearn");
   String idl=idlearn.substring(0,3);
   //out.println(idl);
   //out.println(link);
%>
<html>
      <head>
        <title>Posttest</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link href="css/templete.css" rel="stylesheet" type="text/css"/>
        <link href="css/page_posttest.css" rel="stylesheet" type="text/css"/>     
        <link type='text/css' href='css/stylesheet.css' rel='stylesheet' media='screen' />       
         <jsp:useBean id="dataPosttest" class="controller.PostTest" scope="session"/>    
          <jsp:useBean id="dataLesson" class="controller.Lesson" scope="session"/>    
    </head>    
      <body>       
        <div class="container" >
            <div class="header"> </div>          
            <div id="acces">
                <div class="menu" >
                      <nav>
                          <ul>
                              <li><a href="PageTutorial.jsp">Home</a></li>
                              <li><a href="PageUserAccount.jsp">My Account</a></li>
                              <li><a href="PageLessonNotif.jsp">Material List</a></li>
                              <li><a href="PageReport.jsp">Report</a></li>
                              <li><a href="PagePostTestReport.jsp">Study History</a></li>
                              <li><a href="PageLogOut.jsp">Log Out</a></li>
                          </ul>
                      </nav>                                   
                </div> 
            </div>
            <div class="content"> 
                <div class="Questions">    
                    <form action="PagePostTest_FormAction.jsp" method="post">
                    <table border=0>     
                        <% ResultSet rs = dataPosttest.getidPostQuest(idl);
                            int count=1;
                            if(rs!=null){
                                while(rs.next()){  
                                    String idPQ=new String(rs.getString("idpostquestion"));  
                                    String pQ=dataPosttest.getQuestion(idPQ);
                                    System.out.println(pQ);                                                
                        %>
                            <tr>
                                <td width="10px" style="text-align: left"><%=count+"." %></td>      
                                <td colspan="2" >
                                    <% String subQ=pQ.substring(0, 7);
                                    // out.println(subQ);
                                        if(subQ.equals("images/")){%>
                                    <img src="<%=pQ %>">
                                <%}else{%>
                                    <% out.println(pQ); %>
                                <%} %>
                                    </td>      
                            </tr>

                            <tr>       
                                <td>&nbsp;</td>
                                <td width="40"><input type="radio" name="<%=idPQ+"ans"%>" value="a"> A. </td>                       
                                <td ><% String ansA=dataPosttest.getDBAnswer('A', idPQ);
                                        out.println(ansA);
                                %></td>                                                                                 
                            </tr>
                            <tr>    
                                    <td>&nbsp;</td>
                                <td width="45"><input type="radio" name="<%=idPQ+"ans"%>" value="b"> B. </td>                                                               
                                <td ><% String ansB=dataPosttest.getDBAnswer('B', idPQ);
                                        out.println(ansB);
                                %></td>                                                                            
                            </tr>
                            <tr>    
                                <td>&nbsp;</td>
                                <td width="45"><input type="radio" name="<%=idPQ+"ans"%>" value="c"> C. </td>                                                              
                                <td ><% String ansC=dataPosttest.getDBAnswer('C', idPQ);
                                        out.println(ansC);
                                %></td>                                                                             
                            </tr>
                            <tr>  
                                <td>&nbsp;</td>
                                <td width="45"><input type="radio" name="<%=idPQ+"ans"%>" value="d"> D. </td>                                                               
                                <td ><% String ansD=dataPosttest.getDBAnswer('D', idPQ);
                                        out.println(ansD);
                                %></td>                                      
                            </tr> 
                            <tr>    
                                <td colspan="3">&nbsp;</td>                                                                                               
                            </tr> 

                            <% count++; }
                            } %>
                        </table> 
                            <input type="hidden" name="id" value="<%=idl%>">
                            <input type="submit" value="EVALUASI (Update Materi)"> 
                    </form>       
                </div>
               <div style="clear: both "></div>
            </div>
            <div class="footer">
                <div class="designer">
                    Copyright © 2013 siinumu
                </div>
            </div>
      </div>       
    </body>
</html>
