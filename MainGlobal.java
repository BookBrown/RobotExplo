package robot;

import java.time.Duration;
import java.time.Instant;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.utility.Delay;

public class MainGlobal {
	public static void main(String[] args) {
		
		EV3ColorSensor Csensor = new EV3ColorSensor(SensorPort.S1);
		EV3UltrasonicSensor USsensor = new EV3UltrasonicSensor(SensorPort.S2);
		
		Borders bord = new Borders();
		
		float distance = bord.Init(Csensor, USsensor);
		// ajouter l'initilaisation de la pince
		
		distance = bord.parcours(Csensor, USsensor);
		
		
		//phase d'approche � rajouter
		
		
		Pince pince = new Pince();
		
		float distance_float = localisation.detecte(USsensor);	
		int distance_entiere = (int)distance_float;
		
		pince.premiere_recup(distance_entiere);
		
		//on cherche la deuxi�me balle
		distance = bord.parcours(Csensor, USsensor);
		
		//nouvelle phase d'approche � rajouter
		
		
		float distance_float_bis = localisation.detecte(USsensor);	
		int distance_entiere_bis = (int)distance_float_bis;
		
		pince.deuxieme_recup(distance_entiere_bis);
		
		
		//recherche de la zone de recup
		distance = bord.parcours(Csensor, USsensor);
		
		//phase d'approche � rajouter (on arrive � 20 cm de la zone)
		
		
		
		//demi tour et marche arri�re 
		Mouvement mouv = new Mouvement();
		mouv.tourner(30, 180);
		DetectionZone zone = new DetectionZone();
		EV3TouchSensor bouton_g = new EV3TouchSensor(SensorPort.S3);
		EV3TouchSensor bouton_d = new EV3TouchSensor(SensorPort.S4);
		while ((!zone.is_pressed(bouton_g)) && (!zone.is_pressed(bouton_d))){
			mouv.reculer(5, 1);
		}
		if (zone.is_pressed(bouton_g)){
			while (!zone.is_pressed(bouton_d)){
				mouv.tourner(10, -2);
			}
		if (zone.is_pressed(bouton_d)){
			while (!zone.is_pressed(bouton_g)){
				mouv.tourner(10, 2);
			}
		}
		
		//d�pot des �chantillons
		pince.largage();
		pince.butee();
		
		}
	}
}