package robot;

import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;

public class approche {

	
	public static void approcheTarget(SampleProvider sample, Mouvement motors){
		
		float distance = 100*localisation.detecte(sample);
		float newdistance;
		
		int angleRelatifMinimum;
		int compteurGauche;
		int compteurDroit;
		boolean isRecuperationZone=false;
		
		while((distance >30)&&(distance<80)){
			
			angleRelatifMinimum = 0;
			compteurGauche = 0;
			compteurDroit=1;
			
			
			
			motors.avancer(100, 10);
			
			newdistance = 100*localisation.detecte(sample);
			
			while ((newdistance==-100)&&(compteurGauche <3)){
				
				motors.tourner(30,-15);
				angleRelatifMinimum +=-15;
				newdistance = 100*localisation.detecte(sample);
				compteurGauche +=1;

			}
			
			if(newdistance==-100){
				
				motors.tourner(100, 60); //on se met 15degrés a droite de la position initiale
				angleRelatifMinimum= 15;
				
				newdistance = 100*localisation.detecte(sample);
				
				while ((newdistance==-100)&&(compteurDroit <3)){
					
					motors.tourner(30,15);
					angleRelatifMinimum +=15;
					newdistance = 100*localisation.detecte(sample);
					compteurDroit +=1;

				}
				
				if(newdistance==-100){ //ce cas ne devrait jamais se produire comme on avance de 10 cm en 10 cm.
					while ((angleRelatifMinimum <321)&&(newdistance==-100)){ //cela correspond à parcourir tous les anles, cela voudrait dire que le robot a perdu la trace de la cible.
						motors.tourner(30,15);
						compteurDroit +=1;
						angleRelatifMinimum +=15;
						newdistance = 100*localisation.detecte(sample);
					}
					//if(newdistance==-100){
					//	System.out.println("erreur, le systeme a ete perdu de vue !");
					//}
				}
				
				
				
			}
			
			
			distance = newdistance;
		}
		
		//On teste ci-dessous si on est face a la zone de récupération ou un capteur.
		boolean seenLeft= false;
		boolean seenRight= false;
		motors.tourner(30,-25);
		newdistance= 100*localisation.detecte(sample);
		if((newdistance <70)&&(newdistance>0)){
			seenLeft = true;
		}
		motors.tourner(30,50);
		newdistance= 100*localisation.detecte(sample);
		if((newdistance <70)&&(newdistance>0)){
			seenRight = true;
		}
		
		isRecuperationZone = seenRight&&seenLeft; //on a bien que si on détecte à 25 degres a gauche et a droite, on est face a la zone de recuperation
		
			
		while((distance > 20)&&(distance<30)){
			
			angleRelatifMinimum = 0;
			compteurGauche = 0;
			compteurDroit=1;
			
			motors.avancer(10, 1);
			
			newdistance = 100*localisation.detecte(sample);
			
			while ((newdistance==-100)&&(compteurGauche <3)){
				
				motors.tourner(10,-1);
				compteurGauche +=1;
				angleRelatifMinimum +=-1;
				newdistance = 100*localisation.detecte(sample);
			}
			
			if(newdistance==-100){
				
				motors.tourner(10, 4); //on se met 15degres a droite de la position initiale
				angleRelatifMinimum= 1;
				
				newdistance = 100*localisation.detecte(sample);
				
				while ((newdistance==-100)&&(compteurDroit <3)){
					
					motors.tourner(10,1);
					compteurDroit +=1;
					angleRelatifMinimum +=1;
					newdistance = 100*localisation.detecte(sample);
				}
				
				if(newdistance==-100){ //ce cas ne devrait jamais se produire comme on avance de 10 cm en 10 cm.
					while ((angleRelatifMinimum <321)&&(newdistance==-100)){ //cela correspond à parcourir tous les anles, cela voudrait dire que le robot a perdu la trace de la cible.
						motors.tourner(10,5);
						compteurDroit +=5;
						angleRelatifMinimum +=5;
						newdistance = 100*localisation.detecte(sample);
					}
					if(newdistance==-100){
						System.out.println("erreur, le systeme a ete perdu de vue !");
					}
				}
				
				
				
			}
			
			
			distance = newdistance;
		}
		
		System.out.println("isRecuperationZone :"+isRecuperationZone);
	}
		
}

