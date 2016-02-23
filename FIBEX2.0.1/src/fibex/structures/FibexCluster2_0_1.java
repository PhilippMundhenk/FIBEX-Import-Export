package fibex.structures;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;

import net.asam.xml.fbx.CLUSTERTYPE;
import net.asam.xml.fbx.FIBEXDocument;
import fibex.protocols.flexray.FibexFlexRayCluster2_0_1;

/**
 * This class implements all functionality of FibexCluster for FIBEX 2.0.1
 * 
 * @author TUM CREATE - RP3 - Philipp Mundhenk
 */
public class FibexCluster2_0_1 extends FibexCluster
{
	Boolean channelsLoaded = false;
	Boolean ecusLoaded = false;
	
	private Collection<FibexChannel> channels = new LinkedHashSet<FibexChannel>();
	
	/**
	 * This constructor creates a new cluster. Access vie getInstance()
	 * 
	 * @param clusterRepresentation
	 * reference to the cluster node
	 * @param docReference
	 * reference to the FIBEX document
	 */
	protected FibexCluster2_0_1(Object clusterRepresentation, Object docReference)
	{
		super(clusterRepresentation, docReference);
	}
	
	/**
	 * This method returns the ID of the cluster node
	 * 
	 * @return
	 * ID of the cluster node
	 */
	@Override
	public String getId()
	{
		if(this.id == null)
		{
			return ((CLUSTERTYPE)clusterRepresentation).getID();
		}
		else
		{
			return id;
		}
	}

	/**
	 * This method returns the type of the cluster
	 * 
	 * @return
	 * cluster type, e.g. "FlexRay"
	 */
	@Override
	public String getClusterType()
	{
		return ((CLUSTERTYPE)clusterRepresentation).getPROTOCOL().toString();
	}

	/**
	 * This method returns the number of channels in a cluster
	 * 
	 * @return
	 * number of channels in cluster
	 */
	@Override
	public int getNumberOfChannels()
	{
		return getChannels().size();
	}

	/**
	 * This method returns a collection of all channels in the cluster
	 * 
	 * @return
	 * collection of all channels
	 */
	@Override
	public Collection<FibexChannel> getChannels()
	{
		if(!channelsLoaded)
		{
			for (int i = 0; i < ((CLUSTERTYPE)clusterRepresentation).getCHANNELREFS().getCHANNELREFArray().length; i++)
			{
				channels.add(FibexChannel2_0_1.getChannel(((CLUSTERTYPE)clusterRepresentation).getCHANNELREFS().getCHANNELREFArray()[i].getIDREF()));
			}
			channelsLoaded = true;
		}
		return channels;
	}

	/**
	 * This method returns the number of ECUs in this cluster
	 * 
	 * @return
	 * number of ECUs
	 */
	@Override
	public int getNumberOfECUs()
	{
		return this.ecus.size();
	}

	/**
	 * This method returns a collection of all ECUs in this cluster
	 * 
	 * @return
	 * collection of all ECUs
	 */
	@Override
	public Collection<FibexECU> getECUs()
	{
		if(!ecusLoaded)
		{
			if(!(((FIBEXDocument)docReference).getFIBEX().getELEMENTS().getECUS() == null))
			{
				for (int i = 0; i < ((FIBEXDocument)docReference).getFIBEX().getELEMENTS().getECUS().getECUArray().length; i++)
				{
					FibexECU ecu = FibexECU2_0_1.getInstance(((FIBEXDocument)docReference).getFIBEX().getELEMENTS().getECUS().getECUArray(i), docReference);
					if(ecu != null)
					{
						ecus.add(ecu);
					}
				}
			}
			ecusLoaded = true;
		}
		return this.ecus;
	}

	/**
	 * This method returns the speed in bits per second (bps)
	 * 
	 * @return
	 * speed of cluster in bps
	 */
	@Override
	public int getSpeed()
	{
		return ((CLUSTERTYPE)clusterRepresentation).getSPEED().intValue();
	}
	
	/**
	 * This method returns the name (short-name) of the cluster
	 * 
	 * @return
	 * name (short-name) of cluster
	 */
	@Override
	public String getName()
	{
		return ((CLUSTERTYPE)clusterRepresentation).getSHORTNAME();
	}
	
	/**
	 * This method creates and returns a new instance of this class
	 * 
	 * @param clusterRepresentation
	 * reference to the cluster node
	 * @param docReference
	 * reference to the FIBEX document
	 * @return
	 * newly created cluster, null if given node does not contain cluster
	 */
	public static FibexCluster getInstance(Object clusterRepresentation, Object docReference)
	{
		if(clusterRepresentation instanceof net.asam.xml.fbx.flexray.CLUSTERTYPE)
		{
			return FibexFlexRayCluster2_0_1.getInstance(clusterRepresentation, docReference);
		}
		else if(clusterRepresentation instanceof net.asam.xml.fbx.CLUSTERTYPE)
		{
			return new FibexCluster2_0_1(clusterRepresentation, docReference);
		}
		else
		{
			return null;
		}
	}

	/**
	 * This method returns the number of cycles in this cluster
	 * 
	 * @return
	 * number of cycles
	 */
	@Override
	public int getNumberOfCycles()
	{
		return ((CLUSTERTYPE)clusterRepresentation).getNUMBEROFCYCLES();
	}
	
	/**
	 * This method saves the cluster and all subcomponents
	 * 
	 * @param additionalData
	 * additional data
	 */
	@Override
	public void save(Object... additionalData)
	{
		for (Iterator<FibexChannel> i = channels.iterator(); i.hasNext();)
		{
			FibexChannel channel = (FibexChannel) i.next();
			
			channel.save(this.getClusterType());
		}
		for (Iterator<FibexECU> i = ecus.iterator(); i.hasNext();)
		{
			FibexECU ecu = (FibexECU2_0_1)i.next();
			
			ecu.save(docReference, this.getClusterType());
		}
	}

	/**
	 * This method sets the number of cycles for this cluster
	 * 
	 * @param numberOfCycles
	 * number of cycles
	 */
	@Override
	public void setNumberofCycles(short numberOfCycles)
	{
		((CLUSTERTYPE)clusterRepresentation).setNUMBEROFCYCLES(numberOfCycles);
	}
	
	/**
	 * This method creates and returns a new ECU
	 * 
	 * @param name
	 * name of ECU to create
	 */
	@Override
	public FibexECU createNewECU(String name)
	{
		getECUs();
		for (Iterator<FibexECU> i = ecus.iterator(); i.hasNext();)
		{
			FibexECU2_0_1 ecu = (FibexECU2_0_1) i.next();
			
			if(!(ecu.getName() == null))
			{
				if(ecu.getName().equals(name))
				{
					return ecu;
				}
			}
		}
		
		FibexECU ecu = new FibexECU2_0_1(name);
		this.ecus.add(ecu);
		return ecu;
	}

	/**
	 * This method returns the ECU with the given name
	 * 
	 * @param name
	 * name of the requested ECU
	 * @return
	 * requested ECU or null, if ECU not found
	 */
	@Override
	public FibexECU getECUByName(String name)
	{
		for (Iterator<FibexECU> i = ecus.iterator(); i.hasNext();)
		{
			FibexECU ecu = (FibexECU) i.next();
			
			if(ecu.getName().equals(name))
			{
				return ecu;
			}
		}
		return null;
	}
}