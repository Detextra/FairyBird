package mvc;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Audio {
	
	/*
	 * Audio files
	 */
	private String winxxxxxxP = "./WINXXXXXXXXXXXXX/Winx.wav";
	private Clip winxxxxxxO;
	private File musicFile = new File(winxxxxxxP);
	
	public Audio() {
		
		try {
			/*
			 * To output the audio
			 */
			winxxxxxxO = AudioSystem.getClip();
			winxxxxxxO.open(AudioSystem.getAudioInputStream(
					musicFile.getAbsoluteFile() ) );		
		} catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
			e.printStackTrace();	
			}
		
	}
	
	/*
	 * To repeat the audio if it's end
	 */
	public void loopWINXO() {
		winxxxxxxO.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
		
	

}
