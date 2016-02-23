package fibex.exceptions;

/**
 * This class is a FIBEX exception describing that the loaded file is not a valid FIBEX file
 * 
 * @author TUM CREATE - RP3 - Philipp Mundhenk
 */
public class FIBEXNotValidFileException extends FibexException
{
	private static final long serialVersionUID = 1L;

	/**
	 * This constructor creates a new exception
	 */
	public FIBEXNotValidFileException()
	{
		super("File is not a valid FIBEX file");
	}
}
