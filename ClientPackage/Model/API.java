package ClientPackage.Model;

import Commen.Commands;
import Commen.Comment;
import Commen.Post;
import Commen.User;
import ServerPackage.DataBaseManager;
import ServerPackage.Server;
import javafx.geometry.Pos;

import java.rmi.MarshalledObject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class API {
    public static boolean isUserNameExists(String usernameToCheck){
        Map<String,Object> toSend = new HashMap<>();
        toSend.put("command", Commands.IsUsernameUnique);
        toSend.put("username",usernameToCheck);
        Map<String,Object> received = ClientToServer.sendToServer(toSend);

        return (boolean) received.get("answer");
    }

    public static Boolean signUp(User user){
        Map<String,Object> toSend = new HashMap<>();
        toSend.put("command", Commands.SingUp);
        toSend.put("user", user);
        Map<String,Object> received = ClientToServer.sendToServer(toSend);
        if (received.get("answer") == null){
            return null;
        }
        return (Boolean) received.get("answer");
    }

    public static User login(String username , String password){
        Map<String,Object> toSend = new HashMap<>();
        toSend.put("command", Commands.Login);
        toSend.put("username",username);
        toSend.put("password",password);
        Map<String,Object> received = ClientToServer.sendToServer(toSend);
        if ( received.get("answer") == null ){
            return null;
        }
        return (User)received.get("answer");
    }

    public static boolean forgetPassword(String username , String favFood){
        Map<String , Object> toSend =new HashMap<>();
        toSend.put("command" , Commands.ForgetPassword);
        toSend.put("username" , username);
        toSend.put("favFood" , favFood);
        Map<String , Object> received = ClientToServer.sendToServer(toSend);
        return (boolean) received.get("answer");
    }

    public static boolean changePassword(String username , String newPassword){
        Map<String , Object> toSend = new HashMap<>();
        toSend.put("command" , Commands.ChangePassword);
        toSend.put("username" , username);
        toSend.put("newPassword" , newPassword);
        Map<String , Object> received = ClientToServer.sendToServer(toSend);
        return (boolean)received.get("answer");
    }

    public static Boolean addPost(String username , Post newPost){
        Map<String , Object> toSend = new HashMap<>();
        toSend.put("command" , Commands.AddPost);
        toSend.put("username" , username);
        toSend.put("newPost" , newPost);
        Map<String , Object> received = ClientToServer.sendToServer(toSend);
        return (boolean)received.get("answer");
    }

    public static ArrayList<Post> timeLine(String username){
        Map<String , Object> toSend=new HashMap<>();
        toSend.put("command" , Commands.TimeLine);
        toSend.put("username" , username);
        Map<String , Object> received = ClientToServer.sendToServer(toSend);
        return (ArrayList<Post>)received.get("answer");
    }

    public static boolean deleteAccount(String username){
        Map<String , Object> toSend=new HashMap<>();
        toSend.put("command" , Commands.DeleteAccount);
        toSend.put("username" , username);
        Map<String , Object> received = ClientToServer.sendToServer(toSend);
        return (boolean) received.get("answer");
        //to do
    }

    public static ArrayList<User> SearchUser(String words){
        Map<String , Object> toSend=new HashMap<>();
        toSend.put("command" , Commands.SearchUser);
        toSend.put("words" , words);

        Map<String , Object> received = ClientToServer.sendToServer(toSend);
        return (ArrayList<User>) received.get("answer");
    }

    public static ArrayList<Post> getUserPosts(String username){
        Map<String , Object> toSend=new HashMap<>();
        toSend.put("command" , Commands.GetUserPosts);
        toSend.put("username" , username);

        Map<String , Object> received = ClientToServer.sendToServer(toSend);
        return (ArrayList<Post>)received.get("answer");
    }

    public static Boolean like(String username , Post post){
        Map<String , Object> toSend=new HashMap<>();
        toSend.put("command" , Commands.Like);
        toSend.put("username" , username);
        toSend.put("post" , post);

        Map<String , Object> received=ClientToServer.sendToServer(toSend);
        return (Boolean) received.get("answer");
    }

    public static int getLikeNumber(String username , Post post){
        Map<String , Object> toSend=new HashMap<>();
        toSend.put("command" , Commands.LikeNumber);
        toSend.put("username" , username);
        toSend.put("post" , post);

        Map<String , Object> received=ClientToServer.sendToServer(toSend);
        return (Integer) received.get("answer");
    }

    public static boolean addComment(Comment comment , Post post){
        Map<String , Object> toSend = new HashMap<>();
        toSend.put("command" , Commands.AddComment);
        toSend.put("comment" , comment);
        toSend.put("post" , post);

        Map<String , Object> received=ClientToServer.sendToServer(toSend);
        return (boolean) received.get("answer");
    }

    public static boolean repost(String username , Post post){
        Map<String , Object> toSend=new HashMap<>();
        toSend.put("command" , Commands.Repost);
        toSend.put("username" , username);
        toSend.put("post" , post);

        Map<String , Object> received=ClientToServer.sendToServer(toSend);
        return (Boolean) received.get("answer");
    }

    public static int getRepostNumber(String username , Post post){
        Map<String , Object> toSend=new HashMap<>();
        toSend.put("command" , Commands.RepostNumber);
        toSend.put("username" , username);
        toSend.put("post" , post);

        Map<String , Object> received=ClientToServer.sendToServer(toSend);
        return (Integer) received.get("answer");
    }

    public static User changeFirstname(String username , String newFirstname){
        Map<String , Object> toSend = new HashMap<>();
        toSend.put("command" , Commands.ChangeFirstname);
        toSend.put("username" , username);
        toSend.put("newFirstname" , newFirstname);
        Map<String , Object> received = ClientToServer.sendToServer(toSend);
        return (User) received.get("answer");
    }

    public static User changeLastname(String username , String newLastname){
        Map<String , Object> toSend = new HashMap<>();
        toSend.put("command" , Commands.ChangeLastname);
        toSend.put("username" , username);
        toSend.put("newLastname" , newLastname);
        Map<String , Object> received = ClientToServer.sendToServer(toSend);
        return (User) received.get("answer");
    }

    public static User changePhoneNumber(String username , String newPhoneNumber){
        Map<String , Object> toSend = new HashMap<>();
        toSend.put("command" , Commands.ChangePhoneNumber);
        toSend.put("username" , username);
        toSend.put("newPhoneNumber" , newPhoneNumber);
        Map<String , Object> received = ClientToServer.sendToServer(toSend);
        return (User)received.get("answer");
    }

    public static User changeProfileImage(String username , byte[] newProfileImage){
        Map<String , Object> toSend = new HashMap<>();
        toSend.put("command" , Commands.ChangeProfileImage);
        toSend.put("username" , username);
        toSend.put("newProfileImage" , newProfileImage);
        Map<String , Object> received = ClientToServer.sendToServer(toSend);
        return (User)received.get("answer");
    }

    public static Map<String , Object> getUser(String myUsername , String otherUsername){
        Map<String , Object> toSend = new HashMap<>();
        toSend.put("command" , Commands.GetUser);
        toSend.put("myUsername" , myUsername);
        toSend.put("otherUsername" , otherUsername);
        return ClientToServer.sendToServer(toSend);
    }

    public static byte[] getUserProfile(String username){
        Map<String , Object> toSend = new HashMap<>();
        toSend.put("command" , Commands.GetUserProfile);
        toSend.put("username" , username);

        Map<String , Object> received = ClientToServer.sendToServer(toSend);
        return (byte[]) received.get("answer");
    }

    public static User loadUser(String username){
        Map<String , Object> toSend=new HashMap<>();
        toSend.put("command" , Commands.LoadUser);
        toSend.put("username" , username);

        Map<String , Object> received= ClientToServer.sendToServer(toSend);
        return (User) received.get("answer");
    }

    public static ArrayList<Comment> getComments(Post post){
        Map<String , Object> toSend=new HashMap<>();
        toSend.put("command" , Commands.GetComments);
        toSend.put("post" , post);

        Map<String , Object> received = ClientToServer.sendToServer(toSend);
        return (ArrayList<Comment>) received.get("answer");
    }

    public static Map<String, Object> followUser(String myUsername , String othersUsername) {
        Map<String , Object> toSend= new HashMap<>();
        toSend.put("command" , Commands.Follow);
        toSend.put("myUsername" , myUsername);
        toSend.put("othersUsername" , othersUsername);
        return toSend;
    }

    public static Map<String, Object> UnfollowUser(String myUsername , String othersUsername) {
        Map<String , Object> toSend= new HashMap<>();
        toSend.put("command" , Commands.Unfollow);
        toSend.put("myUsername" , myUsername);
        toSend.put("othersUsername" , othersUsername);
        return toSend;
    }


    public static int getFollowerNumber(String username){
        Map<String , Object> toSend= new HashMap<>();
        toSend.put("command" , Commands.FollowerNumber);
        toSend.put("username" , username);
        Map<String , Object> received= ClientToServer.sendToServer(toSend);
        return (Integer) received.get("answer");
    }

    public static int getFollowingNumber(String username) {
        Map<String , Object> toSend= new HashMap<>();
        toSend.put("command" , Commands.FollowingNumber);
        toSend.put("username" , username);
        Map<String , Object> received= ClientToServer.sendToServer(toSend);
        return (Integer) received.get("answer");
    }

    public static int getCommentNumber(String username , Post post){
        Map<String , Object> toSend= new HashMap<>();
        toSend.put("command" , Commands.CommentNumber);
        toSend.put("username" , username);
        toSend.put("post" , post);
        Map<String , Object> received= ClientToServer.sendToServer(toSend);
        return (Integer) received.get("answer");
    }
}
