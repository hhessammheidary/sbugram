package Commen;

import javafx.scene.image.Image;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.*;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

public class Post implements Serializable , Comparable {
    private final String writer;
    private final String title;
    private final String description;
    private final LocalDateTime dateWithTime;
    private final LocalDate date;
    private int like = 0;
    private int repost = 0;
    private final Image image;
    public static CopyOnWriteArrayList<String> comments;

    public Post(String writer , String title , String description , Image image) {
        this.writer=writer;
        this.title=title;
        this.description=description;
        this.image=image;
        this.dateWithTime=LocalDateTime.now();
        this.date=LocalDate.now();
    }

    public String getTitle() {
        return title;
    }

    public String getWriter() {
        return writer;
    }

    public String getDescription() {
        return description;
    }

    public int getLike() {
        return like;
    }

    public void setLike() {
        like++;
    }

    public int getRepost() {
        return repost;
    }

    public void setRepost(){
        repost++;
    }

    public Image getImage() {
        return image;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalDateTime getDateWithTime() {
        return dateWithTime;
    }

    public boolean equals(Object o){
        if(this==o){
            return true;
        }
        if(o==null || getClass()!=o.getClass()){
            return false;
        }
        Post post=(Post) o;
        return (Objects.equals(getTitle() , ((Post) o).getTitle()) && Objects.equals(getDescription() , ((Post) o).getDescription()));
    }

    @Override
    public int compareTo(Object o) {
        return this.getDateWithTime().compareTo(((Post) o).getDateWithTime());
    }
}
