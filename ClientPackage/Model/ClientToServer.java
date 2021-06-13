package ClientPackage.Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Map;

public class ClientToServer {

    public static String serverAddress="127.0.0.1";
    public static final int PORT = 8080;

    private static boolean isConnected = false;
    public static Socket socket;
    public static ObjectInputStream socketIn;
    public static ObjectOutputStream socketOut;

    public static boolean isConnected(){
        return isConnected;
    }

    public static Boolean connectToServer(){
        if(socket != null){
            return false;
        }
        try{
            System.out.println("server ip : " + serverAddress);
            socket = new Socket( serverAddress , PORT);
            socketOut = new ObjectOutputStream( socket.getOutputStream() );
            socketIn = new ObjectInputStream( socket.getInputStream() );
            isConnected = true;
            return true;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Boolean disconnectFromServer(){
        try{
            socketIn.close();
            socketOut.close();
            socket.close();
            isConnected = false;
            socket = null;
            socketIn = null;
            socketOut = null;
            return true;
        }
        catch (SocketException | NullPointerException ignored){
        }
        catch( Exception e){
            e.printStackTrace();
        }
        socket = null;
        socketIn = null;
        socketOut = null;
        return false;
    }

    public static Map<String,Object> sendToServer(Map<String,Object> toSend){
        Map<String,Object> received = null;
        try{
            socketOut.writeObject(toSend);
            socketOut.flush();
            socketOut.reset();
            received = (Map<String,Object>) socketIn.readObject();
            return received;

        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return received;
    }
}
