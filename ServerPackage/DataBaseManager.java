package ServerPackage;

import Commen.Post;
import Commen.User;

import java.io.*;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

public class DataBaseManager {
    public static final String userFile="C:\\Users\\HP\\Desktop\\projeAP\\src\\DataBase\\UserDataBase.bin";//database address
    public static DataBaseManager dataBaseManager=new DataBaseManager();
    //only way to use this class is this method
    public static DataBaseManager getInstance(){
        return dataBaseManager;
    }

    public synchronized void initializeServer(){
        try {
            FileInputStream fileInputStream=new FileInputStream(DataBaseManager.userFile);
            ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
            //get users data from server and save it in database file
            Server.users = new ConcurrentHashMap<>((ConcurrentHashMap<String, User>)objectInputStream.readObject());
            objectInputStream.close();
            fileInputStream.close();
        }
        catch(EOFException | StreamCorruptedException e){
            Server.users = new ConcurrentHashMap<>();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //update profile
    //synchronized cuz multi thread issues
    public synchronized void updateDataBase(){
        try {
            FileOutputStream fileOutputStream1 = new FileOutputStream(userFile);
            ObjectOutputStream objToFile1 = new ObjectOutputStream(fileOutputStream1);
            objToFile1.writeUnshared(Server.users); //writing users
            objToFile1.close();
            fileOutputStream1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}