package ClientPackage.Model;

import Commen.Commands;
import Commen.User;

import java.util.HashMap;
import java.util.Map;

public class API {
    public static boolean isUserNameExists(String usernameTocheck){
        Map<String,Object> toSend = new HashMap<>();
        toSend.put("command", Commands.IsUsernameUnique);
        toSend.put("username",usernameTocheck);
        Map<String,Object> received = ClientToServer.sendToserver(toSend);
        return (boolean) received.get("answer");
    }

    public static Boolean signUp(User user){
        Map<String,Object> toSend = new HashMap<>();
        toSend.put("command", Commands.SingUp);
        toSend.put("user", user);
        Map<String,Object> received = ClientToServer.sendToserver(toSend);
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
        Map<String,Object> received = ClientToServer.sendToserver(toSend);
        if ( received.get("answer") == null ){
            return null;
        }
        return (User)received.get("answer");
    }
}
