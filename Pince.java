package robot;
import lejos.hardware.Battery;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.utility.Delay;
import lejos.hardware.motor.BaseRegulatedMotor;

public class Pince {
	
	/* Ce constructeur part du principe que la pince est en butee bas au préalable 
	 * Il place le robot en position 'vision' 
	 * */
	public Pince() {
		Motor.D.setSpeed(300);
		Motor.D.rotateTo(-180);
		}
	
	public void premiere_recup(int distance) {
		/* Position grab */
		Motor.D.setSpeed(300);
		Motor.D.rotateTo(-10);
		Mouvement mouv = new Mouvement();
		/* Tourner de 10° vers la gauche */
		mouv.tourner(20, 10);
		/* Avancer de distance - 7 cm */
		mouv.avancer(10, distance - 7);
		/* Tourner de 20° vers la droite */
		mouv.tourner(10, -20);
		/* Avancer de 5cm */
		mouv.avancer(10, 5);
		/* Position stock */
		Motor.D.rotateTo(-600);
		/* Position vision */
		Motor.D.rotateTo(-180);
	}
	
	public void deuxieme_recup(int distance) {
		Motor.D.setSpeed(300);
		Motor.D.rotateTo(-10);
		Mouvement mouv = new Mouvement();
		/* Tourner de 10° vers la gauche */
		mouv.tourner(20, 10);
		/* Avancer de distance - 7 cm */
		mouv.avancer(10, distance - 7);
		/* Tourner de 20° vers la droite */
		mouv.tourner(10, -20);
		/* Avancer de 5cm */
		mouv.avancer(10, 5);
		Motor.D.rotateTo(-180);
	}
	
	public void largage() {
		Motor.D.setSpeed(300);
		Motor.D.rotateTo(-700);
	}
	
	public void butee() {
		Motor.D.setSpeed(300);
		Motor.D.rotateTo(0);
	}
}
