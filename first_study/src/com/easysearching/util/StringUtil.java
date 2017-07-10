package com.easysearching.util;

public class StringUtil {

	private static final String prefixHTML = "<font color='red'>";
	private static final String suffixHTML = "</font>";

	public static String handlerHighlighterForHTML(String sourceText) {
		if (null == sourceText || "".equals(sourceText) || "".equals(sourceText.trim())) {
			return "";
		}
		return sourceText.replaceAll("\\^\\.\\.", prefixHTML).replaceAll("\\$\\.\\.", suffixHTML);
	}

	public static void main(String[] args) {
		String sourceText = "^.Java$.语言基础知识^.Java$.语言基础知识议程议程简单数据类型运算符和表达式控制语句数组 标识符标识符程序员对程序中的各个元素加以命名时使用的命名记号称为标识符（identifier）。${begin}Java${end}语言中，标识符是以字母，下划线（_）,美元符($)开始的一个字符序列，后面可以跟字母，下划线，美元符，数字。例如，identifier，userName，User_Name，_sys_val， $change为合法的标识符，而2ma...</";
		System.out.println(StringUtil.handlerHighlighterForHTML(sourceText));
	}
}
