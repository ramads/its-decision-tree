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
           String idT=request.getParameter("idT");
           String idQ=request.getParameter("idQ");
           
           String userAns=request.getParameter("ans")  == null ? "x" : request.getParameter("ans");
           
           ResultSet rs = dataPretest.getDBKey(Integer.parseInt(idT), Integer.parseInt(idQ));
            if(rs!=null){
                while(rs.next()){
                    corectAns=new String(rs.getString("corectAns"));  
                }
            }
            
           //jika jawaban user benar  
           if(userAns.equals(corectAns)){
               //dataPretest.pretestResult.add(idT+";"+idQ+";"+userAns+";"+"true");
              
           
           //jika jawaban user salah
           }else {
              if(userAns.equals("")) userAns="x"; 
              //dataPretest.pretestResult.add(idT+";"+idQ+";"+userAns+";"+"false");
                              
           }
           count++;
           
           if(Integer.parseInt(idT)<15){
               %>
                <jsp:forward page="PagePostTest_STC.jsp">
                    <jsp:param name="count"  value="<%=count%>" />
                </jsp:forward>           
             <%  } else{ %>
                <jsp:forward page="PagePostTestReport_STC.jsp">
                    <jsp:param name="data"  value="<%=userID %>" />
                </jsp:forward>
          <% } %>  
    </body>
</html>
