package backend;

import java.net.URL;
import java.net.URLConnection;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class servletListener implements ServletContextListener {
	public void contextDestroyed(ServletContextEvent arg0) {
	}
	public void contextInitialized(ServletContextEvent arg0) {
	System.out.println("init backend gettweet");
	try {
		backend.appThread.go();
	}catch(Exception e) {
	System.out.println("init fails");
	e.printStackTrace();
	}
	}
}
