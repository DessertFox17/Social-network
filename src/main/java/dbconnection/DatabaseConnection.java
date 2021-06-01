package dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    public Connection get_connection(){
        Connection connection= null;
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/social_network","root","");
        }catch(Exception e){
            System.out.println(e);
        }
        return connection;
    }
}
