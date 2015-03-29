package elements;

@SuppressWarnings("serial")
public class Company extends User
{
	public String getLocation()
	{
		return location;
	}
	
	public void setLocation(String location)
	{
		this.location = location;
	}
	
	private String location;
	
	public Company(String Name, String ContactNumber, String Id,
			String Password, String Location)
	{
		super(Name, ContactNumber, Id, Password);
		this.location = Location;
	}
}
