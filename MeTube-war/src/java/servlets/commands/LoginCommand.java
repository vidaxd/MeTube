package servlets.commands;

import control.UsersFacade;
import entities.Users;
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
import stateful.User;

public class LoginCommand extends FrontCommand{
    
    

    @Override
    public void process() {
        UsersFacade users = null;
        try {
            users = (UsersFacade) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/UsersFacade!control.UsersFacade");
        } catch (NamingException ex) {
            Logger.getLogger(LoginCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        HttpSession session = request.getSession(true);
        String email = (String) request.getParameter("email");
        String password = (String) request.getParameter("password");
        User u = null;
        try {
            u = (User) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/User!stateful.User");
        } catch (NamingException ex) {
            Logger.getLogger(LoginCommand.class.getName()).log(Level.SEVERE, null, ex);
        }

        u.setUser(users.findByEmail(email).get(0));
        
        try {
            LogRegistrer lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"Visitante");
        } catch (NamingException ex) {
            Logger.getLogger(LoginCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(u.getPassword().equals(password)) {
            session.setAttribute("user", u);
            
            try {
                AllStateful as = (AllStateful)InitialContext.doLookup("java:global/MeTube/MeTube-ejb/AllStateful!singleton.AllStateful");
                as.addUser(u);
                UserStatdistics us = (UserStatdistics)InitialContext.doLookup("java:global/MeTube/MeTube-ejb/UserStatdistics!singleton.UserStatdistics");
                us.addUserLogIn(u.getUsername());
                us.addUserOnline(u.getUsername());
                
            } catch (NamingException ex) {
                Logger.getLogger(LoginCommand.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            forward("/index.jsp");
        } catch (ServletException | IOException ex) {
            Logger.getLogger(LoginCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
