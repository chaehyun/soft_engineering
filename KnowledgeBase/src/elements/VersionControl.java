package elements;

public class VersionControl
{
	private static final String currentVersion = "1.0";
	
	public String getCurrentVersion()
	{
		return currentVersion;
	}
	
	public boolean isVersionValid(String clientVersion)
	{
		if (clientVersion.equals(getCurrentVersion()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
