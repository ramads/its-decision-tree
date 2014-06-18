<%-- 
    Document   : PageDemo_SM
    Created on : Nov 25, 2013, 13:01:02 PM
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
        <title>Demo Student Model</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/templete.css" rel="stylesheet" type="text/css"/>
        <link href="css/page_demo.css" rel="stylesheet" type="text/css"/>
        <jsp:useBean id="dataPretest" class="controller.Pretest" scope="session"/>    
        <jsp:useBean id="dataLesson" class="controller.Lesson" scope="session"/>      
        <jsp:useBean id="dataPosttest" class="controller.PostTest" scope="session"/>   
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
                              <li><a href="PagePostTestReport.jsp">Study History</a></li>
                              <li><a href="PageLogOut.jsp">Log Out</a></li>
                          </ul>
                      </nav>                                       
                </div>   
            </div>
            <div class="content">
                <h2 id="title1"><marquee onmouseover="this.stop()" onmouseout="this.start()" width="100%" scrollamount="3" behavior="alternate">Contoh Petentuan Materi yang User Tidak Mengerti</marquee></h2>
                <div class="report"> 
                    <h3>Misalnya Hasil Tes adalah :</h3>
                    <form method="post" action="PageReport.jsp">
                        <table border="1" style=" text-align: center; ">
                            <tr bgColor="#00d0f2">                              
                                <th width="50">No Soal</th>                              
                                <th width="150">Hasil Pretest</th>                                                                                        
                            </tr>
                                <%  Pedagogik pedagogik = new Pedagogik();                                   
                                    for (int i=0; i<15; i++){
                                    String idPretest="F1B009037"+(i+1);
                                    ResultSet rs = dataPretest.getPResult(idPretest);
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
                                            <td width="70" ><%= count%></td>                       
                                            <td width="100"><%= result %></td>  
                                        </tr>
                                        <% count++; } 
                                    } 
                                  }%>
                        </table>
                    </form>
                </div>
                <div class="description">
                    <p><b><font color="blue">Untuk menentukan materi yang user tidak pahami, dilakukan step-step sebagai berikut : </font></b></p>
                    <ol>
                        <li>Menentukan hasil pretest user seperti yang terlihat pada table disamping</li>
                        <li>Memberikan <i>value</i> pada <b>node question</b> sesuai dengan hasil pretest</li>
                        <li>Mencari <b>node concept</b> yang probabilitas <i>false</i>-nya diatas 0.7 atau diatas 70%</li>
                        <li>Mengambil matateri tersebut sebagai <i>week material</i> (Materi yang user tidak mengerti)</li>
                    </ol> 
                    
                    Dari Hasil Pretest Anda Lemah Pada Materi Mengenai :
                    <%  ResultSet rs2 = dataLesson.selectWeakFromDB("F1B009037","weak");
                        if(rs2!=null){
                            String weakMaterial="";
                            while(rs2.next()){ 
                                weakMaterial= new String(rs2.getString("lesson_name"));  %>
                    <ul>
                        <li style="list-style-image: url(images/icon/warning.png)"><font color="red"><%= dataLesson.getTopicBaseOnLessonName(weakMaterial)%></font></li>
                    </ul>
                        <% }           
                        }
                    %>
                    <br><p><a href="PageReport.jsp">Kembali ke halaman sebelumnya</a></p>
                </div>
                <div style="clear: both "></div>
                <div class="BN">
                    <p>Gambar Bayesian Network untuk menentukan materi yang user tidak mengerti (<b>Student Model</b>)</p>
                    <image src="images/icon/SMsample.png" 
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
