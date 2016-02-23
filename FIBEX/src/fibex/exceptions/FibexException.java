package fibex.exceptions;

/**
 * This class represents a generic FIBEX exception. It should not be used directly, but only via an inheriting exception.
 * 
 * @author TUM CREATE - RP3 - Philipp Mundhenk
 */
public class FibexException extends Exception
{
	private static final long serialVersionUID = 1L;

	/**
	 * This constructor creates a new FIBEX exception
	 */
	public FibexException()
	{
		super("FibexException");
	}
	
	/**
	 * This constructor creates a new FIBEX exception with a given message
	 * 
	 * @param msg
	 * message to include/display
	 */
	public FibexException(String msg)
	{
		super("FibexException: " + msg);
	}
}
