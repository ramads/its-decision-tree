<%-- 
    Document   : PagePostTest
    Created on : June 29, 2014, 10:11:38 PM
    Author     : Ami
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
    int count= Integer.parseInt(request.getParameter("count"));
%>

<html>
      <head>
        <title>Posttest</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/templete.css" rel="stylesheet" type="text/css"/>
        <link href="css/page_posttest.css" rel="stylesheet" type="text/css"/>     
        <link type='text/css' href='css/stylesheet.css' rel='stylesheet' media='screen' />       
        <jsp:useBean id="dataPretest" class="controller.Pretest" scope="session"/>
         
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
                        <h2>Pilih salah satu jawaban yang menurut anda benar</h2>
                        <form id="Form"  name="Form" method="post" action="PagePostTest_FormAction_STC.jsp" onsubmit="<% if (count==15){%> alert('Soal sudah habis, Silahkan Lihat Hasil Pretest Anda') <% } %>">
                        <h4>Soal ke-<%=count%></h4>
                        <table border=0 >                             
                            <tr>    
                                <td colspan="3">
                                <% 
                                 // count= Integer.parseInt(request.getParameter("count"));
                                  //out.println(count);
                                 // out.println(dataPretest.pretestResult.size());
                                   dataPretest.setIdType(count);
                                   dataPretest.setIdQuestion(count);
                                   
                                   int idT=dataPretest.getIdType();
                                   int idQ=dataPretest.getIdQuestion();
                                                                    
                                 //out.println(idT);
                                 //out.println(idQ);
                                                                                                         
                                    String q = dataPretest.getQuestion(idT, idQ);
                                    String subQ=q.substring(0, 7);
                                   // out.println(subQ);
                                    
                                    if(subQ.equals("images/")){%>
                                        <img src="<%=q %>">
                                    <%}else{%>
                                        <% out.println(q); %>
                                    <%} %>
                                </td>  
                            </tr>
                            <tr>                            
                                <td width="45"><input type="radio" name="ans" value="a"> A. </td>                       
                                <td colspan="2"><%=dataPretest.getDBAnswer('A',idT, idQ)%></td>                                                                                 
                            </tr>
                            <tr>    
                                <td width="45"><input type="radio" name="ans" value="b"> B. </td>                                                               
                                <td colspan="2"><%=dataPretest.getDBAnswer('B',idT, idQ)%></td>                                                                                 
                            </tr>
                            <tr>    
                                <td width="45"><input type="radio" name="ans" value="c"> C. </td>                                                              
                                <td colspan="2"><%=dataPretest.getDBAnswer('C',idT, idQ)%></td>                                                                                 
                            </tr>
                            <tr>    
                                <td width="45"><input type="radio" name="ans" value="d"> D. </td>                                                               
                                <td colspan="2"><%=dataPretest.getDBAnswer('D',idT, idQ)%></td>                                         
                            </tr> 
                        </table>  
                            <input name="idT" type="hidden" value="<%=idT %>">
                            <input name="idQ" type="hidden" value="<%=idQ %>">
                            <input name="count" type="hidden" value="<%=count %>">   <br>
                            <input type="submit" value="Pertanyaan selanjutnya" onsubmit="tes()" >
                    </form>
                           
                    <script language="Javascript"> 
                        function tes(){
                             
                            var ansa = document.getElementById("ansA").checked;
                            var ansb = document.getElementById("ansB").checked; 
                            var ansc = document.getElementById("ansC").checked; 
                            var ansd = document.getElementById("ansD").checked; 
                            if((!ansa && !ansb) && (!ansc && !ansd)){ 
                                alert("belum dicentang, centang dulu!!!"); 
                                return false; 
                            } 
                            window.location = "PagePostTest_STC.jsp"; 
                        } 
                    </script>    
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
