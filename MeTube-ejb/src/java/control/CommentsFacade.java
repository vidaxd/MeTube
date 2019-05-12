/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entities.Comments;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
public class CommentsFacade extends AbstractFacade<Comments> {

    @PersistenceContext(unitName = "MeTube-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(CommentsFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return em;
    }

    public CommentsFacade() {
        
        super(Comments.class);
    }

    public List<Comments> commentsInVideo(String videoName) {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(CommentsFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        //return em.createQuery("SELECT c FROM Comments c  WHERE c.videoname.name LIKE :videoName").setParameter("videoName", videoName).getResultList();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Comments> cq = cb.createQuery(Comments.class);
        Root<Comments> comment = cq.from(Comments.class);
        
        cq.select(comment);
        cq.where(cb.like(comment.get("videoname").get("name"), videoName));
        TypedQuery<Comments> q = em.createQuery(cq);
        List<Comments> commentsOfVideo = q.getResultList();
        return commentsOfVideo;
    }

    public void addComment(String username, String videoName, String comment) {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(CommentsFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        em.createNativeQuery("INSERT INTO comments (username, videoname, comment) VALUES (?, ?, ?)")
                .setParameter(1, username)
                .setParameter(2, videoName)
                .setParameter(3, comment)
                .executeUpdate();

    }

}
