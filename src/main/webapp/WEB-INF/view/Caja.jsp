<%-- 
    Document   : Caja
    Created on : 27/10/2018, 11:15:10 PM
    Author     : LUIS
--%>
<%@page import="java.util.List"%>
<%@page import="VO.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/base/jquery.ui.all.css">
        <link rel="stylesheet" href="css/jquery.dataTables.css">
        <script type="text/javascript" src="js/jquery-1.9.1.js"></script>
        <script type="text/javascript" src="js/jquery.ui.core.js"></script>
        <script type="text/javascript" src="js/jquery.ui.widget.js"></script>
        <script type="text/javascript" src="js/jquery.ui.button.js"></script>
        <script type="text/javascript" src="js/jquery.ui.dialog.js"></script>
        <script type="text/javascript" src="js/jquery.ui.tabs.js"></script>
        <script type="text/javascript" src="js/jquery.dataTables.js"></script>
        <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
        <link href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css" rel="stylesheet">

    </head> 
    <body> 
        <script >
            var data1;
            function procesarOperacion(valOp, idcam, id) {
                var datos;
                if (valOp === 'GU') {
                    datos = "txtValOpe=" + valOp + "&productos=" + $("#productos").val()
                            + "&idCaja=" + $("#idCaja").val()
                            + "&UserV=" + $("#UserV").val() + "&unds=" + $("#unds").val()
                            + "&p=" + $("#p").val() + "&fecha=" + $("#fecha").val();

                    $.ajax({
                        url: "/Pcorte/Caja_Servlet",
                        type: 'POST',

                        data: datos,
                        succes: function (data) {
                            console.log(data);
                        },
                        complete: function (data) {


                            obj = JSON.parse(data["responseText"]);

                            table = $('#TablaProductotos').DataTable({
                                data: obj,
                                destroy: true,
                                empty: true,
                                columns: [
                                    {data: 'precio'},
                                    {data: 'id'},
                                    {data: 'cantidad'},
                                    {data: 'nombre'}
                                ]
                            });
                        }
                    });


                } else if (valOp === 'RE') {
                    datos = "txtValOpe=" + valOp;
                    $.ajax({
                        url: "/Pcorte/Caja_Servlet",
                        type: 'POST',

                        data: datos,
                        succes: function (data) {
                            console.log(data);
                        },
                        complete: function (data) {



                        }
                    });
                }

            }

            $(document).ready(function () {

                table = $('#TablaProductotos').DataTable({
                    data: '',
                    destroy: true,
                    empty: true,
                    columns: [
                        {data: 'precio'},
                        {data: 'id'},
                        {data: 'cantidad'},
                        {data: 'nombre'}
                    ]
                });
            });




        </script>


        <h1 style="text-align:center"> CAJA </h1>
        <div id="mimensaje"></div>
        <table>
            <tbody>
                <tr>
                    <th>
                        <p >id caja: <input id="idCaja" type="text"></p>
                    </th>
                    <th>
                        <p>Usuario vendedor: <input id="UserV" type="text"> </p>
                    </th>
                    <th>
                        <p>fecha: <input id="fecha" type="date"> </p>
                    </th>
                </tr>
                <tr>
                    <th>
                        <p>Nombre: <input id="nombre" type="text"></p>
                    </th>
                    <th>
            <from action ="Caja_Servlet" method ="GET">
                <p>id producto: 
                    <%
                        System.out.println("fsdfsdfsdfsd");
                        if (request.getAttribute("p2") != null) {
                            System.out.println("-dd");
                            List<Producto> listp = (List<Producto>) request.getAttribute("p2");
                            System.out.println(listp.size());
                            System.out.println("-----------------------");
                    %>
                    <select id="productos2">
                        <%
                            for (Producto p : listp) {%>
                        <option id="p" value="<%=p.getId_producto()%>"><%= p.getId_producto()%> </option>

                        <%
                                }
                            }

                        %>


                    </select> 
                    <input id="productos"type="text"></p>

                </p>

            </from>
        </th>
        <th>

        </th>
    </tr>
    <tr>
        <th>
            <p>id: <input name="tipo_de_dato" type="text"></p>
        </th>
        <th>

        </th>

    </tr>
    <tr>
        <th>
            <p>Dinero: <input id="dinero" type="text">  </p>
        </th>
        <th>
            <p>No unidades: <input id="unds" type="text"> </p>         
            <button id="registrar" onclick="procesarOperacion('GU', 'txtValOpe')">Registrar</button>

        </th>
        <th>
            <p>Satisfaccion: <input name="tipo_de_dato" type="text"></p>
        </th>
        <th style="text-align:center">
    </tr>
</tbody>
</table>

<table id="TablaProductotos" class="display">

</table>


<pre>  
     
</pre>
<table>
    <tbody>
        <tr>
            <th>
                <p>subtotal: <input name="tipo_de_dato" type="text"> IVA 18% </p>
            </th>
            <th>
                <p>total: <input name="tipo_de_dato" type="text"></p>
            </th>
            <th> <button id="facturar" onclick="procesarOperacion('RE', 'txtValOpe')" > <b>Facturar</b> </button> </th >
            <th>                 <a href = "Caja.jsp"> 
                    <button> <b>Cancelar</b> </button>
                </a>
            </th>
            <th> <a href = "login.jsp"> 
                    <button>salir</button> 
                </a> </th>
        </tr>

    </tbody>
</table>
<div id ="dialogM" tilt                         ="inform                                 acion" />



</body>
</html>
