package Commen;

import javafx.scene.image.Image;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.*;
import java.util.Map;
import java.util.Objects;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Post implements Serializable , Comparable {
    PostType postType;
    private String writer;
    private String title;
    private String description;
    private final LocalDateTime dateWithTime;
    private final LocalDate date;
    private Vector<String> likes=new Vector<>();
    private Vector<String> reposts=new Vector<>();
    private Map<String , Comment> comments=new ConcurrentHashMap<>();
    private boolean isRepost=false;
    private byte[] postImageByteArray;

    public Post(PostType postType){
        this.postType=postType;
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
    public void likeOrDislikePost(String username) {
        if(likes.contains(username)){
            likes.remove(username);
        }
        else{
            likes.add(username);
        }
    }

    public int likeNum(){
        return likes.size();
    }

    public Integer repost(String username) {
        reposts.add(username);
        return reposts.size();
    }

    public void comment(String username , Comment comment) {
        comments.put(username , comment);
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

    public Boolean getIsRepost(){
        return isRepost;
    }

    public void setRepost(){
        isRepost=true;
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
