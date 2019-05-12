/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entities.PlaylistModel;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import singleton.LogRegistrer;

/**
 *
 * @author Usuario
 */
@Stateless
public class PlaylistModelFacade extends AbstractFacade<PlaylistModel> {

    @PersistenceContext(unitName = "MeTube-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(PlaylistModelFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return em;
    }

    public PlaylistModelFacade() {
        super(PlaylistModel.class);
    }
    
    public List<PlaylistModel> playListByUsername(String username) {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(PlaylistModelFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return em.createQuery("SELECT plm FROM PlaylistModel plm  WHERE plm.username.username LIKE :userName").setParameter("userName", username).getResultList();
    }
    
    public void createPlaylistModel(String name, String username) {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(PlaylistModelFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        em.createNativeQuery("INSERT INTO playlist_model (name, username) VALUES (?, ?)")
                .setParameter(1, name)
                .setParameter(2, username)
                .executeUpdate();
    }
    
    
}
