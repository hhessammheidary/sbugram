package ServerPackage;



import Commen.Post;
import Commen.User;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.Vector;

public class Server {
    private static final int port=8080;
    private static final boolean isServerUp=true;
    public static Map<String , User> users=null;

    public static boolean isServerUp() {
        return isServerUp;
    }

    public static void main(String[] args) {

        DataBaseManager.getInstance().initializeServer();

        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(port);

        } catch (IOException e) {
            System.exit(1);
        }
        //accept client and start ClientHandler
        while ( isServerUp() ) {
            Socket userSocket = null;
            try {
                userSocket = serverSocket.accept();
                System.out.println("someone connected");
                ClientHandler clientHandler = new ClientHandler(userSocket);
                new Thread(clientHandler).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}