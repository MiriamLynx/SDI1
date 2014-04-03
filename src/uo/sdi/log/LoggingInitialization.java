package uo.sdi.log;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import alb.util.log.Log;
import alb.util.log.LogLevel;

public class LoggingInitialization implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		String level = arg0.getServletContext().getInitParameter("logLevel");
		switch (level) {
		case "OFF":
			Log.setLogLevel(LogLevel.OFF);
			break;
		case "INFO":
			Log.setLogLevel(LogLevel.INFO);
			break;
		// Se ha alcanzado algún hito importante en la ejecución del programa.
		// ... }
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}
}