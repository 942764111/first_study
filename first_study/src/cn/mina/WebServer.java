package cn.mina;

import java.io.IOException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.xlightweb.server.HttpServer;

public class WebServer extends HttpServlet implements ServletContextListener,
HttpSessionListener {

	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void contextInitialized(ServletContextEvent arg0) {
		
		 try {           
			 WebSocketServer.start();
	        } catch (IOException e) {
	          
	            e.printStackTrace();
	        }
	}

}
