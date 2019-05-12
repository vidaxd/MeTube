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
import singleton.LogRegistrer;
import singleton.UserStatdistics;
import stateful.User;

public class RegisterCommand extends FrontCommand {

    @Override
    public void process() {
        UsersFacade users = null;
        try {
            users = (UsersFacade) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/UsersFacade!control.UsersFacade");
        } catch (NamingException ex) {
            Logger.getLogger(RegisterCommand.class.getName()).log(Level.SEVERE, null, ex);
        }

        HttpSession session = request.getSession(true);
        String username = (String) request.getParameter("username");
        String name = (String) request.getParameter("name");
        String email = (String) request.getParameter("email");
        String password = (String) request.getParameter("password");
        String confirmPassword = (String) request.getParameter("confirmPassword");
        
        try {
            LogRegistrer lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"Visitante");
        } catch (NamingException ex) {
            Logger.getLogger(RegisterCommand.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (password.equals(confirmPassword)) {
            users.insertUser(username, name, email, password);
            User u = null;
            try {
                u = (User) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/User!stateful.User");
            } catch (NamingException ex) {
                Logger.getLogger(RegisterCommand.class.getName()).log(Level.SEVERE, null, ex);
            }
            u.setUser(users.findByUsername(username).get(0));
            session.setAttribute("user", u);
            try {
                forward("/index.jsp");
            } catch (ServletException | IOException ex) {
                Logger.getLogger(RegisterCommand.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                UserStatdistics us = (UserStatdistics)InitialContext.doLookup("java:global/MeTube/MeTube-ejb/UserStatdistics!singleton.UserStatdistics");
                us.addUserLogIn(u.getUsername());
                us.addUserOnline(u.getUsername());
            } catch (NamingException ex) {
                Logger.getLogger(RegisterCommand.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
        } else {
            session.setAttribute("error", "La contraseñas no coinciden.");
            session.setAttribute("sign", "signUp");
            try {
                forward("/SignInUp.jsp");
            } catch (ServletException | IOException ex) {
                Logger.getLogger(RegisterCommand.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
