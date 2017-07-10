package com.easysearching.lucene.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hslf.HSLFSlideShow;
import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.model.TextRun;
import org.apache.poi.hslf.usermodel.SlideShow;

public class PPTReader extends FileReader {

	private File file;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public PPTReader(File file) {
		this.file = file;
	}

	public PPTReader() {

	}

	@Override
	public String getContent() {
		InputStream fis = null;
		SlideShow slideShow = null;
		StringBuffer content = new StringBuffer();
		try {
			fis = new FileInputStream(file);
			slideShow = new SlideShow(new HSLFSlideShow(fis));
			Slide[] slides = slideShow.getSlides(); // 获取每一张幻灯片
			TextRun[] textRun = null;
			for (int i = 0; i < slides.length; i++) {
				textRun = slides[i].getTextRuns();// 为了取得幻灯片的文字内容，建立TextRun
				content.append(slides[i].getTitle()).append(System.getProperty("line.separator")); // 获取每一张幻灯片的标题
				for (int j = 0; j < textRun.length; j++) {
					content.append(textRun[j].getText());// 这里会将文字内容加到content中去
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return content.toString();
	}

}
