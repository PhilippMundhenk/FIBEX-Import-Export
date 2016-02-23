package fibex.structures;

import java.util.Iterator;

import net.asam.xml.fbx.CONNECTORTYPE.INPUTS;
import net.asam.xml.fbx.CONNECTORTYPE.OUTPUTS;
import net.asam.xml.fbx.CONTROLLERTYPE;
import net.asam.xml.fbx.ECUPORTTYPE;
import net.asam.xml.fbx.ECUTYPE;
import net.asam.xml.fbx.FIBEXDocument;
import fibex.protocols.flexray.FibexFlexRayController2_0_1;

/**
 * This class implements all functionality of FibexController for FIBEX 2.0.1
 * 
 * @author TUM CREATE - RP3 - Philipp Mundhenk
 */
public class FibexController2_0_1 extends FibexController
{
	protected static int inputPortCounter = -1;
	protected static int outputPortCounter = -1;
	
	/**
	 * This constructor creates a new controller with a given name
	 * 
	 * @param name
	 * name of controller
	 */
	public FibexController2_0_1(String name)
	{
		super(name);
	}

	/**
	 * This constructor creates a new instance of controller. Access via getInstance()
	 * 
	 * @param controllerRepresentation
	 * reference to controller node
	 * @param docReference
	 * reference to FIBEX document
	 */
	protected FibexController2_0_1(Object controllerRepresentation, Object docReference)
	{
		super(controllerRepresentation, docReference);
	}

	/**
	 * This method return the ID of the controller node
	 * 
	 * @return
	 * ID of controller node
	 */
	@Override
	public String getIdController()
	{
		if(this.controllerId == null)
		{
			return ((CONTROLLERTYPE)controllerRepresentation).getID();
		}
		else
		{
			return this.controllerId;
		}
	}
	
	/**
	 * This method returns the name (short-name) of the controller
	 * 
	 * @return
	 * name (short-name) of the controller
	 */
	public String getName()
	{
		if(this.name == null)
		{
			return ((CONTROLLERTYPE)controllerRepresentation).getSHORTNAME();
		}
		else
		{
			return this.name;
		}
	}
	
	/**
	 * This method return the ID of the connector node
	 * 
	 * @return
	 * ID of connector node
	 */
	@Override
	public String getIdConnector()
	{
		if(this.connectorId == null)
		{
			for (int i = 0; i < ((FIBEXDocument)docReference).getFIBEX().getELEMENTS().getECUS().getECUArray().length; i++)
			{
				for (int j = 0; j < ((FIBEXDocument)docReference).getFIBEX().getELEMENTS().getECUS().getECUArray(i).getCONNECTORS().getCONNECTORArray().length; j++)
				{
					if(((FIBEXDocument)docReference).getFIBEX().getELEMENTS().getECUS().getECUArray(i).getCONNECTORS().getCONNECTORArray(j).getCONTROLLERREF().toString().equals(this.getIdController()))
					{
						return ((FIBEXDocument)docReference).getFIBEX().getELEMENTS().getECUS().getECUArray(i).getCONNECTORS().getCONNECTORArray(j).getID();
					}
				}
			}
			return null;
		}
		else
		{
			return this.connectorId;
		}
	}

	/**
	 * This method adds a given frame on a given channel as an outgoing frame to the controller
	 * 
	 * @param frame
	 * frame to add
	 * @param channel
	 * channel that frame is transmitted on
	 */
	@Override
	public void addFrameOutput(FibexFrame frame, FibexChannel channel)
	{
		this.outputFrames.add(frame);
		this.channelReferences.put(frame, channel);
		this.channels.add(channel);
	}

	/**
	 * This method adds a given frame on a given channel as an incoming frame to the controller
	 * 
	 * @param frame
	 * frame to add
	 * @param channel
	 * channel that frame is transmitted on
	 */
	@Override
	public void addFrameInput(FibexFrame frame, FibexChannel channel)
	{
		this.inputFrames.add(frame);
		this.channelReferences.put(frame, channel);
		this.channels.add(channel);
	}
	
