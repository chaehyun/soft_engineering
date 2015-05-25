package elements;

import static org.junit.Assert.*;

import org.junit.Test;

public class VersionControlTest
{
	
	@Test
	public void testGetCurrentVersion()
	{
		String currentVersion = (new VersionControl()).getCurrentVersion();

		// Test Null Pointer
		assertNotNull(currentVersion);
		
		// Test current Data as Expected
		assertEquals("1.0", currentVersion);
	}
	
	@Test
	public void testIsVersionValid()
	{
		String clientVersion = "1.0";
		
		// Test current version as expected
		assertEquals("1.0", clientVersion);
	}
	
}
