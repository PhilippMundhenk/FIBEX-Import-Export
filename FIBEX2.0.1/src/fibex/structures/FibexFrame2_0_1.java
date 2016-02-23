package fibex.structures;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;

import net.asam.xml.fbx.ABSOLUTELYSCHEDULEDTIMINGTYPE;
import net.asam.xml.fbx.FIBEXDocument;
import net.asam.xml.fbx.FRAMETRIGGERINGTYPE;
import net.asam.xml.fbx.FRAMETYPE;
import net.asam.xml.fbx.FRAMETYPE.FRAMETYPE2;
import fibex.exceptions.FIBEXElementNotFoundException;
import fibex.exceptions.FibexException;

/**
 * This class implements all functionality of FibexFrame for FIBEX 2.0.1
 * 
 * @author TUM CREATE - RP3 - Philipp Mundhenk
 */
public class FibexFrame2_0_1 extends FibexFrame
{
	/**
	 * This constructor creates a new instance of frame. Access via getInstance()
	 * 
	 * @param framesRepresentation
	 * reference to frames node
	 * @param frameTriggeringsRepresentation
	 * reference to frame-triggerings node
	 * @param docReference
	 * reference to FIBEX document
	 */
	protected FibexFrame2_0_1(Object framesRepresentation, Object frameTriggeringsRepresentation, Object docReference)
	{
		super(framesRepresentation, frameTriggeringsRepresentation, docReference);
		msgs = new LinkedHashSet<FibexMessage>();
	}
	
	/**
	 * This constructor creates a new instance of frame. Access FibexChannel.createNewFrame()
	 * 
	 * @param name
	 * name of frame to create
	 * @param baseCycle
	 * base cycle of frame to create
	 * @param cycleRepetition
	 * repetition of frame to create
	 * @param length_Byte
	 * length of frame in bytes
	 * @param slot
	 * slot of frame
	 */
	protected FibexFrame2_0_1(String name, short baseCycle, short cycleRepetition, int length_Byte, short slot)
	{
		super(name, baseCycle, cycleRepetition, length_Byte, slot);
	}
	
	/**
	 * This method creates and returns a new instance of the frame
	 * 
	 * @param framesRepresentation
	 * reference to frames node
	 * @param frameTriggeringsRepresentation
	 * reference to frame-triggerings node
	 * @param docReference
	 * reference to FIBEX document
	 * @return
	 * newly created frame, null if given wrong input nodes given
	 */
	public static FibexFrame getInstance(Object framesRepresentation, Object frameTriggeringsRepresentation, Object docReference)
	{
		if(frameTriggeringsRepresentation instanceof net.asam.xml.fbx.most.FRAMETRIGGERINGTYPE)
		{
			return null;
		}
		else if(frameTriggeringsRepresentation instanceof net.asam.xml.fbx.FRAMETRIGGERINGTYPE)
		{
			return new FibexFrame2_0_1(framesRepresentation, frameTriggeringsRepresentation, docReference);
		}
		else
		{
			return null;
		}
	}

	/**
	 * This method returns the base cycle of the frame
	 * 
	 * @return
	 * base cycle
	 */
	@Override
	public int getBaseCycle() throws FibexException
	{
		if(name == null)
		{
			try
			{
				//TODO: support multiple timings
				return ((FRAMETRIGGERINGTYPE)frameTriggeringsRepresentation).getTIMINGS().getABSOLUTELYSCHEDULEDTIMINGArray(0).getBASECYCLE();
			}
			catch(Exception e)
			{
				throw new FIBEXElementNotFoundException();
			}
		}
		else
		{
			return baseCycle;
		}
	}

	/**
	 * This method returns the repetition of of the frame
	 * 
	 * @return
	 * repetition
	 */
	@Override
	public int getCycleRepetition() throws FibexException
	{
		if(name == null)
		{
			try
			{
				//TODO: support multiple timings
				return ((FRAMETRIGGERINGTYPE)frameTriggeringsRepresentation).getTIMINGS().getABSOLUTELYSCHEDULEDTIMINGArray(0).getCYCLEREPETITION();
			}
			catch(Exception e)
			{
				throw new FIBEXElementNotFoundException();
			}
		}
		else
		{
			return cycleRepetition;
		}
	}

