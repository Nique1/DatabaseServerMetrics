package package1;


import java.sql.ResultSet;
import java.sql.Timestamp;

public class DatabaseOperationMetrics {
    private Timestamp startTime;
    private Timestamp endTime;

    public void startOperation() {
        startTime = new Timestamp(System.currentTimeMillis());
    }

    public void endOperation() {
        endTime = new Timestamp(System.currentTimeMillis());
    }

    public long getElapsedTime() {
        if (startTime != null && endTime != null) {
            return endTime.getTime() - startTime.getTime();
        }
        return -1; // Indicate that the operation hasn't started or ended
    }

    public void printMetrics() {
        System.out.println("Database Operation Metrics:");
        System.out.println("Start Time: " + startTime);
        System.out.println("End Time: " + endTime);
        System.out.println("Elapsed Time: " + getElapsedTime() + " milliseconds");
    }



    public void measureDatabaseOperationEfficiency(ResultSet resultSet) {
        startOperation();
        // Execute your database operation here, e.g., executing a query
        endOperation();

        printMetrics();

    }

}
