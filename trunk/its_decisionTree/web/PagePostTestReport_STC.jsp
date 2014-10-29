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
    String idCourseMat = "";
    int count=1; 
%>
<html>
    <head>
        <title>Study History</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/templete.css" rel="stylesheet" type="text/css"/>
        <link href="css/page_posttest_report.css" rel="stylesheet" type="text/css"/>
        <jsp:useBean id="dataPretest" class="controller.Pretest" scope="session"/>    
        <jsp:useBean id="dataPosttest" class="controller.PostTest" scope="session"/>    
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
                <h2 id="title1"><marquee onmouseover="this.stop()" onmouseout="this.start()" width="80%" scrollamount="3" behavior="alternate">Laporan Progres Belajar User "<%= userID %>"</marquee></h2>
                <div class="report">    
                    <form method="post" action="PagePostTestReport_STC.jsp">
                        <table border="1" style=" text-align: center; ">
                            <tr bgColor="#00d0f2">                              
                                <th width="50">No Soal</th>                              
                                <th width="150">Hasil Posttest Terakhir</th>                                                                                        
                            </tr>
                                <%                                     
                                    ResultSet rs = dataPosttest.getLastPostResult(userID);
                                    if(rs!=null){
                                        while(rs.next()){                                    
                                        //String id=new String(rs.getString("iduser"));  
                                        String bagroundcolor="";
                                        String result=new String(rs.getString("result")); 
                                            if(result.equals("0")){
                                                result="salah";
                                                bagroundcolor="#FF9F9F";
                                            }if(result.equals("1")){
                                                result="benar";
                                                bagroundcolor="#9dfdee";
                                            }                                             
                                %>
                              <tr  bgColor="<%=bagroundcolor %>">                                    
                                    <td width="70" ><%= count  %></td>                       
                                    <td width="100"><%= result %></td>  
                              </tr>
                                        <% count++; 
                                        } 
                                    } 
                                %>
                        </table>
                    </form>
                </div>
                <div class="guideAngel">   
                    <div id="angelFace"></div>
                    <div id="angleGuidance">
                    <%
                        Pedagogik pedagogik = new Pedagogik();
                                   
                        //lakukan update weak material setelah posttest
                        pedagogik.updateWeakMaterial(userID);
                        //ambil materi yang akan dipelajari selanjutnya berdasarkan hasil posttest
                        String nextMaterial=pedagogik.getLearnMaterial(userID);
                    %>
                        Dari Hasil Posttest Anda Lemah Pada Materi Mengenai :
                    <%  ResultSet rs2 = dataLesson.selectWeakFromDB(userID,"weak");
                        if(rs2!=null){
                            String weakMaterial="";
                            while(rs2.next()){ 
                                weakMaterial= new String(rs2.getString("lesson_name")); //for(int i=0; i<dataLesson.weakNode.size(); i++){ 
                    %>
                        <ul>
                            <li style="list-style-image: url(images/icon/warning.png)"><%= dataLesson.getTopicBaseOnLessonName(weakMaterial)%></li>
                        </ul>
                    <%      }
                                if(weakMaterial.equals("")){
                                    out.print("(kosong)<br><br>Anda Dikatakan Telah Tamat Belajar. <h1>LULUS....!!</h1>");
                                }else{
                                  %>
                                  <p id="guidence" style="margin-top: 20px;">
                                      <font color="red">"Silahkan Klik "<a href="PageLesson.jsp?data=<%= dataLesson.getIdLessonName(nextMaterial) %> "><b>Belajar</b></a>"</font>
                                  </p>
                                 <%
                                }                    
                            }if (dataPosttest.hadPostest(userID)){%>
                                Atau : <br>
                                <p id="guidence">Silahkan Pilih '<b>Menu Study History</b>'<br>Untuk Melihat Progres Belajar.</p>                                   
                            <% } %>
                           </div>
                           <div style="clear: both "></div>
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