	/**
	 * This method returns the slot of the frame
	 * 
	 * @return
	 * slot
	 */
	@Override
	public int getSlot() throws FibexException
	{
		if(name == null)
		{
			try
			{
				//TODO: support multiple timings
				return ((FRAMETRIGGERINGTYPE)frameTriggeringsRepresentation).getTIMINGS().getABSOLUTELYSCHEDULEDTIMINGArray(0).getSLOTID();
			}
			catch(Exception e)
			{
				throw new FIBEXElementNotFoundException();
			}
		}
		else
		{
			return slot;
		}
	}

	/**
	 * This method returns the length of the frame in bytes
	 * 
	 * @return
	 * length in bytes
	 */
	@Override
	public long getLength_Byte() throws FibexException
	{
		if(name == null)
		{
			try
			{
				return (long)((FRAMETYPE)framesRepresentation).getBYTELENGTH();
			}
			catch(Exception e)
			{
				throw new FIBEXElementNotFoundException();
			}
		}
		else
		{
			return length_Byte;
		}
	}
	
	/**
	 * This method returns the ECU (short-name) sending the message
	 * 
	 * @return
	 * sending ECU (short-name) of the message
	 */
	@Override
	public String getSender()
	{
		for (int i = 0; i < ((FIBEXDocument)docReference).getFIBEX().getELEMENTS().getECUS().getECUArray().length; i++)
		{
			for (int j = 0; j < ((FIBEXDocument)docReference).getFIBEX().getELEMENTS().getECUS().getECUArray(i).getCONNECTORS().getCONNECTORArray().length; j++)
			{
				for (int k = 0; k < ((FIBEXDocument)docReference).getFIBEX().getELEMENTS().getECUS().getECUArray(i).getCONNECTORS().getCONNECTORArray(j).getOUTPUTS().getOUTPUTPORTArray().length; k++)
				{
					if(((FIBEXDocument)docReference).getFIBEX().getELEMENTS().getECUS().getECUArray(i).getCONNECTORS().getCONNECTORArray(j).getOUTPUTS().getOUTPUTPORTArray(k).getFRAMETRIGGERINGREF()
									.getIDREF().equals(((FRAMETRIGGERINGTYPE)frameTriggeringsRepresentation).getID()))
					{
						return ((FIBEXDocument)docReference).getFIBEX().getELEMENTS().getECUS().getECUArray(i).getSHORTNAME();
					}
				}
			}
		}
		
		return null;
	}
	
	/**
	 * This method returns all messages (signals) contained in this frame
	 * 
	 * @return
	 * collection of messages
	 */
	@Override
	public Collection<FibexMessage> getMessages()
	{
		if(msgs.isEmpty())
		{
			for (int i = 0; i < ((FRAMETYPE)framesRepresentation).getSIGNALINSTANCES().getSIGNALINSTANCEArray().length; i++)
			{
				FibexMessage message = FibexSignal2_0_1.getInstance(((FRAMETYPE)framesRepresentation).getSIGNALINSTANCES().getSIGNALINSTANCEArray(i), docReference);
				if(message != null)
				{
					this.msgs.add(message);
				}
			}
		}
		return msgs;
	}

	/**
	 * This method returns the name of the frame
	 * 
	 * @return
	 * name
	 */
	@Override
	public String getName() throws FibexException
	{
		try
		{
			return ((FRAMETYPE)framesRepresentation).getSHORTNAME();
		}
		catch(Exception e)
		{
			throw new FIBEXElementNotFoundException();
		}
	}
	
