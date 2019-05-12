<%-- 
    Document   : VideoView
    Created on : 04-mar-2019, 9:50:41
    Author     : Usuario
--%>

<%@page import="singleton.LogRegistrer"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="singleton.UserStatdistics"%>
<%@page import="stateful.User"%>
<%@page import="entities.PlaylistModel"%>
<%@page import="entities.Comments"%>
<%@page import="java.util.List"%>
<%@page import="entities.Video"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
            Video v = (Video) session.getAttribute("video");
            String videoName = v.getName();
        %>
        <title>Ver <%= videoName%></title>
        <link rel="stylesheet" href="css/style.css" >
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    </head>
    <body>
        <%@include file="components/NavBar.jsp" %>
        <main class="fullScreen">
            <div class="py-4">
                <div class="container">
                    <div class="row mb-3">
                        <div class="col-md-4 themed-grid-col">
                            <div>
                                <object width="320" height="240" data="<%= v.getUrl()%>" ></object>
                            </div>
                            <div>
                                <label>Vistas: <%= v.getViews()%></label>
                            </div>
                            <div>
                                <form class="" action="<%= frontController%>" method="get">
                                    <input type="text" name="command" value="LikedCommand" hidden>
                                    <input type="text" name="liked" value="like" hidden>
                                    <input type="text" name="videoName" value="<%= videoName%>" hidden>
                                    <button class="btn btn-link w-100 mx-auto" type="submit">Me gusta - <%= v.getLikes()%></button>
                                </form>
                                <form class="" action="<%= frontController%>" method="get">
                                    <input type="text" name="command" value="LikedCommand" hidden>
                                    <input type="text" name="liked" value="dislike" hidden>
                                    <input type="text" name="videoName" value="<%= videoName%>" hidden>
                                    <button class="btn btn-link w-100 mx-auto" type="submit">No me gusta - <%= v.getDislikes()%></button>
                                </form>
                                <%
                                    User u = (User) session.getAttribute("user");
                                    if (session.getAttribute("user") != null) {
                                        if (u.getUsername().equals(v.getUsername().getUsername())) {
                                %>
                                <form class="" action="<%= frontController%>" method="get">
                                    <input type="text" name="command" value="DeleteVideoCommand" hidden>
                                    <input type="text" name="videoName" value="<%= videoName%>" hidden>
                                    <button class="btn btn-link w-100 mx-auto" type="submit">Eliminar este video</button>
                                </form>
                                <%
                                        }
                                    }
                                %>
                            </div>
                        </div>
                        <%
                            if (session.getAttribute("user") != null) {
                                List<PlaylistModel> pld = (List<PlaylistModel>)session.getAttribute("userPlayList");
                                
                        %>
                        <div class="col-md-4 themed-grid-col">
                            <nav id="menu">
                                <ul>
                                    <li><a href="#">PlayLists</a>
                                        <ul>
                                            <%
                                                for (PlaylistModel playlist : pld) {
                                            %>
                                            <li><a href="FrontController?command=AddVideoPlayListCommand&videoName=<%= v.getName() %>&playList=<%= playlist.getName() %>"><%= playlist.getName() %></a></li>
                                            <%
                                                }
                                            %>
                                            
                                        </ul>
                                    </li>
                                </ul>
                            </nav>
                        </div>
                        <%                            }
                        %>
                    </div>
                    <div class="row mb-3">
                        <div class="col-md-4 themed-grid-col">
                            <h3>Titulo: </h3> <p><%= videoName%></p>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-md-4 themed-grid-col">
                            <h3>Descripción: </h3> <br> <p><%= v.getDescription()%></p>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-md-4 themed-grid-col">
                            <h2>Comentarios: </h2>
                        </div>
                    </div>
                    <%
                        List<Comments> comments = (List<Comments>) session.getAttribute("comments");
                        if (comments.size() == 0) {
                    %>
                    <div class="row mb-3">
                        <div class="col-md-4 themed-grid-col">
                            <h4>No hay comentarios </h4>
                        </div>
                    </div>
                    <%
                        }
                        for (Comments comment : comments) {
                    %>
                    <div class="row mb-3">
                        <div class="col-md-4 themed-grid-col">
                            <label><%= comment.getUsername().getUsername()%>:</label>
                            <br>
                            <label><%= comment.getComment()%></label>
                        </div>
                    </div> 
                    <%
                        }
                        if (session.getAttribute("user") != null) {
                    %>
                    <div class="row mb-3">
                        <div class="col-md-4 themed-grid-col">
                            <form action="<%= frontController%>" method="get">
                                <input type="text" name="comment" placeholder="Escriba su comentario..." required>
                                <input type="text" name="video" value="<%= v.getName()%>" hidden>
                                <input type="text" name="command" value="AddComment" hidden>
                                <button class="btn btn-link w-100 mx-auto" type="submit" >Enviar</button>
                            </form>
                        </div>
                    </div> 
                    <%
                        }
                    %>
                </div>
            </div>
        </main>
        <%@include file="components/Footer.jsp" %>
        <!-- Optional JavaScript -->
        <script type="text/javascript" src="script/script.js"></script>
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="script/popper.min.js" ></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>
