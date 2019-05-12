package servlets.commands;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import singleton.LogRegistrer;
import stateful.User;

public class UnknownCommand extends FrontCommand {

    @Override
    public void process() {
        HttpSession session = request.getSession(true);
        System.out.println("Error: Comando desconocido.");
        System.out.println(request.getParameter("videoName") + " " + request.getParameter("command"));
        request.setAttribute("command","UnKnownCommand");
        
        try {
            User u = (User) session.getAttribute("user");
            String username = "Visitante";
            if(u != null) username = u.getUsername();
            LogRegistrer lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+username);
        } catch (NamingException ex) {
            Logger.getLogger(UnknownCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            forward("/ErrorView.jsp");
        } catch (ServletException | IOException ex) {
            ex.getStackTrace();
        }
    }

}
