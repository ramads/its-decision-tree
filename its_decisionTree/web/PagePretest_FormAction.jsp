<%-- 
    Document   : PagePretest_FormAction
    Created on : Sep 3, 2013, 2:24:30 PM
    Author     : Arin
    Digunakan sebagai perantara saat pretest
    perantara ini maksudnya untuk melakukan insert data ke database
    data yang di insert adalah hasil jawaban user
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
            //variable untuk menyimpan jawaban yang benar (jawaban dari database)
           String corectAns=""; 
           
           //mengambil parameter count yang di lempar atau diberikan oleh PagePreTest.jsp
           //variable count ini akan di increment (ditambah setiap masuk ke PagePretest_FormAction.jsp) 
           //dan akan dikembalikan ke PagePretest_FormAction.jsp
           int count = Integer.parseInt(request.getParameter("count"));
           
           //mengambil parameter id type yang diberikan oleh PagePretest_FormAction.jsp
           String idT=request.getParameter("idT");
           
           //mengambil parameter id soal yang diberikan oleh PagePretest_FormAction.jsp
           String idQ=request.getParameter("idQ");
           
           //mengambil jawaban user
           String userAns=request.getParameter("ans")  == null ? "x" : request.getParameter("ans");
           
           //mengambil hasil jawaban yang sebenarnya dari database dan disimpan di variable correctAns
           ResultSet rs = dataPretest.getDBKey(Integer.parseInt(idT), Integer.parseInt(idQ));
            if(rs!=null){
                while(rs.next()){
                    corectAns=new String(rs.getString("corectAns"));  
                }
            }
            
           
           //mengecek jawaban dari user dan jawawban yang sebenarnya, hasil log jawaban user ini akan disimpan di database
           if(userAns.equals(corectAns)){
              dataPretest.addPretestLogTest( userID+idT, userID, "pretest", Integer.parseInt(idT), Integer.parseInt(idQ), userAns, true);
           }else {
              if(userAns.equals("")) userAns="x"; 
              //dataPretest.pretestResult.add(idT+";"+idQ+";"+userAns+";"+"false");
              dataPretest.addPretestLogTest( userID+idT, userID, "pretest", Integer.parseInt(idT), Integer.parseInt(idQ), userAns, false);                
           }
           
           //increment variable count
           count++;
           
           //jika count atau type soal dibawah 15 maka akan di arahakan kembali ke PagePretest.jsp
           //dan jika lebih besar dari 15, akan di arahkan ke PageReport.jsp
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
