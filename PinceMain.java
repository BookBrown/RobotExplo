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
import lejos.utility.Delay;


public class PinceMain {
	public static void main(String[] args) {
		
		Pince pince = new Pince();
		
		// get a port instance
		Port port = LocalEV3.get().getPort("S4");

		// Get an instance of the Ultrasonic EV3 sensor
		SensorModes sensor = new EV3UltrasonicSensor(port);
		
		float distance_float = localisation.detecte(sensor);	
		
		int distance_entiere = (int)(distance_float*100);
		
		pince.premiere_recup(distance_entiere);
		
		Delay.msDelay(5000);
		
		float distance_float_bis = localisation.detecte(sensor);	
		
		int distance_entiere_bis = (int)(distance_float_bis*100);
		
		pince.deuxieme_recup(distance_entiere_bis);
		
		Mouvement mouv = new Mouvement();
		
		mouv.avancer(20, -20);
		
		pince.largage();
		
		pince.butee();
		
		// get a port instance
		//Port port = LocalEV3.get().getPort("S2");

		// Get an instance of the Ultrasonic EV3 sensor
		//SensorModes sensor = new EV3UltrasonicSensor(port);
		
		//float distance = localisation.detecte(sensor);
		
		//System.out.println(distance);

	}
}