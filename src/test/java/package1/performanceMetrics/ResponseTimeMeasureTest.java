package package1.performanceMetrics;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

class ResponseTimeMeasureTest {

    @Test
    void testElapsedTimeWhenNotStarted(){
        ResponseTimeMeasure measure = new ResponseTimeMeasure();
        assertThat(measure.getElapsedTime()).isEqualTo(-1);
    }

    @Test
    void testElapsedTimeWhenStartedButNotEnded(){
        ResponseTimeMeasure measure = new ResponseTimeMeasure();
        measure.startOperation();
        assertThat(measure.getElapsedTime()).isEqualTo(-1);
    }

    @Test
    void testElapsedTimeWhenMeasurementCompleted(){
        ResponseTimeMeasure measure = new ResponseTimeMeasure();
        measure.startOperation();
        //delay simulation
        try{
            Thread.sleep(1000);
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }
        measure.endOperation();
        assertThat(measure.getElapsedTime()).isGreaterThanOrEqualTo(1000);
    }

    @Test
    void testStartAndEndTime(){
        ResponseTimeMeasure measure = new ResponseTimeMeasure();
        Instant startTime = Instant.now();
        measure.startOperation();
        Instant endTime = Instant.now();
        measure.endOperation();
        assertThat(measure.getStartTime()).isEqualTo(startTime);
        assertThat(measure.getEndTime()).isEqualTo(endTime);
    }

    @Test
    void testPrintMetrics(){
        ResponseTimeMeasure measure = new ResponseTimeMeasure();
        measure.startOperation();
        //delay simulation
        try{
            Thread.sleep(1000);
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }
        measure.endOperation();

        // Redirect System.out for testing
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        measure.printMetrics();

        String consoleOutput = outContent.toString();
        assertThat(consoleOutput).contains("Start Time:");
        assertThat(consoleOutput).contains("End Time:");
        assertThat(consoleOutput).contains("Elapsed Time:");
    }
}