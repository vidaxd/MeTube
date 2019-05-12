package servlets.commands;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import singleton.AllStateful;
import singleton.LogRegistrer;
import singleton.UserStatdistics;
import stateful.SessionVideoHistory;
import stateful.User;

public class LogoutCommand extends FrontCommand{

    @Override
    public void process() {
        HttpSession session = request.getSession(true);
        User u = (User) session.getAttribute("user");
        session.setAttribute("user", null);
        try {
                UserStatdistics us = (UserStatdistics)InitialContext.doLookup("java:global/MeTube/MeTube-ejb/UserStatdistics!singleton.UserStatdistics");
                us.removeUserOnline(u.getUsername());
            } catch (NamingException ex) {
            Logger.getLogger(LogoutCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            LogRegistrer lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+u.getUsername());
        } catch (NamingException ex) {
            Logger.getLogger(LogoutCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        AllStateful as = null;
        try {
            as = (AllStateful)InitialContext.doLookup("java:global/MeTube/MeTube-ejb/AllStateful!singleton.AllStateful");
        } catch (NamingException ex) {
            Logger.getLogger(LogoutCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        as.removeUser(u);
        u.delete();
        SessionVideoHistory svh = (SessionVideoHistory) session.getAttribute("history");
        if(svh != null) {
            as.removeHistory(svh);
            svh.delete();
            session.removeAttribute("history");
        }
        try {
            forward("/index.jsp");
        } catch (ServletException | IOException ex) {
            Logger.getLogger(LogoutCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
