package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import data.Gun;
import data.User;
import gfx.Camera;
import gfx.ImgAssets;
import input.KeyManager;
import input.MouseManager;
import sfx.SoundBackground;
import sfx.SoundEffect;
import states.GameState;
import states.MainMenuState;
import states.SettingState;
import states.ShopState;
import states.State;

public class Game implements Runnable {
	
	/*
	 * This does not just displays our game
	 * This class is the main window for our game
	 * Login window (Frame) is the 1st window that a player must go through, then
	 * This window will pop-up ==> Game JFrame also belong to this class
	 * This will be the main Frame for displaying multiple sub-windows: Main menu, Settings and >>---> GAME <---<< (Which are called States)
	 * Its instance belong the class Main which contains main()
	 * 
	 * To access any object belong to this class while using other objects, put a parameter of type Game in the constructor of that object's class
	 * ==> we can make a reference to this Game and control (set, get) its components
	 * */

	//test var
	long timer = 0;
	long tick;
	short logoDelay = 4000;
	
	
	private int width = 1400;
	private int height = 1000;
	
	private int fps = 60;	//default = 60fps
	private int update_limit = 60;		//limit the updates
	private final int defaultFPS = 60;	//fps for menu and setting
	private double delta = 0;	//take the time passed since the last update
	private double beta = 0;	//take the time passed since the last frame got displayed
	private long now;
	private long lastTime;
	private double timePerUpdate;	//in nanoSeconds -- it keeps the time between 2 continuous updates
	private double timePerFrame;	//in nanoSeconds -- it keeps the time between 2 continuous frame
	
	//states
	public State gameState;		//the game sub-window: game
	private State mainMenuState;	//the game sub-window: menu
	private State settingState;		//the game sub-window: settings
	private State shopState;		//the game sub-window: shop
	
	//input
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	//camera
	private Camera camera;
	
	//handler
	private Handler handler;
	
	//Thread
	Thread thread;
	
	private Frame_Game fr_g;	//window
	private BufferStrategy bs;	//virtual screen
	private Graphics g;			//paint bursh
	
	private boolean isRunning = false;	//the status of the program
	
	//get things ready
	private void init() {
		//init new JFrame then show it
		fr_g = new Frame_Game("Crewmate Revolt (Version 1.0.0 - Beta)", new Dimension(width, height));
		
		//assign handler
		handler = Handler.createHandler(this);
		
		//init new Keymanager ==> used in Player, make logic for this Frame's Listener
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
		
		//by doing this, our frame and canvas can get key and mouse events
		fr_g.addKeyListener(keyManager);
		
		fr_g.addMouseListener(mouseManager);
		fr_g.addMouseMotionListener(mouseManager);
		
		fr_g.getCanvas().addMouseListener(mouseManager);
		fr_g.getCanvas().addMouseMotionListener(mouseManager);
		
		//assign camera
		camera = new Camera(handler, 0, 0);
		
		//get all the imgages that needed for the moment
		ImgAssets.init();
		
		//get all guns data
		Gun.init();
		
		//get all player's data
		User.init();
		
		//get all sounds
		try {
			SoundEffect.init();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
			e1.printStackTrace();
		}
		
		try {
			SoundBackground.init();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
			e1.printStackTrace();
		}
		
		gameState = new GameState(handler);
		mainMenuState = new MainMenuState(handler);
		shopState = new ShopState(handler);
		settingState = new SettingState(handler);
		setMenuState();
		
		fr_g.remove(fr_g.waiting);
		fr_g.add(fr_g.getCanvas());
		fr_g.revalidate();
		
		timePerUpdate = 1000000000/update_limit;		//1 (s) = 10^9 (ns)
		timePerFrame = 1000000000/fps;
		
		//idk why but this method must be called 2 times in order to render the logo
		renderLogo();
		renderLogo();
		
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	//update everything after every frame: update a state, the state will update its components
	private void update() {
		keyManager.update();
		
		if(State.getState() != null) {
			State.getState().update();
		}
	}
	
	//render new frame: render a state, that state will render its components
	private void render() {
		bs = fr_g.getCanvas().getBufferStrategy();
		
		//if there is no bs yet -> create
		if(bs == null) {
			fr_g.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		
		//draw
		g.clearRect(0, 0, width, height);
		if(State.getState() != null) {
			State.getState().render(g);
		}
		
		//render
		bs.show();
		g.dispose();

		
	}
	
	public void renderLogo() {
		bs = fr_g.getCanvas().getBufferStrategy();
		
		//if there is no bs yet -> create
		if(bs == null) {
			fr_g.getCanvas().createBufferStrategy(3);
			bs = fr_g.getCanvas().getBufferStrategy();
		}
		g = bs.getDrawGraphics();
		
		//draw
		g.clearRect(0, 0, width, height);
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);
		g.drawImage(ImgAssets.logo, width/2 - ImgAssets.logo.getWidth() / 2, height/2 - ImgAssets.logo.getHeight() / 2, null);
		
		//render
		bs.show();
		g.dispose();
		
		
	}
	
	//main codes
	public void run() {
		init();
		
		lastTime = System.nanoTime();
		while(isRunning) {
			now = System.nanoTime();
			timer += now - lastTime;
			delta += now - lastTime;
			beta += now - lastTime;
			lastTime = now;
			if(delta >= timePerUpdate) {
				update();
				delta -= timePerUpdate;
				//tick++;
			}
			
			if(beta >= timePerFrame) {
				render();
				beta -= timePerFrame;
				tick++;
			}
			
			if(timer >= 1000000000) {	//test fps
				System.out.println("Frames: " + tick);
				tick = 0;
				timer = 0;
			}
		}
		stop();
	}
	
	//get the keyManager
	public KeyManager getKeyManager() {
		return this.keyManager;
	}
	
	//get the mouseManager
	public MouseManager getMouseManager() {
		return this.mouseManager;
	}
	
	//get the camera: control or get the offset of the camera in another class by using this
	public Camera getCamera() {
		return this.camera;
	}
	
	//call this to start game sub-window
	public synchronized void start() {
		//if the game is already running
		if(isRunning)
			return;
		
		//else ==> start
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		//if game already stop
		if(!isRunning)
			return;
		
		//else ==> stop
		try {
			isRunning = false;
			thread.join();	//stop the game
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//call to stop everything
	public void stopGame() {
		SoundBackground.stop();
		User.clearData();
		isRunning = false;
	}
	
	//change the state to game
	public void setGameState() {
		gameState.reset();
		State.setState(gameState);
		try {
			SoundBackground.playGameMusic();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	//change the state to menu
	public void setMenuState() {
		State.setState(mainMenuState);
		try {
			if(!SoundBackground.isPlaying()) SoundBackground.playMenuMusic();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
	}
		
	//change the state to shop
	public void setShopState() {
		State.setState(shopState);
	}
	
	//change the state to setting
	public void setSettingState() {
		State.setState(settingState);
	}
	
	//set new fps
	public void setNewFPS(int fps) {
		if(fps > 0) {
			this.fps = fps;
		}
	}

	//get window's width
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
		System.out.println(this.width);
	}

	//get window's height
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
		System.out.println(this.height);
	}
}

//ctrl + shift + / : comment
//ctrl + shift + \ : uncomment