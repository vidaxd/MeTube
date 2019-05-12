
<%@page import="stateful.User"%>
<%@page import="singleton.LogRegistrer"%>
<%@page import="singleton.UserStatdistics"%>
<%@page import="javax.naming.InitialContext"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Stadistics Page</title>
        <link rel="stylesheet" href="css/style.css" >
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    </head>
    
    <%
        //Registrar log de la pagina.
        LogRegistrer lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
        User user = (User)session.getAttribute("user");
        String username = "Visitante";
        if(user != null) username = user.getUsername();
        String nameClass = this.getClass().getSimpleName().replace("_", ".");
        lg.log(nameClass+"::html::"+username);
        
        //Resgistrar visita de la pagina
        UserStatdistics us = (UserStatdistics) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/UserStatdistics!singleton.UserStatdistics");
        us.addPageVisited(nameClass);

    %>
    <body>
        <%@include file="components/NavBar.jsp" %>
        <main class="fullScreen">
            <div class="py-4">
                <div class="container">
                    <div class="row mb-3">
                        <div class="col-md-4 themed-grid-col img">
                            <ul class="list-group">
                                <li class="list-group-item"><h2>Usuarios online:</h2></li>
                                <%                                   
                                    if (us.getUsersOnline().isEmpty()) {
                                %>
                                <li class="list-group-item">No hay usuarios online.</li>
                                    <%
                                        }
                                        for (String userOnline : us.getUsersOnline()) {
                                    %>
                                <li class="list-group-item">Esta online el usuario: <%= userOnline%>.</li>
                                    <%
                                        }
                                    %>
                            </ul>
                        </div>
                        <div class="col-md-4 themed-grid-col img">
                            <ul class="list-group">
                                <li class="list-group-item"><h2>Usuarios que se han iniciado sesión:</h2></li>
                                    <%
                                        if (us.getUsersLogIn().isEmpty()) {
                                    %>
                                <li class="list-group-item">Aún ningún usuario ha iniciado sesión.</li>
                                    <%
                                        }
                                        for (String userLogIn : us.getUsersLogIn()) {
                                    %>
                                <li class="list-group-item">Ha iniciado sesión el usuario: <%= userLogIn%>.</li>
                                    <%
                                        }
                                    %>
                            </ul>
                        </div>
                        <div class="col-md-4 themed-grid-col img">
                            <ul class="list-group">
                                <li class="list-group-item"><h2>Paginas visitadas:</h2></li>
                                    <%
                                        for (String pageVisited : us.getPagesVisited().keySet()) {
                                            if (us.getPagesVisited().get(pageVisited) > 1) {
                                    %>
                                <li class="list-group-item">La pagina <%= pageVisited%> ha sido visitada <%= us.getPagesVisited().get(pageVisited)%> veces.</li>
                                    <%
                                    } else {
                                    %>
                                <li class="list-group-item">La pagina <%= pageVisited%> ha sido visitada <%= us.getPagesVisited().get(pageVisited)%> vez.</li>
                                    <%
                                            }
                                        }
                                    %>
                            </ul>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-md-4 themed-grid-col img scroll">
                            <ul class="list-group">
                                <li class="list-group-item"><h2>Actividad en la pagina:</h2></li>
                                <%
                                    for (String userOnline : lg.getRegistry()) {
                                %>
                                <li class="list-group-item"><%= userOnline %></li>
                                <%
                                    }
                                %>
                            </ul>
                        </div>
                        <%
                            if(user != null) {
                        %>
                        <div class="col-md-4 themed-grid-col img scroll">
                            <ul class="list-group">
                                <li class="list-group-item"><h2>Ver Actividad de la web</h2></li>
                                <li class="list-group-item"><a href="WebActivity.jsp">Actividad de la web</a></li>
                            </ul>
                        </div>
                        <%
                            }
                        %>
                    </div>
                </div>
            </div>
        </main>
        <%@include file="components/Footer.jsp" %>     
        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>
