package servlets;




//import control.VideoFacade;
import servlets.commands.FrontCommand;
import servlets.commands.UnknownCommand;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
//import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import singleton.LogRegistrer;
import stateful.User;


@WebServlet(name = "FrontController", urlPatterns = {"/FrontController"})
public class FrontController extends HttpServlet {
    //@EJB
    //VideoFacade v;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            User u = (User) request.getSession().getAttribute("user");
            String username = "Visitante";
            if(u != null) username = u.getUsername();
            LogRegistrer lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+username);
        } catch (NamingException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        FrontCommand command = getCommand(request);
        command.init(getServletContext(), request, response);
        command.process();
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        try {
            User u = (User) request.getSession().getAttribute("user");
            String username = "Visitante";
            if(u != null) username = u.getUsername();
            LogRegistrer lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+username);
        } catch (NamingException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        
        FrontCommand command = getCommand(request);
        command.init(getServletContext(), request, response);
        command.process();

    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private FrontCommand getCommand(HttpServletRequest request) {
        
        try {
            User u = (User) request.getSession().getAttribute("user");
            String username = "Visitante";
            if(u != null) username = u.getUsername();
            LogRegistrer lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+username);
        } catch (NamingException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        }  
            
        try {
            FrontCommand f = (FrontCommand) getCommandClass(request).newInstance();
            return f;
        }catch (Exception e) {
            
        }
        return null;
    }

    private Class getCommandClass(HttpServletRequest request) {
        try {
            User u = (User) request.getSession().getAttribute("user");
            String username = "Visitante";
            if(u != null) username = u.getUsername();
            LogRegistrer lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+username);
        } catch (NamingException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        Class result;
        final String command = "servlets.commands."+(String)request.getParameter("command");
        System.out.println(command);
        //System.out.println(v.findAll().get(0).getName());
        try {
            result = Class.forName(command);
        } catch(ClassNotFoundException e) {
            HttpSession session = request.getSession(true);
            session.setAttribute("error", "Ha ocurrido un error en el servidor.\n" +
"                                Pruebe de nuevo más tarde.");
            result = UnknownCommand.class;
        }
        return result;

    }

}
