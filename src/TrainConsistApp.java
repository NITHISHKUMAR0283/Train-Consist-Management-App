import java.util.*;

class InvalidCapacityException extends Exception {
    InvalidCapacityException(String message) {
        super(message);
    }
}

class CargoSafetyException extends RuntimeException {
    CargoSafetyException(String message) {
        super(message);
    }
}

class Bogie {
    String name;
    int capacity;

    Bogie(String name, int capacity) throws InvalidCapacityException {
        if (capacity <= 0) {
            throw new InvalidCapacityException("Capacity must be greater than zero");
        }
        this.name = name;
        this.capacity = capacity;
    }
}

class GoodsBogie extends Bogie {
    String shape;
    String cargo;

    GoodsBogie(String name, int capacity, String shape) throws InvalidCapacityException {
        super(name, capacity);
        this.shape = shape;
    }

    void assignCargo(String cargoType) {
        try {
            if (shape.equalsIgnoreCase("Rectangular") &&
                cargoType.equalsIgnoreCase("Petroleum")) {
                throw new CargoSafetyException("Unsafe cargo! Petroleum cannot be stored in Rectangular bogie");
            }
            this.cargo = cargoType;
            System.out.println("Cargo assigned successfully: " + cargoType);
        } catch (CargoSafetyException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Cargo assignment attempt completed for " + name);
        }
    }
}

public class TrainConsistApp {

    static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    static void sortBogieNames(String[] arr) {
        Arrays.sort(arr);
    }

    static boolean linearSearch(String[] arr, String key) {
        for (String id : arr) {
            if (id.equals(key)) {
                return true;
            }
        }
        return false;
    }

    static boolean binarySearch(String[] arr, String key) {
        Arrays.sort(arr);
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = arr[mid].compareTo(key);

            if (cmp == 0) {
                return true;
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App ===");

        try {
            Bogie b1 = new Bogie("Sleeper", 72);
            Bogie b2 = new Bogie("AC Chair", 60);

            System.out.println(b1.name + " -> " + b1.capacity);
            System.out.println(b2.name + " -> " + b2.capacity);

            GoodsBogie g1 = new GoodsBogie("Goods-1", 100, "Cylindrical");
            GoodsBogie g2 = new GoodsBogie("Goods-2", 120, "Rectangular");

            g1.assignCargo("Petroleum");
            g2.assignCargo("Petroleum");
            g2.assignCargo("Grain");

            int[] capacities = {72, 56, 24, 70, 60};
            bubbleSort(capacities);

            String[] bogieNames = {"Sleeper","AC Chair","First Class","General","Luxury"};
            sortBogieNames(bogieNames);

            String[] bogieIds = {"BG101","BG205","BG309","BG412","BG550"};
            String searchKey = "BG309";

            boolean result = binarySearch(bogieIds, searchKey);
            System.out.println("Binary Search Result for " + searchKey + ": " + result);

        } catch (InvalidCapacityException e) {
            System.out.println(e.getMessage());
        }
    }
}