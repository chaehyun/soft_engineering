package elements;

import elements.Request.State;

public class StateManager {
    public State toState(String state) {
	State tmp = null;

	if (state.equals(State.AskForDetails.name()))
	    tmp = State.AskForDetails;
	else if (state.equals(State.NO.name()))
	    tmp = State.NO;
	else if (state.equals(State.Unanswered.name()))
	    tmp = State.Unanswered;
	else if (state.equals(State.YES.name()))
	    tmp = State.YES;

	return tmp;
    }
}
