<%-- 
    Document   : pretest
    Created on : Sep 3, 2013, 11:18:02 AM
    Author     : Arin
--%>

<%@page import="controller.DataUser"%>
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
%>
<html>
    <head>
        <title>My Account</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/templete.css" rel="stylesheet" type="text/css"/>
        <link href="css/page_userAccount.css" rel="stylesheet" type="text/css"/>
         <jsp:useBean id="dataPretest" class="controller.Pretest" scope="session"/> 
         <jsp:useBean id="dataUser" class="controller.DataUser" scope="session"/> 
    </head>
    <body>       
        <div class="container" >
            <div class="header"> </div>          
            <div class="acces">
                <div class="menu" >
                      <nav>
                          <ul>
                               <li><a href="PageTutorial.jsp">Home</a></li>
                              <li><a href="#">My Account</a></li>
                              <li><a href="PageListMaterial.jsp">Material List</a></li>
                               <% boolean check = dataPretest.getPretestLog(userID); 
                                  if(check){ %>
                                    <li><a href="PageReport.jsp">Report</a></li>
                                 <%} else{ %>
                                    <li><a href="Pretest_Notif.jsp">Report</a></li>
                                <% }  %>
                               <li><a href="PagePostTestReport.jsp">Study History</a></li>
                               <li><a href="PageLogOut.jsp">Log Out</a></li>
                          </ul>
                      </nav>                                   
                </div>        
            <div class="content">
                <div class="identity">
                    <table border=0>
                        <tr style=" background-color:white">
                             <th width="200px;"> <image src="images/icon/card1.png"></th>
                             <th width="400px;">My Identity Card</th>
                        </tr> 
                        <tr style=" background-color: #FF9F9F ">
                            <td> &nbsp;&nbsp;User Id </td>
                            <td> &nbsp;&nbsp;<%=userID%></td>
                        </tr> 
                        <tr style=" background-color: #B8F5B1 ">
                            <td>&nbsp;&nbsp;User Name</td>
                            <td>&nbsp;&nbsp;<%=dataUser.getName(userID)%></td>
                        </tr>
                        <tr style=" background-color: aqua ">
                            <td>&nbsp;&nbsp;Password</td>
                            <td>&nbsp;&nbsp;<%=dataUser.getPassword(userID)%> </td>
                        </tr>
                                                    
                    </table>                      
                </div> 
                        <!--
                <div class="browse">
                    <input type="text" readonly="readonly" value='Pilih lokasi file'>
                    <input type="button" value="Browse"/>
                    <input type="file" name="uploaded_file" />
                </div>
                        -->
             </div>
             <div style="clear: both "></div>
            <div class="footer">
                <div class="designer">
                    Copyright © 2013 siinumu
                </div>
            </div>
         </div>
      </div>      
    </body>
</html>
