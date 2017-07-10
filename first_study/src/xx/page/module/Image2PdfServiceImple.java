/*
 *@(#)xx.page.module
 *@Image2PdfServiceImple.java.java  
 *@����ʱ��:2016-4-1����9:17:31
 *@���ߣ�������
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
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
 * @Image2PdfServiceImple <code>{������}</code>
 * @author  {������}
 * @version {�汾,����ʱ�����}
 * @{��������:} 
 */

@Service("image2PdfService")
@Transactional
public class Image2PdfServiceImple implements Image2PdfService{

	/**
	 * @{������}
	 * @param {���������} {�������˵��}
	 * @return {���ز�����} {���ز���˵��}
	 * @throws IOException 
	 * @throws DocumentException 
	 * @{�����Ĺ���/��������}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	 */

	
	@Override
	public void convert(String imgPath, String pdfFile) throws IOException, DocumentException {
		// TODO Auto-generated method stub
		BufferedImage img = ImageIO.read(new File(imgPath));
		FileOutputStream fos = new FileOutputStream(pdfFile);
		// ����PDF�ĵ�
		Document doc = new Document(null, 0, 0, 0, 0);
		
		// ���óߴ�ΪͼƬ�ߴ�
		doc.setPageSize(new Rectangle(img.getWidth(), img.getHeight()));
		Image image = Image.getInstance(imgPath);
		PdfWriter.getInstance(doc, fos);
		doc.open();
		doc.add(image);
		doc.close();
	}

}
