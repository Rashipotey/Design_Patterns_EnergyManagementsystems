interface Device {
    void turnOn();
    void turnOff();
    String getStatus();
    void scheduleAction(String action, String time);
    void setEnergyPredictionStrategy(EnergyPredictionStrategy strategy);
    void addListener(DeviceListener listener);
    void notifyListeners(String message);
}
