import java.time.Duration;
import java.time.Instant;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;

public class Borders {
	
	protected int black = 7;
	protected int white = 6;
	protected boolean echantillon;
	
	public Borders(){
		this.black = 7;
		this.white = 6;
		this.echantillon;
	}
	
	public float Init(EV3ColorSensor sensor){
		
		Mouvement mouv = new Mouvement();
		Localisation loc = new Localisation();
		
        	float distance = -1;

		if (sensor.getColorID() != black){
			System.out.println("le robot n'est pas dans la zone de depart");
		}
		else{
			mouv.avancer(10,20);
			distance = loc.detecte();
			if (distance != -1){
				echantillon = true;
				break;
				}
			mouv.tourner(10,90);
			distance = loc.detecte();
			if (distance != -1){
				echantillon = true;
				break;
				}
			mouv.tourner(10, 180);
			distance = loc.detecte();
			if (distance != -1){
				echantillon = true;
				break;
				}
		}
        return distance;
	}
	
	public float parcours(){
		
		Mouvement mouv = new Mouvement();
		Localisation loc = new Localisation();
		
		EV3ColorSensor sensor1 = new EV3ColorSensor(SensorPort.S1);
		EV3UltrasonicSensor sensor2 = new EV3UltrasonicSensor(SensorPort.S2);
		
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
					distance = loc.detecte(sensor2);
					if (distance != -1){
						echantillon = true;
						break;
						}
					d=0;
				}
			}
			if (droite == true){		
				mouv.avancer(10,-5);
				mouv.tourner(10,90);
				mouv.avancer(10,30);
				mouv.tourner(10,90);
				distance = loc.detecte(sensor2);
				droite = false;
			}
			else {
				mouv.avancer(10,-5);
				mouv.tourner(10,-90);
				mouv.avancer(10,30);
				mouv.tourner(10,-90);
				distance = loc.detecte(sensor2);
				droite = true;
			}
			
			if (distance != -1){
				echantillon = true;
				}
			
			finish = Instant.now();
			timelapse = Duration.between(start,finish).toMillis();
		}
        return distance;
	}

}

