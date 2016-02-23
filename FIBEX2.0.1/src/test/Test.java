package test;

import java.net.MalformedURLException;
import java.util.Iterator;

import fibex.document.FIBEXBasicDocument;
import fibex.document.FIBEXDocumentBasicInteraction;
import fibex.document.fibex2_0_1.FIBEXDocument2_0_1;
import fibex.exceptions.FIBEXNotValidFileException;
import fibex.exceptions.FIBEXUnsupportedVersionException;
import fibex.protocols.flexray.FlexRayCluster;
import fibex.structures.FibexChannel;
import fibex.structures.FibexCluster;

public class Test
{

	/**
	 * @param args
	 * @throws MalformedURLException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 * @throws FIBEXNotValidFileException 
	 * @throws FIBEXUnsupportedVersionException 
	 */
	public static void main(String[] args) throws FIBEXUnsupportedVersionException, FIBEXNotValidFileException, ClassNotFoundException, InstantiationException, IllegalAccessException, MalformedURLException
	{
		FIBEXDocument2_0_1 doc201 = new FIBEXDocument2_0_1();
		FIBEXBasicDocument doc = (FIBEXBasicDocument)doc201;
		doc201.loadDocument("C:\\Philipp\\others\\examples\\fibextest2.xml");
//		System.out.println(doc.getDocument().getFIBEX().getPROJECT().getSHORTNAME());
//		System.out.println("static slots: "+((CLUSTERTYPEImpl)doc.getDocument().getFIBEX().getELEMENTS().getCLUSTERS().getCLUSTERArray(0)).getNUMBEROFSTATICSLOTS());
		
		System.out.println(((FIBEXDocumentBasicInteraction)doc).getProjectName());
		System.out.println(((FibexCluster)((FIBEXDocumentBasicInteraction)doc).getClusters().toArray()[0]).getClusterType());
		for (Iterator<FibexCluster> i = ((FIBEXDocumentBasicInteraction)doc).getClusters().iterator(); i.hasNext();)
		{
			FibexCluster cluster = (FibexCluster) i.next();
			
			System.out.println("Cluster: " + cluster.getName() + " of type " + cluster.getClusterType());
			System.out.println("Contains channels: ");
			for (Iterator<FibexChannel> j = cluster.getChannels().iterator(); j.hasNext();)
			{
				FibexChannel channel = (FibexChannel)j.next();
				
				System.out.println(channel.getName());
			}
			System.out.println("number of static slots in segment: " + ((FlexRayCluster)cluster).getNumberOfStaticSlots());
		}
	}

}
