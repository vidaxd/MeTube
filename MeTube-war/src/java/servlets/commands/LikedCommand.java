package servlets.commands;


import control.VideoFacade;
import entities.Video;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import singleton.LogRegistrer;
import stateful.User;

public class LikedCommand extends FrontCommand{

    @Override
    public void process() {
        
        VideoFacade video = null;
        try {
            video = (VideoFacade) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/VideoFacade!control.VideoFacade");
        } catch (NamingException ex) {
            Logger.getLogger(LikedCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        HttpSession session = request.getSession(true);
        
        String liked = (String)request.getParameter("liked");
        Video v = (Video)session.getAttribute("video");
        
        try {
            User u = (User) session.getAttribute("user");
            String username = "Visitante";
            if(u != null) username = u.getUsername();
            LogRegistrer lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+username);
        } catch (NamingException ex) {
            Logger.getLogger(LikedCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(session.getAttribute("user") == null) {
            session.setAttribute("error", "Debe iniciar sesion para realizar esa acción.");
            try {
                forward("/ErrorView.jsp");
            } catch (ServletException | IOException ex) {
                Logger.getLogger(LikedCommand.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (liked.equals("like") && session.getAttribute("user") != null) {
            System.out.println("Me gusto el video: " + v.getName());
            video.updateLikes(v.getName());
        }
        if (liked.equals("dislike") && session.getAttribute("user") != null) {
            System.out.println("No me gusto el viedo: " + v.getName());
            video.updateDislikes(v.getName());
        }
        
        
        try {
            forward("/VideoView.jsp");
        } catch (ServletException | IOException ex) {
            Logger.getLogger(LikedCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }

    
}
