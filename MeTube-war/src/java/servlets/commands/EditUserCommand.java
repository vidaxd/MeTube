package servlets.commands;

import control.UsersFacade;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import singleton.LogRegistrer;
import stateful.User;

public class EditUserCommand extends FrontCommand{

    @Override
    public void process() {
        UsersFacade users = null;
        try {
            users = (UsersFacade) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/UsersFacade!control.UsersFacade");
        } catch (NamingException ex) {
            Logger.getLogger(EditUserCommand.class.getName()).log(Level.SEVERE, null, ex);
        }

        HttpSession session = request.getSession(true);
        String username = (String) request.getParameter("username");
        String name = (String) request.getParameter("name");
        String email = (String) request.getParameter("email");
        String password = (String) request.getParameter("password");
        String confirmPassword = (String) request.getParameter("confirmPassword");
        User u = (User) session.getAttribute("user");
        
        try {
            LogRegistrer lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+u.getUsername());
        } catch (NamingException ex) {
            Logger.getLogger(EditUserCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        if (password.equals(confirmPassword)) {
            users.editUser(username, name, email, password, u.getId());
            u.setUser(users.find(u.getId()));
            session.setAttribute("user", u);
            
            try {
                forward("/index.jsp");
            } catch (ServletException | IOException ex) {
                Logger.getLogger(EditUserCommand.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            session.setAttribute("error", "La contraseñas no coinciden.");
            session.setAttribute("sign", "signUp");
            try {
                forward("/SignInUp.jsp");
            } catch (ServletException | IOException ex) {
                Logger.getLogger(EditUserCommand.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
