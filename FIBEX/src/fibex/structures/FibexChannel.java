package fibex.structures;

import java.util.Collection;
import java.util.LinkedHashSet;

import fibex.exceptions.FibexException;

/**
 * This class represents a channel in the FIBEX document
 * 
 * @author TUM CREATE - RP3 - Philipp Mundhenk
 */
public class FibexChannel 
{
	protected Object channelRepresentation;
	protected Object docReference;
	protected String id;
	
	protected Collection<FibexFrame> frames = new LinkedHashSet<FibexFrame>();
	
	/**
	 * This constructor creates a channel from a reference to a FIBEX file. Access via getInstance()
	 * 
	 * @param channelRepresentation
	 * reference to the channel node
	 * @param docReference
	 * reference to the FIBEX document
	 */
	protected FibexChannel(Object channelRepresentation, Object docReference)
	{
		this.channelRepresentation = channelRepresentation;
		this.docReference = docReference;
	}
	
	//TODO: add constructor to create instance without relation to FIBEX file. Also needs to include setting of id
	
	/**
	 * This method sets the channel node
	 * 
	 * @param channelRepresentation
	 * reference to the channel node
	 */
	public void setChannel(Object channelRepresentation)
	{
		this.channelRepresentation = channelRepresentation;
	}
	
	/**
	 * This method returns the name (short-name) of the channel
	 * 
	 * @return
	 * name (short-name) of the channel
	 */
	public String getName()
	{
		return null;
	}
	
	/**
	 * This method returns the ID of the channel node
	 * 
	 * @return
	 * ID of the channel node
	 */
	public String getId()
	{
		if(this.id == null)
		{
			System.out.println("[FibexChannel:getId] The FIBEX library does not support direct references to FIBEX files. Please implement referencing in extending library.");
			return null;
		}
		else
		{
			return this.id;
		}
	}
	
	/**
	 * This method returns all frames contained in this channel
	 * 
	 * @return
	 * collection of all FibexFrames in the channel
	 * @throws FibexException
	 */
	public Collection<FibexFrame> getFrames() throws FibexException
	{
		return null;
	}
	
	/**
	 * This method creates and returns a new instance of a FibexChannel
	 * 
	 * @param channelRepresentation
	 * reference to the channel node
	 * @param docReference
	 * reference to the FIBEX document
	 * @return
	 * newly created channel representation
	 */
	public static FibexChannel getInstance(Object channelRepresentation, Object docReference)
	{
		return null;
	}
	
	/**
	 * This methods adds a frame to the channel
	 * 
	 * @param frame
	 * frame to add
	 */
	public void addFrame(FibexFrame frame)
	{
		frames.add(frame);
	}
	
	/**
	 * This method saves the channel and all its subcomponents. Access via FibexFile.save()
	 * 
	 * @param additionalData
	 * additional data
	 */
	public void save(Object... additionalData)
	{
		
	}
	
	/**
	 * This method creates and returns a new frame
	 * 
	 * @param data
	 * initialization data to pass to the frame
	 * @return
	 * newly created frame
	 */
	public FibexFrame createNewFrame(Object... data)
	{
		System.out.println("[FibexChannel:getNewFrame] This method should be overriden");
		return null;
	}
	
	/**
	 * This method returns a frame with the specified data. If the frame does not exist yet, it will be created.
	 * 
	 * @param data
	 * initialization data to pass to the frame
	 * @return
	 * frame
	 */
	public FibexFrame getOrCreateFrame(Object... data) throws FibexException
	{
		System.out.println("[FibexChannel:getOrCreateFrame] This method should be overriden");
		return null;
	}
}
