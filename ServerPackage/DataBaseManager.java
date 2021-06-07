package ServerPackage;

import Commen.User;

import java.io.*;
import java.util.concurrent.ConcurrentHashMap;

public class DataBaseManager {
    public static final String userFile="C:\\Users\\HP\\Desktop\\projeAP\\src\\DataBase\\UserDataBase";
    public static DataBaseManager dataBaseManager=new DataBaseManager();
    //only way to use this class is this method
    public static DataBaseManager getInstance(){
        return dataBaseManager;
    }

    public synchronized void initializeServer(){
        try {
            FileInputStream fileInputStream=new FileInputStream(DataBaseManager.userFile);
            ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
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
    //update post and profile
    //synchronized cuz multithread issues
    public synchronized void updateDataBase(){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(userFile);
            ObjectOutputStream objToFile = new ObjectOutputStream(fileOutputStream);
            objToFile.writeObject(Server.users); //writing profiles
            objToFile.close();
            fileOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
