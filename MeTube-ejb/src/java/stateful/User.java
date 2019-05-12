package stateful;

import entities.Comments;
import entities.PlaylistModel;
import entities.Users;
import entities.Video;
import java.util.Collection;
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
import javax.xml.bind.annotation.XmlTransient;
import singleton.LogRegistrer;

@Stateful
@LocalBean
public class User {
    
    private Users user;
    private LogRegistrer lg;
    
    @PostConstruct
    public void init() {
        try {
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"Visitante");
        } catch (NamingException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @PreDestroy
    public void deconstruct() {
        lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+user.getUsername());
    }
    
    @Remove
    public void delete() {
        lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+user.getUsername());
    }
    
    @PrePassivate
    public void toCache() {
        lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+user.getUsername());
    }
    
    @PostActivate
    public void fromCache() {
        lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+user.getUsername());
    }
    
    public void setUser(Users user) {
        this.user = user;
        lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+user.getUsername());
    }
    
    public Users getUser() {
        lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+user.getUsername());
        return user;
    }
    
    public Integer getId() {
        lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+user.getUsername());
        return user.getId();
    }

    public void setId(Integer id) {
        lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+user.getUsername());
        user.setId(id);
    }

    public String getUsername() {
        lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+user.getUsername());
        return user.getUsername();
    }

    public void setUsername(String username) {
        lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+user.getUsername());
        user.setUsername(username);
    }

    public String getName() {
        lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+user.getUsername());
        return user.getName();
    }

    public void setName(String name) {
        lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+user.getUsername());
        user.setName(name);
    }

    public String getPassword() {
        lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+user.getUsername());
        return user.getPassword();
    }

    public void setPassword(String password) {
        lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+user.getUsername());
        user.setPassword(password);
    }

    public String getEmail() {
        lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+user.getUsername());
        return user.getEmail();
    }

    public void setEmail(String email) {
        lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+user.getUsername());
        user.setEmail(email);
    }

    @XmlTransient
    public Collection<PlaylistModel> getPlaylistModelCollection() {
        lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+user.getUsername());
        return user.getPlaylistModelCollection();
    }

    public void setPlaylistModelCollection(Collection<PlaylistModel> playlistModelCollection) {
        lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+user.getUsername());
        user.setPlaylistModelCollection(playlistModelCollection);
    }

    @XmlTransient
    public Collection<Comments> getCommentsCollection() {
        lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+user.getUsername());
        return user.getCommentsCollection();
    }

    public void setCommentsCollection(Collection<Comments> commentsCollection) {
        lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+user.getUsername());
        user.setCommentsCollection(commentsCollection);
    }

    @XmlTransient
    public Collection<Video> getVideoCollection() {
        lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+user.getUsername());
        return user.getVideoCollection();
    }

    public void setVideoCollection(Collection<Video> videoCollection) {
        lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+user.getUsername());
        user.setVideoCollection(videoCollection);
    }
}
