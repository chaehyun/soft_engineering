package elements;

import java.io.Serializable;

import org.json.JSONArray;

@SuppressWarnings("serial")
public class Message implements Serializable {

	private String source;
	private String dest;
	private JSONArray data;
	private boolean checked;

	public Message(String source, String dest, JSONArray data, boolean checked) {
		super();
		this.source = source;
		this.dest = dest;
		this.data = data;
		this.checked = checked;
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

	public JSONArray getData() {
		return data;
	}

	public void setData(JSONArray data) {
		this.data = data;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

}
