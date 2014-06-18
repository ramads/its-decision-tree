<%-- 
    Document   : PagePostTest_FormAction
    Created on : Aug 27, 2013, 8:11:38 PM
    Author     : Arin
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
         <jsp:useBean id="dataPosttest" class="controller.PostTest" scope="session"/>    
           <jsp:useBean id="dataLesson" class="controller.Lesson" scope="session"/>     
    </head>    
      <body>    
          
    <% // String idl= "m00";
     Pedagogik pedagogik = new Pedagogik();
     String idl= request.getParameter("id");
   //  char answer[]={'A','B','C','D'};
     int count=0;
    String idNextMaterial="";
    String nextMaterial="";
    String nodeName=dataLesson.getLessonName(idl);
    
     ResultSet rs = dataPosttest.getidPostQuest(idl);
        if(rs!=null){
            while(rs.next()){  
              //  String userAns="";
                String idPQ=new String(rs.getString("idpostquestion"));  
                String pQ=dataPosttest.getQuestion(idPQ);    
                                   
               // for (int i=0; i<4; i++){
                String userAns=request.getParameter(idPQ+"ans") == null ? "x" : request.getParameter(idPQ+"ans");
               //     if(userAnsX!=null){ 
                //        userAns=userAnsX;
               //     }
              //  }
               
                String corectAns=dataPosttest.getCorrectAnswer(idPQ);                   
                //out.print("Jawaban user = "+userAns+"<br>");
                //out.print("Jawaban sebenarnya = "+corectAns+"<br>");
                
                if(userAns.equals(corectAns)){
                   count++;
                }                                          
            }
        } 
      int counter=dataLesson.getTotalLearn(userID,idl);  
     String idLearning=idl+counter+userID;
         //out.print("Total Jawaban benar = "+count);
         //out.println("########################################################################## = "+nodeName);
        if(count>=3){
            //out.println("set observalue = true");
            pedagogik.updateMatObserv(userID, nodeName, true);
            dataLesson.addCourseMaterial(idLearning, userID, idl, true);
            
            nextMaterial=pedagogik.getLearnMaterial(userID);
            //dataLesson.tampilKd();
        }else{
            //out.println("set observalue = false");           
            pedagogik.updateMatObserv(userID, nodeName, false);
            dataLesson.addCourseMaterial(idLearning, userID, idl, false); 
            
            nextMaterial=pedagogik.getLearnMaterial(userID);
           // dataLesson.addWeakKD(nodeName);
         //   dataLesson.tampilKd();
        }                          
        
     //out.print("<scrip>alert('Tes')</script>");
       %>
            <jsp:forward page="PagePostTestReport.jsp">
                <jsp:param name="data"  value="<%=userID %>" />
            </jsp:forward>  
    </body>
</html>
