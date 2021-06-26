package ServerPackage;

import Commen.Commands;
import Commen.Comment;
import Commen.Post;
import Commen.User;
import javafx.geometry.Pos;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class API {


    public static Map<String,Object> isUserNameExists(Map<String,Object> income){

        String usernameToCheck = (String) income.get("username");
        User user = Server.users.get(usernameToCheck);
        Boolean exists = (user!=null);
        Map<String,Object> answer = new HashMap<>();
        answer.put("answer" , exists);
        answer.put("command" , Commands.IsUsernameUnique);

        return answer;
    }

    public static Map<String,Object> signUp(Map<String,Object> income){
        Map<String,Object> answer = new HashMap<>();
        User newUser = (User)income.get("user");
        String username = newUser.getUsername();

        Server.users.put(username , newUser);
        DataBaseManager.getInstance().updateDataBase();
        answer.put("command" , Commands.SingUp);
        answer.put("answer" , Boolean.TRUE);
        System.out.println(newUser.getUsername() + " register");
        System.out.println(newUser.getUsername() + " Login");
        System.out.println("time : " + LocalDateTime.now());

        return answer;
    }

    public static Map<String , Object> login(Map<String , Object> income){
        String username = (String)income.get("username");
        String password = (String)income.get("password");
        Map<String , Object> answer=new HashMap<>();
        answer.put("command",Commands.Login);
        answer.put("exists",!(Server.users.get(username) == null));

        User user=Server.users.get(username).Conformity(username , password);
        if(user==null){
            return answer;
        }
        answer.put("command" , Commands.Login);
        answer.put("answer" , user);
        System.out.println(user.getUsername() + " : login");
        System.out.println("time : " + LocalDateTime.now());
        return answer;
    }

    public static Map<String , Object> forgetPassword(Map<String , Object> income){
        String username = (String)income.get("username");
        String favFood = (String)income.get("favFood");
        Map<String , Object> answer=new HashMap<>();

        answer.put("command" , Commands.ForgetPassword);
        if(Server.users.get(username) == null){
            answer.put("answer" , false);
            return answer;
        }
        Boolean isCorrect=Server.users.get(username).isCorrectFavFood(username , favFood);
        answer.put("answer" , isCorrect);
        return answer;
    }

    public static Map<String , Object> changePassword(Map<String , Object> income){
        String username = (String)income.get("username");
        String newPassword = (String)income.get("newPassword");
        Server.users.get(username).changePassword(newPassword);
        DataBaseManager.getInstance().updateDataBase();
        System.out.println(username + " : change password");
        System.out.println("time : " + LocalDateTime.now());

        Map<String , Object> answer=new HashMap<>();
        answer.put("command" , Commands.ChangePassword);
        answer.put("answer" , Boolean.TRUE);
        return answer;
    }

    public static Map<String , Object> addPost(Map<String , Object> income){
        Post post = (Post)income.get("newPost");
        String username = (String)income.get("username");
        Server.users.get(username).addPost(post);
        DataBaseManager.getInstance().updateDataBase();
        System.out.println(username + " : add post");
        System.out.println("time : " + LocalDateTime.now());

        Map<String , Object> answer=new HashMap<>();
        answer.put("command" , Commands.AddPost);
        answer.put("answer" , Boolean.TRUE);
        return answer;
    }

    public static Map<String , Object> timeLine(Map<String , Object> income){
        String username = (String)income.get("username");
        User user = Server.users.get(username);
        ArrayList<Post> timeLinePosts = new ArrayList<>();
        timeLinePosts.addAll(Server.users.get(username).getPosts());
        if(!user.getFollowing().isEmpty()){
            for(int i=0;i<user.getFollowing().size();i++){
                timeLinePosts.addAll(Server.users.get(user.getFollowing().get(i)).getPosts());
            }
        }
        timeLinePosts = (ArrayList<Post>) timeLinePosts.stream().sorted((p1 , p2) ->-p1.getDateWithTime().
                compareTo(p2.getDateWithTime())).collect(Collectors.toList());

        Map<String , Object> answer=new HashMap<>();
        answer.put("command" , Commands.TimeLine);
        answer.put("answer" , timeLinePosts);
        return answer;
    }

    public static Map<String , Object> deleteAccount(Map<String , Object> income){
        String username=(String)income.get("username");
        Server.users.remove(username);
        DataBaseManager.getInstance().updateDataBase();
        System.out.println(username + " : delete account");
        System.out.println("time : " + LocalDateTime.now());

        Map<String , Object> answer=new HashMap<>();
        answer.put("command" , Commands.DeleteAccount);
        answer.put("answer" , Boolean.TRUE);
        return answer;
    }

    public static Map<String , Object> searchUser(Map<String , Object> income){
        String words = (String)income.get("words");
        Collection<User> values = Server.users.values();
        ArrayList<User> users=new ArrayList<>(values);
        for (int i=users.size()-1;i>=0;i--){
            if(!users.get(i).getUsername().startsWith(words)){
                users.remove(i);
            }
        }

        Map<String , Object> answer=new HashMap<>();
        answer.put("command" , Commands.SearchUser);
        answer.put("answer" , users);
        return answer;
    }

    public static Map<String, Object> getUserPost(Map<String, Object> income) {
        String username = (String)income.get("username");
        ArrayList<Post> posts=new ArrayList<>(Server.users.get(username).getPosts());
        posts = (ArrayList<Post>) posts.stream().sorted((p1 , p2) ->-p1.getDateWithTime().
                compareTo(p2.getDateWithTime())).collect(Collectors.toList());

        Map<String , Object> answer=new HashMap<>();
        answer.put("command" , Commands.GetUserPosts);
        answer.put("answer" , posts);
        return answer;
    }

    public static Map<String, Object> like(Map<String , Object> income){
        String username = (String)income.get("username");
        Post post = (Post)income.get("post");
        User user = Server.users.get(username);
        for(int i=0;i<user.getPosts().size();i++){
            if(user.getPosts().get(i).equals(post)){
                user.getPosts().get(i).likeOrDislikePost(username);
                break;
            }
        }
        DataBaseManager.getInstance().updateDataBase();
        System.out.println(username + " : like " + post.getWriter() +" post (title:" + post.getTitle() +")");
        System.out.println("time : " + LocalDateTime.now());
        Map<String , Object> answer=new HashMap<>();
        answer.put("command" , Commands.Like);
        answer.put("answer" , Boolean.TRUE);
        return answer;
    }

    public static Map<String, Object> getLikeNumber(Map<String , Object> income){
        String username = (String)income.get("username");
        Post post = (Post)income.get("post");
        int likeNum=0;
        for(int i=0;i<Server.users.get(username).getPosts().size();i++){
            if(Server.users.get(username).getPosts().get(i).equals(post)){
                likeNum=Server.users.get(username).getPosts().get(i).likeNum();
                break;
            }
        }
        Map<String , Object> answer=new HashMap<>();
        answer.put("command" , Commands.LikeNumber);
        answer.put("answer" , likeNum);
        return answer;
    }

    public static Map<String, Object> addComment(Map<String , Object> income){
        Post post = (Post)income.get("post");
        Comment comment= (Comment)income.get("comment");
        for(int i=0;i<Server.users.get(comment.getUserUsername()).getPosts().size();i++){
            if(Server.users.get(comment.getUserUsername()).getPosts().get(i).equals(post)){
                Server.users.get(comment.getUserUsername()).getPosts().get(i).comment(comment);
                break;
            }
        }
        DataBaseManager.getInstance().updateDataBase();
        System.out.println(comment.getUserUsername() + " : comment " + post.getWriter() +" post (title:" + post.getTitle() +")");
        System.out.println("time : " + LocalDateTime.now());

        Map<String , Object> answer=new HashMap<>();
        answer.put("command" , Commands.AddComment);
        answer.put("answer" , Boolean.TRUE);
        return answer;
    }

    public static Map<String, Object> getCommentNumber(Map<String , Object> income){
        String username = (String)income.get("username");
        Post post = (Post)income.get("post");
        User user = Server.users.get(username);
        int commentNum=0;
        for(int i=0;i<user.getPosts().size();i++){
            if(user.getPosts().get(i).equals(post)){
                commentNum=user.getPosts().get(i).commentNum();
                break;
            }
        }
        Map<String , Object> answer=new HashMap<>();
        answer.put("command" , Commands.CommentNumber);
        answer.put("answer" , commentNum);
        return answer;
    }

    public static Map<String, Object> repost(Map<String , Object> income){
        String username = (String)income.get("username");
        Post post = (Post)income.get("post");
        User postOwner=Server.users.get(post.getWriter());

        for(int i=0;i<postOwner.getPosts().size();i++){
            if(postOwner.getPosts().get(i).equals(post)){
                postOwner.getPosts().get(i).repost(username);
                break;
            }
        }
        Server.users.get(username).addPost(post);
        DataBaseManager.getInstance().updateDataBase();
        System.out.println(username + " : repost " + post.getWriter() +"post (title:" + post.getTitle() +")");
        System.out.println("time : " + LocalDateTime.now());
        Map<String , Object> answer=new HashMap<>();
        answer.put("command" , Commands.Repost);
        answer.put("answer" , Boolean.TRUE);
        return answer;
    }

    public static Map<String, Object> getRepostNumber(Map<String , Object> income){
        String username = (String)income.get("username");
        Post post = (Post)income.get("post");
        User user = Server.users.get(username);
        int repostNum=0;
        for(int i=0;i<user.getPosts().size();i++){
            if(user.getPosts().get(i).equals(post)){
                repostNum=user.getPosts().get(i).repostNum();
                break;
            }
        }
        Map<String , Object> answer=new HashMap<>();
        answer.put("command" , Commands.RepostNumber);
        answer.put("answer" , repostNum);
        return answer;
    }

    public static Map<String , Object> changeFirstname(Map<String , Object> income){
        String username = (String)income.get("username");
        String newFirstname = (String)income.get("newFirstname");
        Server.users.get(username).changeFirstname(newFirstname);
        DataBaseManager.getInstance().updateDataBase();
        System.out.println(username + " : change firstname");
        System.out.println("time : " + LocalDateTime.now());

        Map<String , Object> answer=new HashMap<>();
        answer.put("command" , Commands.ChangeFirstname);
        answer.put("answer" , Server.users.get(username));
        return answer;
    }

    public static Map<String , Object> changeLastname(Map<String , Object> income){
        String username = (String)income.get("username");
        String newLastname = (String)income.get("newLastname");
        Server.users.get(username).changeLastname(newLastname);
        DataBaseManager.getInstance().updateDataBase();
        System.out.println(username + " : change lastname");
        System.out.println("time : " + LocalDateTime.now());

        Map<String , Object> answer=new HashMap<>();
        answer.put("command" , Commands.ChangeLastname);
        answer.put("answer" , Server.users.get(username));
        return answer;
    }

    public static Map<String , Object> changePhoneNumber(Map<String , Object> income){
        String username = (String)income.get("username");
        String newPhoneNumber = (String)income.get("newPhoneNumber");
        Server.users.get(username).changePhoneNumber(newPhoneNumber);
        DataBaseManager.getInstance().updateDataBase();
        System.out.println(username + " : change phone number");
        System.out.println("time : " + LocalDateTime.now());

        Map<String , Object> answer=new HashMap<>();
        answer.put("command" , Commands.ChangePhoneNumber);
        answer.put("answer" , Server.users.get(username));
        return answer;
    }

    public static Map<String, Object> changeProfileImage(Map<String, Object> income) {
        String username = (String)income.get("username");
        byte[] newProfileImage = (byte[]) income.get("newProfileImage");
        Server.users.get(username).setProfileImage(newProfileImage);
        DataBaseManager.getInstance().updateDataBase();
        System.out.println(username + " : change profile image");
        System.out.println("time : " + LocalDateTime.now());

        Map<String , Object> answer=new HashMap<>();
        answer.put("command" , Commands.ChangePhoneNumber);
        answer.put("answer" , Server.users.get(username));
        return answer;
    }

    public static Map<String , Object> getUser(Map<String , Object> income) {
        String myUsername = (String)income.get("myUsername");
        String otherUsername = (String)income.get("otherUsername");
        boolean followOrUnFollow;//true:unfollow button visible false:follow button visible
        User user=Server.users.get(myUsername);
        if(user.getFollowing().contains(otherUsername)){
            followOrUnFollow=true;
        }
        else {
            followOrUnFollow=false;
        }
        Map<String , Object> answer = new HashMap<>();
        answer.put("command" , Commands.GetUser);
        answer.put("answer" , Server.users.get(otherUsername));
        answer.put("followOrUnFollow" , followOrUnFollow);
        return answer;
    }

    public static Map<String, Object> getUserProfile(Map<String, Object> income) {
        String username = (String) income.get("username");

        Map<String , Object> answer=new HashMap<>();
        answer.put("command" , Commands.GetUserProfile);
        answer.put("answer" , Server.users.get(username).getProfileImage());
        return answer;
    }

    public static Map<String , Object> loadUser(Map<String , Object> income){
        String username = (String) income.get("username");

        Map<String , Object> answer = new HashMap<>();
        answer.put("command" , Commands.LoadUser);
        answer.put("answer" , Server.users.get(username));
        return answer;
    }

    public static Map<String , Object> getComments(Map<String , Object> income){
        Post post = (Post)income.get("post");
        ArrayList<Comment> comments=new ArrayList<>();

        for(int i=0;i<Server.users.get(post.getWriter()).getPosts().size();i++){
            if(Server.users.get(post.getWriter()).getPosts().get(i).equals(post)){
                comments.addAll(Server.users.get(post.getWriter()).getPosts().get(i).getComments());
                break;
            }
        }

        Map<String , Object> answer=new HashMap<>();
        answer.put("command" , Commands.GetComments);
        answer.put("answer" , comments);
        return answer;
    }

    public static Map<String, Object> followUser(Map<String, Object> income) {
        String myUsername=(String) income.get("myUsername");
        String othersUsername=(String) income.get("othersUsername");
        Server.users.get(myUsername).getFollowing().remove(othersUsername);
        Server.users.get(othersUsername).getFollowers().remove(myUsername);
        DataBaseManager.getInstance().updateDataBase();
        System.out.println(myUsername + " : follow " +othersUsername);
        System.out.println("time : " + LocalDateTime.now());

        Map<String , Object> answer=new HashMap<>();
        answer.put("command" , Commands.Follow);
        answer.put("answer" , Boolean.TRUE);
        return answer;
    }

    public static Map<String, Object> UnfollowUser(Map<String, Object> income) {
        String myUsername=(String) income.get("myUsername");
        String othersUsername=(String) income.get("othersUsername");
        Server.users.get(myUsername).getFollowing().remove(othersUsername);
        Server.users.get(othersUsername).getFollowers().remove(myUsername);
        DataBaseManager.getInstance().updateDataBase();
        System.out.println(myUsername + " : unfollow " +othersUsername);
        System.out.println("time : " + LocalDateTime.now());

        Map<String , Object> answer=new HashMap<>();
        answer.put("command" , Commands.Unfollow);
        answer.put("answer" , Boolean.TRUE);
        return answer;
    }

    public static Map<String, Object> getFollowerNumber(Map<String, Object> income){
        String username=(String) income.get("username");
        Integer followerNum=Server.users.get(username).getFollowersNum();
        Map<String , Object> answer=new HashMap<>();
        answer.put("command" , Commands.FollowerNumber);
        answer.put("answer" , followerNum);
        return answer;
    }

    public static Map<String, Object> getFollowingNumber(Map<String, Object> income) {
        String username=(String) income.get("username");
        Integer followingNum=Server.users.get(username).getFollowingsNum();
        Map<String , Object> answer=new HashMap<>();
        answer.put("command" , Commands.FollowerNumber);
        answer.put("answer" , followingNum);
        return answer;
    }
}
