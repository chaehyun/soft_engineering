package elements;

public class VersionControl
{
	private static final String currentVersion = "1.0";	//Version begins with 1.0.
	
	/*
	 * Author : Chaehyun Ra
	 * Return Type : String
	 * Description : Method for returning the value of currentVersion.
	 */
	public String getCurrentVersion()
	{
		return currentVersion;		
	}
	
	
	/*
	 * Author : Chaehyun Ra
	 * Return Type : boolean
	 * Description : Method for checking the current program version is the newest one or not.
	 * currentVersion = newestVersion, then return true.
	 * else , then return false.
	 */	
	public boolean isVersionValid(String clientVersion)
	{
		if(clientVersion.equals(getCurrentVersion()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}	

}
