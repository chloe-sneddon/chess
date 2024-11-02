package dataaccess;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SqlUserDAOTests extends SqlTestFunctions{
    @Test
    @DisplayName("positive userExists")
    public void userExists(){
        try (var conn = DatabaseManager.getConnection()){
            addData(conn);
            Boolean b = usrSql.userExists("Puddles");
            Assertions.assertEquals(true,b);
        }
        catch(Exception e){
            Assertions.fail(e.getLocalizedMessage());
        }
    }

    @Test
    @DisplayName("badUserExists")
    public void badUserExists(){
        try{
            Boolean b = usrSql.userExists("Puddles");
            Assertions.assertEquals(false,b);
        }
        catch(Exception e){
            Assertions.fail(e.getLocalizedMessage());
        }
    }
}
