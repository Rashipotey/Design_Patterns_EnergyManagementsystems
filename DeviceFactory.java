class DeviceFactory {
    public static Device createDevice(String deviceType) {
        if (deviceType.equalsIgnoreCase("thermostat")) {
            return new Thermostat();
        } else if (deviceType.equalsIgnoreCase("lighting")) {
            return new Lighting();
        } else if (deviceType.equalsIgnoreCase("washer")) {
            return new Washer();
        } else {
            throw new IllegalArgumentException("Unknown device type: " + deviceType);
        }
    }
}
