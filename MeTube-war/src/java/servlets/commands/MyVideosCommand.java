package servlets.commands;

import control.VideoFacade;
import entities.Video;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import singleton.LogRegistrer;
import stateful.User;

public class MyVideosCommand extends FrontCommand {

    @Override
    public void process() {
        HttpSession session = request.getSession(true);
        User u = (User) session.getAttribute("user");
        VideoFacade video = null;
        try {
            video = (VideoFacade) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/VideoFacade!control.VideoFacade");
        } catch (NamingException ex) {
            Logger.getLogger(MyVideosCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            String username = "Visitante";
            if(u != null) username = u.getUsername();
            LogRegistrer lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+username);
        } catch (NamingException ex) {
            Logger.getLogger(MyVideosCommand.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (request.getParameter("search") != null) {
            String videoName = request.getParameter("search");
            List<Video> videos = video.searchVideos(videoName);
            session.setAttribute("myVideos", videos);
            try {
                forward("/MyVideos.jsp");
            } catch (ServletException | IOException ex) {
                Logger.getLogger(MyVideosCommand.class.getName()).log(Level.SEVERE, null, ex);
            }
        } if(request.getParameter("liked") != null) { //Se muestran los videos por mas likes o mas visitas
            String param = request.getParameter("liked");
            if(param.equals("mostLiked")) {
                List<Video> videos = video.orderByLikes();
                session.setAttribute("myVideos", videos);
            }
            if(param.equals("mostViews")) {
                 List<Video> videos = video.orderByViews();
                session.setAttribute("myVideos", videos);
            }
            try {
                forward("/MyVideos.jsp");
            } catch (ServletException | IOException ex) {
                Logger.getLogger(MyVideosCommand.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else {

            List<Video> videos = video.findByUsername(u.getUsername());
            session.setAttribute("myVideos", videos);

            try {
                forward("/MyVideos.jsp");
            } catch (ServletException | IOException ex) {
                Logger.getLogger(MyVideosCommand.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

}
