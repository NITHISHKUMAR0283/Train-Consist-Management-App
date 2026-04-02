import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TrainConsistAppTest {

    public static void main(String[] args) {
        testMainPrintsTotalSeatingCapacity();
        testMainUsesAllBogiesForTotalCapacity();
        System.out.println("All uc10 tests passed.");
    }

    private static void testMainPrintsTotalSeatingCapacity() {
        String output = runMainAndCaptureOutput();

        assertContains(output, "Total seating capacity: 180", "The summed seating capacity should be printed");
    }

    private static void testMainUsesAllBogiesForTotalCapacity() {
        String output = runMainAndCaptureOutput();

        assertContains(output, "Total seating capacity: 180", "The total should include all three bogies");
>>>>>>> feature/uc10
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
