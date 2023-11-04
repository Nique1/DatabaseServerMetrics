package package1.procedureExecutor;

import java.sql.Connection;

public class InsertData {
    public static void insertData(Connection connection){
        String insertDataSQL = "INSERT INTO WhoIsActiveResults " +
                "([dd hh:mm:ss.mss], session_id, login_name, host_name, database_name) " +
                "VALUES ('00:00:01.000', 1, 'user1', 'host1', 'db1')";
    }
}
