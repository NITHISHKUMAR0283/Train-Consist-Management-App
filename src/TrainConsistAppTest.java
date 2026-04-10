public class TrainConsistAppTest {

    public static void main(String[] args) {
        testSearch_ThrowsExceptionWhenEmpty();
        testSearch_AllowsSearchWhenDataExists();
        testSearch_BogieFoundAfterValidation();
        testSearch_BogieNotFoundAfterValidation();
        testSearch_SingleElementValidCase();

        System.out.println("All UC20 tests passed.");
    }

    private static void testSearch_ThrowsExceptionWhenEmpty() {
        try {
            String[] arr = {};
            TrainConsistApp.binarySearch(arr, "BG101");
            throw new AssertionError("Expected IllegalStateException");
        } catch (IllegalStateException e) {
            assertContains(e.getMessage(), "No bogies available", "Exception message mismatch");
        }
    }

    private static void testSearch_AllowsSearchWhenDataExists() {
        String[] arr = {"BG101","BG205"};
        TrainConsistApp.binarySearch(arr, "BG101");
    }

    private static void testSearch_BogieFoundAfterValidation() {
        String[] arr = {"BG101","BG205","BG309"};
        boolean result = TrainConsistApp.binarySearch(arr, "BG205");
        assertTrue(result);
    }

    private static void testSearch_BogieNotFoundAfterValidation() {
        String[] arr = {"BG101","BG205","BG309"};
        boolean result = TrainConsistApp.binarySearch(arr, "BG999");
        assertFalse(result);
    }

    private static void testSearch_SingleElementValidCase() {
        String[] arr = {"BG101"};
        boolean result = TrainConsistApp.binarySearch(arr, "BG101");
        assertTrue(result);
    }

    private static void assertTrue(boolean condition) {
        if (!condition) {
            throw new AssertionError("Expected true but got false");
        }
    }

    private static void assertFalse(boolean condition) {
        if (condition) {
            throw new AssertionError("Expected false but got true");
        }
    }

    private static void assertContains(String text, String expected, String msg) {
        if (!text.contains(expected)) {
            throw new AssertionError(msg + "\nActual: " + text);
        }
    }
}