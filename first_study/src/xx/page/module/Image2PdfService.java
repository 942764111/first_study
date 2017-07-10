/*
 *@(#)xx.page.module
 *@Image2PdfService.java.java  
 *@创建时间:2016-4-1下午9:25:48
 *@作者：朱永科
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.page.module;

import java.io.IOException;

import com.lowagie.text.BadElementException;
import com.lowagie.text.DocumentException;

/**
 * @Image2PdfService <code>{类名称}</code>
 * @author  {朱永科}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */

public interface Image2PdfService {

	/**
	 * @{方法名}
	 * @param {引入参数名} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @throws BadElementException 
	 * @throws DocumentException 
	 * @{方法的功能/动作描述}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	
	void convert(String imgPath, String pdfFile) throws IOException, BadElementException, DocumentException;

}
