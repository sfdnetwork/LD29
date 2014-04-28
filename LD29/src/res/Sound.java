package res;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Sound {

	public boolean loaded;
	public JFXPanel jfx;
	
	
	private volatile MediaPlayer sound;
	
	public Sound(String file){
		jfx = new JFXPanel();
		create(file);
	}
	
	private void create(final String file){
		new Thread(){
			public void run(){
				try{
					sound = new MediaPlayer(new Media(this.getClass().getResource(file).toString()));
					if(sound != null){
						//sound.stop();
						loaded = true;
						//play();
					}
				}catch(Exception e){e.printStackTrace();}
			}
		}.start();
	}
	
	public void play(){
		new Thread("Sound"){
			public void run(){
				sound.stop();
				sound.setCycleCount(0);
				sound.play();
			}
		}.start();
	}
	
	public void loop(){
		new Thread("Sound"){
			public void run(){
				sound.stop();
				sound.setCycleCount(sound.INDEFINITE);
				sound.play();
			}
		}.start();
	}
	
	public void stop(){
		sound.stop();
	}
	
}
