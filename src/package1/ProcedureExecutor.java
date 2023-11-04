package package1;

import java.sql.*;

public class ProcedureExecutor {
    private Connection connection;

    public ProcedureExecutor(Connection connection){
        this.connection = connection;
    }

    public void executeProcedure(){
        String whoIsActiveQuery = "{call sp_whoisactive}";
        int sessionID = 0;
        try (CallableStatement whoIsActiveStatement = connection.prepareCall(whoIsActiveQuery);
             ResultSet whoIsActiveResultSet = whoIsActiveStatement.executeQuery()) {

            while (whoIsActiveResultSet.next()) {
                // Process and print results from the sp_whoisactive procedure
                System.out.println("sp_whoisactive: " + whoIsActiveResultSet.getString("session_id"));
                // Replace "ColumnName" with the actual column name you want to print.
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
