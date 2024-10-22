package service;


public class UserServiceException extends Exception{
    final String message;
    final int errorCode;

    public UserServiceException(String message, int errorCode) {
        super(message);
        this.message = message;
        this.errorCode = errorCode;
    }

    public int statusCode(){
        return errorCode;
    }

    public String message(){
        return message;
    }
}