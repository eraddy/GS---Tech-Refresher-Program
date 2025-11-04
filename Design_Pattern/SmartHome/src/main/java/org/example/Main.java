package org.example;

import org.example.command.*;
import org.example.controller.SmartHomeController;
import org.example.decorator.LoggingDecorator;
import org.example.decorator.VoiceControlDecorator;
import org.example.device.DeviceGroup;
import org.example.device.SmartDevice;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        SmartHomeController controller = SmartHomeController.getInstance();

        // Create devices via factories
        SmartDevice livingLight1 = controller.createAndRegister("BrandA", "light", "light-living-1", "Living Room Lamp");
        SmartDevice livingLight2 = controller.createAndRegister("BrandB", "light", "light-living-2", "Living Room Ceiling");
        SmartDevice hallTherm = controller.createAndRegister("BrandB", "thermostat", "therm-hall", "Hall Thermostat");
        SmartDevice frontDoor = controller.createAndRegister("BrandA", "doorlock", "door-front", "Front Door");
        SmartDevice cam = controller.createAndRegister("BrandB", "camera", "cam-porch", "Porch Cam");

        // Add decorators (optional features)
        SmartDevice loggedLivingLight1 = new LoggingDecorator(livingLight1);
        // replace registration so controller's registry references decorated device (optional)
        controller.registerDevice(loggedLivingLight1);

        SmartDevice voiceControlledCam = new VoiceControlDecorator(cam);
        controller.registerDevice(voiceControlledCam);

        // Group lights using Composite
        DeviceGroup livingRoomLights = new DeviceGroup("group-living", "Living Room Lights");
        livingRoomLights.add(loggedLivingLight1);
        livingRoomLights.add(livingLight2);
        controller.registerDevice(livingRoomLights);

        // Commands
        Command turnOnLivingLights = new TurnOnCommand(livingRoomLights);
        Command setHallTemp = new SetTemperatureCommand(hallTherm, 24.0);
        Command lockFront = new LockCommand(frontDoor);
        Command unlockFront = new UnlockCommand(frontDoor);
        Command camOn = new TurnOnCommand(voiceControlledCam);

        // Macro "Good Night": turn off lights, set thermostat, lock door, turn on camera
        MacroCommand goodNight = new MacroCommand(Arrays.asList(
                new TurnOffCommand(livingRoomLights),
                new SetTemperatureCommand(hallTherm, 18.0),
                lockFront,
                camOn
        ));

        // Execute commands via controller (Singleton)
        System.out.println("\n--- Executing single commands ---");
        controller.executeCommand(turnOnLivingLights);
        controller.executeCommand(setHallTemp);
        controller.executeCommand(unlockFront);

        System.out.println("\n--- Executing macro: Good Night ---");
        controller.executeCommand(goodNight);

        // List devices
        System.out.println("\n--- Registered devices ---");
        controller.listDevices().forEach(d -> System.out.println(d.getId() + " -> " + d.getName() + " (isOn=" + d.isOn() + ")"));

        // Example: set brightness if underlying adapter supports it (reflection)
        System.out.println("\n--- Attempt to set brightness on livingLight1 via adapter if supported ---");
        try {
            livingLight1.getClass().getMethod("setBrightness", int.class).invoke(livingLight1, 70);
        } catch (Exception e) {
            System.out.println("Brightness not supported on " + livingLight1.getName());
        }
    }
}

