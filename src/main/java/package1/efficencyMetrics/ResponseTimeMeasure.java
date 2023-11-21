package package1.efficencyMetrics;


import java.sql.Time;
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
        return -1;
    }
    public Timestamp getStartTime(){
        return startTime;
    }
    public Timestamp getEndTime(){
        return endTime;
    }







    public void printMetrics() {
        System.out.println("\nDatabase Operation Metrics:");
        System.out.println("Start Time: " + startTime);
        System.out.println("End Time: " + endTime);
        System.out.println("Elapsed Time: " + getElapsedTime() + " milliseconds");
        System.out.println();
    }


}
