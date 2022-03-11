package robot;

import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;
import lejos.hardware.port.SensorPort;


public class PinceMain {
	public static void main(String[] args) {
		
		
		// get a port instance
		//Port port = LocalEV3.get().getPort("S4");
		
		// Get an instance of the Ultrasonic EV3 sensor
		EV3UltrasonicSensor sensor = new EV3UltrasonicSensor(SensorPort.S3);
		
		sensor.enable(); 	
				
		// get an instance of this sensor in measurement mode
		SampleProvider distanceSample = sensor.getDistanceMode();
		
		float distance = localisation.detecte(distanceSample);
		
		System.out.println(distance);
		
		Delay.msDelay(4000);
		//initialise the motors
		Mouvement motors = new Mouvement();
		
		//on fait maintenant la phase d'approche (on suppose avoir repéré le robot).
		
		approche.approcheTarget(distanceSample, motors);
		
		System.out.println("on est sortis de approcheTarget");
		
		sensor.disable();
				
		sensor.close();

	}
}
