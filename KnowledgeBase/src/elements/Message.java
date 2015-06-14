package elements;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;

import server.MyServer;

//@SuppressWarnings("serial")
public class Message implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 349031182432343083L;
    private String source;
    private String dest;
    private ArrayList<String> data;
    private boolean checked;
    private String sentTime;

    private void writeObject(ObjectOutputStream oos) throws IOException {
	// default serialization
	oos.defaultWriteObject();
	// write company id
	// oos.writeObject(company.getId());
    }

    private void readObject(java.io.ObjectInputStream in) throws IOException,
	    ClassNotFoundException {
	// default serialization
	in.defaultReadObject();
	// read company id
	// String companyID;
	// companyID = (String) in.readObject();
	// set company
	// company = MyServer.getInstance().getCompanyById(companyID);
    }

    public Message(String source, String dest, ArrayList<String> data,
	    boolean checked, String sentTime) {
	super();
	this.source = source;
	this.dest = dest;
	this.data = data;
	this.checked = checked;
	this.sentTime = sentTime;
    }

    public String getSource() {
	return source;
    }

    public void setSource(String source) {
	this.source = source;
    }

    public String getDest() {
	return dest;
    }

    public void setDest(String dest) {
	this.dest = dest;
    }

    public boolean isChecked() {
	return checked;
    }

    public void setChecked(boolean checked) {
	this.checked = checked;
    }

    public ArrayList<String> getData() {
	return data;
    }

    public void setData(ArrayList<String> data) {
	this.data = data;
    }

    public String getSentTime() {
	return sentTime;
    }

    public void setSentTime(String sentTime) {
	this.sentTime = sentTime;
    }

}
