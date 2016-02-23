package fibex.structures;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;

/**
 * This class represents an ECU in a FIBEX file
 * 
 * @author TUM CREATE - RP3 - Philipp Mundhenk
 */
public abstract class FibexECU 
{
	protected String name;
	protected HashMap<FibexFrame, FibexChannel> channels;
	protected Collection<FibexController> controllers;
	
	protected Object ecuRepresentation;
	protected Object docReference;
	
	protected static int ecuCounter = -1;
	protected String id;
	
	/**
	 * This constructor creates the ECU with a given name (short-name)
	 * 
	 * @param name
	 * name (short-name) of the ECU to create
	 */
	public FibexECU(String name)
	{
		this.name = name;
		this.channels = new HashMap<FibexFrame, FibexChannel>();
		this.controllers = new LinkedHashSet<FibexController>();
		id = "FIBEX_ECU_"+getNextEcuCounter();
	}
	
	/**
	 * This constructor creates a new instance of ECU. Access via getInstance()
	 * 
	 * @param ecuRepresentation
	 * reference to ECU node
	 * @param docReference
	 * reference to FIBEX document
	 */
	protected FibexECU(Object ecuRepresentation, Object docReference)
	{
		this.ecuRepresentation = ecuRepresentation;
		this.docReference = docReference;
		this.controllers = new LinkedHashSet<FibexController>();
		getNextEcuCounter();
	}
	
	/**
	 * This method increments and returns the counter used for generating IDs for ECU nodes
	 * 
	 * @return
	 * incremented counter
	 */
	protected int getNextEcuCounter()
	{
		ecuCounter++;
		return ecuCounter;
	}
	
	/**
	 * This method returns the name (short-name) of the ECU
	 * 
	 * @return
	 * name (short-name) of the ECU
	 */
	public String getName()
	{
		if(this.name == null)
		{
			System.out.println("[FibexECU:getName] The FIBEX library does not support direct references to FIBEX files. Please implement referencing in extending library.");
			return null;
		}
		else
		{
			return this.name;
		}
	}
	
	/**
	 * This method returns the ID of the ECU
	 * 
	 * @return
	 * ID of the ECU
	 */
	public String getId()
	{
		if(this.id == null)
		{
			System.out.println("[FibexECU:getId] The FIBEX library does not support direct references to FIBEX files. Please implement referencing in extending library.");
			return null;
		}
		else
		{
			return this.id;
		}
	}
	
	/**
	 * This message returns the controller matching a certain name
	 * 
	 * @param name
	 * name (short-name) of the controller to find
	 * @return
	 * controller to find or null, if no controller found for name
	 */
	public FibexController getControllerByName(String name)
	{
		for (Iterator<FibexController> i = controllers.iterator(); i.hasNext();)
		{
			FibexController controller = (FibexController) i.next();
			
			if(controller.getName().equals(name))
			{
				return controller;
			}
		}
		return null;
	}
	
	/**
	 * This method creates and returns a new controller
	 * 
	 * @param name
	 * name (short-name) of the controller to create
	 * @return
	 * newly created controller
	 */
	public FibexController getNewController(String name)
	{
		System.out.println("[FibexECU:getNewController] This method should be overriden");
		return null;
	}
	
	/**
	 * This method returns all controllers of this ECU
	 * 
	 * @return
	 * collection of all controllers in this ECU
	 */
	public Collection<FibexController> getControllers()
	{
		return this.controllers;
	}
	
	/**
	 * This method creates and returns a new instance of the ECU.
	 * 
	 * @param ecuRepresentation
	 * reference to ECU node
	 * @param docReference
	 * reference to FIBEX document
	 * @return
	 * newly created ECU, null if given wrong input nodes given
	 */
	public static FibexECU getInstance(Object ecuRepresentation, Object docReference)
	{
		System.out.println("[FibexECU:getInstance] This method should be overriden");
		return null;
	}
	
	/**
	 * This method saves the ECU and all its subcomponents
	 * 
	 * @param docReference
	 * reference to the FIBEX document
	 * @param additionalData
	 * additional data
	 */
	public abstract void save(Object docReference, Object... additionalData);
}
