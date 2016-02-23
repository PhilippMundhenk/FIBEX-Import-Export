package fibex.structures;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;

/**
 * This class represents a controller in the FIBEX file
 * 
 * @author TUM CREATE - RP3 - Philipp Mundhenk
 */
public abstract class FibexController 
{
	protected String name;
	protected Collection<FibexFrame> outputFrames;
	protected Collection<FibexFrame> inputFrames;
	protected ControllerParameters controllerParameters;
	protected Collection<FibexChannel> channels;
	protected HashMap<FibexFrame, FibexChannel> channelReferences;
	
	protected Object controllerRepresentation;
	protected Object docReference;
	
	protected String controllerId;
	protected String connectorId;
	
	protected static int controllerCounter = -1;
	protected static int connectorCounter = -1;
	
	/**
	 * This constructor creates a new controller. The controller parameters are set to standard values
	 * 
	 * @param name
	 * name (short-name) of the controller to create
	 */
	public FibexController(String name)
	{
		this.name = name;
		this.inputFrames = new LinkedHashSet<FibexFrame>();
		this.outputFrames = new LinkedHashSet<FibexFrame>();
		this.channels = new LinkedHashSet<FibexChannel>();
		this.channelReferences = new HashMap<FibexFrame, FibexChannel>();
		this.controllerParameters = new ControllerParameters();
		controllerId = "FIBEX_CONTROLLER_"+getNextControllerCounter();
		connectorId = "FIBEX_CONNECTOR_"+getNextConnectorCounter();
	}
	
	/**
	 * This constructor creates a new instance of controller. Access via getInstance()
	 * 
	 * @param controllerRepresentation
	 * reference to controller node
	 * @param docReference
	 * reference to FIBEX document
	 */
	protected FibexController(Object controllerRepresentation, Object docReference)
	{
		this.controllerRepresentation = controllerRepresentation;
		this.docReference = docReference;
		getNextControllerCounter();
		getNextConnectorCounter();
	}
	
	/**
	 * This method sets the control parameters for the controller
	 * 
	 * @param controllerParameters
	 * parameters for the controller
	 */
	public void setParameters(ControllerParameters controllerParameters)
	{
		this.controllerParameters = controllerParameters;
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
			System.out.println("[FibexController:getName] The FIBEX library does not support direct references to FIBEX files. Please implement referencing in extending library.");
			return name;
		}
		else
		{
			return this.name;
		}
	}
	
	/**
	 * This method returns the ID of the controller node
	 * 
	 * @return
	 * ID of the controller node
	 */
	public String getIdController()
	{
		if(this.controllerId == null)
		{
			System.out.println("[FibexController:getId] The FIBEX library does not support direct references to FIBEX files. Please implement referencing in extending library.");
			return null;
		}
		else
		{
			return this.controllerId;
		}
	}
	
	/**
	 * This method returns the ID of the connector node
	 * 
	 * @return
	 * ID of the connector node
	 */
	public String getIdConnector()
	{
		if(this.connectorId == null)
		{
			System.out.println("[FibexController:getId] The FIBEX library does not support direct references to FIBEX files. Please implement referencing in extending library.");
			return null;
		}
		else
		{
			return this.connectorId;
		}
	}
	
	/**
	 * This method increments and returns the counter used to generate controller node IDs
	 * 
	 * @return
	 * incremented counter
	 */
	public int getNextControllerCounter()
	{
		controllerCounter++;
		return controllerCounter;
	}
	
	/**
	 * This method increments and returns the counter used to generate connector node IDs
	 * 
	 * @return
	 * incremented counter
	 */
	public int getNextConnectorCounter()
	{
		connectorCounter++;
		return connectorCounter;
	}
	
	/**
	 * This method returns all output frames
	 * 
	 * @return
	 * collection of all output frames
	 */
	public Collection<FibexFrame> getOutputFrames()
	{
		return outputFrames;
	}

	/**
	 * This method returns all input frames
	 * 
	 * @return
	 * collection of all input frames
	 */
	public Collection<FibexFrame> getInputFrames()
	{
		return inputFrames;
	}

	/**
	 * This method adds a given output frame
	 * 
	 * @param frame
	 * frame to add
	 * @param channel
	 * channel on which frame is transmitted
	 */
	public abstract void addFrameOutput(FibexFrame frame, FibexChannel channel);
	
	/**
	 * This method adds a given input frame
	 * 
	 * @param frame
	 * frame to add
	 * @param channel
	 * channel on which frame is transmitted
	 */
	public abstract void addFrameInput(FibexFrame frame, FibexChannel channel);
	
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
		System.out.println("[FibexController:getInstance] This method should be overriden");
		return null;
	}
	
	/**
	 * This method saves the controller
	 * 
	 * @param docReference
	 * reference to FIBEX document
	 * @param controllersReference
	 * reference to the controllers node
	 * @param connectorsReference
	 * reference to the connectors node
	 * @param additionalData
	 * additional data
	 */
	public abstract void save(Object docReference, Object controllersReference, Object connectorsReference, Object... additionalData);
	
	/**
	 * This class represents the controller parameters needed for a controller to operate
	 * 
	 * @author TUM CREATE - RP3
	 */
	public class ControllerParameters
	{
		public FlexRayParameters FlexRay = new FlexRayParameters();
		
		/**
		 * This class represents the parameters for FlexRay controllers
		 * 
		 * @author TUM CREATE - RP3
		 */
		public class FlexRayParameters
		{
			public short KEY_SLOT_USAGE__STARTUP_SYNC = 8;
			public short MAX_DYNAMIC_PAYLOAD_LENGTH = 2;
			public short CLUSTER_DRIFT_DAMPING = 1;
			public short DECODING_CORRECTION = 36;
			public int LISTEN_TIMEOUT = 401202;
			public short MAX_DRIFT = 601;
			public short EXTERN_OFFSET_CORRECTION = 0;
			public short EXTERN_RATE_CORRECTION = 0;
			public short LATEST_TX = 134;
			public int MICRO_PER_CYCLE = 200000;
			public short OFFSET_CORRECTION_OUT = 259;
			public short RATE_CORRECTION_OUT = 601;
			public short SAMPLES_PER_MICROTICK = 2;
			public short DELAY_COMPENSATION_A = 10;
			public short DELAY_COMPENSATION_B = 10;
			public short WAKE_UP_PATTERN = 63;
			public Boolean ALLOW_HALT_DUE_TO_CLOCK = true;
			public short ALLOW_PASSIVE_TO_ACTIVE = 0;
			public short ACCEPTED_STARTUP_RANGE = 109;
			public short MACRO_INITIAL_OFFSET_A = 8;
			public short MACRO_INITIAL_OFFSET_B = 8;
			public short MICRO_INITIAL_OFFSET_A = 34;
			public short MICRO_INITIAL_OFFSET_B = 34;
			public Boolean SINGLE_SLOT_ENABLED = false;
			public double MICROTICK = 0.025000000000000001;
		}
	}
}
