import java.util.Arrays;

public class TrainConsistAppTest {

    public static void main(String[] args) {
        testSort_BasicAlphabeticalSorting();
        testSort_UnsortedInput();
        testSort_AlreadySortedArray();
        testSort_DuplicateBogieNames();
        testSort_SingleElementArray();

        System.out.println("All UC17 tests passed.");
    }

    private static void testSort_BasicAlphabeticalSorting() {
        String[] arr = {"Sleeper","AC Chair","First Class","General","Luxury"};
        TrainConsistApp.sortBogieNames(arr);
        assertArrayEquals(arr, new String[]{"AC Chair","First Class","General","Luxury","Sleeper"});
    }

    private static void testSort_UnsortedInput() {
        String[] arr = {"Luxury","General","Sleeper","AC Chair"};
        TrainConsistApp.sortBogieNames(arr);
        assertArrayEquals(arr, new String[]{"AC Chair","General","Luxury","Sleeper"});
    }

    private static void testSort_AlreadySortedArray() {
        String[] arr = {"AC Chair","First Class","General"};
        TrainConsistApp.sortBogieNames(arr);
        assertArrayEquals(arr, new String[]{"AC Chair","First Class","General"});
    }

    private static void testSort_DuplicateBogieNames() {
        String[] arr = {"Sleeper","AC Chair","Sleeper","General"};
        TrainConsistApp.sortBogieNames(arr);
        assertArrayEquals(arr, new String[]{"AC Chair","General","Sleeper","Sleeper"});
    }

    private static void testSort_SingleElementArray() {
        String[] arr = {"Sleeper"};
        TrainConsistApp.sortBogieNames(arr);
        assertArrayEquals(arr, new String[]{"Sleeper"});
    }

    private static void assertArrayEquals(String[] actual, String[] expected) {
        if (!Arrays.equals(actual, expected)) {
            throw new AssertionError("Expected: " + Arrays.toString(expected) +
                                     " but got: " + Arrays.toString(actual));
        }
    }
}