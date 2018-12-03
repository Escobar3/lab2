<%-- 
    Document   : login
    Created on : 2/12/2018, 11:31:05 AM
    Author     : Luis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=windows-1252">
        <style>
            table {
                border: 1px solid #dddddd;
                font-family: arial, sans-serif;
                border-collapse: collapse;
                width: 100%;

            }

            td,th {
                border: #000000;
                text-align: left;
                padding: 8px;
            }

            p{
                text-align:right
            }


        </style> </head>
    <body >
        <h1 style="text-align:center"> LOGIN </h1>
        <form action="LoginServlet" method="POST">
            <table>
                <tbody>
                    <tr>
                        <th>
                            <p>Usuario:</p>
                        </th>
                        <th> <input type="text" class="form-control" id="pwd" name="Usuario" placeholder="Usuario"> </th>
                    </tr>
                    <tr>
                    </tr>
                    <tr>
                        <th>
                            <p>Contraseña:</p>
                        </th>
                        <th> <input type="text" class="form-control" id="pwd" name="Clave" placeholder="Clave"> </th>
                    </tr>
                    <tr>
                </tbody>
            </table>

            
                <button type="submit" name="Boton1" class="btn btn-default"> <b>Login</b> </button>
</form>

                <a href = "Registro.jsp"> 
                    <button> Registro </button>
                </a> 


      

        
    </body>
</html>
