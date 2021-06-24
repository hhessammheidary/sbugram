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
                switch(command){
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
                    case Comment:
                        API.comment(income);
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
        // after logout!
        try {
            socketIn.close();
            socketOut.close();
            userSocket.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
