package fibex;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Iterator;

import support.FIBEXConfiguration;
import support.Log;
import fibex.document.FIBEXBasicDocument;
import fibex.document.FIBEXBasicInterface;
import fibex.exceptions.FIBEXNotValidFileException;
import fibex.exceptions.FIBEXUnsupportedVersionException;
import fibex.utilities.SupportedVersion;

/**
 * This class represents a FIBEX file. It is the main access point for all other programs. A FIBEX file is loaded in this class
 * 
 * @author TUM CREATE - RP3 - Philipp Mundhenk
 */
public class FIBEXFile
{
	private static final String module = "Fibex";
	private FIBEXBasicDocument doc;
	private String fileName;

	/**
	 * This constructor loads a new FIBEX file and the library implementing the functionality for this version, if both are available. 
	 * A version override is provided to parse the FIBEX file with another version than the FIBEX version specified in the file itself
	 * 
	 * @param fileName
	 * path and name of the FIBEX file to load
	 * @param versionOverride
	 * override of the version in the FIBEX file
	 * 
	 * @throws FIBEXUnsupportedVersionException
	 * @throws FIBEXNotValidFileException
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IOException
	 */
	public FIBEXFile(String fileName, String versionOverride) throws FIBEXUnsupportedVersionException, FIBEXNotValidFileException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException
	{
		/* loads all supported FIBEX versions */
		SupportedVersion.loadLibs();
		
		this.fileName = fileName;
		
		if(SupportedVersion.getVersions().isEmpty())
		{
			Log.logLowln("no libraries found to load", module);
		}
		
		String version;
		if(versionOverride == "")
		{
			version = getVersion(fileName);
		}
		else
		{
			version = versionOverride;
		}
		if(version == "" || version == null)
		{
			throw new FIBEXUnsupportedVersionException();
		}
		
		for (Iterator<SupportedVersion> i = SupportedVersion.getVersions().iterator(); i.hasNext();)
		{
			SupportedVersion ver = (SupportedVersion) i.next();
			
			if(ver.getVersion().equals(version))
			{
				Class<?>[] parameters = new Class[] { URL.class };
				File file = new File(FIBEXConfiguration.LIB_FOLDER+"/"+ver.getFileName());
				Log.logLowln("loading library: "+file.getName()+", from: "+FIBEXConfiguration.LIB_FOLDER+"/"+ver.getFileName(), module);
				URL url = file.toURI().toURL();
				ClassLoader classLoader = (URLClassLoader)ClassLoader.getSystemClassLoader();
				
				/* needed to access protected method: */
				Class<?> sysclass = URLClassLoader.class;	
				try
				{
					Method method = sysclass.getDeclaredMethod("addURL", parameters);
					method.setAccessible(true);
					method.invoke(classLoader, new Object[]{url});
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}

				Class<?> c = classLoader.loadClass("fibex.document."+ver.getPackageName()+"."+ver.getClassName());
				doc = (FIBEXBasicDocument)c.newInstance();
				doc.loadDocument(fileName);
				return;
			}
		}
		
		throw new FIBEXUnsupportedVersionException();
	}

	/**
	 * This method checks if FIBEX document implements given interface.
	 * 
	 * @param interfaceName
	 * name of interface to cast to
	 * @return
	 * if interface is implemented, document is returned. If interface is not implemented, null is returned
	 */
	public FIBEXBasicInterface getDocumentAsInterface(String interfaceName)
	{
		Class<?>[] interfaces = doc.getClass().getInterfaces();
		
		for (int i = 0; i < interfaces.length; i++) 
		{
			if(interfaces[i].getName().equals(interfaceName))
			{
				return (FIBEXBasicInterface)doc;
			}
		}
		
		return null;
	}
	
	/**
	 * This method returns the document which gives access to the contents of the FIBEX file
	 * 
	 * @return
	 * FIBEX document
	 */
	public FIBEXBasicDocument getDocument()
	{
		return doc;
	}
	
	/**
	 * This method extract the version from the FIBEX file
	 * 
	 * @param fileName
	 * path and name of the FIBEX file
	 * @return
	 * extracted version
	 * @throws FIBEXNotValidFileException
	 */
	private String getVersion(String fileName) throws FIBEXNotValidFileException
	{
		/* determine FIBEX version */
		FileInputStream fstream;
		try
		{
			fstream = new FileInputStream(fileName);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
		
			String[] str = null;
			String input = br.readLine();
			input = br.readLine();
			if(input != null)
			{
				str = input.split(" ");
			}
			
			if(str != null)
			{
				for (int i = 0; i < str.length; i++)
				{
					if(!(str[0].contains("FIBEX")))
					{
						br.close();
						throw new FIBEXNotValidFileException();
					}
					if(str[i].contains("VERSION"))
					{
						String[] versionStr = str[i].split("\"");
						br.close();
						
						return versionStr[1];
					}
				}
			}
			br.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * This method saves the FIBEX file and all its subcomponents
	 * 
	 * @param fileName
	 * path and name to safe to
	 */
	public void save(String fileName)
	{
		doc.save(fileName);
	}
	
	/**
	 * This method returns the path and name of the loaded FIBEX file
	 * 
	 * @return
	 * path and name of FIBEX file
	 */
	public String getFileName()
	{
		return fileName;
	}
}
