package com.easysearching.web.util;

import java.io.PrintWriter;

public class JSUtil {

	public static void alert(PrintWriter out, String msg) {
		out.println("<script type='text/javascript'>alert('" + msg + "');</script>");
		out.close();
	}

	public static void location(PrintWriter out, String url) {
		out.println("<script type='text/javascript'>location.href='" + url + "'</script>");
		out.close();
		return;
	}

	public static void location(PrintWriter out, String msg, String url) {
		out.println("<script type='text/javascript'>alert('" + msg + "');location.href='" + url + "'</script>");
		out.close();
		return;
	}

	public static void back(PrintWriter out, String msg) {
		out.println("<script type='text/javascript'>alert('" + msg + "');history.back();</script>");
		out.close();
		return;
	}

}
