import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TrainConsistAppTest {

    public static void main(String[] args) {
        testMainPrintsMatchingResultSizes();
        testMainPrintsTimingMetrics();
        System.out.println("All uc13 tests passed.");
    }

    private static void testMainPrintsMatchingResultSizes() {
        String output = runMainAndCaptureOutput();

        assertContains(output, "Loop result size: 100000", "The loop should keep all sleeper bogies");
        assertContains(output, "Stream result size: 100000", "The stream should keep all sleeper bogies");
    }

    private static void testMainPrintsTimingMetrics() {
        String output = runMainAndCaptureOutput();

        assertContains(output, "Loop time (ns):", "Loop timing should be reported");
        assertContains(output, "Stream time (ns):", "Stream timing should be reported");
    }

    private static String runMainAndCaptureOutput() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            System.setOut(new PrintStream(outputStream));
            TrainConsistApp.main(new String[0]);
        } finally {
            System.setOut(originalOut);
        }
        return outputStream.toString();
    }

    private static void assertContains(String text, String expectedFragment, String message) {
        if (!text.contains(expectedFragment)) {
            throw new AssertionError(message + " Missing fragment: " + expectedFragment + "\nActual output:\n" + text);
        }
    }
}
