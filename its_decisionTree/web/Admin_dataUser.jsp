<%-- 
    Document   : Admin_dataUser
    Created on : Sep 3, 2013, 7:22:41 PM
    Author     : Arin
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="controller.DataUser"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

 <% DataUser data = new DataUser();  %>
 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Data User</title>
       
    </head>
    <body>
        Data User
        <br><br>
        <form name="form 1" method="post">
            <table border="1">
                <tr bgColor="#ddcc45">
                    <th width="100">User Id</th>           
                    <th width="300">User Name</th>              
                    <th width="100">Password</th>                              
                </tr>
                <% ResultSet rs = data.getDataUser();
                    if(rs!=null){
                        while(rs.next()){
                           String userId=new String(rs.getString("iduser"));
                           String username=new String(rs.getString("name"));
                           String password=new String(rs.getString("password"));                   
                        %>
                        <tr> 
                            <td width="100"><%= userId%></td>                
                            <td width="300"><%= username%></td>                       
                            <td width="100"><%= password %></td>  
                        </tr>
                        <% }
                   }  %>
            </table>
        </form>
    </body>
</html>
