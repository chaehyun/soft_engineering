package elements;

import static org.junit.Assert.*;

import org.junit.Test;

import elements.Request.State;

public class StateManagerTest {

    @Test
    public final void testToState() {
	StateManager state = new StateManager();
	State s = state.toState(Request.State.NO.name());
	
	assertEquals(Request.State.NO, s);
	
	s = state.toState(Request.State.AskForDetails.name());
	
	assertEquals(Request.State.AskForDetails, s);
	
	s = state.toState(Request.State.Unanswered.name());
	
	assertEquals(Request.State.Unanswered, s);
	
	s = state.toState(Request.State.YES.name());
	
	assertEquals(Request.State.YES, s);
    }

}
