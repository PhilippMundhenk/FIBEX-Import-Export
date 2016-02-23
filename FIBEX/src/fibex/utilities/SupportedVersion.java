package fibex.utilities;

import java.io.File;
import java.util.Collection;
import java.util.LinkedHashSet;

import support.FIBEXConfiguration;
import support.Log;

/**
 * This class extracts and represents all supported versions from the library folder
 * 
 * @author TUM CREATE - RP3 - Philipp Mundhenk
 */
public class SupportedVersion
{
	static Collection<SupportedVersion> versions = new LinkedHashSet<SupportedVersion>();
	
	private String version;
	private String className;
	private String packageName;
	private String fileName;
	private static final String module = "Fibex";
	
	/**
	 * This constructor creates an instance without initializing any fields
	 */
	public SupportedVersion()
	{
		
	}
	
	/**
	 * This method returns the version string of the instance
	 * 
	 * @return
	 * version string
	 */
	public String getVersion()
	{
		return version;
	}
	
	/**
	 * This method returns the version string of the instance
	 * 
	 * @param version
	 * version string
	 */
	public void setVersion(String version)
	{
		this.version = version;
	}
	
	/**
	 * This method returns the name of the class inheriting from FibexDocument
	 * 
	 * @return
	 * name of class
	 */
	public String getClassName()
	{
		return className;
	}
	
	/**
	 * This method sets the name of the class inheriting from FibexDocument
	 * 
	 * @param className
	 * name of class
	 */
	public void setClassName(String className)
	{
		this.className = className;
	}
	
	/**
	 * This method returns the name of the package where the initiating classes are located
	 * 
	 * @return
	 * name of package
	 */
	public String getPackageName()
	{
		return packageName;
	}
	
	/**
	 * This method sets the name of the package where the initiating classes are located
	 * 
	 * @param packageName
	 * name of package
	 */
	public void setPackageName(String packageName)
	{
		this.packageName = packageName;
	}
	
	/**
	 * This method returns the filename of the library for this version
	 * 
	 * @return
	 * filename
	 */
	public String getFileName()
	{
		return fileName;
	}
	
	/**
	 * This method sets the filename of the library for this version
	 * 
	 * @param fileName
	 * filename
	 */
	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}
	
	/**
	 * This method returns a collection of all support versions
	 * 
	 * @return
	 * collection of all supported version
	 */
	public static Collection<SupportedVersion> getVersions()
	{
		return versions;
	}
	
	/**
	 * This method sets all supported versions
	 * 
	 * @param versions
	 * collection of all supported versions
	 */
	public static void setVersions(Collection<SupportedVersion> versions)
	{
		SupportedVersion.versions = versions;
	}
	
	/**
	 * This method adds a version to the list of supported versions
	 * 
	 * @param version
	 * version to add
	 */
	public static void addVersion(SupportedVersion version)
	{
		versions.add(version);
	}
	
	/**
	 * This method checks the library folder defined in the config and saves all libraries found as supported versions
	 */
	public static void loadLibs()
	{
		String file;
		File folder = new File(FIBEXConfiguration.LIB_FOLDER);
		File[] listOfFiles = folder.listFiles(); 
		
		if(listOfFiles != null)
		{	
			for (int i = 0; i < listOfFiles.length; i++) 
			{
				if (listOfFiles[i].isFile()) 
				{
					file = listOfFiles[i].getName();
					Log.logLowln("found library: " + file, module);
					if(file.startsWith(FIBEXConfiguration.LIB_PREFIX))
					{
						if (file.endsWith(".jar") || file.endsWith(".JAR") || file.endsWith(".Jar") || file.endsWith(".jAr") || file.endsWith(".jaR") || file.endsWith(".JAr") || file.endsWith(".jAR") || file.endsWith(".Jar"))
						{
							SupportedVersion ver = new SupportedVersion();
							ver.setFileName(file);
							String[] str = file.split(FIBEXConfiguration.LIB_PREFIX);
							str[1] = str[1].split(".jar")[0];
							ver.setClassName("FIBEXDocument"+str[1]);
							ver.setPackageName("fibex"+str[1]);
							str[1] = str[1].replace('_', '.');
							ver.setVersion(str[1]);
							versions.add(ver);
						}
					}
				}
			}
		}
		
		
	}
}
