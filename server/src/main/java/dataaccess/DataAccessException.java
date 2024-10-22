package dataaccess;

/**
 * Indicates there was an error connecting to the database
 */
public class DataAccessException extends Exception{
    final String message;
    final int errorCode;
    public DataAccessException(String message, int errorCode) {
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

    @Override
    public String toString() {
        return "message:" + message;
    }
}
