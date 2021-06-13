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

        User user=Server.users.get(username).Conformity(username , password);
        if(user==null){
            return answer;
        }
        answer.put(username , user);
        if(user!=null){
            System.out.println(user.getUsername() + " : login");
            System.out.println("time : " + LocalDateTime.now());
        }


        return answer;
    }
}
