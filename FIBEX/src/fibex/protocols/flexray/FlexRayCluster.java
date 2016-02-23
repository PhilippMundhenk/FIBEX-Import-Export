package fibex.protocols.flexray;

/**
 * This interface defines the additional functionality of a FlexRay FIBEX cluster over a standard FIBEX cluster
 * 
 * @author TUM CREATE - RP3 - Philipp Mundhenk
 */
public interface FlexRayCluster
{
	/**
	 * This method returns the number of static slots defined in this cluster
	 * 
	 * @return
	 * number of static slots
	 */
	public int getNumberOfStaticSlots();
	
	/**
	 * This method returns the number of minislots in this cluster
	 * 
	 * @return
	 * number of minislots
	 */
	public int getNumberOfMinislots();
	
	/**
	 * This method returns the duration of a minislot in microseconds
	 * 
	 * @return
	 * duration of minislot in microseconds
	 */
	public double getMinislotDuration_micros();
	
	/**
	 * This method returns the duration of the network idle time (NIT) in microseconds
	 * 
	 * @return
	 * duration of NIT in microseconds
	 */
	public double getNITDuration_micros();
	
	/**
	 * This method returns the duration of the symbol windows in microseconds
	 * 
	 * @return
	 * duration of symbol window in microseconds
	 */
	public double getSymbolWindowDuration_micros();
	
	/**
	 * This method returns the duration of a static slot in microseconds
	 * 
	 * @return
	 * duration of static slot in microseconds
	 */
	public double getStaticSlotDuration_micros();
	
	/**
	 * This method returns the duration of a minislot in macroticks
	 * 
	 * @return
	 * duration of minislot in macroticks
	 */
	public int getMinislotDuration_Macroticks();
	
	/**
	 * This method returns the duration of the network idle time (NIT) in macroticks
	 * 
	 * @return
	 * duration of NIT in macroticks
	 */
	public int getNITDuration_Macroticks();
	
	/**
	 * This method returns the duration of the symbol window in macroticks
	 * 
	 * @return
	 * duration of symbol window in macroticks
	 */
	public int getSymbolWindowDuration_Macroticks();
	
	/**
	 * This method returns the duration of a static slot in macroticks
	 * 
	 * @return
	 * duration of static slot in macroticks
	 */
	public int getStaticSlotDuration_Macroticks();
	
	/**
	 * This method returns the duration of a cycle in microseconds
	 * 
	 * @return
	 * duration of cycle in microseconds
	 */
	public double getCycleDuration_micros();
	
	/**
	 * This method returns the duration of a cycle in macroticks
	 * 
	 * @return
	 * duration of cycle in macroticks
	 */
	public int getCycleDuration_Macroticks();
	
	/**
	 * This method returns the duration of a macrotick in microseonds
	 * 
	 * @return
	 * duration of macrotick in microseconds
	 */
	public double getMacrotickDuration_micros();
	
	/**
	 * This method returns the duration of a bit in microseconds
	 * 
	 * @return
	 * duration of bit in microseconds
	 */
	public double getBitDuration_micros();
	
	/**
	 * This method returns the length of the payload per static slot in bytes
	 * 
	 * @return
	 * static payload length in bytes
	 */
	public int getStaticSlotPayloadLength_Byte();
	
	//TODO: implement all unimplemented functions to access all data in FIBEX
}
