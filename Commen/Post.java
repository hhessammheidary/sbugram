package Commen;

import javafx.scene.image.Image;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.*;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Post implements Serializable , Comparable {
    private String writer;
    private String title;
    private String description;
    private final LocalDateTime dateWithTime;
    private final LocalDate date;
    private Map<String , Post> likes=new ConcurrentHashMap<>();
    private Map<String , Post> reposts=new ConcurrentHashMap<>();
    private Map<String , Post> comments=new ConcurrentHashMap<>();
    public byte[] postImageByteArray;

    public Post(){
        this.dateWithTime=LocalDateTime.now();
        this.date=LocalDate.now();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void likePost(String username , Post post) {
        likes.put(username , post);
    }

    public void repost(String username , Post post) {
        reposts.put(username, post);
    }

    public void comment(String username , Post post) {
        comments.put(username, post);
    }

    public byte[]  getPostImageByteArray() {
        return postImageByteArray;
    }

    public void setPostImageByteArray(byte[] postImageByteArray) {
        this.postImageByteArray=postImageByteArray;
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
