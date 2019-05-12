package servlets.commands;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import singleton.LogRegistrer;
import stateful.User;


public abstract class FrontCommand  {

    protected ServletContext context;
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    
    public void init(ServletContext context, HttpServletRequest request, HttpServletResponse response) {
        
            this.context = context;
            this.request = request;
            this.response = response;
        try {    
            User u = (User) request.getSession().getAttribute("user");
            String username = "Visitante";
            if(u != null) username = u.getUsername();
            LogRegistrer lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+username);
        } catch (NamingException ex) {
            Logger.getLogger(FrontCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        
}
    
    abstract public void process();
    
    public void forward(String target) throws ServletException, IOException{
        
        try {    
            User u = (User) request.getSession().getAttribute("user");
            String username = "Visitante";
            if(u != null) username = u.getUsername();
            LogRegistrer lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+username);
        } catch (NamingException ex) {
            Logger.getLogger(FrontCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        RequestDispatcher dp = context.getRequestDispatcher(target);
        dp.forward(request, response);
    }


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
    }
}
