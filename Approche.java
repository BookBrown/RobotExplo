package robot;

import lejos.hardware.motor.Motor;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;

public class approcheTo {

	
	public static boolean approcheTarget(SampleProvider sample, Mouvement motors){
		
		Wheel Roue_D = WheeledChassis.modelWheel(Motor.C, 5.6).offset(-8.35);//D=5.6cm, entreaxe=8.35cm
		Wheel Roue_G = WheeledChassis.modelWheel(Motor.B, 5.6).offset(8.35);
		Chassis chassis = new WheeledChassis(new Wheel[] {Roue_D, Roue_G}, WheeledChassis.TYPE_DIFFERENTIAL);
		MovePilot robot = new MovePilot(chassis);
		float distance = 100*localisation.detecte(sample);
		
		while (true) {
			robot.forward();
			while (distance > 40) {
				distance = 100*localisation.detecte(sample);
				Delay.msDelay(20);
			}
			robot.stop();
		
			if (distance < 40 && distance > 20) {
				break;
			}
			
			motors.tourner(10, 10);
			while (distance == -100) {
				distance = 100*localisation.detecte(sample);
				motors.tourner(10, -2);
			}
			robot.stop();
		}
		
		boolean zone_de_recup = false;
		motors.tourner(10, 22);
		distance = 100*localisation.detecte(sample);
		if (distance < 40 && distance > 20) {
			zone_de_recup = true;
		}
		motors.tourner(10, -44);
		distance = 100*localisation.detecte(sample);
		if (distance < 40 && distance > 20) {
			zone_de_recup = true;
		}
		
		motors.tourner(10, 22);
		
		return zone_de_recup;
	}
}
