import chess.*;
import dataaccess.DataAccessException;
import dataaccess.DatabaseManager;
import handler.HandlerClass;
import server.Server;

public class Main {
    public static void main(String[] args) {
        var server =  new Server();
//        TODO: Change port to be initialized by args
        var port = server.run(3306);
        try{
            DatabaseManager.configureDatabase();
        }
        catch(Exception e){
            System.out.printf("Unable to start server: %s%n", e.getMessage());
        }
        System.out.println("♕ 240 Chess Server: " + port);
    }
}