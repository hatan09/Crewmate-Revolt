package sound;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundEffect {
	
	
	private static Clip STEP;
	private static AudioInputStream audioInputStream;
	
	public static void init() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		AudioListener myListener = new AudioListener();
		
		audioInputStream = AudioSystem.getAudioInputStream(new File("res/sound/step.wav"));
        // create clip reference 
		STEP = AudioSystem.getClip();
        // open audioInputStream to the clip 
		STEP.open(audioInputStream);
		STEP.addLineListener(myListener);
	}
	
	public static void playStep() {
		if(STEP.getFramePosition() >= STEP.getFrameLength()) STEP.setFramePosition(0);
		STEP.start();
	}
	
	public static void stopStep() {
		STEP.stop();
		STEP.setFramePosition(0);
	}
	
	public static void playAR() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		Clip ar;
		audioInputStream = AudioSystem.getAudioInputStream(new File("res/sound/AR.wav"));
        // create clip reference 
		ar = AudioSystem.getClip();
        // open audioInputStream to the clip 
		ar.open(audioInputStream);
		ar.start();
	}
	
	public static void playDE() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		Clip de;
		audioInputStream = AudioSystem.getAudioInputStream(new File("res/sound/DE.wav"));
        // create clip reference 
		de = AudioSystem.getClip();
        // open audioInputStream to the clip 
		de.open(audioInputStream);
		de.start();
	}
}
