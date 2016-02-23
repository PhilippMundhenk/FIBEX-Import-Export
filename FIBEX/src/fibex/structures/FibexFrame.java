package fibex.structures;

import java.util.Collection;
import java.util.LinkedHashSet;

import fibex.exceptions.FibexException;

/**
 * This class represents a frame in the FIBEX file
 * 
 * @author TUM CREATE - RP3 - Philipp Mundhenk
 */
public class FibexFrame
{
	protected Object framesRepresentation;
	protected Object frameTriggeringsRepresentation;
	protected Object docReference;
	
	protected short baseCycle;
	protected String name;
	protected short cycleRepetition;
	protected int length_Byte;
	protected short slot;
	
	protected String triggeringId;
	protected String frameId;
	private static int frameTriggerCounter = -1;
	private static int frameCounter = -1;
	
	protected Collection<FibexMessage> msgs;
	
	/**
	 * This constructor creates a new frame. Access via getInstance()
	 * 
	 * @param framesRepresentation
	 * reference to the frames node in the FIBEX file
	 * @param frameTriggeringsRepresentation
	 * reference to the frame-triggerings node in the FIBEX file
	 * @param docReference
	 * reference to the FIBEX document
	 */
	protected FibexFrame(Object framesRepresentation, Object frameTriggeringsRepresentation, Object docReference)
	{
		this.framesRepresentation = framesRepresentation;
		this.frameTriggeringsRepresentation = frameTriggeringsRepresentation;
		this.docReference = docReference;
		msgs = new LinkedHashSet<FibexMessage>();
		getNextFrameTriggerCounter();
		getNextFrameCounter();
	}
	
	/**
	 * This constructor creates a frame without reference to a FIBEX file. Access via getInstance()
	 * 
	 * @param name
	 * name (short-name) of the frame
	 * @param baseCycle
	 * base cycle of the frame
	 * @param cycleRepetition
	 * repetition of the frame
	 * @param length_Byte
	 * length of the frame in bytes
	 * @param slot
	 * slot of the frame
	 */
	protected FibexFrame(String name, short baseCycle, short cycleRepetition, int length_Byte, short slot)
	{
		this.baseCycle = baseCycle;
		this.name = name;
		this.cycleRepetition = cycleRepetition;
		this.length_Byte = length_Byte;
		this.slot = slot;
		msgs = new LinkedHashSet<FibexMessage>();
		triggeringId = "FIBEX_FRAME_TRIGGERING_"+getNextFrameTriggerCounter();
		frameId = "FIBEX_FRAME_"+getNextFrameCounter();
	}
	
	/**
	 * This method increments and returns the counter used for generating IDs for the frame-triggering node
	 * 
	 * @return
	 * incremented counter
	 */
	protected static int getNextFrameTriggerCounter()
	{
		frameTriggerCounter++;
		return frameTriggerCounter;
	}
	
	/**
	 * This message increments and returns the counter used for generating IDs for the frame node
	 * 
	 * @return
	 * incremented counter
	 */
	protected static int getNextFrameCounter()
	{
		frameCounter++;
		return frameCounter;
	}
	
	/**
	 * This method returns the ID of the frame-triggering node
	 * 
	 * @return
	 * ID of the frame-triggering node
	 */
	public String getIdTriggering()
	{
		if(this.triggeringId == null)
		{
			System.out.println("[FibexFrame:getIdTriggering] The FIBEX library does not support direct references to FIBEX files. Please implement referencing in extending library.");
			return null;
		}
		else
		{
			return this.triggeringId;
		}
	}
	
	/**
	 * This method returns the ID of the frame node
	 * 
	 * @return
	 * ID of the frame node
	 */
	public String getIdFrame()
	{
		if(this.frameId == null)
		{
			System.out.println("[FibexFrame:getIdFrame] The FIBEX library does not support direct references to FIBEX files. Please implement referencing in extending library.");
			return null;
		}
		else
		{
			return this.frameId;
		}
	}
	
	/**
	 * This method sets the references to the nodes in the FIBEX file
	 * 
	 * @param framesRepresentation
	 * reference to frames node
	 * @param frameTriggeringsRepresentation
	 * reference to frame-triggerings node
	 * @param docReference
	 */
	public void setReferences(Object framesRepresentation, Object frameTriggeringsRepresentation, Object docReference)
	{
		this.framesRepresentation = framesRepresentation;
		this.frameTriggeringsRepresentation = frameTriggeringsRepresentation;
		this.docReference = docReference;
	}
	
