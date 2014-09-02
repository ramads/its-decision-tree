<%-- 
    Document   : PagePretest
    Created on : Aug 27, 2013, 8:11:38 PM
    Author     : Arin
--%>

<%@page import="controller.Pretest"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% 
    //mengecek user yang sedang aktif sekarang dan menyimpan user id nya
    String userID = (String) session.getAttribute("useridijpt");
    
    //jika user id tidak ada (berati belum login), dan mengakses halaman ini maka akan di direct ke page sign up
    if(userID==null){
%>
    <jsp:forward page="SignUp_notif.jsp"></jsp:forward>
<%
    } 
%>
<% 
    //mengambil parameter count yang dilempar/diberikan oleh PagePreTest_FormAction
    //parameter count ini digunakan untuk pengambilan soal berdasarkan type soal
    //parameter ini akan bertambah secara terurut mulai dari angka 1 sampai 15 sesuai dengan jumalh soal pretest
    int count= Integer.parseInt(request.getParameter("count")); 
%>
<html>
      <head>
        <title>Pretest</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link href="css/templete.css" rel="stylesheet" type="text/css"/>
        <link href="css/page_pretest.css" rel="stylesheet" type="text/css"/>     
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
                <div class="Questions">                   
                        <h2>Pilih salah satu jawaban yang menurut anda benar</h2>
                        
                        <!-- 
                        setelah jawaban dipilih oleh user maka aplikasi akan mengarah ke PagePretest_FormAction.jsp 
                        pada PagePretest_FormAction.jsp akan dilakukan input hasi jawaban user ke database
                        -->
                        <form id="Form"  name="Form" method="post" action="PagePretest_FormAction.jsp" onsubmit="<% if (count==15){%> alert('Soal sudah habis, Silahkan Lihat Hasil Pretest Anda') <% } %>">
                        <h4>Soal ke-<%=count%></h4>
                        <table border=0 >                             
                            <tr>    
                                <td colspan="3">
                                <% 
                                 //mengeset type soal dengan parameter count yang di berikan oleh PagePretest_FormAction
                                 dataPretest.setIdType(count);
                                 
                                 //mengeset id soal yang akan ditampilkan berdasarkan parameter count
                                 dataPretest.setIdQuestion(count);
                                   
                                 int idT=dataPretest.getIdType();
                                 int idQ=dataPretest.getIdQuestion();
                                 
                                 //mengambil soal yang akan diberikan berdasarkan id type dan id soal
                                 String q = dataPretest.getQuestion(idT, idQ);
                                 
                                 //mengambil parameter untuk pengecekan soal
                                 //pengecekan soal disini dimaksudkan apabila ada soal yang bertipe gambar
                                 //hasil eksekusi method getQuestion (pada line 79) jika soalnya adalah gambar adalah sebagai berikut : "images/questions/question-5-14.jpg"
                                 //jadi huruf ke 0 sampai ke 7 diambil dengan menggunakan method substring dan akan di cek nantinya
                                 String subQ=q.substring(0, 7);
                                 
                                 //mengecek hasil query soal,
                                 //jika huruf ke 0 sampai ke 7 (yang disimpan di variable subQ) adalah kata "images/" maka dapat dipastikan soal
                                 //tersebut adalah gambar dan akan ditampilkan pada halaman web dengan tag <img>
                                 if(subQ.equals("images/")){%>
                                    <img src="<%=q %>">
                                    
                                 <!-- jika buka gambar maka akan ditampilkan langsung pada halaman web tampa mengggunakan tag <img> -->
                                 <%}else{%>
                                    <% out.println(q); %>
                                 <%} %>
                                </td>  
                            </tr>
                            
                            <!-- dari line 101 sampai 117 akan menampilkan pilihan jawaban soal -->
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
                            window.location = "PagePretest.jsp"; 
                        } 
                    </script>    
                </div>
            </div>
            <div class="footer">
                <div class="designer">
                    Copyright © 2011 siinumu 
                </div>
            </div>
      </div>       
    </body>
</html>
