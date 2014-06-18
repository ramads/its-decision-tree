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
          // String userAns="";
           String corectAns=""; 
           int count = Integer.parseInt(request.getParameter("count"));
           String idT=request.getParameter("idT");
           String idQ=request.getParameter("idQ");

            //for (int i=0; i<4; i++){
           String userAns=request.getParameter("ans")  == null ? "x" : request.getParameter("ans");     
           //     if(userAnsX!=null){
            //        userAns=userAnsX;
          //      }
          // }    
           
         //  out.println(idT);
          // out.println(idQ);
         //  out.println(userAns);
        //   if(userAns==null){
         //     out.print("<script>alert('Nooooooo......!!')</script>");
        //   }
           
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
           
          // out.println(q.dataPretest.get(0));
         //  String []dataa=q.dataPretest.get(0).split(";");
         //  out.println(dataa[0]);
        //   out.println(dataa[1]);
         //  out.println(dataa[2]);
       //    out.println(dataa[3]);
           
           if(Integer.parseInt(idT)<15){
               %>
                <jsp:forward page="PagePretest.jsp">
                    <jsp:param name="count"  value="<%=count%>" />
                </jsp:forward>           
             <%  } else{ 
               
                dataPretest.addPretestLog(userID); 
              //  out.println(dataPretest.pretestResult.size());
           
              //  ArrayList<String> baru = dataPretest.pretestResult;
              //  for(int i=0; i<baru.size(); i++){
                    
             //        String[] data=baru.get(i).split(";");
            //         out.println(data[0]);
              //       out.println(data[1]);
             //        out.println(data[2]);
              //       out.println(data[3]);
                   //  dataPretest.addLogTest(dataUser.gettId(), "pretest", Integer.parseInt(data[0]), Integer.parseInt(data[1]), data[2], Boolean.valueOf(data[3]));
               // }  
               
               
                %>
                <jsp:forward page="PageReport.jsp">
                    <jsp:param name="id"  value="<%=userID%>" />
                 </jsp:forward> 
          <% } %>  
      
    </body>
</html>
