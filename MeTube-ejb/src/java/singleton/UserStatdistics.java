package singleton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;

@Singleton
@LocalBean
public class UserStatdistics {
    
    
    private List<String> usersLogIn;
    private List<String> usersOnline;
    private Map<String, Integer> pagesVisited;
    
    @PostConstruct
    public void init() {
        
        usersLogIn = new ArrayList<>();
        usersOnline = new ArrayList<>();
        pagesVisited = new HashMap<>();
    }
    
    public void addUserLogIn(String username) {
        usersLogIn.add(username);
        
    }
    
    public void addUserOnline(String username) {
        usersOnline.add(username);
    }
    
    public void removeUserOnline(String username) {
        usersOnline.remove(username);
    }
    
    public void addPageVisited(String page) {
        if(pagesVisited.containsKey(page)) {
            pagesVisited.put(page, pagesVisited.get(page)+1);
        } else {
            pagesVisited.put(page, 1);
        }
    }

    public List<String> getUsersLogIn() {
        return usersLogIn;
    }

    public List<String> getUsersOnline() {
        return usersOnline;
    }

    public Map<String, Integer> getPagesVisited() {
        return pagesVisited;
    }
    
    
    
}
