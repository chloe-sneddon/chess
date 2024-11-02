package dataaccess;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SqlGameDAOTests extends SqlTestFunctions{

    @Test
    @DisplayName("createGame")
    public void createGame(){
        try{
            String gameName = "bestGame";
            gameSql.createGame(gameName);
            String statement = "Select gameName from gameData;";

            try (var conn = DatabaseManager.getConnection()) {
                try (var actualToken = conn.prepareStatement(statement)) {
                    var rs = actualToken.executeQuery();
                    rs.next();
                    var result = rs.getString(1);

                    if(!(gameName.equals(result))){
                        throw new Exception("authToken not equal");
                    }
                }
            }
        }
        catch(Exception e){
            Assertions.fail("Unexpected Exception: " + e.getLocalizedMessage());
        }
    }
}
