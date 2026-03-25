import java.util.ArrayList;
import java.util.List;

public class TrainConsistApp {
    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App ===");

        List<String> trainConsist = new ArrayList<>();

        trainConsist.add("Sleeper");
        trainConsist.add("AC Chair");
        trainConsist.add("First Class");

        System.out.println("Passenger bogies after addition: " + trainConsist);

        trainConsist.remove("AC Chair");

        boolean hasSleeper = trainConsist.contains("Sleeper");
        System.out.println("Does the train have Sleeper bogie? " + hasSleeper);

        System.out.println("Final train consist: " + trainConsist);
    }
}