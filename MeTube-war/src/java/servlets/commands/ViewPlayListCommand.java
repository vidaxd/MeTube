package servlets.commands;

import control.PlaylistDataFacade;
import control.VideoFacade;
import entities.PlaylistData;
import entities.Video;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import singleton.LogRegistrer;
import stateful.User;

public class ViewPlayListCommand extends FrontCommand{

    @Override
    public void process() {
        PlaylistDataFacade pData = null;
        try {
            pData = (PlaylistDataFacade) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/PlaylistDataFacade!control.PlaylistDataFacade");
        } catch (NamingException ex) {
            Logger.getLogger(ViewPlayListCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*VideoFacade video = null;
        try {
            video = (VideoFacade) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/VideoFacade!control.VideoFacade");
        } catch (NamingException ex) {
            Logger.getLogger(VideoCommand.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        HttpSession session = request.getSession(true);
        String name = request.getParameter("playList");
        List<PlaylistData> playlists = pData.playListByName(name);
        List<Video> videos = new ArrayList<Video>();
        for (PlaylistData playlist : playlists) {
            videos.add(playlist.getVideoname());
        }
        session.setAttribute("myVideos", videos);
        
        try {
            User u = (User) session.getAttribute("user");
            LogRegistrer lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+u.getUsername());
        } catch (NamingException ex) {
            Logger.getLogger(ViewPlayListCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            forward("/MyVideos.jsp");
        } catch (ServletException | IOException ex) {
            Logger.getLogger(ViewPlayListCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
