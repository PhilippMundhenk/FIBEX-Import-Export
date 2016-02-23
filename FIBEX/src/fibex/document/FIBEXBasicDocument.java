package fibex.document;

/**
 * This class defines the basic functionality a document needs to implement to be treated as a FIBEX document
 * 
 * @author TUM CREATE - RP3 - Philipp Mundhenk
 */
public abstract class FIBEXBasicDocument implements FIBEXBasicInterface
{
	/**
	 * This constructor creates a document
	 */
	public FIBEXBasicDocument() 
	{
	}
	
	/**
	 * This constructor creates a document and loads a FIBEX file
	 * 
	 * @param fileName
	 * path and name of a FIBEX file
	 */
	public FIBEXBasicDocument(String fileName)
	{
		loadDocument(fileName);
	}
	
	/**
	 * This method saves the document and all subcomponents
	 * 
	 * @param fileName
	 * path and name of the file to save to
	 */
	public abstract void save(String fileName);
	
	/**
	 * This method loads a FIBEX file
	 * 
	 * @param fileName
	 * path and name of a FIBEX file
	 */
	public abstract void loadDocument(String fileName);
}