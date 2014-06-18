<%-- 
    Document   : LessonList
    Created on : Aug 27, 2013, 8:11:38 PM
    Author     : Arin
--%>

<%@page import="controller.Pretest"%>
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
    </script>    
          
        <title>Home</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link href="css/templete.css" rel="stylesheet" type="text/css"/>
        <link href="css/page_welcome.css" rel="stylesheet" type="text/css"/>     
        <link type='text/css' href='css/stylesheet.css' rel='stylesheet' media='screen' /> 
              <jsp:useBean id="dataPretest" class="controller.Pretest" scope="session"/>  
              <jsp:useBean id="dataLesson" class="controller.Lesson" scope="session"/>  
    </head>
    <body onload='gulungtulisan()'>       
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
                <div class="Welcome" id="textDestination" style="text-align: center; text-shadow: 5px 5px 5px #FF0000;"><b></b>
                  <!--  <form name="formgulung"><input name="kotakgulung" size="50"></form> --> 
                  <script language="JavaScript">
                        var text="Tentang TCJP";
                        var delay=10;
                        var currentChar=1;
                        var destination="[none]";
                        function type()
                        {
                            var dest=document.getElementById(destination);
                            if (dest)
                                {
                                dest.innerHTML=text.substr(0, currentChar)+"<blink>_</blink>";
                                currentChar++;
                                if (currentChar>text.length){
                                    currentChar=1;
                                    setTimeout("type()", 5000);
                                }else{               
                                    setTimeout("type()", delay);
                                }
                            }
                        }
                        function startTyping(textParam, delayParam, destinationParam)
                        {
                            text=textParam;
                            delay=delayParam;
                            currentChar=1;
                            destination=destinationParam;
                            type();
                        }
                    </script>
                    <script language="JavaScript">
                        javascript:startTyping(text, 50, "textDestination");
                    </script>

                </div>   
                <div class="Description">
                    <p>&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;Tutorial Cerdas Java Programing adalah sebuah alat pembelajaran yang digunakan untuk mengenalkan kepada anda bahasa pemograman Java.
                    Aplikasi ini masih dalam proses penelitian sehingga masukan dan saran sangat diharapkan untuk sebagai acuan penyempurnaan.</p>
                    <p>&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;Alat pembelajaran ini dirancang dengan menggunakan kecerdasan buatan yang mampu memberikan materi sesuai dengan kemampuan anda. Dimana dalam
                    penyajian materi user akan diberikan test berupa pretest untuk mengetahui kemampuan dasar user (menentukan materi mana yang tidak dipahami) dan posttest yang berguna untuk menentukan hasil belajar user. </p>
                    <p>Dalam Aplikasi ini juga terdapat beberapa menu yang bisa anda akses dalam proses belajar anda, yaitu :</p>
                    <ul>
                        <li><b>Material List</b> &nbsp; &nbsp; &nbsp; : Berfungsi untuk menampilkan seluruh materi yang ada, anda bisa mereview terlebih dahulu &nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; ataupun tidak.</li> 
                        <br><li><b>Report</b>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; : Berfungsi untuk menampilkan materi yang anda tidak pahami.</li> 
                        <br><li><b>Study History</b>&nbsp; &nbsp; &nbsp; : Berfungsi untuk menampilkan history belajar anda, dan materi yang belum anda pahami.</li> 
                    </ul>
                    <br>
                    <p>Sebelum memulai belajar, silahkan melakukan Pretest terlebih dahulu untuk mengatahui kemampuan dasar anda.</p>
                </div>
                
                <div class="guideAngel">  
                          <div id="angelFace"></div>
                           <div id="angleGuidance">                      
                               <%
                                  if(dataPretest.getPretestLog(userID)){
                               %>
                                    <p id="guidence"><font style="font-size: 18px;">Anda telah melakukan pretest sebelumnya, <br>silahkan cek Pada menu <b>'REPORT'</b>.</font></p>
                                <% } else {  %>                            
                                    <p id="guidence"><font style="font-size: 24px;">Klik <a href="PagePretest.jsp?count=1"><b>Pretest</b></a> untuk memulai</font></p>                                   
                                <% } %>
                           </div>
                    </div>
                   <script type="text/javascript">
                    function blinklink(){
                        if (!document.getElementById('guidence').style.color){
                            document.getElementById('guidence').style.color="black";
                        }
                        if (document.getElementById('guidence').style.color=="black"){
                            document.getElementById('guidence').style.color="red";
                        }else{
                        document.getElementById('guidence').style.color="black";
                        }

                        timer=setTimeout("blinklink()",300);
                        }

                    function stoptimer(){
                        clearTimeout(timer);
                    }
                    blinklink();
                </script>
            </div>
            <div class="footer">
                <div class="designer">
                    Copyright © 2013 siinumu 
                </div>
            </div>
      </div>       
    </body>
</html>
