import java.util.LinkedList;

public class TrainConsistApp {
    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App ===");

        LinkedList<String> consist = new LinkedList<>();

        consist.add("Engine");
        consist.add("Sleeper");
        consist.add("AC");
        consist.add("Cargo");
        consist.add("Guard");

        consist.add(2, "Pantry Car");  // Insert at position 2

        consist.removeFirst();  // Remove Engine
        consist.removeLast();   // Remove Guard

        System.out.println("Final ordered train consist: " + consist);
    }
}