package robot;
import lejos.hardware.Battery;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;


public class PinceMain {
	public static void main(String[] args) {
		
		//Pince pince = new Pince();
		
		//Delay.msDelay(2000);
		
		//pince.premiere_recup();
		
		//pince.deuxieme_recup();
		
		//pince.largage();
		
		// get a port instance
		Port port = LocalEV3.get().getPort("S2");

		// Get an instance of the Ultrasonic EV3 sensor
		EV3UltrasonicSensor sensor = new EV3UltrasonicSensor(port);
		
		//initialise the motors
		Mouvement motors = new Mouvement();
		
		//on fait maintenant la phase d'approche (on suppose avoir repéré le robot).
		
		
		
		approche.approcheTarget(sensor, motors);
		
		
		sensor.close();

	}
}
