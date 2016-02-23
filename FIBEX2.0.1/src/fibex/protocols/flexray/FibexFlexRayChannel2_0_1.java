package fibex.protocols.flexray;

import fibex.structures.FibexChannel;
import fibex.structures.FibexChannel2_0_1;

/**
 * This class represents the FlexRay specific functionality of a FIBEX channel
 * 
 * @author TUM CREATE - RP3 - Philipp Mundhenk
 */
public class FibexFlexRayChannel2_0_1 extends FibexChannel2_0_1 implements FlexRayChannel
{
	/**
	 * This constructor creates a new channel. Access via getInstance()
	 * 
	 * @param channelRepresentation
	 * reference to the channel node in the FIBEX file
	 * @param docReference
	 * reference to the FIBEX document
	 */
	protected FibexFlexRayChannel2_0_1(Object channelRepresentation, Object docReference)
	{
		super(channelRepresentation, docReference);
	}

	/**
	 * This method returns the FlexRay-Channel-Name
	 * 
	 * @return
	 * FlexRay-Channel-Name ("A" or "B")
	 */
	@Override
	public String getFlexRayChannelName()
	{
		return ((net.asam.xml.fbx.flexray.CHANNELTYPE)channelRepresentation).getFLEXRAYCHANNELNAME().toString();
	}
	
	/**
	 * This method creates and returns a new instance of FlexRayChannel
	 * 
	 * @param channelRepresentation
	 * reference to the channel node in the FIBEX file
	 * @param docReference
	 * reference to the FIBEX document
	 * @return
	 * newly created channel, null if channel is not of type FlexRay
	 */
	public static FibexChannel getInstance(Object channelRepresentation, Object docReference)
	{
		if(channelRepresentation instanceof net.asam.xml.fbx.flexray.CHANNELTYPE)
		{
			return new FibexFlexRayChannel2_0_1(channelRepresentation, docReference);
		}
		else
		{
			return null;
		}
	}

}
