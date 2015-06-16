package elements;

import static org.junit.Assert.*;

import org.junit.Test;

public class VersionControlTest {

    @Test
    public void testGetCurrentVersion() {
	VersionControl version = new VersionControl();
	String currentVersion = version.getCurrentVersion();

	// Test Null Pointer
	assertNotNull(currentVersion);

	// Test current Data as Expected
	assertEquals(version.getCurrentVersion(), currentVersion);
    }

    @Test
    public void testIsVersionValid() {
	String clientVersion = "1.0";
	VersionControl version = new VersionControl();

	// Test current version as expected
	assertEquals(version.getCurrentVersion(), clientVersion);
    }

}
