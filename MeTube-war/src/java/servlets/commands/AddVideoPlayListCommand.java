package servlets.commands;

import control.PlaylistDataFacade;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import singleton.LogRegistrer;
import stateful.User;

public class AddVideoPlayListCommand extends FrontCommand{

    @Override
    public void process() {
        
        PlaylistDataFacade pData = null;
        try {
            pData = (PlaylistDataFacade) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/PlaylistDataFacade!control.PlaylistDataFacade");
        } catch (NamingException ex) {
            Logger.getLogger(AddVideoPlayListCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        HttpSession session = request.getSession(true);
        
        String videoName = request.getParameter("videoName");
        String playlist = request.getParameter("playList");
        
        pData.createPlaylistData(playlist, videoName);
        
        try {
            User u = (User)session.getAttribute("user");
            LogRegistrer lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+u.getUsername());
        } catch (NamingException ex) {
            Logger.getLogger(AddVideoPlayListCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            forward("/VideoView.jsp");
        } catch (ServletException | IOException ex) {
            Logger.getLogger(AddVideoPlayListCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
