package servlets.commands;

import control.PlaylistModelFacade;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import singleton.LogRegistrer;
import stateful.User;

public class CreatePlayListCommand extends FrontCommand{

    @Override
    public void process() {
        PlaylistModelFacade plModel = null;
        try {
            plModel = (PlaylistModelFacade) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/PlaylistModelFacade!control.PlaylistModelFacade");
        } catch (NamingException ex) {
            Logger.getLogger(CreatePlayListCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        HttpSession session = request.getSession(true);
        User u = (User) session.getAttribute("user");
        String name = request.getParameter("playList");
        plModel.createPlaylistModel(name, u.getUsername());
        
        try {
            LogRegistrer lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+u.getUsername());
        } catch (NamingException ex) {
            Logger.getLogger(CreatePlayListCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            forward("/PlayList.jsp");
        } catch (ServletException | IOException ex) {
            Logger.getLogger(CreatePlayListCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
