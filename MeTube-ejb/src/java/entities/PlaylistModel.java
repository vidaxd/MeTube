/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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
@Table(name = "playlist_model")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PlaylistModel.findAll", query = "SELECT p FROM PlaylistModel p")
    , @NamedQuery(name = "PlaylistModel.findById", query = "SELECT p FROM PlaylistModel p WHERE p.id = :id")
    , @NamedQuery(name = "PlaylistModel.findByName", query = "SELECT p FROM PlaylistModel p WHERE p.name = :name")})
public class PlaylistModel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000000000)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "playlistname")
    private Collection<PlaylistData> playlistDataCollection;
    @JoinColumn(name = "username", referencedColumnName = "username")
    @ManyToOne(optional = false)
    private Users username;

    public PlaylistModel() {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+"Builder"+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(PlaylistModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public PlaylistModel(Integer id) {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+"Builder"+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(PlaylistModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.id = id;
    }

    public PlaylistModel(Integer id, String name) {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+"Builder"+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(PlaylistModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(PlaylistModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public void setId(Integer id) {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(PlaylistModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.id = id;
    }

    public String getName() {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(PlaylistModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return name;
    }

    public void setName(String name) {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(PlaylistModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.name = name;
    }

    @XmlTransient
    public Collection<PlaylistData> getPlaylistDataCollection() {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(PlaylistModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return playlistDataCollection;
    }

    public void setPlaylistDataCollection(Collection<PlaylistData> playlistDataCollection) {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(PlaylistModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.playlistDataCollection = playlistDataCollection;
    }

    public Users getUsername() {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(PlaylistModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return username;
    }

    public void setUsername(Users username) {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(PlaylistModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.username = username;
    }

    @Override
    public int hashCode() {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(PlaylistModel.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(PlaylistModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlaylistModel)) {
            return false;
        }
        PlaylistModel other = (PlaylistModel) object;
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
            Logger.getLogger(PlaylistModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "entities.PlaylistModel[ id=" + id + " ]";
    }
    
}
