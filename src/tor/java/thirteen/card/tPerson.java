package tor.java.thirteen.card;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;




import javax.xml.bind.annotation.XmlRootElement;

//import sun.util.calendar.CalendarDate;
//import sun.util.calendar.BaseCalendar.Date;
import JCommonTools.CC;

@XmlRootElement (name = "Persona")
public class tPerson extends tObj 
{
	public final static String FILE_EXTENTION = "per";
	
	public final static int SEX_UNKNOWN = 0;
	public final static int SEX_WOMEN = 1;
	public final static int SEX_MAN = 2;
	
	/**
	 * last name
	 */
	private String _lame;
	/**
	 * first name
	 */
	private String _fame;
	/**
	 * patronymic name
	 */
	private String _pame;
	/**
	 * birthday in format YYYYMMDD
	 */
	private String BDay;
	/**
	 * sex 0-unknown; 1-women; 2-man; 
	 */
	private int Sex;
	/**
	 * Personal images 
	 */
    //@XmlElementWrapper (name = "ImgColl")
    //@XmlElement (name = "tBin")
    //private ArrayList<tBin> ImgColl;
	
    /** 
     * personal contacts (phones, address, email, www and so on) collection 
     */
    private ArrayList<tVTN> ContactColl;

    /** 
     * personal addresses
     */
    private ArrayList<tAdr> AddrColl;

	/** 
     * personal document collection
     */
    private ArrayList<tDoc> DocColl;
    
    
    public String getLName() {
		return _lame;
	}
	public void setLName(String lName) {
		_lame = lName;
	}
	public String getFName() {
		return _fame;
	}
	public void setFName(String fName) {
		_fame = fName;
	}
	public String getPName() {
		return _pame;
	}
	public void setPName(String pName) {
		_pame = pName;
	}
	public String getBDay() {
		return BDay;
	}
	public void setBDay(String bDay) {
		BDay = bDay;
	}
	public int getSex() {
		return Sex;
	}
	public void setSex(int sex) {
		Sex = sex;
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
	
	public tPerson(String aLName, String aFName, String aPName, String aBDay, int aSex)
    {
		Type = 1;
    	_lame = aLName;
    	_fame = aFName;
    	_pame = aPName;
    	BDay = aBDay;
    	Sex = aSex;
    	
    	//ImgColl =new ArrayList<tBin>();
    	ContactColl = new ArrayList<tVTN>();
    	AddrColl = new ArrayList<tAdr>();
    	DocColl = new ArrayList<tDoc>();
    	
    }
    public tPerson(String aLName, String aFName, String aPName)
    {
    	this (aLName, aFName, aPName, CC.STR_EMPTY, SEX_UNKNOWN);
    }
    public tPerson()
    {
    	this (CC.STR_EMPTY, CC.STR_EMPTY, CC.STR_EMPTY, CC.STR_EMPTY, SEX_UNKNOWN);
    }
    
 	public static tPerson Load(String aFileName)
	{
 		tPerson ret = null;

		if (aFileName != null && aFileName.length() > 0)
		{
	    	try
	    	{
	    		JAXBContext context = JAXBContext.newInstance(tPerson.class);
	    		Unmarshaller um = context.createUnmarshaller();
	    		Object obj = um.unmarshal(new File(aFileName));
	    		ret = (tPerson) obj;
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
	    		JAXBContext context = JAXBContext.newInstance(tPerson.class);
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
	

    
	public java.util.Calendar getDBCalendar()
	{
		
		GregorianCalendar gc = null;
		try
		{
			if (BDay.length() == 8)
				gc = new GregorianCalendar(
						Integer.parseInt(BDay.substring(0, 4)),
						Integer.parseInt(BDay.substring(4, 6))-1, 
						Integer.parseInt(BDay.substring(6, 8))
				);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return gc;		
	}
	public java.util.Date getDBDate()
	{
		Calendar gc = getDBCalendar();
		if (gc != null)
			return gc.getTime();
		else
			return null;
	}
	public void setBDDate(java.util.Date bdd)
	{
		if (bdd != null)
		{
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			BDay = df.format(bdd);
		}
	}

	public void addTelephone()
	{
		
	}

	public String getInitialName()
	{
		StringBuilder sb = new StringBuilder();
		if (_fame != null && _fame.length() > 0)
		{
			sb.append(_fame.substring(0, 1));
			if (_pame != null && _pame.length() > 0)
				sb.append(_pame.substring(0, 1));
		}
		return sb.toString();
	}
	public String getInitialNameWithDot()
	{
		StringBuilder sb = new StringBuilder();
		if (_fame != null && _fame.length() > 0)
		{
			sb.append(_fame.substring(0, 1)+".");
			if (_pame != null && _pame.length() > 0)
				sb.append(_pame.substring(0, 1)+".");
		}
		return sb.toString();
	}
}
