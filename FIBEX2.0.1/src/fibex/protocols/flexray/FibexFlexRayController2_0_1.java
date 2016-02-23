package fibex.protocols.flexray;

import net.asam.xml.fbx.flexray.CONTROLLERTYPE;
import fibex.structures.FibexController;
import fibex.structures.FibexController2_0_1;

/**
 * This class implements all functionality of FibexController for FIBEX 2.0.1
 * 
 * @author TUM CREATE - RP3 - Philipp Mundhenk
 */
public class FibexFlexRayController2_0_1 extends FibexController2_0_1
{
	/**
	 * This constructor creates a new controller with a given name
	 * 
	 * @param name
	 * name of controller
	 */
	public FibexFlexRayController2_0_1(String name)
	{
		super(name);
	}

	/**
	 * This constructor creates a new instance of controller. Access via getInstance()
	 * 
	 * @param controllerRepresentation
	 * reference to controller node
	 * @param docReference
	 * reference to FIBEX document
	 */
	protected FibexFlexRayController2_0_1(Object controllerRepresentation, Object docReference)
	{
		super(controllerRepresentation, docReference);
	}

	/**
	 * This method return the ID of the controller node
	 * 
	 * @return
	 * ID of controller node
	 */
	@Override
	public String getIdController()
	{
		if(this.controllerId == null)
		{
			return ((CONTROLLERTYPE)controllerRepresentation).getID();
		}
		else
		{
			return this.controllerId;
		}
	}

	/**
	 * This method creates and returns a new instance of the controller.
	 * 
	 * @param controllerRepresentation
	 * reference to controller node
	 * @param docReference
	 * reference to FIBEX document
	 * @return
	 * newly created controller, null if given wrong input nodes given
	 */
	public static FibexController getInstance(Object controllerRepresentation, Object docReference)
	{
		if(controllerRepresentation instanceof net.asam.xml.fbx.flexray.CONTROLLERTYPE)
		{
			return new FibexFlexRayController2_0_1(controllerRepresentation, docReference);
		}
		else
		{
			return null;
		}
	}
}
