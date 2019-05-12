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
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import singleton.LogRegistrer;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "comments")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comments.findAll", query = "SELECT c FROM Comments c")
    , @NamedQuery(name = "Comments.findById", query = "SELECT c FROM Comments c WHERE c.id = :id")
    , @NamedQuery(name = "Comments.findByComment", query = "SELECT c FROM Comments c WHERE c.comment = :comment")})
public class Comments implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000000000)
    @Column(name = "comment")
    private String comment;
    @JoinColumn(name = "username", referencedColumnName = "username")
    @ManyToOne(optional = false)
    private Users username;
    @JoinColumn(name = "videoname", referencedColumnName = "name")
    @ManyToOne(optional = false)
    private Video videoname;

    public Comments() {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+"Builder"+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(Comments.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Comments(Integer id) {
        this.id = id;
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+"Builder"+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(Comments.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Comments(Integer id, String comment) {
        this.id = id;
        this.comment = comment;
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+"Builder"+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(Comments.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Integer getId() {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(Comments.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Comments.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getComment() {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(Comments.class.getName()).log(Level.SEVERE, null, ex);
        }
        return comment;
    }

    public void setComment(String comment) {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(Comments.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.comment = comment;
    }

    public Users getUsername() {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(Comments.class.getName()).log(Level.SEVERE, null, ex);
        }
        return username;
    }

    public void setUsername(Users username) {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(Comments.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.username = username;
    }

    public Video getVideoname() {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(Comments.class.getName()).log(Level.SEVERE, null, ex);
        }
        return videoname;
    }

    public void setVideoname(Video videoname) {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(Comments.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.videoname = videoname;
    }

    @Override
    public int hashCode() {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(Comments.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Comments.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comments)) {
            return false;
        }
        Comments other = (Comments) object;
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
            Logger.getLogger(Comments.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "entities.Comments[ id=" + id + " ]";
    }
    
}
