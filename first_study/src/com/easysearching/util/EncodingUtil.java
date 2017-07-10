package com.easysearching.util;

import java.io.UnsupportedEncodingException;

public class EncodingUtil {

	// 用于ajax获取值后解决乱码问题
	public static String ConvertISOToUTF8(String source) {
		try {
			source = new String(source.replaceAll("\\s+", "").getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return source;
	}

	public static String ConvertISOToGBK(String source) {
		try {
			source = new String(source.getBytes("ISO-8859-1"), "GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return source;
	}

}
