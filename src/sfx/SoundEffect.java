package sfx;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import gfx.ImageLoader;

public class SoundEffect {
	private static URL url;
	
	private static Clip STEP;
	private static AudioInputStream audioInputStream;
	
	public static void init() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		AudioListener myListener = new AudioListener();
		url = SoundBackground.class.getResource("/sound/step.wav");
		audioInputStream = AudioSystem.getAudioInputStream(url);
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
		url = SoundBackground.class.getResource("/sound/AR.wav");
		audioInputStream = AudioSystem.getAudioInputStream(url);
        // create clip reference 
		ar = AudioSystem.getClip();
        // open audioInputStream to the clip 
		ar.open(audioInputStream);
		ar.start();
	}
	
	public static void playDE() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		Clip de;
		url = SoundBackground.class.getResource("/sound/DE.wav");
		audioInputStream = AudioSystem.getAudioInputStream(url);
        // create clip reference 
		de = AudioSystem.getClip();
        // open audioInputStream to the clip 
		de.open(audioInputStream);
		de.start();
	}
	
	public static void playReload() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		Clip reload;
		url = ImageLoader.class.getResource("/sound/reload.wav");
		audioInputStream = AudioSystem.getAudioInputStream(url);
        // create clip reference 
		reload = AudioSystem.getClip();
        // open audioInputStream to the clip 
		reload.open(audioInputStream);
		reload.start();
	}
	
	public static void playKnife() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		Clip knife;
		url = SoundBackground.class.getResource("/sound/knife.wav");
		audioInputStream = AudioSystem.getAudioInputStream(url);
        // create clip reference 
		knife = AudioSystem.getClip();
        // open audioInputStream to the clip 
		knife.open(audioInputStream);
		knife.start();
	}
	
	public static void playRoar() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		Clip roar;
		url = ImageLoader.class.getResource("/sound/roar.wav");
		audioInputStream = AudioSystem.getAudioInputStream(url);
        // create clip reference 
		roar = AudioSystem.getClip();
        // open audioInputStream to the clip 
		roar.open(audioInputStream);
		roar.start();
	}
}
