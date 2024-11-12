package client;

import model.AuthData;
import model.UserData;
import model.GameData;

import com.google.gson.Gson;

import java.io.*;
import java.net.*;

public class ServerFacade {
    private final String serverUrl;

    public ServerFacade(String url){
        serverUrl = url;
    }

    public AuthData register(String username, String password, String email) throws ResponseException{
        var path = "/user";
        var httpMeth = "POST";
        UserData usrdta = new UserData(username, password, email);
        return makeRequest(httpMeth,path,usrdta, AuthData.class);
    }

    public AuthData login(String username, String password) throws ResponseException{
        var path = "/session";
        var httpMeth = "POST";
        UserData usrdta = new UserData(username,password, null);
        return makeRequest(httpMeth,path,usrdta, AuthData.class);
    }

    private <T> T makeRequest(String httpMethod, String path, Object request, Class<T> responseClass) throws ResponseException{
        try{
            URL url = (new URI(serverUrl+path)).toURL();
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod(httpMethod);
//            must have if there is a request body
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
        if (status/100 == 2) {
            throw new ResponseException(status, "failure: " + status);
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