	/**
	 * This method returns the name (short-name) of the frame
	 * 
	 * @return
	 * name (short-name) of the frame
	 * @throws FibexException
	 */
	public String getName() throws FibexException
	{
		System.out.println("[FibexFrame:getName] This method should be overriden");
		return null;
	}
	
	/**
	 * This method returns the base cycle of the frame
	 * 
	 * @return
	 * base cycle of the frame
	 * @throws FibexException
	 */
	public int getBaseCycle() throws FibexException
	{
		System.out.println("[FibexFrame:getBaseCycle] This method should be overriden");
		return 0;
	}
	
	/**
	 * This method returns the repetition of the frame
	 * 
	 * @return
	 * repetition of the frame
	 * @throws FibexException
	 */
	public int getCycleRepetition() throws FibexException
	{
		System.out.println("[FibexFrame:getCycleRepetition] This method should be overriden");
		return 0;
	}
	
	/**
	 * This method returns the slot of the frame
	 * 
	 * @return
	 * slot of the frame
	 * @throws FibexException
	 */
	public int getSlot() throws FibexException
	{
		System.out.println("[FibexFrame:getSlot] This method should be overriden");
		return 0;
	}
	
	/**
	 * This method returns the length of the frame in bytes
	 * 
	 * @return
	 * length of the frame in bytes
	 * @throws FibexException
	 */
	public long getLength_Byte() throws FibexException
	{
		System.out.println("[FibexFrame:getLength_Byte] This method should be overriden");
		return 0;
	}
	
	/**
	 * This method returns the ECU (short-name) sending the message
	 * 
	 * @return
	 * sending ECU (short-name) of the message
	 */
	public String getSender()
	{
		System.out.println("[FibexFrame:getSender] This method should be overriden");
		return null;
	}
	
	/**
	 * This method adds a new message to the frame
	 * 
	 * @param msg
	 * message to add
	 */
	public void addMessage(FibexMessage msg)
	{
		msgs.add(msg);
	}
	
	/**
	 * This method returns all messages associated with this frame
	 * 
	 * @return
	 * collection of messages in this frame
	 */
	public Collection<FibexMessage> getMessages()
	{
		System.out.println("[FibexFrame:getMessages] This method should be overriden");
		return null;
	}
	
	/**
	 * This method creates and returns a new instance of the frame
	 * 
	 * @param framesRepresentation
	 * reference to the frames node of the FIBEX file
	 * @param frameTriggeringsRepresentation
	 * reference to the frame-triggering node of the FIBEX file
	 * @param docReference
	 * reference to the FIBEX document
	 * @return
	 * newly created FIBEX frame
	 */
	public static FibexFrame getInstance(Object framesRepresentation, Object frameTriggeringsRepresentation, Object docReference)
	{
		return new FibexFrame(framesRepresentation, frameTriggeringsRepresentation, docReference);
	}
	
	/**
	 * This method creates and returns a new instance of the frame
	 * 
	 * @param name
	 * name (short-name) of the frame
	 * @param baseCycle
	 * base cycle of the frame
	 * @param cycleRepetition
	 * repetition of the frame
	 * @param length_Byte
	 * length of the frame in bytes
	 * @param slot
	 * slot of the frame
	 * @return
	 */
	public static FibexFrame getInstance(String name, short baseCycle, short cycleRepetition, int length_Byte, short slot)
	{
		return new FibexFrame(name, baseCycle, cycleRepetition, length_Byte, slot);
	}
	
	/**
	 * This method saves the frame and all its subcomponents
	 * 
	 * @param frameTriggeringsRepresentation
	 * reference to the frame-triggerings node in the FIBEX file
	 * @param docReference
	 * reference to the FIBEX document
	 * @param additionalData
	 * additional data
	 */
	public void save(Object frameTriggeringsRepresentation, Object docReference, Object... additionalData)
	{
		System.out.println("[FibexFrame:save] This method should be overriden");
	}
	
	/**
	 * This method creates and returns a new message
	 * 
	 * @param data
	 * data to pass as parameters to the message for creation
	 * @return
	 * newly created message
	 */
	public FibexMessage createNewMessage(Object... data)
	{
		System.out.println("[FibexFrame:getNewMessage] This method should be overriden");
		return null;
	}
}
