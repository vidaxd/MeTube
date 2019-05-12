/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entities.Users;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import singleton.LogRegistrer;

/**
 *
 * @author Usuario
 */
@Stateless
public class UsersFacade extends AbstractFacade<Users> {

    @PersistenceContext(unitName = "MeTube-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(UsersFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return em;
    }

    public UsersFacade() {
        super(Users.class);
    }
    
    public void insertUser(String username, String name, String email, String password) {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(UsersFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        Query q = em.createNativeQuery("INSERT INTO users (username, name, password, email) VALUES (?, ?, ?, ?)");
        q.setParameter(1, username);
        q.setParameter(2, name);
        q.setParameter(3, password);
        q.setParameter(4, email);
        q.executeUpdate();
    }
    
    public List<Users> findByUsername(String username) {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(UsersFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return em.createQuery("SELECT u FROM Users u  WHERE u.username LIKE :userName").setParameter("userName", username).getResultList();
    }
    
    public List<Users> findByEmail(String email) {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(UsersFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        //return em.createQuery("SELECT u FROM Users u  WHERE u.email LIKE :email").setParameter("email", email).getResultList();
        return em.createNamedQuery("Users.findByEmail2").setParameter("email", email).getResultList();
    }
    
    public void editUser(String username, String name, String email, String password, int id) {
        try {
            LogRegistrer lg = null;
            lg = (LogRegistrer) InitialContext.doLookup("java:global/MeTube/MeTube-ejb/LogRegistrer!singleton.LogRegistrer");
            lg.log(this.getClass().getSimpleName()+"::"+Thread.currentThread().getStackTrace()[1].getMethodName()+"::"+"NoUserData");
        } catch (NamingException ex) {
            Logger.getLogger(UsersFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        em.createQuery("UPDATE Users u SET u.username = :newUsername, u.name = :newName, u.email = :newEmail, u.password = :newPassword WHERE u.id = :id" )
                .setParameter("newUsername", username)
                .setParameter("newName", name)
                .setParameter("newEmail", email)
                .setParameter("newPassword", password)
                .setParameter("id", id)
                .executeUpdate();
    }
    
}
