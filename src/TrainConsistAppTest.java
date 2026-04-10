import java.util.Arrays;

public class TrainConsistAppTest {

    public static void main(String[] args) {
        testSort_BasicSorting();
        testSort_AlreadySortedArray();
        testSort_DuplicateValues();
        testSort_SingleElementArray();
        testSort_AllEqualValues();

        System.out.println("All UC16 tests passed.");
    }

    private static void testSort_BasicSorting() {
        int[] arr = {72, 56, 24, 70, 60};
        TrainConsistApp.bubbleSort(arr);
        assertArrayEquals(arr, new int[]{24, 56, 60, 70, 72});
    }

    private static void testSort_AlreadySortedArray() {
        int[] arr = {24, 56, 60, 70, 72};
        TrainConsistApp.bubbleSort(arr);
        assertArrayEquals(arr, new int[]{24, 56, 60, 70, 72});
    }

    private static void testSort_DuplicateValues() {
        int[] arr = {72, 56, 56, 24};
        TrainConsistApp.bubbleSort(arr);
        assertArrayEquals(arr, new int[]{24, 56, 56, 72});
    }

    private static void testSort_SingleElementArray() {
        int[] arr = {50};
        TrainConsistApp.bubbleSort(arr);
        assertArrayEquals(arr, new int[]{50});
    }

    private static void testSort_AllEqualValues() {
        int[] arr = {40, 40, 40};
        TrainConsistApp.bubbleSort(arr);
        assertArrayEquals(arr, new int[]{40, 40, 40});
    }

    private static void assertArrayEquals(int[] actual, int[] expected) {
        if (!Arrays.equals(actual, expected)) {
            throw new AssertionError("Expected: " + Arrays.toString(expected) +
                                     " but got: " + Arrays.toString(actual));
        }
    }
}