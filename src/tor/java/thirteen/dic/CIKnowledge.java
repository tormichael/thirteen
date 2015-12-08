package tor.java.thirteen.dic;

import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Knowledge column item.
 * This is simple list, where string corresponded code from various dictionary 
 * 
 * 
 * @author M.Tor
 *
 */

@XmlRootElement (name = "CIKnowledge")
public class CIKnowledge 
{
    @XmlElementWrapper (name = "CIDB")
    @XmlElement (name = "ColumItem")
	public ArrayList<CIK> ArrCIK;

    public CIKnowledge()
    {
    	ArrCIK = new ArrayList<CIK>();
    }
    
	public static CIKnowledge Load(String aFN)
	{
		CIKnowledge ret = null;
		
		if (aFN != null && aFN.length() > 0)
		{
	    	try
	    	{
	    		JAXBContext context = JAXBContext.newInstance(CIKnowledge.class);
	    		Unmarshaller um = context.createUnmarshaller();
	    		Object obj = um.unmarshal(new File(aFN));
	    		ret = (CIKnowledge) obj;
	    	}
	    	catch (JAXBException ex)
	    	{
	    		//ex.printStackTrace();
	    	}
		}
		return ret;
	}
	
	public String Save(String aFN)
	{
		String ret = null;
		
		if (aFN != null && aFN.length() > 0)
		{
	    	try
	    	{
	    		JAXBContext context = JAXBContext.newInstance(CIKnowledge.class);
	    		Marshaller m = context.createMarshaller();
	    		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	    		m.marshal(this, new File(aFN));
	    	}
	    	catch (JAXBException ex)
	    	{
	    		ret = ex.getMessage();
	    	}
		}
		return ret;
	}
    
}
