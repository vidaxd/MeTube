package stateful;

import entities.Video;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateful;
import javax.ejb.LocalBean;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Remove;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import singleton.LogRegistrer;


@Stateful
@LocalBean
public class SessionVideoHistory {

    private List<Video> videoViewed;
    private User user;
    
    private LogRegistrer lg;
    
    @PostConstruct
    public void init() {
        try {
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(SessionVideoHistory.class.getName()).log(Level.SEVERE, null, ex);
        }
        videoViewed = new ArrayList<>();
        
    }
    
    @PreDestroy
    public void deconstruct() {
        lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
    }
    
    @Remove
    public void delete() {
        lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
    }
    
    @PrePassivate
    public void toCache() {
        lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
    }
    
    @PostActivate
    public void fromCache() {
        lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
    }
    
    public void addVideoViewed(Video video){
        lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        videoViewed.add(video);
    }

    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        this.user = user;
    }
    

    public List<Video> getVideoViewed() {
        lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        return videoViewed;
    }
    
    
    
    
}
