<%-- 
    Document   : Report
    Created on : 2/12/2018, 03:30:05 PM
    Author     : Luis
--%>

<%@page import="VO.Vendedor"%>
<%@page import="java.util.List"%>
<%@page import="VO.Producto"%>
<%@page import="java.io.*"%>
<%@page import="org.jfree.chart.*"%>
<%@page import="org.jfree.chart.plot.*"%>
<%@page import="org.jfree.data.general.*"%>
<%@page import="org.jfree.data.category.*"%>
<%@page import="org.jfree.chart.ChartFactory"%>
<%@page import="org.jfree.data.category.DefaultCategoryDataset"%>
<%@page import="java.sql.Connection"%>
<%@page import="org.jfree.chart.*"%>
<%@page import="org.jfree.chart.plot.*"%>
<%@page import="org.jfree.data.general.*"%>
<%@page import="org.jfree.data.category.*"%>
<%@page import="java.io.*"%>
<%@page import="org.jfree.chart.*"%>
<%@page import="org.jfree.chart.plot.*"%>
<%@page import="org.jfree.data.general.*"%>
<%@page import="org.jfree.data.category.*"%>
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
                    datos = "txtValOpe=" + valOp;

                    $.ajax({
                        url: "/Pcorte/ReportServlet",
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
                                    {data: 'porcentage'},
                                    {data: 'id'},
                                    {data: 'cantidad'},
                                    {data: 'nombre'}
                                ]
                            });
                        }
                    });


                } else if (valOp === 'VE') {
                    datos = "txtValOpe=" + valOp;
                    $.ajax({
                        url: "/Pcorte/ReportServlet",
                        type: 'POST',

                        data: datos,
                        succes: function (data) {
                            console.log(data);
                        },
                        complete: function (data) {
                            obj2 = JSON.parse(data["responseText"]);
                            table = $('#TablaProductotos2').DataTable({
                                data: obj2,
                                destroy: true,
                                empty: true,
                                columns: [
                                    {data: 'porcentage'},
                                    {data: 'cantidad'},
                                    {data: 'nombre'}
                                ]
                            });


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
                        {data: 'porcentage'},
                        {data: 'id'},
                        {data: 'cantidad'},
                        {data: 'nombre'}
                    ]
                });
            });
            $(document).ready(function () {

                table = $('#TablaProductotos2').DataTable({
                    data: '',
                    destroy: true,
                    empty: true,
                    columns: [
                        {data: 'porcentage'},
                        {data: 'cantidad'},
                        {data: 'nombre'}
                    ]
                });
            });




        </script>
        <h1>Report!</h1>

        <table id="TablaProductotos" class="display"></table>
        <button id="registrar" onclick="procesarOperacion('GU', 'txtValOpe')">VentasP</button>
        <br>
        <br>
        <form action="ReportServlet" method="GET">  
            <button type="submit" name="Boton1" class="btn btn-default"> <b>Grafico</b> </button>                              

        </form>
        <br>
        <table id="TablaProductotos2" class="display"></table>
        <button id="registrar" onclick="procesarOperacion('VE', 'txtValOpe')">Vendedores</button>
        <form action="ReportServlet" method="GET">  

            <button type="submit" name="Boton2" class="btn btn-default"> <b>Grafico</b> </button>  
        </form>
        <br>
        <br>
        <a href = "vAdministrador.jsp"> 
            <button>salir</button> 
        </a> 

        <form action="ReportServlet" method="GET">

            <%
                System.out.println("fdffffffffffffff");
                if (request.getAttribute("lista") != null) {
                    System.out.println("-dd");
                    List<Producto> listp = (List<Producto>) request.getAttribute("lista");
                    System.out.println(listp.size());
                    System.out.println("-----------------------");

                    DefaultPieDataset data = new DefaultPieDataset();
                    //cargamos los datos
                    //-------------titulo,valor-----------//
                    for (int i = 0; i < listp.size(); i++) {
                        System.out.println("safgvhdbvddasfgv");
                        System.out.println("");
                        data.setValue(listp.get(i).getNombre(), listp.get(i).getPorcentaje());
                    }
                    System.out.println("dasdasdasdasdasd");
                    //objeto donde estara el grafico
                    //--------------------------------------------titulo de la grafica,datos,decoraciones activas
                    JFreeChart grafico = ChartFactory.createPieChart("ingresos año 2018", data, true, true, true);
                    System.out.println("+++++++++++");
                    //donde se visualizara la grafica
                    response.setContentType("image/JPEG");

                    //la salida de la grafica
                    OutputStream sa = response.getOutputStream();
                    //impresion
                    System.out.println("´{lpkoasfg");
                    //---------------------------salida,grafico,tamaños
                    ChartUtilities.writeChartAsPNG(sa, grafico, 350, 350);
                    System.out.println("fsdghb");
                } else if (request.getAttribute("lista2") != null) {
                    List<Vendedor> listp2 = (List<Vendedor>) request.getAttribute("lista2");
                    DefaultCategoryDataset data1 = new DefaultCategoryDataset();
                    for (int i = 0; i < listp2.size(); i++) {
                        System.out.println("safgvhdbvddasfgv");
                        System.out.println("");

                        data1.setValue(listp2.get(i).getProductos_vend(), "ventas", listp2.get(i).getNombre());
                    }

                    //--------------------------------------------titulo de la grafica,titulo abajo,titulo izquierda, datos,orientacion,decoraciones activas
                    JFreeChart grafico1 = ChartFactory.createBarChart("Vebdedores", "vendedores", "Ventas", data1, PlotOrientation.VERTICAL, true, true, true);
                    //donde se visualizara la grafica
                    response.setContentType("image/JPEG");
                    //la salida de la grafica
                    OutputStream sa1 = response.getOutputStream();
                    //impresion
                    //---------------------------salida,grafico,tamaños
                    ChartUtilities.writeChartAsPNG(sa1, grafico1, 350, 350);

                }
            %>
        </form>


    </body>
</html>
