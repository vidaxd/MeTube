<%-- 
    Document   : VideoUpload
    Created on : 20-abr-2019, 18:24:37
    Author     : Usuario
--%>

<%@page import="stateful.User"%>
<%@page import="singleton.LogRegistrer"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="singleton.UserStatdistics"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Subir Video</title>
        <link rel="stylesheet" href="css/style.css" >
        <link href="css/signin.css" rel="stylesheet">
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
        %>
        <main>
            <div class="fullScreen signFrom">
            <form class="form-signin " action="<%= frontController %>" method="get">
                <h1 class="h3 mb-3 font-weight-normal">Introduzca datos del video</h1>
                <label for="inputVideoName" class="sr-only">Nombre del video</label>
                <input type="text" id="inputVideoName" name="videoName" class="form-control" placeholder="Nombre del video" required autofocus>
                <label for="inputDescription" class="sr-only">Descripción del video</label>
                <textarea name="description" placeholder="Descripción del video" cols="50" rows="10"></textarea>
                <label for="inputVideo" class="sr-only">Video</label>
                <input type="text" name="url" class="form-control" placeholder="Url del video" required autofocus>
                <input type="text" name="command" value="VideoUploadCommand" hidden>
                
                <button class="btn btn-lg btn-primary btn-block" type="submit">Subir Videos</button>
                <p class="mt-5 mb-3 text-muted">&copy; 2018-2019</p>
            </form>
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
