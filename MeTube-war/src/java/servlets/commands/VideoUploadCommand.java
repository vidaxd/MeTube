package servlets.commands;

import control.VideoFacade;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import singleton.LogRegistrer;
import stateful.User;

public class VideoUploadCommand extends FrontCommand{

    @Override
    public void process() {
        VideoFacade video = null;
        try {
            video = (VideoFacade) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/VideoFacade!control.VideoFacade");
        } catch (NamingException ex) {
            Logger.getLogger(VideoUploadCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        HttpSession session = request.getSession(true);
        String videoName = request.getParameter("videoName");
        String desciption = request.getParameter("description");
        String url = request.getParameter("url");
        User u = (User)session.getAttribute("user");
        String username = u.getUsername();
        
        video.insertVideo(videoName, username, desciption, url);
        
        try {
            LogRegistrer lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+username);
        } catch (NamingException ex) {
            Logger.getLogger(VideoUploadCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            forward("/MyVideos.jsp");
        } catch (ServletException | IOException ex) {
            Logger.getLogger(VideoUploadCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
    
}
