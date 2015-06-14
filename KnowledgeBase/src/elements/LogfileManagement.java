package elements;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class LogfileManagement {
    private String LogfileName;
    private File logFile;
    private OutputStream out;
    private String logMessage;
    private TimeManager time;

    public LogfileManagement() {
	time = new TimeManager();

	// Set Logfile Name with current Date and Time.txt
	// For Mac OS X, set path like "/Users/.../logfile/
	setLogfileName("/Users/nj/testlog/" + time.getCurrentTime() + ".txt");
	// setLogfileName("C:\\logfile\\"+ getCurrentTime() + ".txt");
	logFile = new File(getLogfileName());
	try {
	    out = new FileOutputStream(logFile);
	} catch (FileNotFoundException e1) {
	    e1.printStackTrace();
	}

	setLogMessage("");
    }

    public void generateLog(String Message) throws IOException {
	// Write Log Message with current time
	setLogMessage(time.getCurrentTime() + ": " + Message + "\n");
	out.write(logMessage.getBytes());
    }

    public void generateRequestLog(String Message) throws IOException {
	// Write Log Message with current time
	setLogMessage(time.getCurrentTime() + ": Request from Client\n"
		+ Message + "\n");
	out.write(logMessage.getBytes());
    }

    public void generateResponseLog(String Message) throws IOException {
	// Write Log Message with current time
	setLogMessage(time.getCurrentTime() + ": Server Response\n" + Message
		+ "\n");
	out.write(logMessage.getBytes());
    }

    public String getLogfileName() {
	return LogfileName;
    }

    public void setLogfileName(String logfileName) {
	LogfileName = logfileName;
    }

    public String getLogMessage() {
	return logMessage;
    }

    public void setLogMessage(String logMessage) {
	this.logMessage = logMessage;
    }
}
