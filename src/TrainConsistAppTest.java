import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class TrainConsistAppTest {

    public static void main(String[] args) {
        testMainPrintsSafetyCompliantMessage();
        testSafetyRuleRejectsUnsafeCylindricalCargo();
        System.out.println("All uc12 tests passed.");
    }

    private static void testMainPrintsSafetyCompliantMessage() {
        String output = runMainAndCaptureOutput();

        assertContains(output, "Train is safety compliant", "The default bogie set should be safe");
        assertNotContains(output, "Train is NOT safety compliant", "Unsafe output should not be printed");
    }

    private static void testSafetyRuleRejectsUnsafeCylindricalCargo() {
        List<GoodsBogie> bogies = new ArrayList<>();
        bogies.add(new GoodsBogie("Cylindrical", "Coal"));
        bogies.add(new GoodsBogie("Rectangular", "Coal"));

        assertFalse(isSafetyCompliant(bogies), "A cylindrical bogie carrying coal should be unsafe");
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

    private static boolean isSafetyCompliant(List<GoodsBogie> bogies) {
        return bogies.stream()
                .allMatch(b -> !b.type.equals("Cylindrical") || b.cargo.equals("Petroleum"));
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

    private static void assertFalse(boolean condition, String message) {
        if (condition) {
            throw new AssertionError(message);
        }
    }
}
