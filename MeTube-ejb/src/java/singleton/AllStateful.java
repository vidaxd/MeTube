package singleton;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import stateful.SessionVideoHistory;
import stateful.User;

@Singleton
@LocalBean
public class AllStateful {
    private List<SessionVideoHistory> videoHistory;
    private List<User> usersOnline;
    
    @PostConstruct
    public void init() {
        videoHistory = new ArrayList<>();
        usersOnline = new ArrayList<>();
    }
    
    public void addHistory(SessionVideoHistory svh) {
        
        if(!videoHistory.contains(svh)) videoHistory.add(svh);
    }
    
    public void addUser(User user) {
        usersOnline.add(user);
    }
    
    public void removeHistory(SessionVideoHistory svh) {
        videoHistory.remove(svh);
    }
    
    public void removeUser(User user) {
        usersOnline.remove(user);
    }

    public List<SessionVideoHistory> getVideoHistory() {
        return videoHistory;
    }
    
    public SessionVideoHistory getHistoryByUser(String username) {
        for (SessionVideoHistory sessionVideoHistory : videoHistory) {
            if(sessionVideoHistory.getUser().getUsername().equals(username)) {
                return sessionVideoHistory;
            }
        }
        return null;
    }

    public List<User> getUsersOnline() {
        return usersOnline;
    }
    
    
    
    
}
