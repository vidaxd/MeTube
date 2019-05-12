package servlets.commands;

import control.CommentsFacade;
import control.PlaylistModelFacade;
import control.VideoFacade;
import entities.Comments;
import entities.PlaylistModel;
import entities.Video;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import singleton.AllStateful;
import singleton.LogRegistrer;
import stateful.SessionVideoHistory;
import stateful.User;


public class VideoCommand extends FrontCommand{

    @Override
    public void process() {
        
        VideoFacade video = null;
        try {
            video = (VideoFacade) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/VideoFacade!control.VideoFacade");
        } catch (NamingException ex) {
            Logger.getLogger(VideoCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        CommentsFacade comment = null;
        try {
            comment = (CommentsFacade)InitialContext.doLookup("java:global/MeTube/MeTube-ejb/CommentsFacade!control.CommentsFacade");
        } catch (NamingException ex) {
            Logger.getLogger(VideoCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        PlaylistModelFacade plModel = null;
        try {
            plModel = (PlaylistModelFacade) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/PlaylistModelFacade!control.PlaylistModelFacade");
        } catch (NamingException ex) {
            Logger.getLogger(VideoCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        HttpSession session = request.getSession(true);
        SessionVideoHistory svh = (SessionVideoHistory) session.getAttribute("history");
        
        
        //Se extrae el nombre del video
        String videoName = request.getParameter("videoName");
        System.out.println("El nombre del video es: "+ videoName);
        video.updateView(videoName); //Se actualizan las visualizaciones
        //Actualizamos la entidad video.
        Video v = (Video)video.findByVideoName(videoName).get(0);
        session.setAttribute("video", v);
        //Estraemos los comentaios de un video
        List<Comments> comments = comment.commentsInVideo(videoName);
        session.setAttribute("comments", comments);
        if(session.getAttribute("user") != null) {
            try {
                AllStateful as = (AllStateful)InitialContext.doLookup("java:global/MeTube/MeTube-ejb/AllStateful!singleton.AllStateful");
                if(svh == null) svh = (SessionVideoHistory) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/SessionVideoHistory!stateful.SessionVideoHistory");
                as.addHistory(svh);
            } catch (NamingException ex) {
                Logger.getLogger(VideoCommand.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            User u = (User) session.getAttribute("user");
            List<PlaylistModel> playlists = plModel.playListByUsername(u.getUsername());
            session.setAttribute("userPlayList", playlists);
            svh.setUser(u);
            session.setAttribute("history", svh);
            svh.addVideoViewed(v);
        }
        
        
        try {
            User u = (User) session.getAttribute("user");
            String username = "Visitante";
            if(u != null) username = u.getUsername();
            LogRegistrer lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+username);
        } catch (NamingException ex) {
            Logger.getLogger(VideoCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            forward("/VideoView.jsp");
        } catch (ServletException | IOException ex) {
            Logger.getLogger(VideoCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
