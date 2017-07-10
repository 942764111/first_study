package xx.page.module;

import java.io.File;
import java.util.List;

public class VideoPictureUtil {
	/**
	 * 获得视频缩略图，获取成功返回true，获取失败返回false
	 * @param ffmpegPath  是ffmpeg.exe存放的路径
	 * @param path   是视频文件的存放路径
	 * @param outImagePath 输出缩略图的保存路径
	 * @return
	 */
	public static boolean getVideoImage(String path,String outImagePath) {
		String ffmpegPath="E:\\javaserve\\ffmpeg.exe";
		File file = new File(path);
		if (!file.exists()) {//判断视频文件是否存在
			System.err.println("路径[" + path + "]对应的视频文件不存在!");
			return false;
		}
		//设置参数
		List<String> commands = new java.util.ArrayList<String>();
		commands.add(ffmpegPath);//这里设置ffmpeg.exe存放的路径
		commands.add("-i");
		commands.add(path);//这里是设置要截取缩略图的视频的路径
		commands.add("-y");
		commands.add("-f");
		commands.add("image2");
		commands.add("-ss"); 
		commands.add("2");//这里设置的是要截取视频开始播放多少秒后的图，可以自己设置时间
		commands.add("-t");
		commands.add("0.001");
		commands.add("-s");
		commands.add("320x240");//这里设置输出图片的大小
		commands.add(outImagePath);//这里设置输出的截图的保存路径
	
		try {
		    //截取缩略图并保存	
			ProcessBuilder builder = new ProcessBuilder();
			builder.command(commands);
			builder.start();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
//	//测试类**************帅吧!!!
//	public static void main(String[] args)throws Exception{
//		//获取当前工程目录
//		File directory = new File("."); 
//		String root = directory.getCanonicalPath();
//		//经过上面的两步root=d:/videoimage
//		String ffmpegPath ="E:\\javaserve\\ffmpeg.exe";
//		String path ="H://input//开班简介.avi";
//		String outImagePath ="H:\\ceshi1.jpg";
//		System.out.println(getVideoImage(ffmpegPath,path,outImagePath));
//		//执行了后，刷新一下工程，注意观察你的工程目录中的image文件夹里，和控制台打印内容，成功了吧，，，，，
//	}
}
