package fibex.structures;

import java.util.Collection;
import java.util.LinkedHashSet;

import net.asam.xml.BASEDATATYPEAttribute.BASEDATATYPE;
import net.asam.xml.CODEDTYPEDocument.CODEDTYPE.CATEGORY;
import net.asam.xml.fbx.CODINGTYPE;
import net.asam.xml.fbx.FIBEXDocument;
import net.asam.xml.fbx.SIGNALINSTANCETYPE;
import net.asam.xml.fbx.SIGNALTYPE;

/**
 * This class implements all functionality of FibexSignal for FIBEX 2.0.1.
 * FIBEX 2.0.1 specifies signals as messages. So the implementation of FibexMessage is called FibexSignal
 * 
 * @author TUM CREATE - RP3 - Philipp Mundhenk
 */
public class FibexSignal2_0_1 extends FibexMessage
{
	protected Object codingRepresentation;
	
	/**
	 * This constructor creates a new instance of the signal. Access via FibexFrame.createNewMessage()
	 * 
	 * @param offset
	 * offset of the signal inside the frame in bits
	 * @param length_Bit
	 * length of the signal in bits
	 * @param name
	 * name of the signal
	 */
	protected FibexSignal2_0_1(int offset, int length_Bit, String name)
	{
		super((Integer)offset, (Integer)length_Bit, (String)name);
	}
	
	/**
	 * This constructor creates a new instance of the signal. Access via getInstance()
	 * 
	 * @param elementRepresentation
	 * reference to the signal node
	 * @param instanceRepresentation
	 * reference to the signal-instance node
	 * @param docReference
	 * reference to the FIBEX document
	 * @param settings
	 * additional settings string
	 */
	protected FibexSignal2_0_1(Object elementRepresentation, Object instanceRepresentation, Object docReference, String settings)
	{
		super(elementRepresentation, instanceRepresentation, docReference, "");
	}
	
	/**
	 * This constructor creates a new instance of the signal. Access via getInstance()
	 * 
	 * @param elementRepresentation
	 * reference to the signal node
	 * @param instanceRepresentation
	 * reference to the signal-instance node
	 * @param codingRepresentation
	 * reference to the coding node
	 * @param docReference
	 * reference to the FIBEX document
	 */
	protected FibexSignal2_0_1(Object elementRepresentation, Object instanceRepresentation, Object codingRepresentation, Object docReference)
	{
		super(elementRepresentation, instanceRepresentation, docReference, "");
		this.codingRepresentation = codingRepresentation;
	}
	
