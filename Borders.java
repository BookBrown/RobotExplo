package robot;

import java.time.Duration;
import java.time.Instant;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;

public class Borders {
	
	protected int black;
	protected int white;
	protected boolean echantillon;
	
	public Borders(){
		this.black = 7;
		this.white = 6;
		this.echantillon = false;
	}
	
	public float Init(EV3ColorSensor sensor, EV3UltrasonicSensor USsensor){
		
		Mouvement mouv = new Mouvement();
		
        float distance = -1;

		if (sensor.getColorID() != black){
			System.out.println("le robot n'est pas dans la zone de depart");
		}
		else{
			mouv.avancer(10,20);
			distance = localisation.detecte(USsensor);
			if (distance != -1){
				echantillon = true;
				return distance;
				}
			mouv.tourner(10,90);
			distance = localisation.detecte(USsensor);
			if (distance != -1){
				echantillon = true;
				return distance;
				}
			mouv.tourner(10, 180);
			distance = localisation.detecte(USsensor);
			if (distance != -1){
				echantillon = true;
				return distance;
				}
		}
        return distance;
	}
	
	
	public float parcours(EV3ColorSensor sensor1, EV3UltrasonicSensor sensor2){
		
		Mouvement mouv = new Mouvement();
		
        float distance = -1;
		boolean droite = true;
		
		Instant start = Instant.now();
		Instant finish = Instant.now();
		long timelapse = Duration.between(start,finish).toMillis();
		
		while (! echantillon && timelapse < 30000){
			
			int d = 0;
			
			while (sensor1.getColorID() == white){
				mouv.avancer(10,5);
				d++;
				if(d == 10){
					distance = localisation.detecte(sensor2);
					if (distance != -1){
						echantillon = true;
						return distance;
						}
					d=0;
				}
			}
			
			int compteur = 0;
			
			if (droite == true){		
				mouv.avancer(10,-5);
				mouv.tourner(10,90);
				while ((sensor1.getColorID() == white) && (compteur < 6)){
					mouv.avancer(10,5);
					compteur++;
				}
				mouv.tourner(10,90);
				distance = localisation.detecte(sensor2);
				droite = false;
			}
			else {
				mouv.avancer(10,-5);
				mouv.tourner(10,-90);
				while ((sensor1.getColorID() == white) && (compteur < 6)){
					mouv.avancer(10,5);
					compteur++;
				}
				mouv.tourner(10,-90);
				distance = localisation.detecte(sensor2);
				droite = true;
			}
			
			if (distance != -1){
				echantillon = true;
				return distance;
				}
			
			finish = Instant.now();
			timelapse = Duration.between(start,finish).toMillis();
		}
        return distance;
	}
}
