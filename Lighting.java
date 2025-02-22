import java.text.SimpleDateFormat;
import java.util.*;

class Lighting implements Device {
    private List<DeviceListener> listeners = new ArrayList<>();
    private EnergyPredictionStrategy predictionStrategy;
    private String scheduledTime;
    private String scheduledAction;
    private Timer timer;

    @Override
    public void turnOn() {
        System.out.println("Lighting is turned on.");
        notifyListeners("Lighting turned on.");
    }

    @Override
    public void turnOff() {
        System.out.println("Lighting is turned off.");
        notifyListeners("Lighting turned off.");
    }

    @Override
    public String getStatus() {
        return "Lighting status: Active";
    }

    @Override
    public void scheduleAction(String action, String time) {
        scheduledTime = time;
        scheduledAction = action;
        System.out.println("Lighting action '" + action + "' scheduled at " + time);
        scheduleTimer();
    }

    private void scheduleTimer() {
        if (timer != null) {
            timer.cancel();  // Cancel any previous timer if it exists
        }
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                String currentTime = new SimpleDateFormat("hh:mm a").format(new Date());
                if (currentTime.equals(scheduledTime)) {
                    executeScheduledAction();
                    timer.cancel();  // Stop the timer after the scheduled action
                }
            }
        }, 0, 60000);  // Check every minute
    }

    private void executeScheduledAction() {
        if (scheduledAction.equalsIgnoreCase("Turn Off")) {
            turnOff();
        } else if (scheduledAction.equalsIgnoreCase("Turn On")) {
            turnOn();
        }
        scheduledAction = null;  // Clear the scheduled action after execution
        scheduledTime = null;
    }

    @Override
    public void setEnergyPredictionStrategy(EnergyPredictionStrategy strategy) {
        this.predictionStrategy = strategy;
        System.out.println("Lighting prediction strategy set.");
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
