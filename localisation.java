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
		float[] sample = new float[1];
		distance.fetchSample(sample, 0);
		Delay.msDelay(100);
		if (sample[0]==Float.intBitsToFloat(0x7f800000)){
			return -1;
		}
		else{
			if(sample[0]==0){
				return -1;
			}
			return sample[0];
		}
		
	}
	

	
}
