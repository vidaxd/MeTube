
<%@page import="stateful.SessionVideoHistory"%>
<%@page import="singleton.AllStateful"%>
<%@page import="singleton.UserStatdistics"%>
<%@page import="stateful.User"%>
<%@page import="singleton.LogRegistrer"%>
<%@page import="javax.naming.InitialContext"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Web Components</title>
        <link rel="stylesheet" href="css/style.css" >
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    </head>
    <body>
        <%
            //Registrar log de la pagina.
            LogRegistrer lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            User user = (User) session.getAttribute("user");
            String username = "Visitante";
            if (user != null) {
                username = user.getUsername();
            }
            String nameClass = this.getClass().getSimpleName().replace("_", ".");
            lg.log(nameClass + "::html::" + username);

            //Resgistrar visita de la pagina
            UserStatdistics us = (UserStatdistics) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/UserStatdistics!singleton.UserStatdistics");
            us.addPageVisited(nameClass);

            AllStateful as = (AllStateful) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/AllStateful!singleton.AllStateful");

        %>
        <%@include file="components/NavBar.jsp" %>
        <main class="fullScreen">
            <div class="py-4">
                <div class="container">
                    <div class="row mb-3">
                        <div class="col-md-4 themed-grid-col img">
                            <ul class="list-group">
                                <li class="list-group-item"><h2>Usuarios online:</h2></li>
                                    <%                                    
                                        if (as.getUsersOnline().isEmpty()) {
                                    %>
                                <li class="list-group-item">No hay usuarios online.</li>
                                    <%
                                        }
                                        for (User userOnline : as.getUsersOnline()) {
                                    %>
                                <li class="list-group-item"><h4>Usuario con id: <%= userOnline.getId() %></h4></li>
                                <li class="list-group-item">Nombre de cuenta de usuario: <%= userOnline.getUsername() %>.</li>
                                <li class="list-group-item">Nombre de usuario: <%= userOnline.getName() %>.</li>
                                <li class="list-group-item">Email del usuario <%= userOnline.getEmail() %>.</li>
                                    <%
                                        }
                                    %>
                            </ul>
                        </div>
                        <div class="col-md-4 themed-grid-col img">
                            <ul class="list-group">
                                <li class="list-group-item"><h2>Historiales de usuarios online</h2></li>
                                    <%
                                        if (as.getVideoHistory().isEmpty()) {
                                    %>
                                <li class="list-group-item">No hay historiales de video aún</li>
                                    <%
                                        }
                                        for (SessionVideoHistory svh : as.getVideoHistory()) {
                                    %>
                                <li class="list-group-item">Historial del usuario: <a href="History.jsp?all=<%= svh.getUser().getUsername() %>"><%= svh.getUser().getUsername() %></a></li>
                                    <%
                                        }
                                    %>
                            </ul>
                        </div>
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
