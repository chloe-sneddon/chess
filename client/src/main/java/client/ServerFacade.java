package client;

import model.AuthData;
import model.UserData;
import model.GameData;

import com.google.gson.Gson;
import model.request.JoinGameRequest;
import model.response.ListGamesResponse;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class ServerFacade {
    private final String serverUrl;
    private String token;

    public ServerFacade(String url){
        serverUrl = url;
        token = null;
    }

    public void register(String username, String password, String email) throws ResponseException{
        var path = "/user";
        var httpMeth = "POST";

        UserData usrdta = new UserData(username, password, email);
        AuthData dta = makeRequest(httpMeth,path,usrdta, AuthData.class);

        token = dta.authToken();
    }

    public AuthData login(String username, String password) throws ResponseException{
        var path = "/session";
        var httpMeth = "POST";
        UserData usrdta = new UserData(username,password, null);
        return makeRequest(httpMeth,path,usrdta, AuthData.class);
    }

    public GameData createGame(String gameName) throws ResponseException{
        var path = "/game";
        var httpMeth = "POST";
        GameData gmDta = new GameData(0,null,null,gameName,null);
        return makeRequest(httpMeth,path,gmDta, GameData.class);
    }
    public ArrayList<GameData> listGames() throws ResponseException {
        var path = "/game";
        var httpMeth = "GET";
        return makeRequest(httpMeth,path,null,ListGamesResponse.class).games();

    }
    public void joinGame(int gameID, String playerColor) throws ResponseException{
        var path = "/game";
        var httpMeth = "PUT";
        JoinGameRequest dta = new JoinGameRequest(playerColor,gameID);
        makeRequest(httpMeth,path,dta,null);
    }

    private <T> T makeRequest(String httpMethod, String path, Object request, Class<T> responseClass) throws ResponseException{
        try{
            URL url = (new URI(serverUrl+path)).toURL();
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestProperty("authorization", token);
            http.setRequestMethod(httpMethod);
            http.setDoOutput(true);

            writeBody(request, http);
            http.connect();
            throwIfNotSuccessful(http);
            return readBody(http, responseClass);
        }
        catch (Exception e){
            throw new ResponseException(500, e.getMessage());
        }
    }

    private static void writeBody(Object request, HttpURLConnection http) throws IOException {
        if (request != null) {
            http.addRequestProperty("Content-Type", "application/json");
            String reqData = new Gson().toJson(request);
            try (OutputStream reqBody = http.getOutputStream()) {
                reqBody.write(reqData.getBytes());
            }
        }
    }

    private void throwIfNotSuccessful(HttpURLConnection http) throws IOException, ResponseException {
        var status = http.getResponseCode();
        if (status == 400) {
            throw new ResponseException(status, "Error: bad request");
        }
        else if (status == 401) {
            throw new ResponseException(status, "Error: unauthorized");
        }
        else if (status == 403) {
            throw new ResponseException(status, "Error: already taken");
        }
         else if (status/100 != 2) {
            throw new ResponseException(status, "Failure");
        }
    }

    private static <T> T readBody(HttpURLConnection http, Class<T> responseClass) throws IOException {
        T response = null;
        if (http.getContentLength() < 0) {
            try (InputStream respBody = http.getInputStream()) {
                InputStreamReader reader = new InputStreamReader(respBody);
                if (responseClass != null) {
                    response = new Gson().fromJson(reader, responseClass);
                }
            }
        }
        return response;
    }
}

