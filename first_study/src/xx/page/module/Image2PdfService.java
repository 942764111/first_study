/*
 *@(#)xx.page.module
 *@Image2PdfService.java.java  
 *@����ʱ��:2016-4-1����9:25:48
 *@���ߣ�������
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.page.module;

import java.io.IOException;

import com.lowagie.text.BadElementException;
import com.lowagie.text.DocumentException;

/**
 * @Image2PdfService <code>{������}</code>
 * @author  {������}
 * @version {�汾,����ʱ�����}
 * @{��������:} 
 */

public interface Image2PdfService {

	/**
	 * @{������}
	 * @param {���������} {�������˵��}
	 * @return {���ز�����} {���ز���˵��}
	 * @throws BadElementException 
	 * @throws DocumentException 
	 * @{�����Ĺ���/��������}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	
	void convert(String imgPath, String pdfFile) throws IOException, BadElementException, DocumentException;

}
