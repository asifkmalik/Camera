package com.develogical.camera;

public class Camera {

    private Sensor sensor;
    private MemoryCard memoryCard;
    boolean power;

    public Camera(Sensor sensor) {
        this.sensor = sensor;
    }

    public Camera(Sensor sensor, MemoryCard memoryCard) {
        this.sensor = sensor;
        this.memoryCard = memoryCard;
    }

    public void pressShutter() {
        if (power == true) {
            byte[] data = sensor.readData();
            this.memoryCard.write(data, null);
        }
    }

    public void powerOn() {
        power = true;
        this.sensor.powerUp();
    }

    public void powerOff() {
        power = false;
       this.sensor.powerDown();
    }
}

