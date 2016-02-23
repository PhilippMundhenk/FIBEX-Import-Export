package fibex.exceptions;

/**
 * This class is a FIBEX exception representing the fact that the FIBEX file to be loaded contains 
 * a version number which is not supported by the given libraries. 
 * As long as newer FIBEX versions support older files (downwards compatibility), a version override 
 * to a new version of the FIBEX library which is present might solve this issue.
 * 
 * @author TUM CREATE - RP3 - Philipp Mundhenk
 */
public class FIBEXUnsupportedVersionException extends FibexException
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * This constructor creates a new exceptio
	 */
	public FIBEXUnsupportedVersionException()
	{
		super("Version of FIBEX file not supported");
	}
}
