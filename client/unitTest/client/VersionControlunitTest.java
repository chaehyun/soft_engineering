/**
 * 
 */
package client;

import static org.junit.Assert.*;

import org.junit.Test;

import elements.VersionControl;

/**
 * @author nj
 *
 */
public class VersionControlunitTest
{	
	@Test
	public void test()
	{
		String currentVersion = (new VersionControl()).getCurrentVersion();
		
		// Test Null Pointer
		assertNotNull(currentVersion);
		
		// Test current Data as Expected
		assertEquals("1.0", currentVersion);
	}

}
