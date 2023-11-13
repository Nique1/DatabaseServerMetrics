package package1.efficencyMetrics;


import java.sql.ResultSet;
import java.sql.Timestamp;

public class ResponseTimeMeasure {
    private Timestamp startTime;
    private Timestamp endTime;

    public void startOperation() {
        startTime = new Timestamp(System.nanoTime());
    }

    public void endOperation() {
        endTime = new Timestamp(System.nanoTime());
    }

    public long getElapsedTime() {
        if (startTime != null && endTime != null) {
            return endTime.getTime() - startTime.getTime();
        }
        return -1; // Indicate that the operation hasn't started or ended
    }

    public void printMetrics() {
        System.out.println("\nDatabase Operation Metrics:");
        System.out.println("Start Time: " + startTime);
        System.out.println("End Time: " + endTime);
        System.out.println("Elapsed Time: " + getElapsedTime() + " milliseconds");
    }



    //pomyslec jakby mozna zapisac to do jakiejs tablicy moze ?
    
}
