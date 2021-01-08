package sfx;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import gfx.ImageLoader;

public abstract class SoundBackground {
	private static boolean isMenuPlaying = false, isGamePlaying = false;
	private static AudioListener menuAudioListener;
	private static AudioListener gameAudioListener;

	private static Clip[] MUSIC = new Clip[4];
	private static Clip MENU;
	private static AudioInputStream audioInputStream;
	private static int i = 0;
	
	private static URL url;
	
	public static void init() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		menuAudioListener = new AudioListener() {
			@Override
			public synchronized void update(LineEvent event) {
				super.update(event);
				if(this.done) {
					this.done = false;
					if (isMenuPlaying) {
						try {
							playMenuMusic();
						} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
							e.printStackTrace();
						}
					}
				}
			}
		};
		
		gameAudioListener = new AudioListener() {
			@Override
			public synchronized void update(LineEvent event) {
				super.update(event);
				if(this.done) {
					this.done = false;
					if(isGamePlaying) {
						i = (i == 3)? 0 : i + 1;
						try {
							playGameMusic();
						} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
							e.printStackTrace();
						}
					}
				}
			}
		};
		
		MENU = AudioSystem.getClip();
		for(int count = 0; count < MUSIC.length; count++) {
			MUSIC[count] = AudioSystem.getClip();
		}
	}
	public static boolean isPlaying() {
		return isMenuPlaying;
	}
	
	public static void playMenuMusic() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		url = ImageLoader.class.getResource("/sound/menu_music.wav");
		audioInputStream = AudioSystem.getAudioInputStream(url);
		MENU = AudioSystem.getClip();
		MENU.open(audioInputStream);
		MENU.addLineListener(menuAudioListener);
		MENU.start();
		isMenuPlaying = true;
	}
	
	public static void playGameMusic() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		url = ImageLoader.class.getResource("/sound/0" + Integer.toString(i) + ".wav");
		audioInputStream = AudioSystem.getAudioInputStream(url);
		MUSIC[i].open(audioInputStream);
		MUSIC[i].addLineListener(gameAudioListener);
		MUSIC[i].start();
		isGamePlaying = true;
	}
	
	public static void stop() {
		isMenuPlaying = false;
		isGamePlaying = false;
		
		MENU.stop();
		MENU.close();
		
		MUSIC[i].stop();
		MUSIC[i].close();
		
		i = (i == 3)? 0 : i + 1;
	}
}
