package fibex.structures;

import java.util.Iterator;

import net.asam.xml.fbx.ECUTYPE;
import net.asam.xml.fbx.FIBEXDocument;

/**
 * This class implements all functionality of FibexECU for FIBEX 2.0.1
 * 
 * @author TUM CREATE - RP3 - Philipp Mundhenk
 */
public class FibexECU2_0_1 extends FibexECU
{
	Boolean controllersLoaded = false;
	
	/**
	 * This constructor creates a new ECU
	 * 
	 * @param name
	 * name of ECU
	 */
	public FibexECU2_0_1(String name)
	{
		super(name);
	}
	
	/**
	 * This constructor creates a new instance of ECU. Access via getInstance()
	 * 
	 * @param ecuRepresentation
	 * reference to ECU node
	 * @param docReference
	 * reference to FIBEX document
	 */
	protected FibexECU2_0_1(Object ecuRepresentation, Object docReference)
	{
		super(ecuRepresentation, docReference);
	}

	/**
	 * This method returns the ID of the ECU node
	 * 
	 * @return
	 * ID of ECU node
	 */
	@Override
	public String getId()
	{
		if(this.id == null)
		{
			return ((ECUTYPE)ecuRepresentation).getID();
		}
		else
		{
			return this.id;
		}
	}
	
	/**
	 * This method returns the name (short-name) of the ECU
	 * 
	 * @return
	 * name (short-name) of the ECU
	 */
	public String getName()
	{
		if(this.name == null)
		{
			return ((ECUTYPE)ecuRepresentation).getSHORTNAME();
		}
		else
		{
			return this.name;
		}
	}
	
	/**
	 * This method creates and returns a new controller
	 * 
	 * @param name
	 * name of controller to create
	 * @return
	 * newly created controller
	 */
	@Override
	public FibexController getNewController(String name)
	{
		if(this.name == null)
		{
			/* ECU is linked to FIBEX */
			if(!controllersLoaded)
			{
				for (int i = 0; i < ((ECUTYPE)ecuRepresentation).getCONTROLLERS().getCONTROLLERArray().length; i++)
				{
					FibexController controller = FibexController2_0_1.getInstance(((ECUTYPE)ecuRepresentation).getCONTROLLERS().getCONTROLLERArray(i), docReference);
					if(controller != null)
					{
						controllers.add(controller);
					}
				}
				
				controllersLoaded = true;
			}
		}
		
		for (Iterator<FibexController> i = controllers.iterator(); i.hasNext();)
		{
			FibexController controller = (FibexController) i.next();
			if(controller.getName().equals(name))
			{
				return controller;
			}
		}
		
		FibexController controller = new FibexController2_0_1(name);
		this.controllers.add(controller);
		return controller;
	}

	/**
	 * This method saves the ECU and all its subcomponents
	 * 
	 * @param docReference
	 * reference to FIBEX document
	 * @param additionalData
	 * addition data
	 */
	@Override
	public void save(Object docReference, Object... additionalData)
	{
		if(!(this.name == null))
		{
			String clusterType = (String)additionalData[0];
			if(((FIBEXDocument)docReference).getFIBEX().getELEMENTS().getECUS() == null)
			{
				((FIBEXDocument)docReference).getFIBEX().getELEMENTS().addNewECUS();
			}
			
			ECUTYPE ecu = ((FIBEXDocument)docReference).getFIBEX().getELEMENTS().getECUS().addNewECU();
			ecu.setSHORTNAME(name);
			ecu.setID(getId());
			ecu.addNewDESC().setStringValue("This is an ECU");
			ecu.addNewCONTROLLERS();
			ecu.addNewCONNECTORS();
			for (Iterator<FibexController> i = controllers.iterator(); i.hasNext();)
			{
				FibexController controller = (FibexController) i.next();
				
				controller.save(docReference, ecu.getCONTROLLERS(), ecu.getCONNECTORS(), clusterType);
			}
		}
	}

	/**
	 * This method creates and returns a new instance of the ECU.
	 * 
	 * @param ecuRepresentation
	 * reference to ECU node
	 * @param docReference
	 * reference to FIBEX document
	 * @return
	 * newly created ECU, null if given wrong input nodes given
	 */
	public static FibexECU getInstance(Object ecuRepresentation, Object docReference)
	{
		if(ecuRepresentation instanceof net.asam.xml.fbx.ECUTYPE)
		{
			return new FibexECU2_0_1(ecuRepresentation, docReference);
		}
		else
		{
			return null;
		}
	}
}
