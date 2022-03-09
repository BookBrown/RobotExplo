package robotexplor;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.localization.PoseProvider;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.navigation.Pose;
import lejos.utility.Delay;

public class Mouvement {
	int vitesse_a;
	int vitesse_r;
	int distance_a;
	int distance_r;
	int vitesse_ang;
	int angle;
	
	public Mouvement(){
		this.vitesse_a=0;
		this.vitesse_r=0;
		this.distance_a=0;
		this.distance_r=0;
		this.angle=0;
	}
	
	public void avancer(int vitesse_a, int distance_a){
		Wheel Roue_D = WheeledChassis.modelWheel(Motor.C, 5.6).offset(-8.35);//D=5.6cm, entreaxe=8.35cm
		Wheel Roue_G = WheeledChassis.modelWheel(Motor.B, 5.6).offset(8.35);
		Chassis chassis = new WheeledChassis(new Wheel[] {Roue_D, Roue_G}, WheeledChassis.TYPE_DIFFERENTIAL);
		MovePilot robot = new MovePilot(chassis);
		
		robot.setLinearSpeed(vitesse_a);//vitesse en cm/s
		robot.travel(distance_a);//distance en cm
	}
	
	public void reculer(int vitesse_r, int distance_r){
		Wheel Roue_D = WheeledChassis.modelWheel(Motor.C, 5.6).offset(-8.35);//D=5.6cm, entreaxe=8.35cm
		Wheel Roue_G = WheeledChassis.modelWheel(Motor.B, 5.6).offset(8.35);
		Chassis chassis = new WheeledChassis(new Wheel[] {Roue_D, Roue_G}, WheeledChassis.TYPE_DIFFERENTIAL);
		MovePilot robot = new MovePilot(chassis);
		
		robot.setLinearSpeed(vitesse_r);//vitesse en cm/s
		robot.travel(-distance_r);//distance en cm
		
	}
	
	public void tourner(int vitesse_ang, int angle){
		Wheel Roue_D = WheeledChassis.modelWheel(Motor.C, 5.6).offset(-8.35);//D=5.6cm, entreaxe=8.35cm
		Wheel Roue_G = WheeledChassis.modelWheel(Motor.B, 5.6).offset(8.35);
		Chassis chassis = new WheeledChassis(new Wheel[] {Roue_D, Roue_G}, WheeledChassis.TYPE_DIFFERENTIAL);
		MovePilot robot = new MovePilot(chassis);
		
		robot.setAngularSpeed(vitesse_ang);
		robot.rotate(angle);
	}
	
	public void arreter(){
		Wheel Roue_D = WheeledChassis.modelWheel(Motor.C, 5.6).offset(-8.35);//D=5.6cm, entreaxe=8.35cm
		Wheel Roue_G = WheeledChassis.modelWheel(Motor.B, 5.6).offset(8.35);
		Chassis chassis = new WheeledChassis(new Wheel[] {Roue_D, Roue_G}, WheeledChassis.TYPE_DIFFERENTIAL);
		MovePilot robot = new MovePilot(chassis);
		
		robot.stop();
	}
}

