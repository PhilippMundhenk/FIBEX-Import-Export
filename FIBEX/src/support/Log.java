package support;


/**
 * This class contains all logging functions for the systems standard output
 * 
 * @author TUM CREATE - RP3 - Philipp Mundhenk
 */
public class Log
{
	public static final int LOG_LEVEL_LOW = 0;
	public static final int LOG_LEVEL_MEDIUM = 10;
	public static final int LOG_LEVEL_TRACE = 20;
	
	private static Boolean newLine = true;
	private static long startTime;
	
	/**
	 * This method initializes the log functionality. It needs to be called before any calls to log messages are executed
	 */
	public static void initLog()
	{
		startTime =  System.currentTimeMillis();
	}
	
	
	/**
	 * This method logs a message with a specified debug level and a module name
	 * 
	 * @param debugMessage
	 * message to log
	 * @param debugLevel
	 * level to log on
	 * @param module
	 * name of logging module
	 */
	public static void log(String debugMessage, int debugLevel, String module)
	{
		long time =  System.currentTimeMillis() - startTime;
		if(debugLevel <= FIBEXConfiguration.LOG_LEVEL)
		{
			if(newLine)
			{
				System.out.print("[" + module + "; " + time + "ms] ");
			}
			System.out.print(debugMessage);
		}
		
		newLine = false;
	}
	
	/**
	 * This method logs a message with a specified debug level and a module name.
	 * A new line is added at the end of the message
	 * 
	 * @param debugMessage
	 * message to log
	 * @param debugLevel
	 * level to log on
	 * @param module
	 * name of logging module
	 */
	public static void logln(String debugMessage, int debugLevel, String module)
	{
		long time =  System.currentTimeMillis() - startTime;
		if(debugLevel <= FIBEXConfiguration.LOG_LEVEL)
		{
			if(newLine)
			{
				System.out.print("[" + module + "; " + time + "ms] ");
			}
			System.out.println(debugMessage);
		}
		
		newLine = true;
	}
	
	/**
	 * This method adds a new line to the logging output
	 * 
	 * @param debugLevel
	 * level to log the new line on
	 */
	public static void logln(int debugLevel)
	{
		if(debugLevel <= FIBEXConfiguration.LOG_LEVEL)
		{
			System.out.println();
		}
		
		newLine = true;
	}
	
	/**
	 * This method logs a message with a specified debug level and a module name.
	 * The debug level is LOW
	 * 
	 * @param debugMessage
	 * message to log
	 * @param module
	 * name of logging module
	 */
	public static void logLow(String debugMessage, String module)
	{
		log(debugMessage, LOG_LEVEL_LOW, module);
	}
	
	/**
	 * This method logs a message with a specified debug level and a module name.
	 * A new line is added at the end of the message.
	 * The debug level is LOW
	 * 
	 * @param debugMessage
	 * message to log
	 * @param module
	 * name of logging module
	 */
	public static void logLowln(String debugMessage, String module)
	{
		logln(debugMessage, LOG_LEVEL_LOW, module);
	}
	
	/**
	 * This method adds a new line to the logging output on debug level LOW
	 */
	public static void logLowln()
	{
		logln(LOG_LEVEL_LOW);
	}
	
	/**
	 * This method logs a message with a specified debug level and a module name.
	 * The debug level is MEDIUM
	 * 
	 * @param debugMessage
	 * message to log
	 * @param module
	 * name of logging module
	 */
	public static void logMedium(String debugMessage, String module)
	{
		log(debugMessage, LOG_LEVEL_MEDIUM, module);
	}
	
	/**
	 * This method logs a message with a specified debug level and a module name.
	 * A new line is added at the end of the message.
	 * The debug level is LOW
	 * 
	 * @param debugMessage
	 * message to log
	 * @param module
	 * name of logging module
	 */
	public static void logMediumln(String debugMessage, String module)
	{
		logln(debugMessage, LOG_LEVEL_MEDIUM, module);
	}
	
	/**
	 * This method adds a new line to the logging output on debug level MEDIUM
	 */
	public static void logMediumln()
	{
		logln(LOG_LEVEL_MEDIUM);
	}
	
	/**
	 * This method logs a message with a specified debug level and a module name.
	 * The debug level is TRACE
	 * 
	 * @param debugMessage
	 * message to log
	 * @param module
	 * name of logging module
	 */
	public static void logTrace(String debugMessage, String module)
	{
		log(debugMessage, LOG_LEVEL_TRACE, module);
	}
	
	/**
	 * This method logs a message with a specified debug level and a module name.
	 * A new line is added at the end of the message.
	 * The debug level is LOW
	 * 
	 * @param debugMessage
	 * message to log
	 * @param module
	 * name of logging module
	 */
	public static void logTraceln(String debugMessage, String module)
	{
		logln(debugMessage, LOG_LEVEL_TRACE, module);
	}
	
	/**
	 * This method adds a new line to the logging output on debug level TRACE
	 */
	public static void logTraceln()
	{
		logln(LOG_LEVEL_TRACE);
	}
}
