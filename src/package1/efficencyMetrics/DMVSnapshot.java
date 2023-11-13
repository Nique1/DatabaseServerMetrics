package package1.efficencyMetrics;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import package1.databaseConnection.DataSourceSwitcher;

import java.sql.*;


public class DMVSnapshot {
    DataSourceSwitcher dataSourceSwitcher;

    public DMVSnapshot(DataSourceSwitcher dataSourceSwitcher) {
        this.dataSourceSwitcher = dataSourceSwitcher;
    }

    public ResultSet retrieveCPUInfo(){
        return executeQuery("SELECT * FROM sys.dm_os_performance_counters WHERE counter_name LIKE '%CPU%'");
    }
    public ResultSet retrieveDatabaseInfo(){
        return executeQuery("SELECT * FROM sys.database");
    }

    public ResultSet executeQuery(String query){
        Connection conn = dataSourceSwitcher.getConnection();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            return resultSet;
        }
         catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
