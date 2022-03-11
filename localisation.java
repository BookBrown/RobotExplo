package robot;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class localisation {
	
	public static float detecte(SampleProvider distance) {
		float[] sample = new float[distance.sampleSize()];
		distance.fetchSample(sample, 0);
		Delay.msDelay(100);
		if (sample.length == 0){
			return -1;
		}
		
		if(sample[0]>0.8 || sample[0] == 0){
				return -1;
		} else {
			return sample[0];
		}
	}
}
