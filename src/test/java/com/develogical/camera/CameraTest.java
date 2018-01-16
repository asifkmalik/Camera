package com.develogical.camera;

import org.junit.Test;
import org.mockito.ArgumentCaptor;


import static org.mockito.Mockito.*;

public class CameraTest {
    Sensor mockSensor = mock(Sensor.class);
    MemoryCard mockMemoryCard = mock(MemoryCard.class);
    Camera camera = new Camera(mockSensor, mockMemoryCard);

    @Test
    public void switchingTheCameraOnPowersUpTheSensor() {
        camera.powerOn();
        verify(mockSensor).powerUp();
    }

    @Test
    public void switchingTheCameraOffPowersDownTheSensor() {
        camera.powerOff();
        verify(mockSensor).powerDown();
    }

    @Test
    public void pressingTheShutterWhenPowerIsOffDoesNothing() {
        camera.powerOff();
        camera.pressShutter();
        verify(mockSensor).powerDown();
        verifyNoMoreInteractions(mockSensor);
    }

    @Test
    public void pressingTheShutterWithPowerOnCopiesDataFromSensorToMemoryCard() {
        byte[] mockData = "".getBytes();
        when(mockSensor.readData()).thenReturn(mockData);

        camera.powerOn();
        camera.pressShutter();

        verify(mockMemoryCard).write(eq(mockData), any());
    }

    //@Test
    public void dataCurrentlyBeingWrittenSwitchingTheCameraOffDoesNotPowerOffTheSensor() {

    }
}
