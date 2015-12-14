package tor.java.thirteen;

import java.util.ResourceBundle;

public class Thirteen 
{
	/**
	 * Path to the common text resource 
	 */
	public final static String CT_RESOURCE_TEXT = "tor/java/thirteen/res/text";

	public static String getRS(String aKey)
	{
		return ResourceBundle.getBundle(Thirteen.CT_RESOURCE_TEXT).getString(aKey);
	}
}