	/**
	 * This method increments and returns the counter used to generate input port node IDs
	 * 
	 * @return
	 * incremented counter
	 */
	public int getNextInputPortCounter()
	{
		inputPortCounter++;
		return inputPortCounter;
	}
	
	/**
	 * This method increments and returns the counter used to generate output port node IDs
	 * 
	 * @return
	 * incremented counter
	 */
	public int getNextOutputPortCounter()
	{
		outputPortCounter++;
		return outputPortCounter;
	}	

	/**
	 * This method saves the controller and all subcomponents
	 * 
	 * @param docReference
	 * reference to FIBEX document
	 * @param controllersReference
	 * reference to controllers node
	 * @param connectorsReference
	 * reference to connectors node
	 * @param additionalData
	 * additional data
	 */
	@Override
	public void save(Object docReference, Object controllersReference, Object connectorsReference, Object... additionalData)
	{
		String clusterType = ((String)additionalData[0]);
		net.asam.xml.fbx.CONTROLLERTYPE controller;
		if(clusterType.equals(net.asam.xml.fbx.flexray.PROTOCOLTYPE.FLEX_RAY.toString()))
		{
			net.asam.xml.fbx.flexray.CONTROLLERTYPE flexRayController = (net.asam.xml.fbx.flexray.CONTROLLERTYPE)((ECUTYPE.CONTROLLERS)controllersReference).addNewCONTROLLER().changeType(net.asam.xml.fbx.flexray.CONTROLLERTYPE.Factory.newInstance().schemaType());
			controller = flexRayController;
		}
		else if(clusterType.equals(net.asam.xml.fbx.lin.PROTOCOLTYPE.LIN.toString()))
		{
			net.asam.xml.fbx.lin.CONTROLLERTYPE linController = (net.asam.xml.fbx.lin.CONTROLLERTYPE)((ECUTYPE.CONTROLLERS)controllersReference).addNewCONTROLLER().changeType(net.asam.xml.fbx.lin.CONTROLLERTYPE.Factory.newInstance().schemaType());
			controller = linController;
		}
		else if(clusterType.equals(net.asam.xml.fbx.can.PROTOCOLTYPE.CAN.toString()))
		{
			net.asam.xml.fbx.can.CONTROLLERTYPE canController = (net.asam.xml.fbx.can.CONTROLLERTYPE)((ECUTYPE.CONTROLLERS)controllersReference).addNewCONTROLLER().changeType(net.asam.xml.fbx.can.CONTROLLERTYPE.Factory.newInstance().schemaType());
			controller = canController;
		}
		else
		{
			net.asam.xml.fbx.CONTROLLERTYPE basicController = (net.asam.xml.fbx.CONTROLLERTYPE)((ECUTYPE.CONTROLLERS)controllersReference).addNewCONTROLLER().changeType(net.asam.xml.fbx.CONTROLLERTYPE.Factory.newInstance().schemaType());
			controller = basicController;
		}
		controller.setID(controllerId);
		controller.setSHORTNAME(controllerId);
		controller.addNewDESC().setStringValue("This is a controller");
		controller.addNewMANUFACTUREREXTENSION();
		((net.asam.xml.fbx.flexray.CONTROLLERTYPE)controller).addNewKEYSLOTUSAGE().setSTARTUPSYNC(controllerParameters.FlexRay.KEY_SLOT_USAGE__STARTUP_SYNC);
		((net.asam.xml.fbx.flexray.CONTROLLERTYPE)controller).setMAXDYNAMICPAYLOADLENGTH(controllerParameters.FlexRay.MAX_DYNAMIC_PAYLOAD_LENGTH);
		((net.asam.xml.fbx.flexray.CONTROLLERTYPE)controller).setCLUSTERDRIFTDAMPING(controllerParameters.FlexRay.CLUSTER_DRIFT_DAMPING);
		((net.asam.xml.fbx.flexray.CONTROLLERTYPE)controller).setDECODINGCORRECTION(controllerParameters.FlexRay.DECODING_CORRECTION);
		((net.asam.xml.fbx.flexray.CONTROLLERTYPE)controller).setLISTENTIMEOUT(controllerParameters.FlexRay.LISTEN_TIMEOUT);
		((net.asam.xml.fbx.flexray.CONTROLLERTYPE)controller).setMAXDRIFT(controllerParameters.FlexRay.MAX_DRIFT);
		((net.asam.xml.fbx.flexray.CONTROLLERTYPE)controller).setEXTERNOFFSETCORRECTION(controllerParameters.FlexRay.EXTERN_OFFSET_CORRECTION);
		((net.asam.xml.fbx.flexray.CONTROLLERTYPE)controller).setEXTERNRATECORRECTION(controllerParameters.FlexRay.EXTERN_RATE_CORRECTION);
		((net.asam.xml.fbx.flexray.CONTROLLERTYPE)controller).setLATESTTX(controllerParameters.FlexRay.LATEST_TX);
		((net.asam.xml.fbx.flexray.CONTROLLERTYPE)controller).setMICROPERCYCLE(controllerParameters.FlexRay.MICRO_PER_CYCLE);
		((net.asam.xml.fbx.flexray.CONTROLLERTYPE)controller).setOFFSETCORRECTIONOUT(controllerParameters.FlexRay.OFFSET_CORRECTION_OUT);
		((net.asam.xml.fbx.flexray.CONTROLLERTYPE)controller).setRATECORRECTIONOUT(controllerParameters.FlexRay.RATE_CORRECTION_OUT);
		((net.asam.xml.fbx.flexray.CONTROLLERTYPE)controller).setSAMPLESPERMICROTICK(controllerParameters.FlexRay.SAMPLES_PER_MICROTICK);
		((net.asam.xml.fbx.flexray.CONTROLLERTYPE)controller).setDELAYCOMPENSATIONA(controllerParameters.FlexRay.DELAY_COMPENSATION_A);
		((net.asam.xml.fbx.flexray.CONTROLLERTYPE)controller).setDELAYCOMPENSATIONB(controllerParameters.FlexRay.DELAY_COMPENSATION_B);
		((net.asam.xml.fbx.flexray.CONTROLLERTYPE)controller).setWAKEUPPATTERN(controllerParameters.FlexRay.WAKE_UP_PATTERN);
		((net.asam.xml.fbx.flexray.CONTROLLERTYPE)controller).setALLOWHALTDUETOCLOCK(controllerParameters.FlexRay.ALLOW_HALT_DUE_TO_CLOCK);
		((net.asam.xml.fbx.flexray.CONTROLLERTYPE)controller).setALLOWPASSIVETOACTIVE(controllerParameters.FlexRay.ALLOW_PASSIVE_TO_ACTIVE);
		((net.asam.xml.fbx.flexray.CONTROLLERTYPE)controller).setACCEPTEDSTARTUPRANGE(controllerParameters.FlexRay.ACCEPTED_STARTUP_RANGE);
		((net.asam.xml.fbx.flexray.CONTROLLERTYPE)controller).setMACROINITIALOFFSETA(controllerParameters.FlexRay.MACRO_INITIAL_OFFSET_A);
		((net.asam.xml.fbx.flexray.CONTROLLERTYPE)controller).setMACROINITIALOFFSETB(controllerParameters.FlexRay.MACRO_INITIAL_OFFSET_B);
		((net.asam.xml.fbx.flexray.CONTROLLERTYPE)controller).setMICROINITIALOFFSETA(controllerParameters.FlexRay.MICRO_INITIAL_OFFSET_A);
		((net.asam.xml.fbx.flexray.CONTROLLERTYPE)controller).setMICROINITIALOFFSETB(controllerParameters.FlexRay.MICRO_INITIAL_OFFSET_B);
		((net.asam.xml.fbx.flexray.CONTROLLERTYPE)controller).setSINGLESLOTENABLED(controllerParameters.FlexRay.SINGLE_SLOT_ENABLED);
		((net.asam.xml.fbx.flexray.CONTROLLERTYPE)controller).setMICROTICK((float)controllerParameters.FlexRay.MICROTICK);
		
		for (FibexChannel channel : channels)
		{
			net.asam.xml.fbx.CONNECTORTYPE connector;
			if(clusterType.equals("FlexRay"))
			{
				connector = (net.asam.xml.fbx.flexray.CONNECTORTYPE)((ECUTYPE.CONNECTORS)connectorsReference).addNewCONNECTOR().changeType(net.asam.xml.fbx.flexray.CONNECTORTYPE.Factory.newInstance().schemaType());
				((net.asam.xml.fbx.flexray.CONNECTORTYPE)connector).setWAKEUPCHANNEL(true);
			}
			else
			{
				connector = (net.asam.xml.fbx.CONNECTORTYPE)((ECUTYPE.CONNECTORS)connectorsReference).addNewCONNECTOR().changeType(net.asam.xml.fbx.CONNECTORTYPE.Factory.newInstance().schemaType());
			}
			
			connector.addNewCHANNELREF().setIDREF(channel.getId());
			connector.setID(connectorId);
			connector.addNewCONTROLLERREF().setIDREF(controllerId);
			INPUTS in = connector.addNewINPUTS();
			for (Iterator<FibexFrame> i = inputFrames.iterator(); i.hasNext();)
			{
				FibexFrame frame = (FibexFrame) i.next();
				
				ECUPORTTYPE port = in.addNewINPUTPORT();
				port.setID("FIBEX_INPUT_PORT_"+getNextInputPortCounter());
				port.addNewFRAMETRIGGERINGREF().setIDREF(frame.getIdTriggering());
				ECUPORTTYPE.SIGNALINSTANCEREFS signalInstanceRefs = port.addNewSIGNALINSTANCEREFS();
				for (Iterator<FibexMessage> j = frame.getMessages().iterator(); j.hasNext();)
				{
					FibexMessage message = (FibexMessage) j.next();
					
					signalInstanceRefs.addNewSIGNALINSTANCEREF().setIDREF(message.getIdMessage());
				}
			}
			OUTPUTS out = connector.addNewOUTPUTS();
			for (Iterator<FibexFrame> i = outputFrames.iterator(); i.hasNext();)
			{
				FibexFrame frame = (FibexFrame) i.next();
				
				ECUPORTTYPE port = out.addNewOUTPUTPORT();
				port.setID("FIBEX_OUTPUT_PORT_"+getNextOutputPortCounter());
				port.addNewFRAMETRIGGERINGREF().setIDREF(frame.getIdTriggering());
				ECUPORTTYPE.SIGNALINSTANCEREFS signalInstanceRefs = port.addNewSIGNALINSTANCEREFS();
				for (Iterator<FibexMessage> j = frame.getMessages().iterator(); j.hasNext();)
				{
					FibexMessage message = (FibexMessage) j.next();
					
					signalInstanceRefs.addNewSIGNALINSTANCEREF().setIDREF(message.getIdMessage());
				}
			}
		}
	}

	/**
	 * This method creates and returns a new instance of the controller.
	 * 
	 * @param controllerRepresentation
	 * reference to controller node
	 * @param docReference
	 * reference to FIBEX document
	 * @return
	 * newly created controller, null if given wrong input nodes given
	 */
	public static FibexController getInstance(Object controllerRepresentation, Object docReference)
	{
		if(controllerRepresentation instanceof net.asam.xml.fbx.flexray.CONTROLLERTYPE)
		{
			return FibexFlexRayController2_0_1.getInstance(controllerRepresentation, docReference);
		}
		else if(controllerRepresentation instanceof net.asam.xml.fbx.lin.CONTROLLERTYPE)
		{
			//TODO: build LIN controller
			return null;
		}
		else if(controllerRepresentation instanceof net.asam.xml.fbx.can.CONTROLLERTYPE)
		{
			//TODO: build CAN controller
			return null;
		}
		else if(controllerRepresentation instanceof net.asam.xml.fbx.CONTROLLERTYPE)
		{
			return new FibexController2_0_1(controllerRepresentation, docReference);
		}
		else
		{
			return null;
		}
	}
}
