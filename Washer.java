import java.util.*;
class Washer implements Device {
    private List<DeviceListener> listeners = new ArrayList<>();
    private EnergyPredictionStrategy predictionStrategy;

    @Override
    public void turnOn() {
        System.out.println("Washer is turned on.");
        notifyListeners("Washer turned on.");
    }

    @Override
    public void turnOff() {
        System.out.println("Washer is turned off.");
        notifyListeners("Washer turned off.");
    }
    @Override
    public String getStatus() {
        return "Washer status: Active";
    }

    @Override
    public void scheduleAction(String action, String time) {
        System.out.println("Washer " + action + " scheduled at " + time);
        notifyListeners("Washer " + action + " scheduled at " + time);
    }

    @Override
    public void setEnergyPredictionStrategy(EnergyPredictionStrategy strategy) {
        this.predictionStrategy = strategy;
        System.out.println("Washer prediction strategy set.");
        predictionStrategy.predictEnergyUsage();
    }

    @Override
    public void addListener(DeviceListener listener) {
        listeners.add(listener);
    }
    @Override
    public void notifyListeners(String message) {
        for (DeviceListener listener : listeners) {
            listener.update(message);
        }
    }
}
