/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import classes.VideoRating;
import java.io.Serializable;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import singleton.LogRegistrer;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "video")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Video.findAll", query = "SELECT v FROM Video v")
    , @NamedQuery(name = "Video.findById", query = "SELECT v FROM Video v WHERE v.id = :id")
    , @NamedQuery(name = "Video.findByName", query = "SELECT v FROM Video v WHERE v.name = :name")
    , @NamedQuery(name = "Video.findByUrl", query = "SELECT v FROM Video v WHERE v.url = :url")
    //, @NamedQuery(name = "Video.findByLikes", query = "SELECT v FROM Video v WHERE v.likes = :likes")
    //, @NamedQuery(name = "Video.findByDislikes", query = "SELECT v FROM Video v WHERE v.dislikes = :dislikes")
    , @NamedQuery(name = "Video.findByDescription", query = "SELECT v FROM Video v WHERE v.description = :description")
    , @NamedQuery(name = "Video.findByViews", query = "SELECT v FROM Video v WHERE v.views = :views")})
public class Video implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000000000)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000000000)
    @Column(name = "url")
    private String url;
    //@Column(name = "likes")
    //private Integer likes;
    //@Column(name = "dislikes")
    //private Integer dislikes;
    @Size(max = 2000000000)
    @Column(name = "description")
    private String description;
    @Column(name = "views")
    private Integer views;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "videoname")
    private Collection<PlaylistData> playlistDataCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "videoname")
    private Collection<Comments> commentsCollection;
    @JoinColumn(name = "username", referencedColumnName = "username")
    @ManyToOne(optional = false)
    private Users username;
    @Embedded
    private VideoRating vr;

    public Video() {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+"Builder"+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(Video.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Video(Integer id) {
        this.id = id;
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+"Builder"+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(Video.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Video(Integer id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.vr = new VideoRating();
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+"Builder"+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(Video.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Integer getId() {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(Video.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
        
    }

    public void setId(Integer id) {
        this.id = id;
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(Video.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getName() {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(Video.class.getName()).log(Level.SEVERE, null, ex);
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(Video.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getUrl() {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(Video.class.getName()).log(Level.SEVERE, null, ex);
        }
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(Video.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Integer getLikes() {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(Video.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vr.getLikes();
    }

    public void setLikes(Integer likes) {
        vr.setLikes(likes);
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(Video.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Integer getDislikes() {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(Video.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vr.getDislikes();
    }

    public void setDislikes(Integer dislikes) {
        vr.setDislikes(dislikes);
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(Video.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public VideoRating getVideoRating() {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(Video.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vr;
    }
    
    public void setVideoRating(VideoRating vr) {
        this.vr = vr;
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(Video.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getDescription() {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(Video.class.getName()).log(Level.SEVERE, null, ex);
        }
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(Video.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Integer getViews() {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(Video.class.getName()).log(Level.SEVERE, null, ex);
        }
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(Video.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @XmlTransient
    public Collection<PlaylistData> getPlaylistDataCollection() {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(Video.class.getName()).log(Level.SEVERE, null, ex);
        }
        return playlistDataCollection;
    }

    public void setPlaylistDataCollection(Collection<PlaylistData> playlistDataCollection) {
        this.playlistDataCollection = playlistDataCollection;
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(Video.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @XmlTransient
    public Collection<Comments> getCommentsCollection() {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(Video.class.getName()).log(Level.SEVERE, null, ex);
        }
        return commentsCollection;
    }

    public void setCommentsCollection(Collection<Comments> commentsCollection) {
        this.commentsCollection = commentsCollection;
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(Video.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Users getUsername() {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(Video.class.getName()).log(Level.SEVERE, null, ex);
        }
        return username;
    }

    public void setUsername(Users username) {
        this.username = username;
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(Video.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int hashCode() {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(Video.class.getName()).log(Level.SEVERE, null, ex);
        }
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(Video.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Video)) {
            return false;
        }
        Video other = (Video) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(Video.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "entities.Video[ id=" + id + " ]";
    }
    
}
