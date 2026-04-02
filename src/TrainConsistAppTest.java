import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TrainConsistAppTest {

    public static void main(String[] args) {
        testMainAcceptsValidTrainAndCargoCodes();
        testMainRejectsInvalidTrainAndCargoCodes();
        System.out.println("All uc11 tests passed.");
    }

    private static void testMainAcceptsValidTrainAndCargoCodes() {
        String output = runMainAndCaptureOutput("TRN-1234\nPET-AB\n");

        assertContains(output, "Train ID is valid", "A valid train ID should be accepted");
        assertContains(output, "Cargo Code is valid", "A valid cargo code should be accepted");
    }

    private static void testMainRejectsInvalidTrainAndCargoCodes() {
        String output = runMainAndCaptureOutput("TRAIN-12\nPET-abc\n");

        assertContains(output, "Train ID is invalid", "An invalid train ID should be rejected");
        assertContains(output, "Cargo Code is invalid", "An invalid cargo code should be rejected");
    }

    private static String runMainAndCaptureOutput(String input) {
        PrintStream originalOut = System.out;
        java.io.InputStream originalIn = System.in;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            System.setOut(new PrintStream(outputStream));
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            TrainConsistApp.main(new String[0]);
        } finally {
            System.setOut(originalOut);
            System.setIn(originalIn);
        }
        return outputStream.toString();
    }

    private static void assertContains(String text, String expectedFragment, String message) {
        if (!text.contains(expectedFragment)) {
            throw new AssertionError(message + " Missing fragment: " + expectedFragment + "\nActual output:\n" + text);
        }
    }
}
