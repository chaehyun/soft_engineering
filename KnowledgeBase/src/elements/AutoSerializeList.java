package elements;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class AutoSerializeList<T extends Serializable> extends ArrayList<T> {

    String filename;

    @SuppressWarnings({ "unchecked" })
    public AutoSerializeList(String Filename) throws IOException,
	    ClassNotFoundException {
	super();
	filename = Filename;
	try {
	    this.addAll((ArrayList<T>) (new ObjectInputStream(
		    new FileInputStream(Filename))).readObject());
	} catch (FileNotFoundException e) {
	    System.out.println(Filename + " is not exist.");
	}
    }

    public void save() throws IOException {
	try (FileOutputStream fStream = new FileOutputStream(filename);
		ObjectOutputStream oStream = new ObjectOutputStream(fStream);) {
	    oStream.writeObject(this);
	}
    }

}