	/**
	 * This method creates and returns a new instance of signal
	 * 
	 * @param instancesRepresentation
	 * reference to the instances node
	 * @param docReference
	 * reference to the FIBEX document
	 * @return
	 * newly created signal
	 */
	public static FibexMessage getInstance(Object instancesRepresentation, Object docReference)
	{
		if(instancesRepresentation instanceof net.asam.xml.fbx.SIGNALINSTANCETYPE)
		{
			for (int i = 0; i < ((FIBEXDocument)docReference).getFIBEX().getELEMENTS().getSIGNALS().getSIGNALArray().length; i++)
			{
				if(((FIBEXDocument)docReference).getFIBEX().getELEMENTS().getSIGNALS().getSIGNALArray(i).getID().equals(((SIGNALINSTANCETYPE)instancesRepresentation).getSIGNALREF().getIDREF()))
				{
					for (int j = 0; j < ((FIBEXDocument)docReference).getFIBEX().getPROCESSINGINFORMATION().getCODINGS().getCODINGArray().length; j++)
					{
						if(((FIBEXDocument)docReference).getFIBEX().getPROCESSINGINFORMATION().getCODINGS().getCODINGArray(i).getID().equals(((FIBEXDocument)docReference).getFIBEX().getELEMENTS().getSIGNALS().getSIGNALArray(i).getCODINGREF().getIDREF()))
						{
							return new FibexSignal2_0_1(((FIBEXDocument)docReference).getFIBEX().getELEMENTS().getSIGNALS().getSIGNALArray(i), 
													instancesRepresentation, 
													((FIBEXDocument)docReference).getFIBEX().getPROCESSINGINFORMATION().getCODINGS().getCODINGArray(i), 
													docReference);
						}
					}
				}
			}
			return null;
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * This method returns the offset of the signal inside the frame in bits
	 * 
	 * @return
	 * offset in bits
	 */
	@Override
	public int getOffset_Bit()
	{
		if(offset == 0)
		{
			return ((SIGNALINSTANCETYPE)instanceRepresentation).getBITPOSITION();
		}
		else
		{
			return offset;
		}
	}

	/**
	 * This method returns the length of the signal in bits
	 * 
	 * @return
	 * length in bit
	 */
	@Override
	public long getLength_Bit()
	{
		if(length_Bit == 0)
		{
			return ((CODINGTYPE)codingRepresentation).getCODEDTYPE().getBITLENGTH();
		}
		else
		{
			return length_Bit;
		}
	}

	/**
	 * This method returns the name of the signal
	 * 
	 * @return
	 * name
	 */
	@Override
	public String getName()
	{
		if(name == null)
		{
			return ((SIGNALTYPE)elementRepresentation).getSHORTNAME();
		}
		else
		{
			return name;
		}
	}
	
	/**
	 * This method returns all receivers of this frame.
	 * 
	 * @return
	 * collection of receivers
	 */
	public Collection<Object> getReceivers()
	{
		Collection<Object> receivers = new LinkedHashSet<Object>();
		
		for (int i = 0; i < ((FIBEXDocument)docReference).getFIBEX().getELEMENTS().getECUS().getECUArray().length; i++)
		{
			for (int j = 0; j < ((FIBEXDocument)docReference).getFIBEX().getELEMENTS().getECUS().getECUArray(i).getCONNECTORS().getCONNECTORArray().length; j++)
			{
				for (int k = 0; k < ((FIBEXDocument)docReference).getFIBEX().getELEMENTS().getECUS().getECUArray(i).getCONNECTORS().getCONNECTORArray(j).getINPUTS().getINPUTPORTArray().length; k++)
				{
					for (int l = 0; l < ((FIBEXDocument)docReference).getFIBEX().getELEMENTS().getECUS().getECUArray(i).getCONNECTORS().getCONNECTORArray(j).getINPUTS().
													getINPUTPORTArray(k).getSIGNALINSTANCEREFS().getSIGNALINSTANCEREFArray().length; l++)
					{
						if(((FIBEXDocument)docReference).getFIBEX().getELEMENTS().getECUS().getECUArray(i).getCONNECTORS().getCONNECTORArray(j).getINPUTS().getINPUTPORTArray(k).
													getSIGNALINSTANCEREFS().getSIGNALINSTANCEREFArray(l).getIDREF().equals(((SIGNALINSTANCETYPE)instanceRepresentation).getID()))
						{
							receivers.add(((FIBEXDocument)docReference).getFIBEX().getELEMENTS().getECUS().getECUArray(i).getSHORTNAME());
						}
					}
				}
			}
		}
		
		return receivers;
	}

	/**
	 * This method adds a submessage to this signal. This is not supported in FIBEX 2.0.1
	 * 
	 * @return
	 * -1: failed
	 */
	@Override
	public int addMessage(FibexMessage msg)
	{
		/* FIBEX 2.0.1 does not support PDUs or multi-layer messages */
		return -1;
	}
	
	/**
	 * This method saves the signal and all its subcomponents
	 * 
	 * @param instanceReference
	 * reference to the the signal-instance node to use
	 * @param docReference
	 * reference to the FIBEX file
	 * @param additionalData
	 * additional data
	 */
	@Override
	public void save(Object instanceReference, Object docReference, Object... additionalData)
	{
		if(this.length_Bit == 0 && this.name == null && this.offset == 0)
		{
			/* nothing to do, has been loaded from file */
		}
		else
		{
			/* have to act, has been generated in program */
			
			this.instanceRepresentation = instanceReference;
			this.docReference = docReference;
			
			if(((FIBEXDocument)docReference).getFIBEX().getPROCESSINGINFORMATION().getCODINGS() == null)
			{
				((FIBEXDocument)docReference).getFIBEX().getPROCESSINGINFORMATION().addNewCODINGS();
			}
			CODINGTYPE coding = ((FIBEXDocument)docReference).getFIBEX().getPROCESSINGINFORMATION().getCODINGS().addNewCODING();
			coding.setSHORTNAME("Coding_"+name);
			coding.addNewPHYSICALTYPE().setBASEDATATYPE(BASEDATATYPE.OTHER); //TODO: check and adjust to correct type
			coding.addNewCODEDTYPE();
			coding.getCODEDTYPE().setBITLENGTH(length_Bit);
			coding.getCODEDTYPE().setCATEGORY(CATEGORY.STANDARD_LENGTH_TYPE); //TODO: check and adjust to correct type
			coding.getCODEDTYPE().setBASEDATATYPE(BASEDATATYPE.OTHER); //TODO: check and adjust to correct type
			coding.setID(idCoding);
			
			this.codingRepresentation = coding;
			
			if(((FIBEXDocument)docReference).getFIBEX().getELEMENTS().getSIGNALS() == null)
			{
				((FIBEXDocument)docReference).getFIBEX().getELEMENTS().addNewSIGNALS();
			}
			SIGNALTYPE signal = ((FIBEXDocument)docReference).getFIBEX().getELEMENTS().getSIGNALS().addNewSIGNAL();
			signal.setID(idMessage);
			signal.setSHORTNAME("signal_"+name);
			signal.addNewCODINGREF().setIDREF(idCoding);
			
			this.elementRepresentation = signal;
			
			((SIGNALINSTANCETYPE)instanceReference).setBITPOSITION(offset);
			((SIGNALINSTANCETYPE)instanceReference).setID(idInstance);
			((SIGNALINSTANCETYPE)instanceReference).setISHIGHLOWBYTEORDER(true);
			((SIGNALINSTANCETYPE)instanceReference).addNewSIGNALREF().setIDREF(idMessage);
		}
	}

}
