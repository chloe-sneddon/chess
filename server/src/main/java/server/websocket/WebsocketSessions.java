package server.websocket;

import org.eclipse.jetty.websocket.api.Session;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class WebsocketSessions {
    public final ConcurrentHashMap<Integer, ArrayList<Connection>> connections = new ConcurrentHashMap<>();

    public void addSessionToGame(int gameID, Session session) {
        var connection = new Connection(gameID, session);
        
        if (connections.get(gameID) != null){
            ArrayList<Connection> tmp = connections.get(gameID);
            tmp.add(connection);
        }
        else{
            ArrayList <Connection> gameCon = new ArrayList<>();
            gameCon.add(connection);
            connections.put(gameID, gameCon);
        }
    }

    public void removeSessionFromGame(int gameID, Session session) {
        ArrayList<Connection> tmpCon = connections.get(gameID);

        if (tmpCon != null){
            for(var con: tmpCon){
                if(con.session.equals(session)){
                    tmpCon.remove(con);
                }
            }
        }
    }

    public ArrayList<Session> getSessionsForGame(int gameID){
        ArrayList<Connection> tmp = connections.get(gameID);
        ArrayList<Session> sessions = new ArrayList<>();
        if(tmp != null){
            for (var con: tmp){
                sessions.add(con.session);
            }
        }
        return sessions;
    }



}
