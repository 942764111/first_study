package com.easysearching.lucene.exception;

public class FileTypeNotSupportException extends Exception{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FileTypeNotSupportException(String fileType)
	{
		super("the file type:"+fileType+" is not supported by FileReader to parse!");
	}

}
