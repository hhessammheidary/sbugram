package ServerPackage;

import Commen.Commands;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;

public class ClientHandler implements Runnable{
    private Socket userSocket;
    private ObjectOutputStream socketOut;
    private ObjectInputStream socketIn;
    public Boolean isClientOnline = true;

    public ClientHandler(Socket socket){
        try {
            this.userSocket = socket;
            this.socketIn = new ObjectInputStream(socket.getInputStream());
            this.socketOut = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    @Override
    public void run() {
        while(isClientOnline){
            Map<String,Object> income = null;
            try{
                income = (Map<String,Object>) socketIn.readObject();
                Map<String,Object> answer = null;
                Commands command = (Commands) income.get("command");
                switch(command){//take message from client and handle it and send answer from server
                    case IsUsernameUnique:
                        answer = API.isUserNameExists(income);
                        break;
                    case Login:
                        answer = API.login(income);
                        break;
                    case SingUp:
                        answer = API.signUp(income);
                        break;
                    case ForgetPassword:
                        answer=API.forgetPassword(income);
                        break;
                    case ChangePassword:
                        answer=API.changePassword(income);
                        break;
                    case AddPost:
                        answer=API.addPost(income);
                        break;
                    case TimeLine:
                        answer=API.timeLine(income);
                        break;
                    case DeleteAccount:
                        answer=API.deleteAccount(income);
                        break;
                    case SearchUser:
                        answer=API.searchUser(income);
                        break;
                    case GetUserPosts:
                        answer=API.getUserPost(income);
                        break;
                    case Like:
                        answer=API.like(income);
                        break;
                    case LikeNumber:
                        answer=API.getLikeNumber(income);
                        break;
                    case AddComment:
                        answer=API.addComment(income);
                        break;
                    case Repost:
                        answer=API.repost(income);
                        break;
                    case ChangeFirstname:
                        answer=API.changeFirstname(income);
                        break;
                    case ChangeLastname:
                        answer=API.changeLastname(income);
                        break;
                    case ChangePhoneNumber:
                        answer=API.changePhoneNumber(income);
                        break;
                    case ChangeProfileImage:
                        answer=API.changeProfileImage(income);
                        break;
                    case GetUser:
                        answer=API.getUser(income);
                        break;
                    case RepostNumber:
                        answer=API.getRepostNumber(income);
                        break;
                    case GetUserProfile:
                        answer=API.getUserProfile(income);
                        break;
                    case LoadUser:
                        answer=API.loadUser(income);
                        break;
                    case GetComments:
                        answer=API.getComments(income);
                        break;
                    case CommentNumber:
                        answer=API.getCommentNumber(income);
                        break;
                    case Follow:
                        answer=API.followUser(income);
                        break;
                    case Unfollow:
                        answer=API.UnfollowUser(income);
                        break;
                    case FollowerNumber:
                        answer=API.getFollowerNumber(income);
                        break;
                    case FollowingNumber:
                        answer=API.getFollowingNumber(income);
                        break;
                    case GetUserWithUsername:
                        answer=API.getUserWithUsername(income);
                        break;
                }
                socketOut.writeObject(answer);
                socketOut.flush();
            }
            catch(ClassCastException | ClassNotFoundException e){

            }
            catch(EOFException e){
                break;
            }
            catch(IOException e){
                break;
            }

        }
        // after logout
        try {
            socketIn.close();
            socketOut.close();
            userSocket.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
