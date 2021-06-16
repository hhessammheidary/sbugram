package ServerPackage;

import Commen.Commands;
import Commen.User;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

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
        answer.put("answer" , user);
        System.out.println(user.getUsername() + " : SignUp");
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
        System.out.println(username + "change password");

        Map<String , Object> toSend=new HashMap<>();
        toSend.put("command" , Commands.ChangePassword);
        toSend.put("answer" , Boolean.TRUE);
        return toSend;
    }
}
