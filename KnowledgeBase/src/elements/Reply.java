package elements;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import database.getstudentbyid;
import server.MyServer;

@SuppressWarnings("serial")
public class Reply implements Serializable {

	public static enum State {
		YES, NO, AskForDetails, Unanswered
	}

	private transient Student student;

	private State state;
	private ArrayList<String> message;
	
	private void writeObject(ObjectOutputStream oos) throws IOException {
		// default serialization
		oos.defaultWriteObject();
		// write student id
		oos.writeObject(student.getId());
	}
	
	private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
		// default serialization
		in.defaultReadObject();
		//read company id
		String studentID;
		studentID = (String) in.readObject();
		//set company
		student = new getstudentbyid().getstudentbyid(studentID);
	}

	public Reply(Student student, State state, ArrayList<String> message) {
		super();
		this.student = student;
		this.state = state;
		this.message = message;
	}
	
	public Reply(Student student) {
		this.student = student;
		this.state = State.Unanswered;
		this.message = new ArrayList<>();
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public ArrayList<String> getMessage() {
		return message;
	}

	public void setMessage(ArrayList<String> message) {
		this.message = message;
	}

}
