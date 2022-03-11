package robot;

//import java.time.Duration;
//import java.time.Instant;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class Borders {
	
	protected int black;
	protected int white;
	protected boolean echantillon;
	
	public Borders(){
		this.black = 7;
		this.white = 6;
		this.echantillon = false;
	}
	
	public float Init(SampleProvider csensor, SampleProvider USsensor ){
		
		float[] coul = new float[1];
		
		Mouvement mouv = new Mouvement();
		
        float distance = -1;

        csensor.fetchSample(coul,0);
        
		if (coul[0] != black){
			System.out.println("le robot n'est pas dans la zone de depart");
			Delay.msDelay(5000);
			distance = -2;
		}
		else{
			mouv.avancer(10,60);
			distance = localisation.detecte(USsensor);
			if (distance != -1){
				echantillon = true;
				return distance;
				}
			mouv.tourner(30,85);
			distance = localisation.detecte(USsensor);
			if (distance != -1){
				echantillon = true;
				return distance;
				}
			mouv.tourner(30, 170);
			distance = localisation.detecte(USsensor);
			if (distance != -1){
				echantillon = true;
				return distance;
				}
		}
        return distance;
	}
	
	
	public float parcours(SampleProvider Csensor, SampleProvider USsensor){
		
		Mouvement mouv = new Mouvement();
		
        float distance = -1;
		boolean droite = true;
		
		//Instant start = Instant.now();
		//Instant finish = Instant.now();
		//long timelapse = Duration.between(start,finish).toMillis();
		
		float[] coul = new float[1];
		int t = 0;
		
		while (! echantillon && (t<5)){
			t++;
			//timelapse < 30000
			
			int d = 0;
			Csensor.fetchSample(coul,0);
			while (coul[0] == white){
				mouv.avancer(10,5);
				Csensor.fetchSample(coul,0);
				d++;
				if(d == 10){
					distance = localisation.detecte(USsensor);
					if (distance != -1){
						echantillon = true;
						return distance;
						}
					d=0;
				}
			}
			
			int compteur = 0;
			
			if (droite == true){	
				mouv.avancer(10,-20);
				mouv.tourner(10,90);
				Csensor.fetchSample(coul,0);
				while ((coul[0] == white) && (compteur < 6)){
					mouv.avancer(10,5);
					compteur++;
					Csensor.fetchSample(coul,0);
				}
				mouv.tourner(10,90);
				distance = localisation.detecte(USsensor);
				droite = false;
			}
			else {
				mouv.avancer(10,-20);
				mouv.tourner(10,-90);
				while ((coul[0] == white) && (compteur < 6)){
					mouv.avancer(10,5);
					compteur++;
					Csensor.fetchSample(coul,0);
				}
				mouv.tourner(10,-90);
				distance = localisation.detecte(USsensor);
				droite = true;
			}
			
			if (distance != -1){
				echantillon = true;
				return distance;
				}
			
			//finish = Instant.now();
			//timelapse = Duration.between(start,finish).toMillis();
		}
        return distance;
	}
}
