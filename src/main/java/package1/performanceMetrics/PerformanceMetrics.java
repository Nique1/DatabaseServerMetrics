package package1.performanceMetrics;

import package1.databaseConnection.DataSourceSwitcher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class PerformanceMetrics {
    private DataSourceSwitcher dataSourceSwitcher;

    public PerformanceMetrics(DataSourceSwitcher dataSourceSwitcher) {
        this.dataSourceSwitcher = dataSourceSwitcher;
    }

    public ResultSet retrieveCPUInfo() {
        return executeQuery("SELECT * FROM sys.dm_os_performance_counters WHERE counter_name LIKE '%CPU%'");
    }

    public ResultSet retrieveDiskUsage() {
        return executeQuery
                ("SELECT name AS DatabaseName, " +
                "type_desc AS FileType, " +
                "size * 8 AS TotalSizeKB, " +
                "fileproperty(name, 'SpaceUsed') * 8 AS UsedSpaceKB, " +
                "(size - fileproperty(name, 'SpaceUsed')) * 8 AS AvailableSpaceKB " +
                "FROM sys.database_files"
                );
    }

    public ResultSet retrieveMemoryUsage() {
        return executeQuery
                ("SELECT SUM(pages_kb) AS TotalMemoryUsedKB FROM sys.dm_os_memory_clerks ");
    }

    public ResultSet executeQuery(String query) {
        Connection conn = dataSourceSwitcher.getConnection();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
