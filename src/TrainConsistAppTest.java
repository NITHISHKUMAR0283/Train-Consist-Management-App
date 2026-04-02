import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TrainConsistAppTest {

    public static void main(String[] args) {
        testMainPrintsGroupedBogiesHeader();
        testMainGroupsBogiesByNameAndPreservesAllCapacities();
        System.out.println("All uc9 tests passed.");
    }

    private static void testMainPrintsGroupedBogiesHeader() {
        String output = runMainAndCaptureOutput();

        assertContains(output, "Grouped bogies by type:", "Heading should be printed");
        assertContains(output, "Sleeper:", "Sleeper group should be printed");
        assertContains(output, "AC Chair:", "AC Chair group should be printed");
        assertContains(output, "First Class:", "First Class group should be printed");
    }

    private static void testMainGroupsBogiesByNameAndPreservesAllCapacities() {
        String output = runMainAndCaptureOutput();

        assertContains(output, "  72 seats", "Sleeper capacity 72 should be included");
        assertContains(output, "  70 seats", "Second Sleeper capacity should be included");
        assertContains(output, "  60 seats", "AC Chair capacity 60 should be included");
        assertContains(output, "  55 seats", "Second AC Chair capacity should be included");
        assertContains(output, "  48 seats", "First Class capacity should be included");

        assertEquals(1, countOccurrences(output, "Sleeper:"), "Sleeper should appear once as a group header");
        assertEquals(1, countOccurrences(output, "AC Chair:"), "AC Chair should appear once as a group header");
        assertEquals(1, countOccurrences(output, "First Class:"), "First Class should appear once as a group header");
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

    private static void assertEquals(int expected, int actual, String message) {
        if (expected != actual) {
            throw new AssertionError(message + " Expected: " + expected + ", Actual: " + actual);
        }
    }

    private static int countOccurrences(String text, String fragment) {
        int count = 0;
        int index = 0;
        while ((index = text.indexOf(fragment, index)) != -1) {
            count++;
            index += fragment.length();
        }
        return count;
    }
}
