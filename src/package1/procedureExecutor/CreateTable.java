package package1.procedureExecutor;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {
    public static void createTable(Connection connection){
        String createSQLTable = "CREATE TABLE WhoIsActiveResults ( " +
                "                [dd hh:mm:ss.mss] NVARCHAR(50), " +
                "                session_id INT, " +
                "                login_name NVARCHAR(100), " +
                "                host_name NVARCHAR(100), " +
                "                database_name NVARCHAR(100), " +
                "                wait_info NVARCHAR(150), " +
                "                CPU INT, " +
                "                tempdb_allocations NVARCHAR(150)," +
                "                tempdb_current NVARCHAR(150), " +
                "                blocking_session_id INT, " +
                "                reads INT, " +
                "                writes INT, " +
                "                physical_reads, INT " +
                "                used_memory, INT " +
                "                status NVARCHAR(100), " +
                "                open_tran_count INT, " +
                "                percent_complete INT " +
                "                host_name NVARCHAR(100), " +
                "                database_name NVARCHAR(100),  " +
                "                program_name NVARCHAR(150), " +
                "                start_time DATETIME(100), " +
                "                login_time DATETIME(100), " +
                "                request_id INT, " +
                "                collection_time DATETIME(100)"+
                "                )";
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate(createSQLTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
