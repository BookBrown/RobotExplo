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
		int index = sample.length - 1;
		while (!Float.isFinite(sample[index]) || sample[index] == 0){
			if(index == 0){
				return -1;
			}
			index--;
		}
		return sample[index];
	}
}
