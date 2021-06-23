package ServerPackage;

import Commen.Commands;
import Commen.Post;
import Commen.User;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class API {
    public static Map<String , Object> getUser(Map<String , Object> income){
        String username=(String) income.get("username");
        Map<String , Object> answer=new HashMap<>();
        answer.put("command" , Commands.GetUser);
        answer.put("answer" , Server.users.get(username));

        return answer;
    }
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

        Map<String , Object> answer=new HashMap<>();
        answer.put("command" , Commands.ChangePassword);
        answer.put("answer" , Boolean.TRUE);
        return answer;
    }

    public static Map<String , Object> addPost(Map<String , Object> income){
        Post post = (Post)income.get("newPost");
        System.out.println(post.getTitle());
        String username = (String)income.get("username");
        Server.users.get(username).addPost(post);
        DataBaseManager.getInstance().updateDataBase();
        System.out.println(username + " : add post");

        Map<String , Object> answer=new HashMap<>();
        answer.put("command" , Commands.AddPost);
        answer.put("answer" , Boolean.TRUE);
        return answer;
    }

    public static Map<String , Object> timeLine(Map<String , Object> income){
        String username = (String)income.get("username");
        User user = Server.users.get(username);
        ArrayList<Post> timeLinePosts = new ArrayList<>(Server.users.get(username).getPosts());
        if(!user.getFollowing().isEmpty()){
            for(int i=0;i<user.getFollowing().size();i++){
                timeLinePosts.addAll(Server.users.get(user.getFollowing().get(i)).getPosts());
            }
        }
        timeLinePosts = (ArrayList<Post>) timeLinePosts.stream().sorted((p1 , p2) ->-p1.getDateWithTime().compareTo(p2.getDateWithTime())).collect(Collectors.toList());

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
            if(!users.get(i).getUsername().contains(words)){
                users.remove(i);
            }
        }

        Map<String , Object> answer=new HashMap<>();
        answer.put("command" , Commands.SearchUser);
        answer.put("answer" , users);
        return answer;
    }

    public static void like(Map<String , Object> income){
    }

    public static void comment(Map<String , Object> income){
    }

    public static void repost(Map<String , Object> income){
    }
}
