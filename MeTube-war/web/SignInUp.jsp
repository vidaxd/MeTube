<%-- 
    Document   : SignIn_Up
    Created on : 18-abr-2019, 17:11:37
    Author     : Usuario
--%>

<%@page import="singleton.LogRegistrer"%>
<%@page import="singleton.UserStatdistics"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="stateful.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro</title>
        <link rel="stylesheet" href="css/style.css" >

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <!-- Boostrap login -->
        <link href="css/signin.css" rel="stylesheet">

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
            String sign = (String) request.getParameter("sign");
            if (sign.equals("signIn")) {
        %>

        <div class="fullScreen signFrom">
            <form class="form-signin " action="<%= frontController%>" method="get">
                <h1 class="h3 mb-3 font-weight-normal">Introduzca sus datos</h1>
                <label for="inputEmail" class="sr-only">Email</label>
                <input type="email" id="inputEmail" name="email" class="form-control" placeholder="Email" required autofocus>
                <label for="inputPassword" class="sr-only">Contraseña</label>
                <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Contraseña" required>
                <input type="text" name="command" value="LoginCommand" hidden>
                <div class="checkbox mb-3">
                    <label>
                        <input type="checkbox" value="remember-me"> Recordarme
                    </label>
                </div>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Iniciar Sesion</button>
                <p class="mt-5 mb-3 text-muted">&copy; 2018-2019</p>
            </form>
        </div>
        <%
        } else if (sign.equals("signUp")) {
            String error = "";
            if (session.getAttribute("sign") != null && session.getAttribute("sign").equals("signUp")) {
                error = (String) session.getAttribute("error");
                session.setAttribute("sign", null);
                session.setAttribute("error", null);
            }

        %>


        <div class="fullScreen signFrom">
            <form class="form-signin" action="<%= frontController%>" method="get">
                <h1 class="h3 mb-3 font-weight-normal">Introduzca sus datos</h1>
                <label for="inputUserName" class="sr-only">Nombre de Usuario</label>
                <input type="username" id="inputEmail" name="username" class="form-control" placeholder="username" required autofocus>
                <label for="inputName" class="sr-only">Nombre</label>
                <input type="name" id="inputEmail" name="name" class="form-control" placeholder="name" required autofocus>
                <label for="inputEmail" class="sr-only">Email</label>
                <input type="email" id="inputEmail" name="email" class="form-control" placeholder="Email" required autofocus>
                <label for="inputPassword" class="sr-only">Contraseña</label>
                <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Contraseña" required>
                <label for="confirmPassword" class="sr-only">Comfirmar Contraseña</label>
                <input type="password" id="inputPassword" name="confirmPassword" class="form-control" placeholder="Comfirmar Contraseña" required>
                <input type="text" name="command" value="RegisterCommand" hidden>
                <h1><%= error%></h1>

                <button class="btn btn-lg btn-primary btn-block" type="submit">Resgistrarse</button>
                <p class="mt-5 mb-3 text-muted">&copy; 2018-2019</p>
            </form>
        </div>

        <%

        } else if (sign.equals("editUser") && session.getAttribute("user") != null) {
            User u = (User)session.getAttribute("user");
        %>

        <div class="fullScreen signFrom">
            <form class="form-signin" action="<%= frontController%>" method="get">
                <h1 class="h3 mb-3 font-weight-normal">Cambie los datos que desee</h1>
                <label for="inputUserName" class="sr-only">Nombre de Usuario</label>
                <input type="username" id="inputEmail" name="username" class="form-control" placeholder="username" value="<%= u.getUsername() %>" required autofocus>
                <label for="inputName" class="sr-only">Nombre</label>
                <input type="name" id="inputName" name="name" class="form-control" placeholder="name" value="<%= u.getName()%>" required autofocus>
                <label for="inputEmail" class="sr-only">Email</label>
                <input type="email" id="inputEmail" name="email" class="form-control" placeholder="Email" value="<%= u.getEmail() %>" required autofocus>
                <label for="inputPassword" class="sr-only">Contraseña</label>
                <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Contraseña" value="<%= u.getPassword() %>" required>
                <label for="confirmPassword" class="sr-only">Comfirmar Contraseña</label>
                <input type="password" id="inputPassword" name="confirmPassword" class="form-control" placeholder="Comfirmar Contraseña" value="<%= u.getPassword() %>" required>
                <input type="text" name="command" value="EditUserCommand" hidden>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Resgistrarse</button>
                <p class="mt-5 mb-3 text-muted">&copy; 2018-2019</p>
            </form>
        </div>

        <%
            }
        %>


        <%@include file="components/Footer.jsp" %>
        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>



    </body>
</html>
