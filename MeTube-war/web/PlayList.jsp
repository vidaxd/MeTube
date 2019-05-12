

<%@page import="stateful.User"%>
<%@page import="singleton.LogRegistrer"%>
<%@page import="singleton.UserStatdistics"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="entities.PlaylistModel"%>
<%@page import="java.util.ArrayList"%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PlayList</title>
        <link rel="stylesheet" href="css/style.css" >
        
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        
    </head>
    <body>
        <%@include file="components/NavBar.jsp" %>
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
            String frontController = "FrontController";
            List<PlaylistModel> playlist = (List<PlaylistModel>)session.getAttribute("pModel");
            
            
        %>
        
        <main class="fullScreen">
            <div class="py-4">
                <div class="container">
                    <div class="row mb-3">
                        <div class="col-md-4 themed-grid-col img">
                            <form class="form-inline mt-2 mt-md-0" action="<%= frontController %>" method="get">
                                <input type="text" name="playList" >
                                <input type="text" name="command" value="CreatePlayListCommand" hidden>
                                <button class="btn btn-link w-100 mx-auto" type="submit">Crear PlayList</button>
                            </form>
                        </div>
                    </div>
                    
                        <%
                            if (playlist != null) {
                                for (PlaylistModel pl: playlist) {
                                    
                        %>
                    <div class="row mb-3">
                        <div class="col-md-4 themed-grid-col img">
                            <form action="<%= frontController %>" method="get">
                                <input type="text" name="playList" value="<%= pl.getName() %>" hidden="">
                                <input type="text" name="command" value="ViewPlayListCommand" hidden>
                                <button class="btn btn-link w-100 mx-auto" type="submit"><%= pl.getName() %></button>
                            </form>
                        </div>
                    </div>
                        <%
                                }
                            } else {
                                
                        %>
                    <div class="row mb-3">
                        <div class="col-md-4 themed-grid-col img">
                            <h1>No hay PlayLists aun</h1>
                        </div>
                    </div>
                        <%
                            }
                        %>
                </div>
            </div>
        </main>

        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <%@include file="components/Footer.jsp" %>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>
