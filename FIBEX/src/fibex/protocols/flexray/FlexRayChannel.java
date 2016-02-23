package fibex.protocols.flexray;

/**
 * This interface defines the additional functionality needed for a FlexRay FIBEX channel over a standard FIBEX channel
 * 
 * @author TUM CREATE - RP3 - Philipp Mundhenk
 */
public interface FlexRayChannel
{
	/**
	 * This method returns the FlexRay-Channel-Name
	 * 
	 * @return
	 * FlexRay-Channel-Name ("A" or "B")
	 */
	public String getFlexRayChannelName();
}
