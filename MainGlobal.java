import java.time.Duration;
import java.time.Instant;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.utility.Delay;

public class MainGlobal {
	public static void main(String[] args) {
		
		System.out.println("appuyez pour démarrer");
		Button.waitForAnyPress();
		EV3ColorSensor Csensor = new EV3ColorSensor(SensorPort.S1);
		EV3UltrasonicSensor USsensor = new EV3UltrasonicSensor(SensorPort.S3);
		
		//mettre des sampleProvider pour les 2
		
		SampleProvider csample = Csensor.getColorIDMode();
		SampleProvider USsample = USsensor.getDistanceMode();
		
		Mouvement mouv = new Mouvement();
		
		Borders bord = new Borders();
		
		//Initilaisation de la pince
		Pince pince = new Pince();	
		
		//sort de la zone de depart
		float distance = bord.Init(csample, USsample);
		
		//definition recupzone
		
		boolean isRecuperationZone = true;
		
		//commence son parcours
		
		while (isRecuperationZone) {	
		
			distance = bord.parcours(csample, USsample);
			
			//phase d'approche à rajouter
			isRecuperationZone = approcheTo.approcheTarget(USsample, mouv);
			if (isRecuperationZone) {
				mouv.tourner(40, 150);
			}
		}
		//recuperation de la premiere balle
		float distance_float = localisation.detecte(USsensor);	
		int distance_entiere = (int)distance_float;
		pince.premiere_recup(distance_entiere);
		
		//on cherche la deuxième balle
		distance = bord.parcours(csample, USsample);
		
		isRecuperationZone = true;
		
		//nouvelle phase d'approche à rajouter
		
		while (isRecuperationZone) {	
			
			distance = bord.parcours(csample, USsample);
			
			//phase d'approche à rajouter
			isRecuperationZone = approcheTo.approcheTarget(USsample, mouv);
			if (isRecuperationZone) {
				mouv.tourner(40, 150);
			}
			
		}
		
		//recuperation de la deuxieme balle
		float distance_float_bis = localisation.detecte(USsensor);	
		int distance_entiere_bis = (int)distance_float_bis;
		pince.deuxieme_recup(distance_entiere_bis);
		
		
		//recherche de la zone de recup
		
		while (!isRecuperationZone) {	
			
			distance = bord.parcours(csample, USsample);
			
			//phase d'approche à rajouter
			isRecuperationZone = approcheTo.approcheTarget(USsample, mouv);
			if (!isRecuperationZone) {
				mouv.tourner(40, 150);
			}
			
		}
		
		//phase d'approche à rajouter (on arrive à 20 cm de la zone)
		
		//demi tour et marche arrière 
		mouv.tourner(30, 180);
		DetectionZone zone = new DetectionZone();
		EV3TouchSensor bouton_g = new EV3TouchSensor(SensorPort.S2);
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
	
		//dépot des échantillons
		pince.largage();
		pince.butee();
		
		
		//fermer les capteurs
		Csensor.close();
		USsensor.close();
		bouton_g.close();
		bouton_d.close();
		}
	}
}
