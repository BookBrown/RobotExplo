package robot;

import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;

public class approche {

	
	public static void approcheTarget(EV3UltrasonicSensor USsensor, Mouvement motors){
		
		float distance = localisation.detecte(USsensor);
		float newdistance;
		
		float minimum = distance;
			int angleRelatifMinimum;
			int compteurGauche;
			int compteurDroit;
		
		while(distance > 10){
			
			minimum = distance;
			angleRelatifMinimum = 0;
			compteurGauche = 0;
			compteurDroit=1;
			
			
			
			motors.avancer(100, 10);
			
			newdistance = localisation.detecte(USsensor);
			
			while ((newdistance==-1)&&(compteurGauche <3)){
				
				motors.tourner(30,-15);
				compteurGauche +=1;
				angleRelatifMinimum +=-15;
				newdistance = localisation.detecte(USsensor);
			}
			
			if(newdistance==1){
				
				motors.tourner(100, 60); //on se met 15° à droite de la position initiale
				angleRelatifMinimum= 15;
				
				newdistance = localisation.detecte(USsensor);
				
				while ((newdistance==-1)&&(compteurDroit <3)){
					
					motors.tourner(30,15);
					compteurDroit +=1;
					angleRelatifMinimum +=15;
					newdistance = localisation.detecte(USsensor);
				}
				
				if(newdistance==-1){ //ce cas ne devrait jamais se produire comme on avance de 10 cm en 10 cm.
					while ((angleRelatifMinimum <321)&&(newdistance==-1)){ //cela correspond à parcourir tous les anles, cela voudrait dire que le robot a perdu la trace de la cible.
						motors.tourner(30,15);
						compteurDroit +=1;
						angleRelatifMinimum +=15;
						newdistance = localisation.detecte(USsensor);
					}
					if(newdistance==-1){
						System.out.println("erreur, le système a été perdu de vue !");
					}
				}
				
				
				
			}
			
			
			motors.tourner(30, 10);
			if (newdistance < minimum){
				minimum = newdistance;
				angleRelatifMinimum = 0;
			}
		}
			
		while(distance > 5){
			
			minimum = distance;
			angleRelatifMinimum = 0;
			compteurGauche = 0;
			compteurDroit=1;
			
			motors.avancer(10, 1);
			
			newdistance = localisation.detecte(USsensor);
			
			while ((newdistance==-1)&&(compteurGauche <3)){
				
				motors.tourner(10,-1);
				compteurGauche +=1;
				angleRelatifMinimum +=-1;
				newdistance = localisation.detecte(USsensor);
			}
			
			if(newdistance==1){
				
				motors.tourner(10, 4); //on se met 15° à droite de la position initiale
				angleRelatifMinimum= 1;
				
				newdistance = localisation.detecte(USsensor);
				
				while ((newdistance==-1)&&(compteurDroit <3)){
					
					motors.tourner(10,1);
					compteurDroit +=1;
					angleRelatifMinimum +=1;
					newdistance = localisation.detecte(USsensor);
				}
				
				if(newdistance==-1){ //ce cas ne devrait jamais se produire comme on avance de 10 cm en 10 cm.
					while ((angleRelatifMinimum <321)&&(newdistance==-1)){ //cela correspond à parcourir tous les anles, cela voudrait dire que le robot a perdu la trace de la cible.
						motors.tourner(10,1);
						compteurDroit +=1;
						angleRelatifMinimum +=1;
						newdistance = localisation.detecte(USsensor);
					}
					if(newdistance==-1){
						System.out.println("erreur, le système a été perdu de vue !");
					}
				}
				
				
				
			}
			
			
			motors.tourner(30, 10);
			if (newdistance < minimum){
				minimum = newdistance;
				angleRelatifMinimum = 0;
			}
		}
	}
		
}

