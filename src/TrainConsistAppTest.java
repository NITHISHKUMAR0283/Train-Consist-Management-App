import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TrainConsistAppTest {

    public static void main(String[] args) {
        testMainPrintsOnlyBogiesAboveSixtyCapacity();
        testMainPrintsCapacityLabelInSeatsFormat();
        System.out.println("All uc8 tests passed.");
    }

    private static void testMainPrintsOnlyBogiesAboveSixtyCapacity() {
        String output = runMainAndCaptureOutput();

        assertContains(output, "Filtered bogies (capacity > 60):", "Heading should be printed");
        assertContains(output, "Sleeper -> 72 seats", "Sleeper bogie should be printed");
        assertNotContains(output, "AC Chair -> 60 seats", "Bogies with capacity 60 should be excluded");
        assertNotContains(output, "First Class -> 48 seats", "Bogies below 60 should be excluded");
    }

    private static void testMainPrintsCapacityLabelInSeatsFormat() {
        String output = runMainAndCaptureOutput();

        assertContains(output, " -> 72 seats", "Capacity lines should use the seats suffix");
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

    private static void assertNotContains(String text, String forbiddenFragment, String message) {
        if (text.contains(forbiddenFragment)) {
            throw new AssertionError(message + " Unexpected fragment: " + forbiddenFragment + "\nActual output:\n" + text);
        }
    }
}
