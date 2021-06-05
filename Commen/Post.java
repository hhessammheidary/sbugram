package Commen;

import javafx.scene.image.Image;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Post {
    private String writer;
    private String title;
    private String description;
    private int like=0;
    private int repost=0;
    private Image image;
    public static List<String> comments=new ArrayList<>();
    private String date=LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

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

    public void setImage(Image image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public boolean equals(Object o){
        if(this==o){
            return true;
        }
        if(o==null || getClass()!=o.getClass()){
            return false;
        }
        Post post=(Post) o;
        return (Objects.equals(title , ((Post) o).title) && Objects.equals(description , ((Post) o).description));
    }
}
