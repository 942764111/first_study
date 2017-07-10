import java.io.BufferedReader;  
import java.io.File;  
import java.io.IOException;  
import java.io.InputStreamReader;  
  
public class TestPdf2Swf {  
    public static int convertPDF2SWF(String sourcePath, String destPath,  
                    String fileName) throws IOException {  
        // Ŀ��·������������Ŀ��·��  
        File dest = new File(destPath);  
        if (!dest.exists())  
            dest.mkdirs();  
  
        // Դ�ļ��������򷵻�  
        File source = new File(sourcePath);  
        if (!source.exists())  
            return 0;  
  
        // ����pdf2swf�������ת��  
        String command = "E:\\pdf2swf.exe" + " " + sourcePath+ " -o "   
                + destPath + fileName + " -f -T 9";  
        System.out.println(command);  
        Process pro = Runtime.getRuntime().exec(command);  
  
        BufferedReader bufferedReader = new BufferedReader(  
                new InputStreamReader(pro.getInputStream()));  
        while (bufferedReader.readLine() != null);  
        try {  
            pro.waitFor();  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
        return pro.exitValue();  
    }  
  
    /** 
     * @param args 
     */  
    public static void main(String[] args) {  
        String sourcePath = "E:\\123.pdf"; //Դ�ļ�·��  
        String destPath = "F:\\";                      //Ŀ��·��  
        String fileName = "������ƿ.swf";       //�����ļ���  
        try {  
            TestPdf2Swf.convertPDF2SWF(sourcePath, destPath, fileName);  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
}  