<%-- 
    Document   : PageListMaterial
    Created on : Sep 3, 2013, 11:18:02 AM
    Author     : Arin
--%>

<%@page import="controller.Lesson"%>
<%@page import="controller.DataUser"%>
<%@page import="java.util.ArrayList"%>
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
%>
<html>
    <head>
        <title>Lesson</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/templete.css" rel="stylesheet" type="text/css"/>
        <link href="css/page_list_material.css" rel="stylesheet" type="text/css"/>
        <jsp:useBean id="dataLesson" class="controller.Lesson" scope="session"/>    
        <jsp:useBean id="dataPretest" class="controller.Pretest" scope="session"/>  
    </head>
    <body>       
        <div class="container" >
            <div class="header"> </div>          
            <div class="acces">
                <div class="menu" >
                      <nav>
                          <ul>
                              <li><a href="PageTutorial.jsp">Home</a></li>
                              <li><a href="PageUserAccount.jsp">My Account</a></li>
                              <li><a href="#">Material List</a></li>
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
            </div>
            <div class="content">
                <div  class="content_left">
                    <div class="matList">
                    <%                                                        
                        ResultSet rs = dataLesson.getAllTopic();
                        //   out.println(rs.next());
                            if(rs!=null){       
                                while(rs.next()){      
                                    String topic=new String(rs.getString("topic")); 
                        %>                                      
                        <ul>
                            <li><div class="tittle"><b><%=topic %></b></div>  
                                
                                <%  String idt=dataLesson.getIdTopic(topic);
                                    ResultSet rs2 = dataLesson.getIDsubLesson(idt);
                                        if(rs2!=null){       
                                            while(rs2.next()){      
                                                String IdSubles=new String(rs2.getString("idsublesson"));
                                    %>                                                               
                                        <br><ul>
                                            <li><a href="PageListMaterial.jsp?data=<%=IdSubles%>"><%= dataLesson.getsubLesson(IdSubles)%></a></li>                                  
                                        </ul>                                                              
                                            <% } 
                                        }%>   
                                    </li> 
                                </ul>
                            <% }
                        } %>           
                    </div>            
                </div>
                <div class="content_right">
                    <div class="material">
                         <% String ids = request.getParameter("data");
                           // out.println(ids);               
                             String lesson= dataLesson.getLesson(ids);
                             if(ids==null){ %>
                                <p  style="margin-top: 20px; font-size: 25px; height: 200px;"><b><font color="navy">Pilih Salah Satu Materi yang ada Pada List</font></b></p>
                            <% }else{
                                 out.print(lesson);
                             }
                         %>
                    </div>
                </div>
            </div>  
                    <div style="clear: both "></div>
                <div class="footer">
                <div class="designer">
                    Copyright © 2013 siinumu
                </div>
            </div>
        </div>  
    </body>
</html>
