package servlets.commands;

import control.CommentsFacade;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import singleton.LogRegistrer;
import stateful.User;

public class AddComment extends FrontCommand{

    @Override
    public void process() {
        CommentsFacade comment = null;
        try {
            comment = (CommentsFacade)InitialContext.doLookup("java:global/MeTube/MeTube-ejb/CommentsFacade!control.CommentsFacade");
        } catch (NamingException ex) {
            Logger.getLogger(AddComment.class.getName()).log(Level.SEVERE, null, ex);
        }
        HttpSession session = request.getSession(true);
        User u = (User)session.getAttribute("user");
        String videoName = request.getParameter("video");
        String c = request.getParameter("comment");
        comment.addComment(u.getUsername(), videoName, c);
        
        try {
            LogRegistrer lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+u.getUsername());
        } catch (NamingException ex) {
            Logger.getLogger(AddComment.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            forward("/VideoView.jsp");
        } catch (ServletException | IOException ex) {
            Logger.getLogger(AddComment.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
