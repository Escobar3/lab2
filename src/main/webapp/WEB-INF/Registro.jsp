<%-- 
    Document   : Registro
    Created on : 1/12/2018, 08:12:07 PM
    Author     : Luis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                border: 1px solid #dddddd;
                text-align: left;
                padding: 8px;
            }

            p{
                text-align:left
            }


        </style> </head>
    <body>
        <h1 style="text-align:center">  REGISTRO </h1>
        <form action="RegistroServlet" method="POST">

            <table style="text-align:center">
                <tbody>
                    <tr>
                        <th>
                            <p>Nombre: <input name="nombre" type="text"></p>

                    </tr>
                    <tr>
                        <th>
                            <p>Apellido: <input name="apellido" type="text"></p>
                        </th>
                    </tr>
                    <tr>
                        <th>
                            <p>Id: <input name="id" type="text"></p>
                        </th>
                    </tr>
                    <tr>
                        <th>
                            <p>Usuario: <input name="usuario" type="text"></p>
                        </th>
                    </tr>
                    <tr>
                        <th>
                            <p>Clave: <input name="Clave" type="text"></p>
                        </th>
                    </tr>
                </tbody>
                <tbody>
                    <tr>
                    </tr>
                </tbody>
            </table>
            <center>
                <select name="tipo">
                    <option value="Vendedor">vendedor</option>
                    <option value="Administrador">Administrador</option>
                </select>

                <button type="submit" name="guardar" class="btn btn-default"> <b>Guardar</b> </button><button>  
            </center>
        </form>
    <center>
        <a href = "login.jsp"> 
            <button>   <b>Salir</b> </button>
        </a> 

    </center>
</body>
</html>