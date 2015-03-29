package elements;

import java.io.Serializable;

@SuppressWarnings("serial")
public class User implements Serializable
{
	
	protected String name;
	protected String contactNumber;
	protected String id;
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getContactNumber()
	{
		return contactNumber;
	}
	
	public void setContactNumber(String contactNumber)
	{
		this.contactNumber = contactNumber;
	}
	
	public String getId()
	{
		return id;
	}
	
	public void setId(String id)
	{
		this.id = id;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	protected String password;
	
	public User(String Name, String ContactNumber, String Id, String Password)
	{
		name = Name;
		contactNumber = ContactNumber;
		id = Id;
		password = Password;
	}
	
}
