package ClientPackage.Model;

import Commen.Commands;
import Commen.Post;
import Commen.User;

import java.io.PipedOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class API {
    public static boolean isUserNameExists(String usernameTocheck){
        Map<String,Object> toSend = new HashMap<>();
        toSend.put("command", Commands.IsUsernameUnique);
        toSend.put("username",usernameTocheck);
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
}
