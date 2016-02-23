package fibex.document;

import java.util.Collection;

import fibex.structures.FibexCluster;

/**
 * This interface defines the basic interactions possible with a loaded document
 * 
 * @author TUM CREATE - RP3 - Philipp Mundhenk
 */
public interface FIBEXDocumentBasicInteraction extends FIBEXBasicInterface
{
	/**
	 * This method returns the name of the FIBEX project
	 * 
	 * @return
	 * name of project
	 */
	public String getProjectName();
	
	/**
	 * This method returns the number of clusters
	 * 
	 * @return
	 * number of clusters
	 */
	public int getNumberOfClusters();
	
	/**
	 * This method returns a collection of clusters
	 * 
	 * @return
	 * collection of clusters
	 */
	public Collection<FibexCluster> getClusters();
}
