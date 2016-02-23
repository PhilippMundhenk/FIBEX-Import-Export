package fibex.protocols.flexray;

import fibex.structures.FibexCluster;
import fibex.structures.FibexCluster2_0_1;

/**
 * This class represents the FlexRay specific functionality of a FIBEX cluster
 * 
 * @author TUM CREATE - RP3 - Philipp Mundhenk
 */
public class FibexFlexRayCluster2_0_1 extends FibexCluster2_0_1 implements FlexRayCluster
{
	/**
	 * This constructor creates a new FlexRay cluster. Access via getInstance()
	 * 
	 * @param clusterRepresentation
	 * reference to the FIBEX cluster node
	 * @param docReference
	 * reference to the FIBEX document
	 */
	protected FibexFlexRayCluster2_0_1(Object clusterRepresentation, Object docReference)
	{
		super(clusterRepresentation, docReference);
	}
	
	/**
	 * This method creates and returns a new instance of a FlexRay cluster
	 * 
	 * @param clusterRepresentation
	 * reference to FIBEX cluster node
	 * @param docReference
	 * reference to FIBEX file
	 * @return
	 * newly created cluster, null if cluster is not of type FlexRay
	 */
	public static FibexCluster getInstance(Object clusterRepresentation, Object docReference)
	{
		if(((net.asam.xml.fbx.CLUSTERTYPE)clusterRepresentation).getPROTOCOL().equals("FlexRay"))
		{
			return new FibexFlexRayCluster2_0_1(clusterRepresentation, docReference);
		}
		else
		{
			return null;
		}
	}

	/**
	 * This method returns the number of static slots in the cluster
	 * 
	 * @return
	 * number of static slots
	 */
	@Override
	public int getNumberOfStaticSlots()
	{
		return ((net.asam.xml.fbx.flexray.CLUSTERTYPE)clusterRepresentation).getNUMBEROFSTATICSLOTS();
	}

	/**
	 * This method returns the number of minislots in the cluster
	 * 
	 * @return
	 * number of minislots
	 */
	@Override
	public int getNumberOfMinislots()
	{
		return ((net.asam.xml.fbx.flexray.CLUSTERTYPE)clusterRepresentation).getNUMBEROFMINISLOTS();
	}

	/**
	 * This method returns the duration of a minislot in microseconds
	 * 
	 * @return
	 * duration of minislot in microseconds
	 */
	@Override
	public double getMinislotDuration_micros()
	{
		return getMinislotDuration_Macroticks()*getMacrotickDuration_micros();
	}

	/**
	 * This method returns the network idle time (NIT) in microseconds
	 * 
	 * @return
	 * duration of NIT in microseconds
	 */
	@Override
	public double getNITDuration_micros()
	{
		return getNITDuration_Macroticks()*getMacrotickDuration_micros();
	}

	/**
	 * This method returns the duration of the symbol window in microseconds
	 * 
	 * @return
	 * duration of symbol windows in microseconds
	 */
	@Override
	public double getSymbolWindowDuration_micros()
	{
		return getSymbolWindowDuration_Macroticks()*getMacrotickDuration_micros();
	}

	/**
	 * This method returns the duration of a static slot in microseconds
	 * 
	 * @return
	 * duration of static slot in microseconds
	 */
	@Override
	public double getStaticSlotDuration_micros()
	{
		return getStaticSlotDuration_Macroticks()*getMacrotickDuration_micros();
	}

	/**
	 * This method returns the duration of a minislot in macroticks
	 * 
	 * @return
	 * duration of minislot in macroticks
	 */
	@Override
	public int getMinislotDuration_Macroticks()
	{
		return ((net.asam.xml.fbx.flexray.CLUSTERTYPE)clusterRepresentation).getMINISLOT();
	}

	/**
	 * This method returns the duration of the netowrk idle time (NIT) in macroticks
	 * 
	 * @return
	 * duration of NIT in macroticks
	 */
	@Override
	public int getNITDuration_Macroticks()
	{
		return ((net.asam.xml.fbx.flexray.CLUSTERTYPE)clusterRepresentation).getNIT();
	}

	/**
	 * This method returns the duration of the symbol window in macroticks
	 * 
	 * @return
	 * duration of symbol window in macroticks
	 */
	@Override
	public int getSymbolWindowDuration_Macroticks()
	{
		return ((net.asam.xml.fbx.flexray.CLUSTERTYPE)clusterRepresentation).getSYMBOLWINDOW();
	}

	/**
	 * This method returns the duration of a static slot in macroticks
	 * 
	 * @return
	 * duration of static slot in macroticks
	 */
	@Override
	public int getStaticSlotDuration_Macroticks()
	{
		return ((net.asam.xml.fbx.flexray.CLUSTERTYPE)clusterRepresentation).getSTATICSLOT();
	}

	/**
	 * This method returns the duration of a cycle in microseconds
	 * 
	 * @return
	 * duration of cycle in microseconds
	 */
	@Override
	public double getCycleDuration_micros()
	{
		return getCycleDuration_Macroticks()*getMacrotickDuration_micros();
	}

	/**
	 * This method returns the duration of a cycle in macroticks
	 * 
	 * @return
	 * duration of cycle in macroticks
	 */
	@Override
	public int getCycleDuration_Macroticks()
	{
		return ((net.asam.xml.fbx.flexray.CLUSTERTYPE)clusterRepresentation).getCYCLE();
	}

	/**
	 * This method returns the duration of a macrotick in microseconds
	 * 
	 * @return
	 * duration of macrotick in microseconds
	 */
	@Override
	public double getMacrotickDuration_micros()
	{
		return ((net.asam.xml.fbx.flexray.CLUSTERTYPE)clusterRepresentation).getMACROTICK();
	}

	/**
	 * This method returns the duration of a bit in microseconds
	 * 
	 * @return
	 * duration of bit in microseconds
	 */
	@Override
	public double getBitDuration_micros()
	{
		return ((net.asam.xml.fbx.flexray.CLUSTERTYPE)clusterRepresentation).getBIT();
	}

	/**
	 * This method returns the length of the payload in each static slot in bytes
	 * 
	 * @return
	 * length of static payload in bytes
	 */
	@Override
	public int getStaticSlotPayloadLength_Byte()
	{
		return ((net.asam.xml.fbx.flexray.CLUSTERTYPE)clusterRepresentation).getPAYLOADLENGTHSTATIC()*2;
	}
}