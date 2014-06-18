<%-- 
    Document   : LessonList
    Created on : Aug 27, 2013, 8:11:38 PM
    Author     : Arin
--%>

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
        <title>Tutorial</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link href="css/templete.css" rel="stylesheet" type="text/css"/>
        <link href="css/page_welcome.css" rel="stylesheet" type="text/css"/>     
        <link type='text/css' href='css/stylesheet.css' rel='stylesheet' media='screen' /> 
              <jsp:useBean id="dataLesson" class="controller.Lesson" scope="session"/>  
               <jsp:useBean id="dataPretest" class="controller.Pretest" scope="session"/>    
    </head>
    <body onload='gulungtulisan()'>  
        
        <div class="container" >  
            <div class="header"> </div>       
            <div id="acces">
                <div class="menu" >
                       <nav>
                          <ul>
                              <li><a href="#">Home</a></li>
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
                        var text="Silahkan Pilih Jenis Tutorial";
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
                    <div class="tutorial">
                        <table>
                            <th><a href="PageListMaterial.jsp">Tutorial Biasa</a></th>
                            <th><a href="PageWelcome.jsp">Tutorial Java</a></th>                            
                        </table>
                    </div>
                </div>
                </div>
            <div class="footer">
                <div class="designer">
                    Copyright © 2013 siinumu 
                </div>
            </div>
      </div>       
    </body>
</html>
