<%-- 
    Document   : PageDemo_Pedagogik
    Created on : Nov 25, 2013, 14:18:02 PM
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
        <title>Demo Pedagogik</title>
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
                <h2 id="title1"><marquee onmouseover="this.stop()" onmouseout="this.start()" width="100%" scrollamount="3" behavior="alternate">Contoh Petentuan Materi Selanjutnya</marquee></h2>
                <div class="report">
                    <%  
                            Pedagogik pedagogik = new Pedagogik();  
                            pedagogik.addWeakToDB("F1B009037");
                            String lesName = pedagogik.getLearnMaterial("F1B009037");    
                        %>
                       Dari Hasil Pretest Anda Lemah Pada Materi Mengenai :
                        <%  ResultSet rs2 = dataLesson.selectWeakFromDB("F1B009037","weak");
                            if(rs2!=null){
                                String weakMaterial="";
                                while(rs2.next()){ 
                                    weakMaterial= new String(rs2.getString("lesson_name")); %>
                        <ul>
                            <li style="list-style-image: url(images/icon/warning.png)"><%= dataLesson.getTopicBaseOnLessonName(weakMaterial)%></li>
                        </ul>
                             <%   }
                        } %>
                        
                            <h3>Hasil Belajar User :</h3>
                            <form method="post" action="PageReport.jsp">
                        <table border="1" style=" text-align: center;" bgColor="black">
                                    <tr bgColor="palevioletred">                              
                                        <th width="10px">No.</th>                                                         
                                        <th width="270px">Materi</th>                              
                                        <th width="100px">Hasil Posttest</th>                                                                                        
                                    </tr>
                                        <%    
                                        String idCourseMat="";
                                        ResultSet rs = dataLesson.getIdCourseMaterial("F1B009037");
                                        if(rs!=null){
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
                                        }  %>    
                                </table>
                            </form>
                        </div>
                </div>
                
                <div class="description">
                    <p><b><font color="blue">Untuk menentukan materi selanjutnya, dilakukan step-step sebagai berikut : </font></b></p>
                    <ol>
                        <li>Mengambil materi apa saja yang user tidak mengerti (<i>weak material</i>)</li>
                        <li>Memberikan <i>value false</i> dari <i>weak material</i> pada <b>network knowledge domain</b></i> 
                        <li>Mengambil hasil belajar user</li>
                        <li>Memberikan <i>value</i> pada <b>node</b> yang ada di <b>network knowledge domain</b> sesuai dengan hasil belajar user</i> 
                        <li>Mencari <b>parent</b> dari node-node yang telah ditentukan veluenya tadi yang probabilitas <b>false</b>nya terbesar diatas 0.6 atau diatas 60% </li>
                        <li>Mengambil <b>node</b> tersebut sebagai <i>Next Material</i> (Materi yang akan diberikan selanjutnya)</li>
                    </ol> 
                    
                    <p>Dari tabel disamping dan network dibawah, maka diadapatkan Materi selanjutnya adalah : <font color="red">Operator Aritmatika</font> </p>  
                    <p><a href="PagePostTestReport.jsp" style="margin-left: 300px;">Kembali ke halaman sebelumnya</a></p>
                </div>
               
                <div style="clear: both "></div>
                <div class="BN">
                    <p>Gambar Bayesian Network untuk menentukan materi selanjutnya (<b>Pedagogik Module</b>)</p>
                    <image src="images/icon/KDsample.png">
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
