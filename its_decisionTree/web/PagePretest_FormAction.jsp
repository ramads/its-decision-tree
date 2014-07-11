<%-- 
    Document   : PagePretest_FormAction
    Created on : Sep 3, 2013, 2:24:30 PM
    Author     : Arin
--%>

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
        <jsp:useBean id="dataPretest" class="controller.Pretest" scope="session"/>     
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
            
             
           if(userAns.equals(corectAns)){
               //dataPretest.pretestResult.add(idT+";"+idQ+";"+userAns+";"+"true");
              dataPretest.addPretestLogTest( userID+idT, userID, "pretest", Integer.parseInt(idT), Integer.parseInt(idQ), userAns, true);
           }else {
              if(userAns.equals("")) userAns="x"; 
              //dataPretest.pretestResult.add(idT+";"+idQ+";"+userAns+";"+"false");
              dataPretest.addPretestLogTest( userID+idT, userID, "pretest", Integer.parseInt(idT), Integer.parseInt(idQ), userAns, false);                
           }
           count++;
           
           if(Integer.parseInt(idT)<15){
               %>
                <jsp:forward page="PagePretest.jsp">
                    <jsp:param name="count"  value="<%=count %>" />
                </jsp:forward>           
             <%  } else{ 
               
                dataPretest.addPretestLog(userID); 
                  
                %>
                <jsp:forward page="PageReport.jsp">
                    <jsp:param name="id"  value="<%=userID%>" />
                 </jsp:forward> 
          <% } %>  
      
    </body>
</html>