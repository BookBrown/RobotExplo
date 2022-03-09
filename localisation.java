package robot;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;

public class localisation {

	
	
	public static float detecte(SensorModes sensor) {
		
		// get an instance of this sensor in measurement mode
		SampleProvider distance= sensor.getMode("Distance");
		
		float[] sample = new float[1];
		distance.fetchSample(sample, 0);
		LCD.clear(2);
		LCD.drawString(" distance: "+sample[0], 0, 4);
		return sample[0];
		
		
	}
	

}