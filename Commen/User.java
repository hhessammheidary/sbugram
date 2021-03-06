package Commen;

import javafx.geometry.Pos;
import javafx.scene.image.Image;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class User implements  Serializable {
    private final String username;
    private String password;
    private String name;
    private String lastName;
    private String phoneNumber;
    private byte[] profileImageByteArray;
    private String favFood;
    private List<Post> posts=new ArrayList<>();
    private List<String> followers = new ArrayList<>();
    private List<String> following = new ArrayList<>();

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword(String password) {
        this.password = password;
    }

    public void changeFirstname(String name) {
        this.name = name;
    }

    public void changeLastname(String lastName) {
        this.lastName = lastName;
    }

    public void changePhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFavFood() {
        return favFood;
    }

    public void setFavFood(String favFood) {
        this.favFood = favFood;
    }

    public byte[]  getProfileImage() {
        return profileImageByteArray;
    }

    public void setProfileImage(byte[] profileImageByteArray) {
        this.profileImageByteArray=profileImageByteArray;
    }

    public void addPost(Post post){
        posts.add(post);
    }

    public List<Post> getPosts() {
        return posts;
    }

    public List<String> getFollowers() {
        return followers;
    }

    public int getFollowersNum() {
        return followers.size();
    }

    public int getFollowingsNum() {
        return following.size();
    }

    public void follow(String username) {
        following.add(username);
    }

    public List<String> getFollowing() {
        return following;
    }

    public void followed(String username){
        followers.add(username);
    }

    public User Conformity(String username , String password){
        if(this.username.equals(username) && this.password.equals(password)){
            return this;
        }
        return null;
    }

    public boolean isCorrectFavFood(String  username, String favFood){
        return (this.username.equals(username) && this.favFood.equals(favFood));
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username);
    }

}
