class SmartHomeController implements DeviceListener {
    @Override
    public void update(String message) {
        System.out.println("SmartHomeController received update: " + message);
    }
}
