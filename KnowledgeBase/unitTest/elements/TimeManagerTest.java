package elements;

import static org.junit.Assert.*;

import org.junit.Test;

public class TimeManagerTest {

    @Test
    public final void testGetCurrentTime() {
	TimeManager time = getDummy();
	String test = time.getCurrentTime();
	
	assertNotNull(test);
    }

    @Test
    public final void testGetMsgTime() {
	TimeManager time = getDummy();
	String test = time.getMsgTime();
	
	assertNotNull(test);
    }
    
    public TimeManager getDummy() {
	TimeManager time = new TimeManager();
	
	return time;
    }

}
