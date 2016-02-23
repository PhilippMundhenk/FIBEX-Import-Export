package fibex.structures;

import java.util.Collection;

/**
 * This class represents a FIBEX message. This can for example be a signal or a PDU in FIBEX
 * 
 * @author TUM CREATE - RP3 - Philipp Mundhenk
 */
public class FibexMessage
{
	protected Object elementRepresentation;
	protected Object instanceRepresentation;
	protected Object docReference;
	
	protected int offset;
	protected int length_Bit;
	protected String name;
	
	protected String idMessage;
	protected String idInstance;
	protected String idCoding;
	
	protected static int messageCounter = -1;
	protected static int instanceCounter = -1;
	protected static int codingCounter = -1;
	
	/**
	 * This constructor creates a new message. Access via getInstance()
	 * 
	 * @param offset
	 * offset of the message inside the frame
	 * @param length_Bit
	 * length of the message in bit
	 * @param name
	 * name (short-name) of the message
	 */
	protected FibexMessage(int offset, int length_Bit, String name)
	{
		this.offset = offset;
		this.length_Bit = length_Bit;
		this.name = name;
		idMessage = "FIBEX_SIGNAL_" + getNextMessageCounter();
		idInstance = "FIBEX_SIGNAL_INST_"+getNextInstanceCounter();
		idCoding = "FIBEX_CODING_" + getNextCodingCounter();
	}
	
	/**
	 * This constructor creates a message with reference to a FIBEX file. Access via getInstance()
	 * 
	 * @param elementRepresentation
	 * representation of the message node
	 * @param instanceRepresentation
	 * reference to the message instance node
	 * @param docReference
	 * reference to the FIBEX document
	 * @param settings
	 * a string defining additional settings
	 */
	protected FibexMessage(Object elementRepresentation, Object instanceRepresentation, Object docReference, String settings)
	{
		this.elementRepresentation = elementRepresentation;
		this.instanceRepresentation = instanceRepresentation;
		this.docReference = docReference;
	}
	
	/**
	 * This method returns the ID of the message node
	 * 
	 * @return
	 * ID of the message node
	 */
	public String getIdMessage()
	{
		if(this.idMessage == null)
		{
			System.out.println("[FibexMessage:getIdMessage] The FIBEX library does not support direct references to FIBEX files. Please implement referencing in extending library.");
			return null;
		}
		else
		{
			return this.idMessage;
		}
	}
	
	/**
	 * This message returns the ID of the instance node of the message
	 * 
	 * @return
	 * ID of the instance node
	 */
	public String getIdInstance()
	{
		if(this.idInstance == null)
		{
			System.out.println("[FibexMessage:getIdInstance] The FIBEX library does not support direct references to FIBEX files. Please implement referencing in extending library.");
			return null;
		}
		else
		{
			return this.idInstance;
		}
	}
	
	/**
	 * This method returns the ID of the config node of the message
	 * 
	 * @return
	 * ID of the config node
	 */
	public String getIdCoding()
	{
		if(this.idCoding == null)
		{
			System.out.println("[FibexMessage:getIdCoding] The FIBEX library does not support direct references to FIBEX files. Please implement referencing in extending library.");
			return null;
		}
		else
		{
			return this.idCoding;
		}
	}
	
	/**
	 * This method increments and returns the counter used to generate the ID for the message node
	 * 
	 * @return
	 * incremented counter value
	 */
	protected static int getNextMessageCounter()
	{
		messageCounter++;
		return messageCounter;
	}
	
	/**
	 * This method increments and returns the counter used to generate the ID for the instance node
	 * 
	 * @return
	 * incremented counter value
	 */
	protected static int getNextInstanceCounter()
	{
		instanceCounter++;
		return instanceCounter;
	}
	
	/**
	 * This method increments and returns the counter used to generate the ID for the coding node
	 * 
	 * @return
	 * incremented counter value
	 */
	protected static int getNextCodingCounter()
	{
		codingCounter++;
		return codingCounter;
	}
	
	/**
	 * This method creates and returns a new instance of message
	 * 
	 * @param instancesRepresentation
	 * reference to the instances node in the FIBEX file
	 * @param docReference
	 * reference to the FIBEX document
	 * @return
	 * newly created message
	 */
	public static FibexMessage getInstance(Object instancesRepresentation, Object docReference)
	{
		return null;
	}
	
	/**
	 * This method creates and returns a new instance of message
	 * 
	 * @param offset
	 * offset in bit of the message inside the frame
	 * @param length_Bit
	 * length of the message in bit
	 * @param name
	 * name (short-name) of the message
	 * @return
	 * newly created message
	 */
	public static FibexMessage getInstance(int offset, int length_Bit, String name)
	{
		return new FibexMessage(offset, length_Bit, name);
	}
	
	/**
	 * This method returns the offset of the message inside a frame in bit
	 * 
	 * @return
	 * offset in bit
	 */
	public int getOffset_Bit()
	{
		return 0;
	}
	
	/**
	 * This method returns the length of the message in bit
	 * 
	 * @return
	 * length in bit
	 */
	public long getLength_Bit()
	{
		return 0;
	}
	
	/**
	 * This method returns the name (short-name) of the message
	 * 
	 * @return
	 * name (short-name) of the message
	 */
	public String getName()
	{
		return null;
	}
	
	/**
	 * This method returns all receivers of this frame.
	 * 
	 * @return
	 * collection of receivers
	 */
	public Collection<Object> getReceivers()
	{
		System.out.println("[FibexMessage:getReceivers] This method should be overriden");
		return null;
	}
	
	/**
	 * This method adds a new submessage to this message. This can be used for 
	 * implementation of signals in FIBEX 3, if this message is a PDU
	 * 
	 * @param msg
	 * message to add
	 * @return
	 * 0: success, -1: failure
	 */
	public int addMessage(FibexMessage msg)
	{
		return -1;
	}
	
	/**
	 * This method saves the message and all subcomponents
	 * 
	 * @param instanceReference
	 * reference to the message instance node
	 * @param docReference
	 * reference to the FIBEX document
	 * @param additionalData
	 * additional data
	 */
	public void save(Object instanceReference, Object docReference, Object... additionalData)
	{
		
	}
}
