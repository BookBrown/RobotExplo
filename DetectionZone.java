package robotexplor;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.SampleProvider;

public class DetectionZone{
	
	public DetectionZone(){
	}
	
	public boolean is_pressed(EV3TouchSensor bouton){
		SampleProvider info = bouton.getTouchMode();
		float[] sample = new float[info.sampleSize()];
		info.fetchSample(sample,0);
		if (sample[0]==0){
			return false;
		}
		else{
			return true;//sample[0]=1 ie le bouton est pressé
		}
	}
	
}

