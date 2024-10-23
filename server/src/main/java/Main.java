import chess.*;
import server.Server;

public class Main {
    public static void main(String[] args) {
        var server =  new Server();
//        Change port to be initialized by args
//        var port = server.run(0);
        var port = server.run(8080);
        System.out.println("♕ 240 Chess Server: " + port);
    }
}