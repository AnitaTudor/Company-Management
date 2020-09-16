package utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DbHandler {
    private static DbHandler instance = null;
    private Connection connection;

    private DbHandler(){
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost/pao?useSSL=false" , "root" , "");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static DbHandler getInstance(){
        if (instance == null)
            instance = new DbHandler();
        return instance;
    }

    public Connection getDbConnection(){
        return connection;
    }

}
