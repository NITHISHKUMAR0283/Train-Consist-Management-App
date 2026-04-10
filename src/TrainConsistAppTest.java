import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TrainConsistAppTest {

    public static void main(String[] args) throws Exception {
        testCargo_SafeAssignment();
        testCargo_UnsafeAssignmentHandled();
        testCargo_CargoNotAssignedAfterFailure();
        testCargo_ProgramContinuesAfterException();
        testCargo_FinallyBlockExecution();

        System.out.println("All UC15 tests passed.");
    }

    private static void testCargo_SafeAssignment() throws Exception {
        GoodsBogie g = new GoodsBogie("G1", 100, "Cylindrical");
        g.assignCargo("Petroleum");

        if (!"Petroleum".equals(g.cargo)) {
            throw new AssertionError("Safe cargo should be assigned");
        }
    }

    private static void testCargo_UnsafeAssignmentHandled() throws Exception {
        String output = capture(() -> {
            try {
                GoodsBogie g = new GoodsBogie("G2", 100, "Rectangular");
                g.assignCargo("Petroleum");
            } catch (Exception e) {}
        });

        assertContains(output, "Unsafe cargo", "Exception should be handled");
    }

    private static void testCargo_CargoNotAssignedAfterFailure() throws Exception {
        GoodsBogie g = new GoodsBogie("G3", 100, "Rectangular");
        g.assignCargo("Petroleum");

        if (g.cargo != null) {
            throw new AssertionError("Cargo should not be assigned on failure");
        }
    }

    private static void testCargo_ProgramContinuesAfterException() throws Exception {
        GoodsBogie g = new GoodsBogie("G4", 100, "Rectangular");

        g.assignCargo("Petroleum");
        g.assignCargo("Grain");

        if (!"Grain".equals(g.cargo)) {
            throw new AssertionError("Program should continue after exception");
        }
    }

    private static void testCargo_FinallyBlockExecution() throws Exception {
        String output = capture(() -> {
            try {
                GoodsBogie g = new GoodsBogie("G5", 100, "Rectangular");
                g.assignCargo("Petroleum");
            } catch (Exception e) {}
        });

        assertContains(output, "Cargo assignment attempt completed", "Finally block must execute");
    }

    private static String capture(Runnable r) {
        PrintStream original = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        try {
            r.run();
        } finally {
            System.setOut(original);
        }
        return out.toString();
    }
    

    private static void assertContains(String text, String expected, String msg) {
        if (!text.contains(expected)) {
            throw new AssertionError(msg + "\nOutput:\n" + text);
        }
    }
}