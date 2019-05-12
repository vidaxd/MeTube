
<%@page import="stateful.User"%>
<%@page import="singleton.LogRegistrer"%>
<%@page import="singleton.UserStatdistics"%>
<%@page import="entities.Video"%>
<%@page import="java.util.List"%>
<%@page import="javax.naming.InitialContext"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
        List<Video> videos = (List<Video>)session.getAttribute("myVideos");
        int pag = 0;
        
        
        
        if (request.getParameter("pag") == null) {
            pag = 1;
        } else {
            pag = Integer.parseInt(request.getParameter("pag"));
        }
        
        List<Video> actualVideos = null;
        if (videos.size() <= pag * 6 && pag == 1) {
            actualVideos = videos.subList((pag - 1) * 6, videos.size());
        } else if (videos.size() <= pag * 6) {
            actualVideos = videos.subList((pag - 1) * 6, videos.size());
        } else if (videos.size() > pag * 6) {
            actualVideos = videos.subList((pag - 1) * 6, pag * 6);
        }
        int numPages = (int) Math.ceil((double) videos.size() / 6.0);
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mis Videos</title>
        <link rel="stylesheet" href="css/style.css" >
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        
    </head>
    <body>
        <%@include file="components/NavBar.jsp" %>
        <main class="fullScreen">
            <div class="py-4">
                <div class="container">
                    <%
                      if(videos.size()==0) {
                          
                    %>
                    
                    <h1>Aun no ha subido videos</h1>
                    
                    <%
                      }
                      int desp = 0;
                      int numVid = 3;
                      int rounds = (int)Math.ceil((double)actualVideos.size()/3.0);
                      for (int i = 0; i < rounds; i++) {
                          
                              
                    %>
                        <div class="row mb-3">
                    <%
                            if(i == rounds-1) numVid = actualVideos.size()-(i*3);
                            for(int j = 0; j < numVid; j++) {
                    %>
                            <div class="col-md-4 themed-grid-col img">
                                <object data="<%= actualVideos.get(desp).getUrl() %>"></object>
                            <form class="form-inline mt-2 mt-md-0" action="<%= frontController %>" method="get">
                                <input  type="text" name="videoName" value="<%= actualVideos.get(desp).getName() %>"  hidden>
                                <input  type="text" name="command" value="VideoCommand" hidden>
                                <button class="btn btn-link w-100 mx-auto" type="submit"><%= actualVideos.get(desp).getName() %></button>
                            </form>
                            </div>
                    <%
                                desp++;
                            }
                            numVid = 3;
                    %>
                        </div>
                    <%
                        }
                    %>
                </div>
            </div>
        </main>
        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center">
                <%
                if(videos.size() > 0) {
                    if (pag == 1) {
                %>
                <li class="page-item disabled"><a class="page-link" href="MyVideos.jsp?pag=<%= pag - 1%>">Previous</a></li>
                    <%
                    } else {
                    %>
                <li class="page-item"><a class="page-link" href="MyVideos.jsp?pag=<%= pag - 1%>">Previous</a></li>

                <%
                    }
                }
                    for (int i = 1; i <= numPages; i++) {
                        if (i == pag) {
                %>
                <li class="page-item active"><a class="page-link" href="MyVideos.jsp?pag=<%= i%>"><%= i%></a></li>
                    <%
                    } else {
                    %>
                <li class="page-item"><a class="page-link" href="MyVideos.jsp?pag=<%= i%>"><%= i%></a></li>
                    <%
                            }
                        }
                    if(videos.size() > 0) {
                        if (pag == numPages) {
                    %>

                <li class="page-item disabled"><a class="page-link" href="MyVideos.jsp?pag=<%= pag + 1%>">Next</a></li>
                    <%
                        } else {
                    %>
                <li class="page-item"><a class="page-link" href="MyVideos.jsp?pag=<%= pag + 1%>">Next</a></li>
                    <%
                        }
                    }
                    %>
            </ul>
        </nav>
        <%@include file="components/Footer.jsp" %>
        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>
