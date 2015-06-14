package server;

import java.io.IOException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import server.MyServer;

/**
 * Application Lifecycle Listener implementation class LiveCycleListener
 *
 */
@WebListener
public class LiveCycleListener implements ServletContextListener {

    MyServer instance;

    public LiveCycleListener() {

    }

    /* Server Closing action here */
    /* Serialize and save all information into Files */
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
	try {
	    System.out.println("Shutting down is started.");
	    instance.generateLogMessage("Shutting down is started.");
	    instance.getStudents().save();
	    instance.getCompanies().save();
	    instance.getRequests().save();
	    instance.getMessages().save();
	    System.out.println("Context destroyed.");
	    instance.generateLogMessage("Context destroyed");
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
	System.out.println("Context Initialization started.");
	instance = MyServer.getInstance();
	instance.init();
	instance.generateLogMessage("Context Initialization started.");
    }

}
