<%-- 
    Document   : Admin_dataUser
    Created on : Sep 3, 2013, 7:22:41 PM
    Author     : Arin
--%>

<%@page import="controller.Pretest"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

 <% Pretest data = new Pretest();  %>
 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Data User</title>
    </head>
    <body>
        Data Test User
        <br><br>
        <form name="form 1" method="post">
            <table border="1">
                <tr bgColor="#ddcc45">
                    <th width="200">date</th>           
                    <th width="100">User Id</th>           
                    <th width="100">flag</th>              
                    <th width="100">id Type</th>                              
                    <th width="100">id Questions</th>                              
                    <th width="100">User Answer</th>                                                           
                </tr>
                <% ResultSet rs = data.getDataTestUser();
                    if(rs!=null){
                        while(rs.next()){
                           String date=new String(rs.getString("date"));
                           String iduser=new String(rs.getString("iduser"));
                           String flag=new String(rs.getString("flag"));                   
                           String idt=new String(rs.getString("idtype"));                   
                           String idq=new String(rs.getString("idquest"));                   
                           String userAns=new String(rs.getString("userAns"));                   
                        %>
                        <tr> 
                            <td width="100"><%= date%></td>                
                            <td width="300"><%= iduser%></td>                       
                            <td width="300"><%= flag%></td>                       
                            <td width="100"><%= idt %></td>  
                            <td width="100"><%= idq %></td>  
                            <td width="100"><%= userAns %></td>  
                        </tr>
                        <% }
                   }  %>
            </table>
        </form>
    </body>
</html>
