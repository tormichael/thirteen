package tor.java.thirteen.card;

import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import JCommonTools.CC;

@XmlRootElement (name = "Org")
public class tOrg extends tObj 
{
	/**
	 * short name
	 */
	private String _same;
	/**
	 * full name
	 */
	private String _fame;

	/**
	 * form of ownership
	 */
	private int _own;
	
    /** 
     * organization contacts (phones, address, email, www and so on) collection 
     */
    private ArrayList<tVTN> ContactColl;

    /** 
     * organization addresses
     */
    private ArrayList<tAdr> AddrColl;

	/** 
     * organization document collection
     */
    private ArrayList<tDoc> DocColl;

    public static Integer getType ()
    {
    	return 2;
    }
    
    public String getShortName() {
		return _same;
	}
	public void setShortName(String aShortName) {
		_same = aShortName.trim();
	}
	public String getFullName() {
		return _fame;
	}
	public void setFullName(String aFullName) {
		_fame = aFullName.trim();
	}

	public int getFormOwnership()
	{
		return _own;
	}
	
    @XmlElementWrapper (name = "ContactColl")
    @XmlElement (name = "tVTN")
	public ArrayList<tVTN> getContactColl() {
		return ContactColl;
	}
	public void setContactColl(ArrayList<tVTN> contactColl) {
		ContactColl = contactColl;
	}
    @XmlElementWrapper (name = "AddrColl")
    @XmlElement (name = "tAdr")
	public ArrayList<tAdr> getAddrColl() {
		return AddrColl;
	}
	public void setAddrColl(ArrayList<tAdr> addrColl) {
		AddrColl = addrColl;
	}
    @XmlElementWrapper (name = "Docs")
    @XmlElement (name = "tDoc")
	public ArrayList<tDoc> getDocColl() {
		return DocColl;
	}
	public void setDocColl(ArrayList<tDoc> docColl) {
		DocColl = docColl;
	}

	public tOrg(String aShortName, String aFullName)
    {
    	_same = aShortName;
    	_fame = aFullName;
    	
    	ContactColl = new ArrayList<tVTN>();
    	AddrColl = new ArrayList<tAdr>();
    	DocColl = new ArrayList<tDoc>();
    }
	public tOrg()
	{
		this(CC.STR_EMPTY, CC.STR_EMPTY);
	}

 	public static tOrg Load(String aFileName)
	{
 		tOrg ret = null;

		if (aFileName != null && aFileName.length() > 0)
		{
	    	try
	    	{
	    		JAXBContext context = JAXBContext.newInstance(tOrg.class);
	    		Unmarshaller um = context.createUnmarshaller();
	    		Object obj = um.unmarshal(new File(aFileName));
	    		ret = (tOrg) obj;
	    	}
	    	catch (JAXBException ex)
	    	{
	    		ex.printStackTrace();
	    	}
		}
    	
    	return ret;
	}

	public String Save (String aFileName)
	{
		String ret = null;
		
		if (aFileName != null && aFileName.length() > 0)
		{
	    	try
	    	{
	    		JAXBContext context = JAXBContext.newInstance(tOrg.class);
	    		Marshaller m = context.createMarshaller();
	    		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	    		m.marshal(this, new File(aFileName));
	    	}
	    	catch (JAXBException ex)
	    	{
	    		ret = ex.getMessage();
	    	}
		}
    	
    	return ret;
	}
 	
}
