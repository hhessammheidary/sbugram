package Server;

import Commen.User;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;
import java.util.concurrent.ConcurrentHashMap;

public class DataBaseManager {
    public static final String UserFile="./DataBase/UserDataBase";
    public static DataBaseManager dataBaseManager=new DataBaseManager();
    //only way to use this class is this method
    public static DataBaseManager getInstance(){
        return dataBaseManager;
    }

    public synchronized void initializeServer(){
        try {
            FileInputStream fin=new FileInputStream(DataBaseManager.UserFile);
            ObjectInputStream inFromFile=new ObjectInputStream(fin);
            Serverr.users = new ConcurrentHashMap<>( (ConcurrentHashMap<String, User>) inFromFile.readObject());
            inFromFile.close();
            fin.close();
        }
        catch(EOFException | StreamCorruptedException e){
            Serverr.users = new ConcurrentHashMap<>();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
