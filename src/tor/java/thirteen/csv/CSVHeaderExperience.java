package tor.java.thirteen.csv;

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
 * 
 * @author M.Tor
 * 
 *	created 14.08.2015
 * last modified 02.10.2015
 */
@XmlRootElement (name = "CSVHeaderExperience")
public class CSVHeaderExperience 
{
	private ArrayList<CSVHeaderItem> _hdr;

    @XmlElementWrapper (name = "ArrHI")
    @XmlElement (name = "HI")
	public ArrayList<CSVHeaderItem> getCSVHeader ()
	{
		if (_hdr == null)
		{
			_hdr = new ArrayList<CSVHeaderItem>();
		}
		return _hdr;
	}
	
	public CSVHeaderExperience()
	{
		_hdr = null;
	}
	
	public  static CSVHeaderExperience Load(String aFN)
	{
		CSVHeaderExperience ret = null;
		
		if (aFN != null && aFN.length() > 0)
		{
	    	try
	    	{
	    		JAXBContext context = JAXBContext.newInstance(CSVHeaderExperience.class);
	    		Unmarshaller um = context.createUnmarshaller();
	    		Object obj = um.unmarshal(new File(aFN));
	    		ret = (CSVHeaderExperience) obj;
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
	    		JAXBContext context = JAXBContext.newInstance(CSVHeaderExperience.class);
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

