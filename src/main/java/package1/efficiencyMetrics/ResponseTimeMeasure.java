package package1.efficiencyMetrics;


import java.time.Duration;
import java.time.Instant;

public class ResponseTimeMeasure {
    private Instant startTime;
    private Instant endTime;

    public void startOperation() {
        startTime = Instant.now();
    }

    public void endOperation() {
        endTime = Instant.now();
    }

    public long getElapsedTime() {
        if (startTime != null && endTime != null) {
            return Duration.between(startTime,endTime).toMillis();
        }
        return -1;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public Instant getEndTime() {
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
