package xx.page.module;
import java.io.File;
import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncodingAttributes;
import it.sauronsoftware.jave.InputFormatException;
public class Corver2Mp3 {	
	
	
	public  void corver(String source1,String target1 ) throws IllegalArgumentException, InputFormatException, EncoderException {
		File source = new File(source1);
		File target = new File(target1);

		System.out.println(target.getPath());
		AudioAttributes audio = new AudioAttributes();
		audio.setCodec("libmp3lame");
		audio.setBitRate(new Integer(128000));
		audio.setChannels(new Integer(2));
		audio.setSamplingRate(new Integer(44100));
		EncodingAttributes attrs = new EncodingAttributes();
		attrs.setFormat("mp3");
		attrs.setAudioAttributes(audio);
		Encoder encoder = new Encoder();
		encoder.encode(source, target, attrs);
		System.out.print("Success!");
	}
}
