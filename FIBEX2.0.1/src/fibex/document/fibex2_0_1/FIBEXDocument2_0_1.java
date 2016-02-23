package fibex.document.fibex2_0_1;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;

import net.asam.xml.fbx.FIBEXDocument;

import org.apache.xmlbeans.XmlOptions;

import fibex.document.FIBEXDocumentBasicInteraction;
import fibex.structures.FibexChannel2_0_1;
import fibex.structures.FibexCluster;
import fibex.structures.FibexCluster2_0_1;

/**
 * This class implements the functionality of a FIBEX document for FIBEX version 2.0.1
 * 
 * @author TUM CREATE - RP3 - Philipp Mundhenk
 */
public class FIBEXDocument2_0_1 extends fibex.document.FIBEXBasicDocument implements FIBEXDocumentBasicInteraction
{
	FIBEXDocument doc;
	Collection<FibexCluster> clusters = new LinkedHashSet<FibexCluster>();

	/**
	 * This constructor creates a new documents
	 */
	public FIBEXDocument2_0_1()
	{
		
	}
	
	/**
	 * This constructor creates a new document and loads a FIBEX file
	 * 
	 * @param fileName
	 * path and name of FIBEX file
	 */
	public FIBEXDocument2_0_1(String fileName)
	{
		super(fileName);
	}
	
	/**
	 * This method loads a FIBEX file. Loading is done via a library generated by XMLBeans from the schema files for FIBEX 2.0.1
	 * 
	 * @param fileName
	 * path and name of FIBEX file
	 */
	public void loadDocument(String fileName)
	{
		try
		{
			File file = new File(fileName);
			
			doc = FIBEXDocument.Factory.parse(file);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		FibexChannel2_0_1.setDocument(this);
	}
	
	/**
	 * This method returns the document
	 * 
	 * @return
	 * FIBEX document
	 */
	public FIBEXDocument getDocument()
	{
		return doc;
	}

	/**
	 * This method returns the name of the project
	 * 
	 * @return
	 * name of project
	 */
	@Override
	public String getProjectName()
	{
		return doc.getFIBEX().getPROJECT().getSHORTNAME();
	}

	/**
	 * This method returns the number of clusters
	 * 
	 * @return
	 * number of clusters
	 */
	@Override
	public int getNumberOfClusters()
	{
		return doc.getFIBEX().getELEMENTS().getCLUSTERS().getCLUSTERArray().length;
	}

	/**
	 * This method returns a collection of clusters
	 * 
	 * @return
	 * collection of clusters
	 */
	@Override
	public Collection<FibexCluster> getClusters()
	{
		if(clusters.isEmpty())
		{
			for (int i = 0; i < getNumberOfClusters(); i++) 
			{
				clusters.add(FibexCluster2_0_1.getInstance(doc.getFIBEX().getELEMENTS().getCLUSTERS().getCLUSTERArray(i), doc));
			}
		}
		return clusters;
	}
	
	/**
	 * This method saves the document and all its subcomponents
	 * 
	 * @param fileName
	 * path and name of file to save to
	 */
	@Override
	public void save(String fileName)
	{
		for (Iterator<FibexCluster> i = clusters.iterator(); i.hasNext();)
		{
			FibexCluster cluster = (FibexCluster) i.next();
			
			cluster.save();
		}
		
		XmlOptions opts = new XmlOptions();
		opts.setSavePrettyPrint();
		opts.setSavePrettyPrintIndent(3);
		
		File file = new File(fileName);
		try
		{
			doc.save(file, opts);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}