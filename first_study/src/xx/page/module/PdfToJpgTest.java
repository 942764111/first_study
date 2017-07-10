package xx.page.module;
import java.awt.Image;  
import java.awt.Rectangle;  
import java.awt.image.BufferedImage;  
 
import java.io.File;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.RandomAccessFile;  
import java.nio.ByteBuffer;  
import java.nio.channels.FileChannel;  
import javax.swing.SwingUtilities;  
import com.sun.image.codec.jpeg.JPEGCodec;  
import com.sun.image.codec.jpeg.JPEGImageEncoder;  
import com.sun.pdfview.PDFFile;  
import com.sun.pdfview.PDFPage;  
 
public class PdfToJpgTest {  
    public static void setup(String inputfile,String outputfile) throws IOException {  
 
        File file = new File(inputfile);  
        RandomAccessFile raf = new RandomAccessFile(file, "r");  
        FileChannel channel = raf.getChannel();  
        ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel  
                .size());  
        PDFFile pdffile = new PDFFile(buf);  
 
        String getPdfFilePath = System.getProperty("user.dir")+"\\pdfPicFile";
        for (int i = 1; i <= 1; i++) {  
            // draw the first page to an image  
            PDFPage page = pdffile.getPage(i);  
 
            // get the width and height for the doc at the default zoom  
            Rectangle rect = new Rectangle(0, 0, (int) page.getBBox()  
                    .getWidth(), (int) page.getBBox().getHeight());  
 
            // generate the image  
            Image img = page.getImage(rect.width, rect.height, // width &  
                                                                // height  
                    rect, // clip rect  
                    null, // null for the ImageObserver  
                    true, // fill background with white  
                    true // block until drawing is done  
                    );  
 
            BufferedImage tag = new BufferedImage(rect.width, rect.height,  
                    BufferedImage.TYPE_INT_RGB);  
            tag.getGraphics().drawImage(img, 0, 0, rect.width, rect.height,  
                    null); 
           
          
           
            FileOutputStream out = new FileOutputStream(outputfile);
        
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
            encoder.encode(tag); // JPEG±àÂë  
 
            out.close();  
        }  
 
    }  
 
    public static void start(final String inputfile,final String outputfile) {  
        SwingUtilities.invokeLater(new Runnable() {  
            public void run() {  
                try {  
                    PdfToJpgTest.setup(inputfile,outputfile);  
                } catch (IOException ex) {  
                    ex.printStackTrace();  
                }  
            }  
        });  
    }  
 
} 