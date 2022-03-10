package robot;

import java.time.Duration;
import java.time.Instant;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;

public class BordersMain {
	public static void main(String[] args) {
		
		EV3ColorSensor Csensor = new EV3ColorSensor(SensorPort.S3);
		EV3UltrasonicSensor sensor = new EV3UltrasonicSensor(SensorPort.S4);
		
		SampleProvider USsensor= sensor.getDistanceMode();
		
		Borders bord = new Borders();
		float distance = bord.Init(Csensor, sensor);
		distance = bord.parcours(Csensor, sensor);
		
		System.out.println("Un objet se trouve à :" + distance);
		
		}
	}