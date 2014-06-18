<%--
    Document   : PageLesson
    Created on : Sep 3, 2013, 11:18:02 AM
    Author     : Arin
--%>

<%@page import="controller.Lesson"%>
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
    
    String data= request.getParameter("data"); 
    String idl= data.substring(0, 3);
    //out.println(idl);
%>
<html>
    <head>
        <title>Lesson</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/templete.css" rel="stylesheet" type="text/css"/>
        <link href="css/page_lesson.css" rel="stylesheet" type="text/css"/> 
        <jsp:useBean id="dataLesson" class="controller.Lesson" scope="session"/>    
    </head>
    <% //out.println("id user "+dataUser.gettId());%>
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
                              <li><a href="PageReport.jsp">Report</a></li>
                              <li><a href="PagePostTestReport.jsp">Study History</a></li>
                              <li><a href="PageLogOut.jsp">Log Out</a></li>
                          </ul>
                      </nav>                                   
                </div>   
            </div>
            <div class="content">
                <div  class="content_left">
                    <div class="matList">
                        <%String topic= dataLesson.getTopic(idl); %>                  
                        <ul>
                            <li><div class="tittle"><b><%=topic %></b></div>
                            <% ResultSet rs = dataLesson.getIDsubLesson(idl);
                                if(rs!=null){       
                                    while(rs.next()){      
                                        String IdSubles=new String(rs.getString("idsublesson"));

                            %>
                            <br><ul>
                                <li><a href="PageLesson.jsp?data=<%=IdSubles%>"> <%= dataLesson.getsubLesson(IdSubles)%> </a></li> 
                                </ul>

                            <%      } 
                            } %>
                            </li>
                        </ul>
                       
                    </div> 
                    <div class="result">
                        <div class="tittle" id="title"><b>Bagaimana Hasil Belajar anda?</b></div>
                        <form action="PageLesson_FormAction.jsp">
                            <ul>
                                <li><input type="radio" name="result" value="no"> Tidak Mengerti (Update Materi) </li>   
                            </ul>
                            <ul>  
                                <li><input type="radio" name="result" value="doubt"> Tes Saya </li>  
                            </ul>
                            <ul>  
                                <li><input type="submit" name="submit" value="submit"></li>  
                            </ul>
                            <input type="hidden" id="idl" name="idl" value="<%=idl%>">
                        </form>
                        
                        <script type="text/javascript">
                            function blinklink(){
                                if (!document.getElementById('title').style.color){
                                    document.getElementById('title').style.color="black";
                                }
                                if (document.getElementById('title').style.color=="black"){
                                    document.getElementById('title').style.color="blue";
                                }else{
                                document.getElementById('title').style.color="black";
                                }

                                timer=setTimeout("blinklink()",500);
                                }

                            function stoptimer(){
                                clearTimeout(timer);
                            }
                            blinklink();
                        </script>
                        
                    </div>
                    <div class="Quote1">
                        <div class="Quotes">
                            <font color="darksalmon">
                             There is divine beauty in learning. To learn means to accept the postulate
                             that life did not begin at my birth. Other have been here before me,
                             and i walk in their footsteps.
                             </font>
                        </div>
                        <div class="Quotewriter">
                            -Elie Wiesel-
                        </div>

                    </div>
                    <div class="Quote3">
                        <div class="Quotes">
                            <font color="yellowgreen">
                             Person who never made a mistake never tried anything new.
                             </font>
                        </div>
                        <div class="Quotewriter">
                            -Albert enstein-
                        </div>
                    </div>
                    <div class="Quote2">
                        <div class="Quotes">
                            <font color="orange">
                            The world is a university and everyone in it is a teacher. Make sure when you wake up in the morning you go to school.
                            </font>
                        </div>
                        <div class="Quotewriter">
                            -Bishop T.D Jakes-
                        </div>
                    </div>                   
                </div>
                <div class="content_right">
                    <div class="material">
                    <% String ids= data;
                       String lesson= dataLesson.getLesson(ids);
                      
                       if (ids.length()==3){  

                      %> <p  style="margin-left: 20px; font-size: 30px; height: 150px;"><image src="images/icon/hand2.png"><b><font color="blue">List Materi yang anda butuhkan</font></b></p>
                         
                      <p style="margin-top: 100px; margin-left: 20px; font-size: 20px; height: 500px;"><image src="images/icon/hand2.png"><b><font color="blue">Kemudian Tentukan Hasil Belajar Anda  Disini....!!</font></b></p>
                      
                      <%
                           out.print("");
                           
                       }else{                                      
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
