package fibex.structures;

import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * This class represents a cluster in the FIBEX file
 * 
 * @author TUM CREATE - RP3 - Philipp Mundhenk
 */
public abstract class FibexCluster
{
	protected Object clusterRepresentation;
	protected Object docReference;
	protected Collection<FibexECU> ecus;
	
	protected String id;
	
	/**
	 * This constructor creates a new cluster. Access via getInstance()
	 * 
	 * @param clusterRepresentation
	 * reference to the cluster node
	 * @param docReference
	 * reference to the FIBEX document
	 */
	protected FibexCluster(Object clusterRepresentation, Object docReference)
	{
		this.clusterRepresentation = clusterRepresentation;
		this.docReference = docReference;
		this.ecus = new LinkedHashSet<FibexECU>();
	}
	
	//TODO: add constructor to create instance without relation to FIBEX file. Also needs to include setting of id
	
	/**
	 * This method returns the name (short-name) of the cluster
	 * 
	 * @return
	 * name (short-name) of the cluster
	 */
	public abstract String getName();
	
	/**
	 * This method returns the type of the cluster
	 * 
	 * @return
	 * type of the cluster, e.g. "FlexRay"
	 */
	public abstract String getClusterType();
	
	/**
	 * This method returns the number of channels inside this cluster
	 * 
	 * @return
	 * number of channels
	 */
	public abstract int getNumberOfChannels();
	
	/**
	 * This method returns all channels inside this cluster
	 * 
	 * @return
	 * collection of all channels inside this cluster
	 */
	public abstract Collection<FibexChannel> getChannels();
	
	/**
	 * This method returns the number of ECUs in this cluster
	 * 
	 * @return
	 * number of ECUs
	 */
	public abstract int getNumberOfECUs();
	
	/**
	 * This method returns all ECUs in this cluster
	 * 
	 * @return
	 * collection of all ECUs in this cluster
	 */
	public abstract Collection<FibexECU> getECUs();
	
	/**
	 * This method returns the speed of the cluster in bits per second
	 * 
	 * @return
	 * speed in bps
	 */
	public abstract int getSpeed();
	
	/**
	 * This method returns the number of cycles
	 * 
	 * @return
	 * number of cycles
	 */
	public abstract int getNumberOfCycles();
	
	/**
	 * This method sets the number of cycles
	 * 
	 * @param numberOfCycles
	 * number of cycles to set
	 */
	public abstract void setNumberofCycles(short numberOfCycles);
	
	/**
	 * This method returns the ID of the cluster node
	 * 
	 * @return
	 * ID of the cluster node
	 */
	public String getId()
	{
		if(this.id == null)
		{
			System.out.println("[FibexCluster:getId] The FIBEX library does not support direct references to FIBEX files. Please implement referencing in extending library.");
			return null;
		}
		else
		{
			return id;
		}
	}
	
	/**
	 * This method creates and returns a new ECU
	 * 
	 * @param name
	 * name (short-name) of the ECU to create
	 * @return
	 * newly created ECU
	 */
	public abstract FibexECU createNewECU(String name);
	
	/**
	 * This method finds and returns an ECU by its name (short-name)
	 * 
	 * @param name
	 * name (short-name) of the ECU to find
	 * @return
	 * requested ECU or null of ECU not found
	 */
	public abstract FibexECU getECUByName(String name);
	
	/**
	 * This method saves the cluster and all its subcomponents
	 * 
	 * @param additionalData
	 * additional data
	 */
	public abstract void save(Object... additionalData);
	
	/**
	 * This method sets the reference to the cluster node
	 * 
	 * @param clusterRepresentation
	 * reference to the cluster node
	 */
	public void setCluster(Object clusterRepresentation)
	{
		this.clusterRepresentation = clusterRepresentation;
	}
	
	/**
	 * This method creates and returns a new instance of this class
	 * 
	 * @param clusterRepresentation
	 * reference to the cluster node
	 * @param docReference
	 * reference to the FIBEX document
	 * @return
	 * newly created cluster
	 */
	public static FibexCluster getInstance(Object clusterRepresentation, Object docReference)
	{
		return null;
	}
}
