<%-- 
    Document   : PagePostTestReport
    Created on : Sep 3, 2013, 11:18:02 AM
    Author     : Arin
--%>

<%@page import="controller.Pedagogik"%>
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
 int count=1; 
%>
<html>
    <head>
        <title>Study History</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/templete.css" rel="stylesheet" type="text/css"/>
        <link href="css/page_posttest_report.css" rel="stylesheet" type="text/css"/>
        <jsp:useBean id="dataPretest" class="controller.Pretest" scope="session"/>    
        <jsp:useBean id="dataLesson" class="controller.Lesson" scope="session"/>    
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
                              <li><a href="PageListMaterial.jsp">Material List</a></li>
                              <% boolean check = dataPretest.getPretestLog(userID); 
                                  if(check){ %>
                                    <li><a href="PageReport.jsp">Report</a></li>
                                 <%} else{ %>
                                    <li><a href="Pretest_Notif.jsp">Report</a></li>
                                <% }  %>
                              <li><a href="#">Study History</a></li>
                              <li><a href="PageLogOut.jsp">Log Out</a></li>
                          </ul>
                      </nav>                                   
                </div>   
            </div>
            <div class="content">
                <h2 id="title1">
                    <marquee onmouseover="this.stop()" 
                             onmouseout="this.start()" 
                             width="80%" 
                             scrollamount="3" 
                             behavior="alternate">
                        Laporan Progres Belajar User "<%= userID %>"
                    </marquee>
                </h2>
                <div class="report">    
                    <form method="post" action="PageReport.jsp">
                        <table border="1" style=" text-align: center;" bgColor="black">
                            <tr bgColor="palevioletred">                              
                                <th width="10px">No.</th>                                                         
                                <th width="270px">Materi</th>                              
                                <th width="100px">Hasil Posttest</th>                                                                                        
                            </tr>
                            <%
                                   Pedagogik pedagogik = new Pedagogik();
                                   String idCourseMat="";
                                    ResultSet rs = dataLesson.getIdCourseMaterial(userID);
                                    if(rs != null){
                                        idCourseMat="";
                                        while(rs.next()){
                                        String bagroundcolor="";
                                        idCourseMat=new String(rs.getString("idcoursemat"));
                                        String lesson = dataLesson.getCourseMaterial(idCourseMat);
                                        String result = dataLesson.getCourseMaterialResult(idCourseMat);
                                        
                                            if(result.equals("0")){
                                                result="Tidak Mengerti";
                                                bagroundcolor="#FF9F9F";
                                            }if(result.equals("1")){
                                                result="Mengerti";
                                                bagroundcolor="#9dfdee";
                                            }  
                            %>
                                        <tr  bgColor="<%=bagroundcolor %>">                                    
                                            <td width="70" ><%= count%>.</td>                                             
                                            <td width="70" ><%= lesson%></td>                       
                                            <td width="100"><%= result %></td>  
                                        </tr>
                                        
                                        <% count++; } 
                                    if (idCourseMat.equals("")){
                                            //out.println("masuk");
                                        %>
                                        <ul id="warning">
                                             <li style="list-style-image: url(images/icon/warning2.png)"><font color="red"><b>WARNING </b></font></li>
                                             <li style="list-style: none ">Anda belum pernah melakukan Posttest</li>
                                        </ul> 
                                        <%
                                        }
                                    }  %>                                              
                       </table>
                    </form>
                </div>
                 <div class="guideAngel">   
                          <div id="angelFace"></div>
                           <div id="angleGuidance">
                               <%  if(idCourseMat.equals("")){ %>
                                   <p id="guidence" style="margin-top : 50px;">Silahkan Posttest terlebih Dahulu</p>
                               <%}else{                                  
                                   String nextMaterial = pedagogik.getLearnMaterial(userID);
                                   if(nextMaterial.equals("")){                                              
                               %>    
                                        <p  id="guidence">Dari Hasil test Anda Dikatakan Telah Tamat Belajar. <br><h1>LULUS.....!!</h1></p>

                               <%  }else{ 
                                            String topic=dataLesson.getTopicBaseOnLessonName(nextMaterial);
                                        %>
                                        <p  id="guidence">Selanjutnya Materi yang harus anda pelajari adalah :<br><b><a href="PageLesson.jsp?data=<%=dataLesson.getIdLessonName(nextMaterial) %>"> <%=topic%></b></a></p>
                               <% }
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
