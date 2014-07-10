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
          
          // id materi yang terakhir diajarkan
          String idLesson = request.getParameter("idl");
          String result = request.getParameter("result")  == null ? "x" : request.getParameter("result");
            
          if (result.equals("x")) out.println("<h1>Silahkan Kembali dan pilih salah satu hasil blajar anda.............................!!</h1>");
          
          // total berapa kali sudah belajar
          int count=dataLesson.getTotalLearn(userID,idLesson);
          
          // nama materi berdasarkan id materi yang telah diambil
          String lessonName= dataLesson.getLessonName(idLesson);
          
          // id learning -> id yang bakal disimpan kedatabase
          String idLearning=idLesson+count+userID;
          
        if(result.equals("no")){
            dataLesson.addCourseMaterial(idLearning, userID, idLesson, false);
            
            // penentuan materi selanjutnya yang akan diajarkan jika materi yang di
            // ajarkan sebelumnya tidak dimengerti
          
        %>
            <jsp:forward page="PagePostTestReport.jsp">
                   <jsp:param name="data"  value="<%=userID %>" />
                </jsp:forward>  
        <% }else if (result.equals("doubt")){
            dataLesson.addCourseMaterial(idLearning, userID, idLesson, true);
        %>
          <jsp:forward page="PagePostTest_STC.jsp">
              <jsp:param name="count"  value="<%=1%>" />
          </jsp:forward> 
        <% } %>
    </body>
</html>
