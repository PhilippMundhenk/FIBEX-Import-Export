package fibex.structures;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;

import net.asam.xml.fbx.CHANNELTYPE;
import net.asam.xml.fbx.FIBEXDocument;
import fibex.document.fibex2_0_1.FIBEXDocument2_0_1;
import fibex.exceptions.FibexException;
import fibex.protocols.flexray.FibexFlexRayChannel2_0_1;

/**
 * This class implements the functionality of FibexChannel for FIBEX 2.0.1
 * 
 * @author TUM CREATE - RP3 - Philipp Mundhenk
 */
public class FibexChannel2_0_1 extends FibexChannel
{
	Boolean loaded = false;
	private static FIBEXDocument2_0_1 fibexDocument;
	private Collection<FibexFrame> frames = new LinkedHashSet<FibexFrame>();
	
	/**
	 * This constructor creates a new channel. Access via getInstance()
	 * 
	 * @param objectReference
	 * reference to the channel node
	 * @param docReference
	 * reference to FIBEX document
	 */
	protected FibexChannel2_0_1(Object objectReference, Object docReference)
	{
		super(objectReference, docReference);
	}
	
	/**
	 * This method returns a channel for a given ID
	 * 
	 * @param idRef
	 * ID of the requested channel
	 * @return
	 * requested channel or null, if not found
	 */
	public static FibexChannel getChannel(String idRef)
	{
		for (int i = 0; i < fibexDocument.getDocument().getFIBEX().getELEMENTS().getCHANNELS().getCHANNELArray().length; i++)
		{
			if(fibexDocument.getDocument().getFIBEX().getELEMENTS().getCHANNELS().getCHANNELArray()[i].getID().equals(idRef))
			{
				return FibexChannel2_0_1.getInstance(fibexDocument.getDocument().getFIBEX().getELEMENTS().getCHANNELS().getCHANNELArray()[i], fibexDocument.getDocument());
			}
		}
		return null;
	}

	/**
	 * This method sets the document which contains all FIBEX data
	 * 
	 * @param fibexDocument2_0_1
	 * document with FIBEX data
	 */
	public static void setDocument(FIBEXDocument2_0_1 fibexDocument2_0_1)
	{
		fibexDocument = fibexDocument2_0_1;
	}

	/**
	 * This method returns the name (short-name) of the channel
	 * 
	 * @return
	 * name (short-name) of channel
	 */
	@Override
	public String getName()
	{
		return ((CHANNELTYPE)channelRepresentation).getSHORTNAME();
	}
	
	/**
	 * This method creates and returns a new instance of the channel
	 * 
	 * @param channelRepresentation
	 * reference to the channel node
	 * @param docReference
	 * reference to the FIBEX document
	 * @return
	 * newly created channel or null if given channel node does not contain channel
	 */
	public static FibexChannel getInstance(Object channelRepresentation, Object docReference)
	{
		if(channelRepresentation instanceof net.asam.xml.fbx.flexray.CHANNELTYPE)
		{
			return FibexFlexRayChannel2_0_1.getInstance(channelRepresentation, docReference);
		}
		else if(channelRepresentation instanceof net.asam.xml.fbx.CHANNELTYPE)
		{
			return new FibexChannel2_0_1(channelRepresentation, docReference);
		}
		else
		{
			return null;
		}
	}

	/**
	 * This method returns all frames
	 * 
	 * @return
	 * collection of all frames
	 */
	@Override
	public Collection<FibexFrame> getFrames() throws FibexException
	{
		try
		{
			if(!loaded)
			{
				for (int i = 0; i < ((CHANNELTYPE)channelRepresentation).getFRAMETRIGGERINGS().getFRAMETRIGGERINGArray().length; i++)
				{
					for (int j = 0; j < ((FIBEXDocument)docReference).getFIBEX().getELEMENTS().getFRAMES().getFRAMEArray().length; j++)
					{
						if(((FIBEXDocument)docReference).getFIBEX().getELEMENTS().getFRAMES().getFRAMEArray(j).getID().equals(((CHANNELTYPE)channelRepresentation).getFRAMETRIGGERINGS().getFRAMETRIGGERINGArray(i).getFRAMEREF().getIDREF()))
						{
							FibexFrame frame = FibexFrame2_0_1.getInstance(((FIBEXDocument)docReference).getFIBEX().getELEMENTS().getFRAMES().getFRAMEArray(j), ((CHANNELTYPE)channelRepresentation).getFRAMETRIGGERINGS().getFRAMETRIGGERINGArray(i), docReference);
							if(frame != null)
							{
								frames.add(frame);
							}
						}
					}
				}
				loaded = true;
			}
		}
		catch(Exception e)
		{
			return null;
			//throw new FIBEXElementNotFoundException();
		}
		
		return frames;
	}
	
	/**
	 * This method returns the ID of the channel node
	 * 
	 * @return
	 * ID of channel node
	 */
	@Override
	public String getId()
	{
		if(this.id == null)
		{
			return ((CHANNELTYPE)channelRepresentation).getID();
		}
		else
		{
			return this.id;
		}
	}
	
	/**
	 * This method saves the channel and all subcomponents
	 * 
	 * @param additionalData
	 * additional data
	 */
	@Override
	public void save(Object... additionalData)
	{
		for (Iterator<FibexFrame> i = frames.iterator(); i.hasNext();)
		{
			FibexFrame frame = (FibexFrame) i.next();
			
			if(((CHANNELTYPE)channelRepresentation).getFRAMETRIGGERINGS() == null)
			{
				((CHANNELTYPE)channelRepresentation).addNewFRAMETRIGGERINGS();
			}
			frame.save(((CHANNELTYPE)channelRepresentation).getFRAMETRIGGERINGS().addNewFRAMETRIGGERING(), docReference, (String)additionalData[0]);
		}
	}
	
	/**
	 * This method adds a frame to the channel
	 * 
	 * @param frame
	 * frame to add
	 */
	@Override
	public void addFrame(FibexFrame frame)
	{
		frames.add(frame);
	}
	
	/**
	 * This method creates and returns a new frame in this channel
	 * 
	 * @param data
	 * data to pass to frame creation
	 */
	@Override
	public FibexFrame createNewFrame(Object... data)
	{
		FibexFrame frame = new FibexFrame2_0_1((String)data[0], (Short)data[1], (Short)data[2], (Integer)data[3], (Short)data[4]);
		frames.add(frame);
		return frame;
	}
	
	/**
	 * This method returns a frame with the specified data. If the frame does not exist yet, it will be created.
	 * 
	 * @param data
	 * initialization data to pass to the frame
	 * @return
	 * frame
	 */
	@Override
	public FibexFrame getOrCreateFrame(Object... data) throws FibexException
	{
		String name = (String) data[0];
		short baseCycle = (Short) data[1];
		short repetition = (Short) data[2];
		int size = (Integer) data[3];
		short slot = (Short) data[4];
		
		for (Iterator<FibexFrame> i = frames.iterator(); i.hasNext();)
		{
			FibexFrame frame = (FibexFrame) i.next();
			
			if(frame.getBaseCycle() == baseCycle && frame.getCycleRepetition() == repetition && frame.getSlot() == slot)
			{
				return frame;
			}
		}
		FibexFrame frame = new FibexFrame2_0_1(name, baseCycle, repetition, size, slot);
		frames.add(frame);
		return frame;
	}
}
