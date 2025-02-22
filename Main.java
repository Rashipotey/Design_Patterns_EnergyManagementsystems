import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter device type (thermostat, lighting, washer): ");
        String deviceType = scanner.nextLine();
        Device device = DeviceFactory.createDevice(deviceType);

        SmartHomeController controller = new SmartHomeController();
        device.addListener(controller);

        System.out.println("Turn on device? (yes/no): ");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            device.turnOn();
        }

        System.out.println("Device status: " + device.getStatus());

        System.out.println("Choose prediction strategy (1: Time-Based, 2: Weather-Based, 3: Behavior-Based): ");
        int strategyChoice = scanner.nextInt();
        scanner.nextLine();  
        EnergyPredictionStrategy strategy;

        switch (strategyChoice) {
            case 1 -> strategy = new TimeBasedPrediction();
            case 2 -> strategy = new WeatherBasedPrediction();
            case 3 -> strategy = new BehaviorBasedPrediction();
            default -> throw new IllegalArgumentException("Invalid strategy choice");
        }

        device.setEnergyPredictionStrategy(strategy);

        System.out.println("Would you like to schedule an action? (yes/no): ");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            System.out.println("Enter action (Turn On, Turn Off): ");
            String action = scanner.nextLine();
            System.out.println("Enter time (e.g., 10:00 AM): ");
            String time = scanner.nextLine();
            device.scheduleAction(action, time);
        }

        System.out.println("Turn off device? (yes/no): ");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            device.turnOff();
        }

        scanner.close();
    }
}