	/**
	 * This method saves the frame and all subcomponents
	 * 
	 * @param frameTriggeringsRepresentation
	 * reference to frame-triggerings node
	 * @param docReference
	 * reference to FIBEX document
	 * @param additionalData
	 * additional data
	 */
	@Override
	public void save(Object frameTriggeringsRepresentation, Object docReference, Object... additionalData)
	{
		if(!(this.name == null))
		{
			if(((FIBEXDocument)docReference).getFIBEX().getELEMENTS().getFRAMES() == null)
			{
				((FIBEXDocument)docReference).getFIBEX().getELEMENTS().addNewFRAMES();
			}
			this.framesRepresentation = ((FIBEXDocument)docReference).getFIBEX().getELEMENTS().getFRAMES().addNewFRAME();
			this.frameTriggeringsRepresentation = frameTriggeringsRepresentation;
			this.docReference = docReference;
			
			
			
			for (Iterator<FibexMessage> i = msgs.iterator(); i.hasNext();)
			{
				FibexMessage msg = (FibexMessage) i.next();
				
				if(((FRAMETYPE)framesRepresentation).getSIGNALINSTANCES() == null)
				{
					((FRAMETYPE)framesRepresentation).addNewSIGNALINSTANCES();
				}
				msg.save(((FRAMETYPE)framesRepresentation).getSIGNALINSTANCES().addNewSIGNALINSTANCE(), docReference);
				((FRAMETYPE)framesRepresentation).setSHORTNAME("Frame_" + name);
				((FRAMETYPE)framesRepresentation).setBYTELENGTH(length_Byte);
				((FRAMETYPE)framesRepresentation).setFRAMETYPE(FRAMETYPE2.APPLICATION); //TODO: check and adjust to correct type
				((FRAMETYPE)framesRepresentation).setID(frameId);
				
				if(((FRAMETRIGGERINGTYPE)frameTriggeringsRepresentation).getIDENTIFIER() == null)
				{
					((FRAMETRIGGERINGTYPE)frameTriggeringsRepresentation).addNewIDENTIFIER();
				}
				
				if(((FRAMETRIGGERINGTYPE)frameTriggeringsRepresentation).getID() == null)
				{
					((FRAMETRIGGERINGTYPE)frameTriggeringsRepresentation).setID(triggeringId);
				}
				
				if(((FRAMETRIGGERINGTYPE)frameTriggeringsRepresentation).getFRAMEREF() == null)
				{
					((FRAMETRIGGERINGTYPE)frameTriggeringsRepresentation).addNewFRAMEREF().setIDREF(frameId);
				}
				
				ABSOLUTELYSCHEDULEDTIMINGTYPE absoluteTiming = null;
				if(((FRAMETRIGGERINGTYPE)frameTriggeringsRepresentation).getTIMINGS() == null)
				{
					absoluteTiming = ((FRAMETRIGGERINGTYPE)frameTriggeringsRepresentation).addNewTIMINGS().addNewABSOLUTELYSCHEDULEDTIMING();
				}
				else
				{
					if(((FRAMETRIGGERINGTYPE)frameTriggeringsRepresentation).getTIMINGS().getABSOLUTELYSCHEDULEDTIMINGArray() == null)
					{
						absoluteTiming = ((FRAMETRIGGERINGTYPE)frameTriggeringsRepresentation).getTIMINGS().addNewABSOLUTELYSCHEDULEDTIMING();
					}
				}
				
				if(absoluteTiming != null)
				{
					if(((String)additionalData[0]).equals(net.asam.xml.fbx.flexray.PROTOCOLTYPE.FLEX_RAY.toString()))
					{
						net.asam.xml.fbx.flexray.SLOTIDTYPE slotId = net.asam.xml.fbx.flexray.SLOTIDTYPE.Factory.newInstance();
						slotId.setShortValue((short)(slot+1));
						absoluteTiming.xsetSLOTID(slotId);
					}
					else
					{
						net.asam.xml.fbx.SLOTIDTYPE slotId = net.asam.xml.fbx.flexray.SLOTIDTYPE.Factory.newInstance();
						slotId.setShortValue((short)(slot+1));
						absoluteTiming.xsetSLOTID(slotId);
					}
					absoluteTiming.setBASECYCLE(baseCycle);			
					if(((String)additionalData[0]).equals(net.asam.xml.fbx.flexray.PROTOCOLTYPE.FLEX_RAY.toString()))
					{
						net.asam.xml.fbx.flexray.CYCLEREPETITIONTYPE cycleRepetitionType = net.asam.xml.fbx.flexray.CYCLEREPETITIONTYPE.Factory.newInstance();
						cycleRepetitionType.setShortValue(cycleRepetition);
						absoluteTiming.xsetCYCLEREPETITION(cycleRepetitionType);
					}
					else
					{
						net.asam.xml.fbx.CYCLEREPETITIONTYPE cycleRepetitionType = net.asam.xml.fbx.flexray.CYCLEREPETITIONTYPE.Factory.newInstance();
						cycleRepetitionType.setShortValue(cycleRepetition);
						absoluteTiming.xsetCYCLEREPETITION(cycleRepetitionType);
					}
				}
			}
		}
	}
	
	/**
	 * This message creates and returns a new message (signal)
	 * 
	 * @param data
	 * all data necessary to create frame ({@link FibexSignal2_0_1#FibexSignal2_0_1(int, int, String) FibexSignal2_0_1()})
	 */
	@Override
	public FibexMessage createNewMessage(Object... data)
	{
		FibexMessage msg = new FibexSignal2_0_1((Integer)data[0], (Integer)data[1], (String)data[2]);
		msgs.add(msg);
		return msg;
	}
}
