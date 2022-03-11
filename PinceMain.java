package robot;
import lejos.hardware.Battery;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;
import lejos.hardware.port.SensorPort;


public class PinceMain {
	public static void main(String[] args) {
		
		Pince pince = new Pince();
		
		// get a port instance
		Port port = LocalEV3.get().getPort("S3");

		// Get an instance of the Ultrasonic EV3 sensor
		
		Mouvement mouv = new Mouvement();
		
		EV3UltrasonicSensor sensor = new EV3UltrasonicSensor(SensorPort.S3);
		
		SampleProvider distanceSample = sensor.getDistanceMode();
		
		float distance = localisation.detecte(distanceSample);
		
		boolean zone_de_recup = Approche.approcheTarget(distanceSample, mouv);
		
		if (zone_de_recup) {
			sensor.close();
			return;
		}
		
		float distance_float = localisation.detecte(sensor);
		
		int distance_entiere = (int)(distance_float*100);
		
		pince.premiere_recup(distance_entiere);
		
		Delay.msDelay(5000);
		
		mouv.avancer(20, -20);
		
		pince.largage();
		
		Delay.msDelay(5000);
		
		pince.butee();
		
		sensor.close();
		
		// get a port instance
		//Port port = LocalEV3.get().getPort("S2");

		// Get an instance of the Ultrasonic EV3 sensor
		//SensorModes sensor = new EV3UltrasonicSensor(port);
		
		//float distance = localisation.detecte(sensor);
		
		//System.out.println(distance);

	}
}