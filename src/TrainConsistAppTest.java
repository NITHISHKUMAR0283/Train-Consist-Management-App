import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TrainConsistAppTest {

    public static void main(String[] args) {
        testMainPrintsCapacityExceptionMessage();
        testBogieConstructorRejectsNonPositiveCapacity();
        System.out.println("All uc14 tests passed.");
    }

    private static void testMainPrintsCapacityExceptionMessage() {
        String output = runMainAndCaptureOutput();

        assertContains(output, "Capacity must be greater than zero", "The exception message should be printed");
        assertNotContains(output, "First Class -> -10", "Invalid bogie output should not be printed after the exception");
    }

    private static void testBogieConstructorRejectsNonPositiveCapacity() {
        assertThrowsInvalidCapacity(() -> new Bogie("Coach", 0));
        assertThrowsInvalidCapacity(() -> new Bogie("Coach", -5));
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

    private static void assertThrowsInvalidCapacity(BogieSupplier supplier) {
        try {
            supplier.create();
            throw new AssertionError("Expected InvalidCapacityException to be thrown");
        } catch (InvalidCapacityException e) {
            assertContains(e.getMessage(), "Capacity must be greater than zero", "Exception message should be preserved");
        }
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

    private interface BogieSupplier {
        Bogie create() throws InvalidCapacityException;
    }
}
