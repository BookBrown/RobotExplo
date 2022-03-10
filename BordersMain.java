package robot;

import java.time.Duration;
import java.time.Instant;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;

public class BordersMain {
	public static void main(String[] args) {
		
		EV3ColorSensor Csensor = new EV3ColorSensor(SensorPort.S1);
		EV3UltrasonicSensor USsensor = new EV3UltrasonicSensor(SensorPort.S2);
		
		Borders bord = new Borders();
		float distance = bord.Init(Csensor, USsensor);
		distance = bord.parcours(Csensor, USsensor);
		
		System.out.println("Un objet se trouve à :" + distance);
		
		}
	}