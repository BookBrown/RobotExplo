package robotexplo;

import lejos.hardware.Battery;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.utility.Delay;

public class robotexplo {
	
	public static void main(String[] args) throws InterruptedException {
		
		System.out.print(" Niveau Batterie : ");
		System.out.println(Battery.getVoltage());//affiche niveau de batterie
		
		LCD.refresh();
		Delay.msDelay(50);
		Button.LEDPattern(5);
		//Delay.msDelay(500);
		Button.waitForAnyPress();
		
		Motor.A.setSpeed(720);
		Motor.A.forward();
		
		Button.LEDPattern(1);
		Motor.A.stop();
		
		Motor.A.rotateTo(360);
		Motor.A.rotate(-720,true);
		while(Motor.A.isMoving()){
			Button.LEDPattern(2);
		}
		Button.LEDPattern(1);
		int angle = Motor.A.getTachoCount();//doit retourner -360
		LCD.drawInt(angle, 5, 5);
		
		//le moteur tourne pendant 3s
		Motor.A.forward();
		try {Thread.sleep(3000);} catch (InterruptException e ) {}
		Motor.A.stop();
		
		//le moteur tourne de 90° etaprès la brick émet un son
		Motor.A.rotate(90);
		Sound.beep();
		
		//le moteur tourne de 90° et s'arrête après 100ms, même s'il n'a pas fini de tourner
		Motor.A.rotate(90,true);
		try {Thread.sleep(100);} catch (InterruptException e ) {}
		Motor.A.stop();
	}
}
