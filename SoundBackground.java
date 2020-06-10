import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.FloatControl;


public class SoundBackground extends Thread implements Runnable
{

    //private static String fileLocation = "dbz.WAV";
    //private static String fileLocation = "necro.WAV";
    //private static String fileLocation = "terra.WAV";
    private static String fileLocation = "SAO.WAV";
    //private static String fileLocation = "cynthia.WAV";

    private static boolean running = true;
    //private static int nBytesRead = 0;
    private static boolean stop = false;
    private static int volume2 = -10;

    public void Sound() { }

    public void play()
    {
        Thread t = new Thread(this);
        t.start();
    }

    public static void endSong(){
        fileLocation = "Victory.wav";
    }

    public static void volume0(){
        volume2 = 0;
    }

    public static void terminate(){
        stop = true;
        //running = false;
    }

    public void run()
    {
        //while(running)
    //{
        //fileLocation = "SAO.wav";
        playSound(fileLocation);
        //stop = true;
        endSong();
        stop = false;
        volume0();
        playSound(fileLocation);
        terminate();
    //}
    }

    private void playSound(String fileName)
    {
        File soundFile = new File(fileName);
        AudioInputStream audioInputStream = null;
        try
        {
            audioInputStream = AudioSystem.getAudioInputStream(soundFile);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        AudioFormat audioFormat = audioInputStream.getFormat();
        SourceDataLine line = null;
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
        try
        {
            line = (SourceDataLine) AudioSystem.getLine(info);
            line.open(audioFormat);
            if (line.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
             FloatControl volume = (FloatControl) line.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(volume2);
         }
        }
        catch (LineUnavailableException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        line.start();
        int nBytesRead = 0;
        byte[] abData = new byte[128000];
        while (nBytesRead != -1)
        {
            if(stop){
                return;
            }
            try
            {
                nBytesRead = audioInputStream.read(abData, 0, abData.length);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            if (nBytesRead >= 0)
            {
                int nBytesWritten = line.write(abData, 0, nBytesRead);
            }

        }
        line.drain();
        line.close();
    }
}
