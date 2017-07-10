package xx.page.module;

import java.io.File;
import java.util.List;

public class VideoPictureUtil {
	/**
	 * �����Ƶ����ͼ����ȡ�ɹ�����true����ȡʧ�ܷ���false
	 * @param ffmpegPath  ��ffmpeg.exe��ŵ�·��
	 * @param path   ����Ƶ�ļ��Ĵ��·��
	 * @param outImagePath �������ͼ�ı���·��
	 * @return
	 */
	public static boolean getVideoImage(String path,String outImagePath) {
		String ffmpegPath="E:\\javaserve\\ffmpeg.exe";
		File file = new File(path);
		if (!file.exists()) {//�ж���Ƶ�ļ��Ƿ����
			System.err.println("·��[" + path + "]��Ӧ����Ƶ�ļ�������!");
			return false;
		}
		//���ò���
		List<String> commands = new java.util.ArrayList<String>();
		commands.add(ffmpegPath);//��������ffmpeg.exe��ŵ�·��
		commands.add("-i");
		commands.add(path);//����������Ҫ��ȡ����ͼ����Ƶ��·��
		commands.add("-y");
		commands.add("-f");
		commands.add("image2");
		commands.add("-ss"); 
		commands.add("2");//�������õ���Ҫ��ȡ��Ƶ��ʼ���Ŷ�������ͼ�������Լ�����ʱ��
		commands.add("-t");
		commands.add("0.001");
		commands.add("-s");
		commands.add("320x240");//�����������ͼƬ�Ĵ�С
		commands.add(outImagePath);//������������Ľ�ͼ�ı���·��
	
		try {
		    //��ȡ����ͼ������	
			ProcessBuilder builder = new ProcessBuilder();
			builder.command(commands);
			builder.start();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
//	//������**************˧��!!!
//	public static void main(String[] args)throws Exception{
//		//��ȡ��ǰ����Ŀ¼
//		File directory = new File("."); 
//		String root = directory.getCanonicalPath();
//		//�������������root=d:/videoimage
//		String ffmpegPath ="E:\\javaserve\\ffmpeg.exe";
//		String path ="H://input//������.avi";
//		String outImagePath ="H:\\ceshi1.jpg";
//		System.out.println(getVideoImage(ffmpegPath,path,outImagePath));
//		//ִ���˺�ˢ��һ�¹��̣�ע��۲���Ĺ���Ŀ¼�е�image�ļ�����Ϳ���̨��ӡ���ݣ��ɹ��˰ɣ���������
//	}
}
