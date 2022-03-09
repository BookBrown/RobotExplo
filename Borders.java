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
	}
	
	public void Init(EV3ColorSensor sensor){
		
		Mouvement mouv = new Mouvement();
		Localisation loc = new Localisation();
		
		if (sensor.getColorID() != black){
			System.out.println("le robot n'est pas dans la zone de départ");
		}
		else{
			mouv.avancer(10,20);
			loc.detecte();
			mouv.tourner(10,90);
			loc.detecte();
			mouv.tourner(10, 180);
			loc.detecte();
		}
	}
	
	public void parcours(){
		
		Mouvement mouv = new Mouvement();
		Localisation loc = new Localisation();
		
		EV3ColorSensor sensor1 = new EV3ColorSensor(SensorPort.S1);
		EV3UltrasonicSensor sensor2 = new EV3UltrasonicSensor(SensorPort.S2);
		
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
					loc.detecte(sensor2);
					d=0;
				}
			}
			if (droite == true){		
				mouv.avancer(10,-5);
				mouv.tourner(10,90);
				loc.detecte(sensor2);
				mouv.avancer(10,30);
				mouv.tourner(10,90);
				loc.detecte(sensor2);
				droite = false;
			}
			else {
				mouv.avancer(10,-5);
				mouv.tourner(10,-90);
				loc.detecte(sensor2);
				mouv.avancer(10,30);
				mouv.tourner(10,-90);
				loc.detecte(sensor2);
				droite = true;
			}
			finish = Instant.now();
			timelapse = Duration.between(start,finish).toMillis();
		}
	}

}
