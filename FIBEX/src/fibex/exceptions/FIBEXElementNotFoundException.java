package fibex.exceptions;

/**
 * This class is a FIBEX exception describing a case in which a certain node which should be accessed is not present
 * 
 * @author TUM CREATE - RP3 - Philipp Mundhenk
 */
public class FIBEXElementNotFoundException extends FibexException
{
	private static final long serialVersionUID = 1L;

	/**
	 * This constructor creates a new exception
	 */
	public FIBEXElementNotFoundException()
	{
		super("Requested element not found");
	}
}
