<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <header>
            <nav class="navbar navbar-expand-md navbar-dark bg-dark mb-4">
                <a class="navbar-brand" href="index.jsp">MeTube</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarCollapse">
                    <ul class="navbar-nav mr-auto">
                        <%
                            if (session.getAttribute("user") != null) {
                        %>
                        <li class="nav-item active">
                            <form class="form-inline mt-2 mt-md-0" action="FrontController">
                                <!--<input class="form-control mr-sm-2" type="text" placeholder="Buscar" aria-label="Search" hidden>-->
                                <input class="form-control mr-sm-2" type="text" name="command"  value="MyVideosCommand"  hidden>
                                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Mis Videos</button>
                            </form>
                        </li>
                        <li class="nav-item">
                            <form class="form-inline mt-2 mt-md-0" action="VideoUpload.jsp">
                                <!--<input class="form-control mr-sm-2" type="text" placeholder="Buscar" aria-label="Search" hidden>-->
                                <!--<input class="form-control mr-sm-2" type="text" name="command"  value="FavsCommand"  hidden>-->
                                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Subir Video</button>
                            </form>
                        </li>
                        <li class="nav-item">
                            <form class="form-inline mt-2 mt-md-0" action="History.jsp">
                                <!--<input class="form-control mr-sm-2" type="text" placeholder="Buscar" aria-label="Search" hidden>-->
                                <!--<input class="form-control mr-sm-2" type="text" name="command"  value="FavsCommand"  hidden>-->
                                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Historial de videos</button>
                            </form>
                        </li>
                        <% 
                            }
                        %>
                        <li class="nav-item">
                            <form class="form-inline mt-2 mt-md-0" action="FrontController">
                                <input class="form-control mr-sm-2" type="text" name="liked" value="mostLiked" placeholder="Buscar" aria-label="Search" hidden>
                                <input class="form-control mr-sm-2" type="text" name="command"  value="MyVideosCommand"  hidden>
                                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Con más likes</button>
                            </form>
                        </li>
                        <li class="nav-item">
                            <form class="form-inline mt-2 mt-md-0" action="FrontController">
                                <input class="form-control mr-sm-2" type="text" name="liked" value="mostViews" placeholder="Buscar" aria-label="Search" hidden>
                                <input class="form-control mr-sm-2" type="text" name="command"  value="MyVideosCommand"  hidden>
                                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Más populares</button>
                            </form>
                        </li>
                        <li class="nav-item">
                            <form class="form-inline mt-2 mt-md-0" action="FrontController">
                                <input class="form-control mr-sm-2" type="text" name="search" placeholder="Buscar" aria-label="Search">
                                <input class="form-control mr-sm-2" type="text" name="command"  value="MyVideosCommand"  hidden>
                                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Buscar</button>
                            </form>
                        </li>
                        <li class="nav-item">
                            <form class="form-inline mt-2 mt-md-0" action="StadisticsView.jsp">
                                <!--<input class="form-control mr-sm-2" type="text" name="search" placeholder="Buscar" aria-label="Search">
                                <input class="form-control mr-sm-2" type="text" name="command"  value="MyVideosCommand"  hidden>-->
                                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Estadisticas</button>
                            </form>
                        </li>
                    </ul>
                    <%
                        if (session.getAttribute("user") != null) {
                    %>
                    
                    <form class="form-inline mt-2 mt-md-0" action="FrontController">
                        <!--<input class="form-control mr-sm-2" type="text" placeholder="Buscar" aria-label="Search" hidden>-->
                        <input class="form-control mr-sm-2" type="text" name="command"  value="PlayListCommand"  hidden>
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">PlayLists</button>
                    </form>
                    <form class="form-inline mt-2 mt-md-0" action="SignInUp.jsp">
                        <input class="form-control mr-sm-2" type="text" aria-label="Search" name="sign" value="editUser" hidden>
                        <!--<input class="form-control mr-sm-2" type="text" name="command"  value="FavsCommand"  hidden>-->
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Editar Cuenta</button>
                    </form>
                    <form class="form-inline mt-2 mt-md-0" action="FrontController">
                        <!--<input class="form-control mr-sm-2" type="text" placeholder="Buscar" aria-label="Search" hidden>-->
                        <input class="form-control mr-sm-2" type="text" name="command"  value="LogoutCommand"  hidden>

                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Cerrar Sesion</button>
                    </form>
                    <%  } else {
                    %>
                    <form class="form-inline mt-2 mt-md-0" action="SignInUp.jsp" method="get">
                        <input class="form-control mr-sm-2" type="text" aria-label="Search" name="sign" value="signIn" hidden>
                        <!--<input class="form-control mr-sm-2" type="text" name="command"  value="FavsCommand"  hidden>-->

                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Iniciar Sesion</button>
                    </form>

                    <form class="form-inline mt-2 mt-md-0" action="SignInUp.jsp" method="get">
                        <input class="form-control mr-sm-2" type="text" aria-label="Search" name="sign" value="signUp" hidden>
                        <!--<input class="form-control mr-sm-2" type="text" name="command"  value="FavsCommand"  hidden>-->

                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Registrarse</button>
                    </form>
                    <%
                        }
                    %>

                </div>
            </nav>
        </header>
    </body>
</html>
