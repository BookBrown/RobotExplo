package test;

import java.io.IOException;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.port.SensorPort;
import lejos.robotics.SampleProvider;
import lejos.hardware.Battery;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.utility.Delay;

public class testmain{
	private static final String COLOR_FULL=null;

	public static void main(String[] args) throws IOException {
		EV3ColorSensor coulR = new EV3ColorSensor(SensorPort.S1);
		float[] coul = new float[1];
		SampleProvider CoulRProvider = coulR.getColorIDMode();
		
		while(Button.ESCAPE.isUp())
		{
			CoulRProvider.fetchSample(coul,0);
			LCD.clear(4);
			LCD.drawString("Couleur:"+coulR.getColorID(),0,4);
			Delay.msDelay(100);
		}
		coulR.close();
	}
	
}

