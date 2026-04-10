public class TrainConsistAppTest {

    public static void main(String[] args) {
        testBinarySearch_BogieFound();
        testBinarySearch_BogieNotFound();
        testBinarySearch_FirstElementMatch();
        testBinarySearch_LastElementMatch();
        testBinarySearch_SingleElementArray();
        testBinarySearch_EmptyArray();
        testBinarySearch_UnsortedInputHandled();

        System.out.println("All UC19 tests passed.");
    }

    private static void testBinarySearch_BogieFound() {
        String[] arr = {"BG101","BG205","BG309","BG412","BG550"};
        assertTrue(TrainConsistApp.binarySearch(arr, "BG309"));
    }

    private static void testBinarySearch_BogieNotFound() {
        String[] arr = {"BG101","BG205","BG309","BG412","BG550"};
        assertFalse(TrainConsistApp.binarySearch(arr, "BG999"));
    }

    private static void testBinarySearch_FirstElementMatch() {
        String[] arr = {"BG101","BG205","BG309","BG412","BG550"};
        assertTrue(TrainConsistApp.binarySearch(arr, "BG101"));
    }

    private static void testBinarySearch_LastElementMatch() {
        String[] arr = {"BG101","BG205","BG309","BG412","BG550"};
        assertTrue(TrainConsistApp.binarySearch(arr, "BG550"));
    }

    private static void testBinarySearch_SingleElementArray() {
        String[] arr = {"BG101"};
        assertTrue(TrainConsistApp.binarySearch(arr, "BG101"));
    }

    private static void testBinarySearch_EmptyArray() {
        String[] arr = {};
        assertFalse(TrainConsistApp.binarySearch(arr, "BG101"));
    }

    private static void testBinarySearch_UnsortedInputHandled() {
        String[] arr = {"BG309","BG101","BG550","BG205","BG412"};
        assertTrue(TrainConsistApp.binarySearch(arr, "BG205"));
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