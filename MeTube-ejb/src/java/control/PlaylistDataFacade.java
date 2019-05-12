/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entities.PlaylistData;
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
public class PlaylistDataFacade extends AbstractFacade<PlaylistData> {

    @PersistenceContext(unitName = "MeTube-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) { 
            Logger.getLogger(PlaylistDataFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return em;
    }

    public PlaylistDataFacade() {
        
        super(PlaylistData.class);
    }
    
    public List<PlaylistData> playListByName(String name) {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) { 
            Logger.getLogger(PlaylistDataFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return em.createQuery("SELECT pld FROM PlaylistData pld  WHERE pld.playlistname.name LIKE :name").setParameter("name", name).getResultList();
    }
    
    public void createPlaylistData(String name, String videoName) {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) { 
            Logger.getLogger(PlaylistDataFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        em.createNativeQuery("INSERT INTO playlist_data (playlistname, videoname) VALUES (?, ?)")
                .setParameter(1, name)
                .setParameter(2, videoName)
                .executeUpdate();
    }
    
}
