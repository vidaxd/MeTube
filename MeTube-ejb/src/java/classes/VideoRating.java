package classes;

import javax.persistence.Embeddable;




@Embeddable
public class VideoRating {
    
    
    private int likes;
    private int dislikes;

    public VideoRating() {
        
        this.likes = 0;
        this.dislikes = 0;
    }

    public void likes() {
        likes++;
    }

    public void dislikes() {
        dislikes++;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }
    

    public int getLikes() {
        return likes;
    }

    public int getDislikes() {
        return dislikes;
    }
    
    
    
}
