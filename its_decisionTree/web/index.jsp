<%-- 
    Document   : PageLogin
    Created on : Aug 27, 2013, 11:06:41 AM
    Author     : Arin
--%>


<%@page import="controller.DataUser"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <title>Login Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link href="css/templete.css" rel="stylesheet" type="text/css"/>
        <link href="css/page_login.css" rel="stylesheet" type="text/css"/>
        <link type='text/css' href='css/stylesheet.css' rel='stylesheet' media='screen' />
        <script src="css/SpryAssets/SpryValidationTextField.js" type="text/javascript"></script>
        <script src="css/SpryAssets/SpryValidationPassword.js" type="text/javascript"></script>
        <link href="css/SpryAssets/SpryValidationTextField.css" rel="stylesheet" type="text/css" />
        <link href="css/SpryAssets/SpryValidationPassword.css" rel="stylesheet" type="text/css" />
    </head>
    
    <body>       
        <div class="container" >
            <div class="header"> </div>

            <div class="acces">                
               
           </div>   

            <div class="content">
                 <div class="desc"> 
                    <image style="margin-left: 70px; margin-bottom: 20px" src="images/icon/java1.png">
                    <div  id="textDestination">
                    <p><b></b></p>
                    <script language="JavaScript">
                        var text="Help You To Learn JAVA Programming";
                        var delay=10;
                        var currentChar=1;
                        var destination="[none]";
                        function type()
                        {
                            var dest=document.getElementById(destination);
                            if (dest)
                                {
                                dest.innerHTML=text.substr(0, currentChar)+"<blink> </blink>";
                                currentChar++;
                                if (currentChar>text.length){
                                    currentChar=1;
                                    setTimeout("type()", 5000);
                                }else{               
                                    setTimeout("type()", delay);
                                }
                            }
                        }
                        function startTyping(textParam, delayParam, destinationParam)
                        {
                            text=textParam;
                            delay=delayParam;
                            currentChar=1;
                            destination=destinationParam;
                            type();
                        }
                    </script>
                    <script language="JavaScript">
                        javascript:startTyping(text, 50, "textDestination");
                    </script>
                    </div>
                </div>
                
                <div class="FormSignUp">
                <div class="signUp"> 
                    <form id="Frm" name="Form" method="post" action="" onsubmit="validate();">
                        <h2>Sign Up Here</h2>
                        <table border=0 height="150px">
                            <tr>
                                <td><strong>User Id</strong></td>
                                <td><strong> : </strong></td>
                                <td><span id="sprytextfieldUserID">
                                    <label for="userid"></label>
                                     <input type="text" name="userid" id="userid" value=""/>
                                <!--   <span class="textfieldRequiredMsg">Masukan User ID</span> -->
                                    <span class="textfieldMinCharsMsg">Panjang minimal 5 karakter</span>
                                    <span class="textfieldMaxCharsMsg">Panjang Maksimal 15 karakter</span>
                                    </span>
                                </td>                             
                            </tr>
                            <tr>
                                <td><strong>Name</strong></td>
                                <td><strong> : </strong></td>
                                <td><span id="sprytextfieldUserName">
                                    <label for="username"></label>
                                    <input type="text" name="username" id="username" value=""/> 
                                    <span class="textfieldMinCharsMsg">Panjang minimal 5 karakter </span>
                                    <span class="textfieldMaxCharsMsg">Panjang maksimal 50 karakter </span>
                                    </span>
                                </td>                              
                            </tr>                                    
                            <tr>
                                <td><strong>Password</strong></td>
                                <td><strong> : </strong></td>
                                <td><span id="sprypassword1">
                                    <label for="password"></label>
                                    <input type="password" name="password" id="password" value="" />
                                    <span class="passwordMinCharsMsg">Panjang minimal 5 karakter</span>
                                    <span class="passwordMaxCharsMsg">Panjang maksimal 10 karakter</span>
                                    </span>
                                </td>
                            <tr>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td><input type="submit" name="submit" value="Sign Up" ></td>

                            </tr>                                    
                        </table> 
                        </form>


                <script type="text/javascript">
                        var sprytextfieldUserID = new Spry.Widget.ValidationTextField("sprytextfieldUserID", "none", {minChars:5, maxChars:15, validateOn:["change"]});
                        var sprytextfieldUserName = new Spry.Widget.ValidationTextField("sprytextfieldUserName", "none", {minChars:5, maxChars:50, validateOn:["change"]});
                        var sprypassword1 = new Spry.Widget.ValidationPassword("sprypassword1", {validateOn:["change"], minChars:5, maxChars:10});  

                   function validate(){
                        var userid = document.forms["Form"]["userid"].value;
                        var username = document.forms["Form"]["username"].value;
                        var password = document.forms["Form"]["password"].value;
                        if(userid===null || userid===""){
                            alert("Anda belum memasukkan ID");
                            return;
                        }
                        if(username===null || username===""){
                            alert("Anda belum memasukkan Nama");
                            return;
                        }
                        if(password===null || password===""){
                            alert("Anda belum memasukkan Password");
                            return;
                        }
                    }    
                    </script>     
                    <%
                        String userid = "";
                        String username = "";
                        String password = "";

                        if(request.getParameter("userid")!=null)
                            userid = request.getParameter("userid");
                        if(request.getParameter("username")!=null)
                            username = request.getParameter("username");
                        if(request.getParameter("password")!=null)
                            password = request.getParameter("password");

                        if((!userid.equals("") && !username.equals("")) && !password.equals("")){
                            DataUser d = new DataUser();
                            if(d.checkIDuser(userid)){
                                out.print("<script>alert('ID telah digunakan oleh user lain')</script>");
                            }else if(!d.checkValid(userid, username, password)){
                                out.print("<script>alert('Panjang data minimal 5 karakter')</script>");
                            }else {
                                d.addDataUser(userid, username, password);
                                out.print("<script>alert('Data anda berhasil ditambahkan, Silahkan Login Kembali.. ^_^')</script>");                               
                            }
                        }
                    %>
                </div>
                </div>
                
                 <div class="Login">
                    <form name="formLogin" method="post" action="PageLogin_FormAction.jsp">
                   <h2>Login</h2>
                   <table border=0>
                        <tr>
                                <td><strong>Id</strong></td>
                                <td><strong> : </strong></td>
                                <td><input type="text" name="userID" value=""></td>
                                <td>&nbsp; &nbsp;</td>
                        </tr>
                        <tr>
                                <td><strong>Password</strong></td>
                                <td><strong> : </strong></td>
                                <td><input type="password" name="pass" value=""></td>
                                <td>&nbsp; &nbsp;</td>
                        </tr>
                          <tr>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td><input type="submit" name="submit" value="Login" ></td>

                            </tr>  
                        </tr>                                    
                    </table> 
                    </form>
                    
                </div>
             </div>
                <div style="clear: both "></div>      
             <div class="footer">
                <div class="designer">
                    Copyright © 2013 siinumu
                </div>
            </div>
      </div>      
    </body>
</html>
           
                           
                                    
                            
                
                           