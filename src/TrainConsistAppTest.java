public class TrainConsistAppTest {

    public static void main(String[] args) {
        testSearch_BogieFound();
        testSearch_BogieNotFound();
        testSearch_FirstElementMatch();
        testSearch_LastElementMatch();
        testSearch_SingleElementArray();

        System.out.println("All UC18 tests passed.");
    }

    private static void testSearch_BogieFound() {
        String[] arr = {"BG101","BG205","BG309","BG412","BG550"};
        boolean result = TrainConsistApp.linearSearch(arr, "BG309");
        assertTrue(result);
    }

    private static void testSearch_BogieNotFound() {
        String[] arr = {"BG101","BG205","BG309","BG412","BG550"};
        boolean result = TrainConsistApp.linearSearch(arr, "BG999");
        assertFalse(result);
    }

    private static void testSearch_FirstElementMatch() {
        String[] arr = {"BG101","BG205","BG309","BG412","BG550"};
        boolean result = TrainConsistApp.linearSearch(arr, "BG101");
        assertTrue(result);
    }

    private static void testSearch_LastElementMatch() {
        String[] arr = {"BG101","BG205","BG309","BG412","BG550"};
        boolean result = TrainConsistApp.linearSearch(arr, "BG550");
        assertTrue(result);
    }

    private static void testSearch_SingleElementArray() {
        String[] arr = {"BG101"};
        boolean result = TrainConsistApp.linearSearch(arr, "BG101");
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
}