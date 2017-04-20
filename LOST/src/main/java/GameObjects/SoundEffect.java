package GameObjects;

/**
 * Author: Onur Sönmez
 *
 * Github: @sonmezonur
 *
 * Description: Concurred SoundEffect class that holds sound reactions of Characters when they encountered
 */

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;

public class SoundEffect {

    private Clip clip;
    private String url;
    private AtomicBoolean isActive;//atomic variables for synchronization

    /**
     * Construct.
     * @param string url Path of wav file that will be added in.
     * @throws javax.sound.sampled.UnsupportedAudioFileException
     */

    public SoundEffect(String url){
        this.url = url;
        isActive = new AtomicBoolean(false);//initial
        try{
            clip = AudioSystem.getClip();
        }
        catch (Exception e){}
        createSound();
    }

    /**
     * Creating audio input stream for sound.
     * @throws java.util.FileNotFoundException
     */

    private synchronized void createSound(){
        new Thread(()->{
            if(isActive.get()){
                try{
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(url).getAbsoluteFile());
                    clip.open(inputStream);
                    clip.start();
                }catch (Exception e){
                    System.out.println("Problem occurs while loading audio input stream!");
                }
            }
        }).start();
    }

    /**
     * Activating runnable.
     */

    public synchronized void activateSound(){
        isActive.getAndSet(true);
    }

    /**
     * Deactivating runnable and closing audio clip.
     */

    public synchronized void deactivateSound(){
        isActive.getAndSet(false);
        clip.close();
    }
    
}
