<%-- 
    Document   : PagePostTest_FormAction
    Created on : June 29, 2014, 11:34:38 AM
    Author     : Ami
--%>

<%@page import="controller.Pedagogik"%>
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
%>
<html>
      <head>
        <title></title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/templete.css" rel="stylesheet" type="text/css"/>
        <link href="css/page_posttest.css" rel="stylesheet" type="text/css"/>     
        <link type='text/css' href='css/stylesheet.css' rel='stylesheet' media='screen' /> 
        <jsp:useBean id="dataPretest" class="controller.Pretest" scope="session"/> 
        <jsp:useBean id="dataPosttest" class="controller.PostTest" scope="session"/>    
        <jsp:useBean id="dataLesson" class="controller.Lesson" scope="session"/>     
    </head>    
    <body>
        <%  
           char answer[]={'A','B','C','D'};
           String corectAns=""; 
           int count = Integer.parseInt(request.getParameter("count"));
           
           // cek apakah ada posttest terakhir yang tidak selesai
//           if(count==1){
//               try{
//                    dataPosttest.cekLastPostTest(userID);
//               }catch(Exception e){
//                   System.out.println("error: "+e.getMessage());
//               }
//           }
           // id topik
           String idT=request.getParameter("idT");
           
           // id question
           String idQ=request.getParameter("idQ");
           
           // jawaban dari user
           String userAns = request.getParameter("ans")  == null ? "x" : request.getParameter("ans");
           
           // kunci jawaban soal
           ResultSet rs = dataPretest.getDBKey(Integer.parseInt(idT), Integer.parseInt(idQ));
            if(rs!=null){
                while(rs.next()){
                    corectAns = new String(rs.getString("corectAns"));  
                }
            }
            boolean result = true;
           //jika jawaban user benar  
           if(userAns.equals(corectAns)){
              result = true;
           
           //jika jawaban user salah
           }else {
              if(userAns.equals("")) userAns="x"; 
              result = false;  
           }
           String idCourse = dataPosttest.getLastIdCourse(userID);
           dataPosttest.addPosttestResult(userID, idCourse, Integer.parseInt(idT), Integer.parseInt(idQ), userAns, result);
           count++;
           
           if(Integer.parseInt(idT)<15){
               %>
                <jsp:forward page="PagePostTest_STC.jsp">
                    <jsp:param name="count"  value="<%=count%>" />
                </jsp:forward>           
             <%  } else{ %>
                <jsp:forward page="PagePostTestReport_STC.jsp">
                    <jsp:param name="data" value="<%=userID %>" />
                </jsp:forward>
          <% } %>  
    </body>
</html>
