/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import singleton.LogRegistrer;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "playlist_data")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PlaylistData.findAll", query = "SELECT p FROM PlaylistData p")
    , @NamedQuery(name = "PlaylistData.findById", query = "SELECT p FROM PlaylistData p WHERE p.id = :id")})
public class PlaylistData implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "videoname", referencedColumnName = "name")
    @ManyToOne(optional = false)
    private Video videoname;
    @JoinColumn(name = "playlistname", referencedColumnName = "name")
    @ManyToOne(optional = false)
    private PlaylistModel playlistname;

    public PlaylistData() {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+"Builder"+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(PlaylistData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public PlaylistData(Integer id) {
        this.id = id;
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+"Builder"+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(PlaylistData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Integer getId() {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(PlaylistData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public void setId(Integer id) {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(PlaylistData.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.id = id;
    }

    public Video getVideoname() {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(PlaylistData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return videoname;
    }

    public void setVideoname(Video videoname) {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(PlaylistData.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.videoname = videoname;
    }

    public PlaylistModel getPlaylistname() {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(PlaylistData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return playlistname;
    }

    public void setPlaylistname(PlaylistModel playlistname) {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(PlaylistData.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.playlistname = playlistname;
    }

    @Override
    public int hashCode() {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(PlaylistData.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(PlaylistData.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlaylistData)) {
            return false;
        }
        PlaylistData other = (PlaylistData) object;
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
            Logger.getLogger(PlaylistData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "entities.PlaylistData[ id=" + id + " ]";
    }
    
}
