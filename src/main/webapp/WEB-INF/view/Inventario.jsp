<%-- 
    Document   : Inventario
    Created on : 1/12/2018, 11:46:46 AM
    Author     : Luis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=windows-1252">
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
        <script >
            var data1;
            function procesarOperacion(valOp, idcam, id) {
                var datos;
                if (valOp == 'GU') {
                    console.log(valOp);
                    datos = "txtValOpe=" + valOp + "&producto=" + $("#producto").val()
                            + "&idp=" + $("#idp").val()
                            + "&precio=" + $("#precio").val() + "&cantidad=" + $("#cantidad").val();

                } else if (valOp = 'MU') {
                    datos = "txtValOpe=" + valOp;
                    $.ajax({
                        url: "/Pcorte/InventarioServlet2",
                        type: 'POST',

                        data: datos,
                        succes: function (data) {
                            console.log(data);
                        },
                        complete: function (data) {
                            if (data["responseText"] != "nul") {
                                console.log(data["responseText"]),
                                        obj = JSON.parse(data["responseText"]),
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
                        }
                    });


                }

                $.ajax({
                    url: "/Pcorte/InventarioServlet2",
                    type: 'POST',

                    data: datos,
                    succes: function (data) {
                        console.log(data);
                    },
                    complete: function (data) {
                    }
                });
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

        <style>
            table {
                border: 1px solid #dddddd;
                font-family:arial, sans-serif;
                border-collapse: collapse; 
                width: 100%;
            }

            td,th {
                border: 1px solid #dddddd;
                text-align: left;
                padding: 8px;
            }
            field{
                margin-right: 5px;
            }

        </style>
        <title>INVENTARIO</title>
    </head>


    <body>
        <h1 style="text-align:center">INVENTARIO</h1>
        <p>Nombre : <field> <input id="producto" type="text"> </field></p>
<p>id :   <input  id="idp" type="text"> </p>
<p>Precio :   <input id="precio" type="text"> </p>
<p>cantidad:  <input  id="cantidad" type="text"> </p>
<table id="TablaProductotos" class="display">
    <tbody>
        <tr>
            <th>Nombre</th>
            <th>ID</th>
            <th>Precio</th>
            <th>Cantidad</th>
        </tr>
    </tbody>
</table>
<table>
    <tbody>
        <tr>
            <th style="text-align:center">   <button id="registrar" onclick="procesarOperacion('GU', 'txtValOpe')">Registrar</button>
                <button id="mostrar" onclick="procesarOperacion('MU', 'txtValOpe')">  mostrar </button> 


                <a href = "vAdministrador.jsp"> 
                    <button>salir</button> 
                </a>
        </tr>
    </tbody>
</table>
</body>
</html>
