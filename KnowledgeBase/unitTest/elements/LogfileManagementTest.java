package elements;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class LogfileManagementTest {

    @Test
    public final void testGenerateLog() {
	LogfileManagement log = getDummyLogfile();
	try {
	    log.generateLog("Test Case");
	    
	    assertNotNull(log.getLogMessage());
	} catch (IOException e) {
	    e.printStackTrace();
	    fail();
	}
    }

    @Test
    public final void testGenerateRequestLog() {
	LogfileManagement log = getDummyLogfile();
	try {
	    log.generateRequestLog("Test Case");
	    
	    assertNotNull(log.getLogMessage());
	} catch (IOException e) {
	    e.printStackTrace();
	    fail();
	}
    }

    @Test
    public final void testGenerateResponseLog() {
	LogfileManagement log = getDummyLogfile();
	try {
	    log.generateResponseLog("Test Case");
	    
	    assertNotNull(log.getLogMessage());
	} catch (IOException e) {
	    e.printStackTrace();
	    fail();
	}
    }

    public LogfileManagement getDummyLogfile() {
	LogfileManagement log = new LogfileManagement();
	
	return log;
    }
}
