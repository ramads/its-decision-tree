<%-- 
    Document   : PageLesson_FormAction
    Created on : Sep 3, 2013, 2:24:30 PM
    Author     : Arin
--%>

<%@page import="controller.Pedagogik"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Question</title>
        <jsp:useBean id="dataLesson" class="controller.Lesson" scope="session"/>     
    </head>
    <body>
        <%  
          Pedagogik pedagogik = new Pedagogik();
          //String result=request.getParameter("result");    
          String idLesson=request.getParameter("idl");
          String result=request.getParameter("result")  == null ? "x" : request.getParameter("result");    
          //out.println(result);
          //out.println(idLesson);
          // out.println(userID);
            
          if (result.equals("x")) out.println("<h1>Silahkan Kembali dan pilih salah satu hasil blajar anda.............................!!</h1>");
          int count=dataLesson.getTotalLearn(userID,idLesson);  
          String lessonName= dataLesson.getLessonName(idLesson);
          String idLearning=idLesson+count+userID;
          
        if(result.equals("no")){    
            //update knowledge base setobservation 
            //out.println("set observation = false");
            //out.println("leson name : "+lessonName+" user id : "+userID);
            pedagogik.updateMatObserv(userID, lessonName, false);
            dataLesson.addCourseMaterial(idLearning, userID, idLesson, false);
            
            //out.println("nodeName = "+lessonName);
            
            
            //dataLesson.addWeakKD(lessonName);
            
            String nextMaterial=pedagogik.getLearnMaterial(userID);
                   
            String idNextMaterial = dataLesson.getIdLessonName(nextMaterial);          
        %>
            <jsp:forward page="PagePostTestReport.jsp">
                   <jsp:param name="data"  value="<%=userID %>" />
                </jsp:forward>  
        <%   }else if (result.equals("doubt")){
        %>
          <jsp:forward page="PagePostTest.jsp">
                <jsp:param name="idlearn"  value="<%=idLearning%>" />
            </jsp:forward> 
        <% } %>
    </body>
</html>
