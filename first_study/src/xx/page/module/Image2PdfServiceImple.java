/*
 *@(#)xx.page.module
 *@Image2PdfServiceImple.java.java  
 *@创建时间:2016-4-1下午9:17:31
 *@作者：朱永科
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.page.module;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfWriter;

/**
 * @Image2PdfServiceImple <code>{类名称}</code>
 * @author  {朱永科}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */

@Service("image2PdfService")
@Transactional
public class Image2PdfServiceImple implements Image2PdfService{

	/**
	 * @{方法名}
	 * @param {引入参数名} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @throws IOException 
	 * @throws DocumentException 
	 * @{方法的功能/动作描述}
	 * @exception {说明在某情况下,将发生什么异常}
	 */

	
	@Override
	public void convert(String imgPath, String pdfFile) throws IOException, DocumentException {
		// TODO Auto-generated method stub
		BufferedImage img = ImageIO.read(new File(imgPath));
		FileOutputStream fos = new FileOutputStream(pdfFile);
		// 创建PDF文档
		Document doc = new Document(null, 0, 0, 0, 0);
		
		// 设置尺寸为图片尺寸
		doc.setPageSize(new Rectangle(img.getWidth(), img.getHeight()));
		Image image = Image.getInstance(imgPath);
		PdfWriter.getInstance(doc, fos);
		doc.open();
		doc.add(image);
		doc.close();
	}

}
