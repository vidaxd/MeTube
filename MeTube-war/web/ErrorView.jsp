<%@page import="stateful.User"%>
<%@page import="singleton.LogRegistrer"%>
<%@page import="singleton.UserStatdistics"%>
<%@page import="javax.naming.InitialContext"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
        <link rel="stylesheet" href="css/style.css" type="text/css">
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    </head>
    <body>
        <%@include file="components/NavBar.jsp" %>
        <main class="fullScreen">
            <div class="py-4">
                <div class="container">
                    <div class="row mb-3">
                        <div>
                            <h1>
                                <%= (String)session.getAttribute("error") %>
                            </h1>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <%@include file="components/Footer.jsp" %>
        <!-- Optional JavaScript -->
        <script type="text/javascript" src="script/script.js"></script>
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
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
        
        session.setAttribute("error", null);
    %>
</html>
