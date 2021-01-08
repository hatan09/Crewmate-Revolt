package sound;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public abstract class SoundBackground {

	private static Clip[] MUSIC = new Clip[5];
	private static AudioInputStream audioInputStream;
	private static int i = 0;
	
	public static void init() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		AudioListener myListener = new AudioListener() {
			@Override
			public synchronized void update(LineEvent event) {
				// TODO Auto-generated method stub
				super.update(event);
				if(this.done) {
					this.done = false;
					i++;
					if(i == MUSIC.length) i = 0;
					play();
				}
			}
		};
		
		for(int count = 0; count < MUSIC.length; count++) {
			audioInputStream = AudioSystem.getAudioInputStream(new File("res/sound/0" + Integer.toString(count+1) + ".wav"));
	        // create clip reference 
			MUSIC[count] = AudioSystem.getClip();
	        // open audioInputStream to the clip 
			MUSIC[count].open(audioInputStream);
			MUSIC[count].addLineListener(myListener);
		}
	}
	
	public static void play() {
		MUSIC[i].setFramePosition(0);
		MUSIC[i].start();
	}
}
