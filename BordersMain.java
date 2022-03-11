package robot;

import java.time.Duration;
import java.time.Instant;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.SampleProvider;

public class BordersMain {
	public static void main(String[] args) {
		
		EV3ColorSensor Colorsensor = new EV3ColorSensor(SensorPort.S1);
		SampleProvider Csensor = Colorsensor.getColorIDMode();
		
		EV3UltrasonicSensor sensor = new EV3UltrasonicSensor(SensorPort.S3);
		SampleProvider USsensor= sensor.getDistanceMode();
		
		Borders bord = new Borders();
		float distance = bord.Init(Csensor, USsensor);
		if (distance != -2){
			distance = bord.parcours(Csensor, USsensor);
		}
		
		System.out.println("Un objet se trouve à :" + distance);
		
		sensor.close();
		Colorsensor.close();
		
		}
	}
