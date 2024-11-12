import chess.*;
import dataaccess.DataAccessException;
import dataaccess.DatabaseManager;
import handler.HandlerClass;
import server.Server;

public class Main {
    public static void main(String[] args) {
        var server =  new Server();
        var portNum = 8080;
        if (args.length == 1) {
            portNum = Integer.parseInt(args[0]);
        }
//        Change port to be initialized by args
        var port = server.run(portNum);
        System.out.println("♕ 240 Chess Server: " + port);
    }
}