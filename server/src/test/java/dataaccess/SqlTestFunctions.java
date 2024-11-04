package dataaccess;

import dataaccess.auth.AuthSqlAccess;
import dataaccess.game.GameSqlAccess;
import dataaccess.user.UserSqlAccess;
import org.junit.jupiter.api.BeforeEach;
import service.GeneralService;
import java.sql.Connection;
import java.util.ArrayList;

public class SqlTestFunctions {
    public AuthSqlAccess authSql = new AuthSqlAccess();
    public UserSqlAccess usrSql = new UserSqlAccess();
    public GameSqlAccess gameSql = new GameSqlAccess();

    @BeforeEach
    public void run() throws Exception {
        DatabaseManager.configureDatabase();
        GeneralService.clear();
    }

    public void checkData(Connection conn) throws Exception {
        ArrayList<String[]> requestExamples = SqlTestStatements.getSelectsAndExpected();
        for(String[] dataGroup : requestExamples){
            String actual = dataGroup[0];
            String expected = dataGroup[1];

            try (var actualToken = conn.prepareStatement(actual)) {
                var rs = actualToken.executeQuery();
                rs.next();
                var result = rs.getString(1);

                if(!(expected.equals(result))){
                    throw new Exception("authToken not equal");
                }
            }
        }
    }

    public void addData(Connection conn) throws Exception{
        ArrayList<String> addExamples = SqlTestStatements.getAddExamples();
        for(String insert : addExamples){
            try (var preparedStatement = conn.prepareStatement(insert)) {
                preparedStatement.executeUpdate();
            }
        }
    }

}
