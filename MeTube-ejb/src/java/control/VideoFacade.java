/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entities.Video;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import singleton.LogRegistrer;

/**
 *
 * @author Usuario
 */
@Stateless
public class VideoFacade extends AbstractFacade<Video> {

    @PersistenceContext(unitName = "MeTube-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(VideoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return em;
    }

    public VideoFacade() {
        super(Video.class);
    }
    
    public void insertVideo(String videoName, String username, String description, String url) {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(VideoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        Query q = em.createNativeQuery("INSERT INTO video (name, url, username, description) VALUES (?, ?, ?, ?)");
        q.setParameter(1, videoName);
        q.setParameter(2, url);
        q.setParameter(3, username);
        q.setParameter(4, description);
        q.executeUpdate();
    }
    
    public List<Video> findByUsername(String username) {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(VideoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return em.createQuery("SELECT v FROM Video v  WHERE v.username.username LIKE :userName").setParameter("userName", username).getResultList();
    }
    
    public List<Video> findByVideoName(String videoName) {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(VideoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return em.createQuery("SELECT v FROM Video v  WHERE v.name LIKE :videoName").setParameter("videoName", videoName).getResultList();
    }
    
    public void updateView(String videoName) {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(VideoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        em.createQuery("UPDATE Video v SET v.views = v.views + 1 WHERE v.name LIKE :videoName " ).setParameter("videoName", videoName).executeUpdate();
    }
    
    public void updateLikes(String videoName) {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(VideoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        em.createQuery("UPDATE Video v SET v.vr.likes = v.vr.likes + 1 WHERE v.name LIKE :videoName " ).setParameter("videoName", videoName).executeUpdate();
    }
    
    public void updateDislikes(String videoName) {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(VideoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        em.createQuery("UPDATE Video v SET v.vr.dislikes = v.vr.dislikes + 1 WHERE v.name LIKE :videoName " ).setParameter("videoName", videoName).executeUpdate();
    }
    
    public List<Video> searchVideos(String videoName) {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(VideoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return em.createQuery("SELECT v FROM Video v  WHERE v.name LIKE :videoName" ).setParameter("videoName", "%"+videoName+"%").getResultList();
    }
    
    public void deleteVideo(String videoName) {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(VideoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        em.createQuery("DELETE FROM Video v  WHERE v.name LIKE :videoName" ).setParameter("videoName", videoName).executeUpdate();
    }
    
    public List<Video> orderByLikes() {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(VideoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return em.createQuery("SELECT v FROM Video v  ORDER BY v.vr.likes DESC").getResultList();
    }
    
    public List<Video> orderByViews() {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(VideoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        //return em.createQuery("SELECT v FROM Video v  ORDER BY v.views").getResultList();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Video> cq = cb.createQuery(Video.class);
        Root<Video> video = cq.from(Video.class);
        
        cq.select(video);
        cq.orderBy(cb.desc(video.get("views")));
        TypedQuery<Video> q = em.createQuery(cq);
        List<Video> commentsOfVideo = q.getResultList();
        return commentsOfVideo;
    }
    
}
