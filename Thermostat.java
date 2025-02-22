import java.text.SimpleDateFormat;
import java.util.*;
class Thermostat implements Device {
    private List<DeviceListener> listeners = new ArrayList<>();
    private EnergyPredictionStrategy predictionStrategy;
    private String scheduledTurnOffTime;

    @Override
    public void turnOn() {
        System.out.println("Thermostat is turned on.");
        notifyListeners("Thermostat turned on.");
    }

    @Override
    public void turnOff() {
        System.out.println("Thermostat is turned off.");
        notifyListeners("Thermostat turned off.");
    }

    @Override
    public String getStatus() {
        return "Thermostat status: Active";
    }

    @Override
    public void scheduleAction(String action, String time) {
        if (action.equalsIgnoreCase("Turn Off")) {
            scheduledTurnOffTime = time;
            System.out.println("Thermostat turn-off scheduled at " + time);
            checkTurnOffTime();
        }
    }

    private void checkTurnOffTime() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                String currentTime = new SimpleDateFormat("hh:mm a").format(new Date());
                if (currentTime.equals(scheduledTurnOffTime)) {
                    turnOff();
                    timer.cancel();  // Stop the timer after turning off
                }
            }
        }, 0, 60000);  // Check every minute
    }

    @Override
    public void setEnergyPredictionStrategy(EnergyPredictionStrategy strategy) {
        this.predictionStrategy = strategy;
        System.out.println("Thermostat prediction strategy set.");
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