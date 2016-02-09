package tor.java.thirteen.card;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.crypto.Cipher;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;




import javax.xml.bind.annotation.XmlRootElement;



import javax.xml.transform.stream.StreamSource;

//import sun.util.calendar.CalendarDate;
//import sun.util.calendar.BaseCalendar.Date;
import JCommonTools.CC;
import JCommonTools.Crypt;

@XmlRootElement (name = "Persona")
public class tPerson extends tObj 
{
	public final static String FILE_EXTENTION = "per";
	public final static String FILE_EXTENTION_CIPHER = "pes";
	
	public final static int SEX_UNKNOWN = 0;
	public final static int SEX_WOMEN = 1;
	public final static int SEX_MAN = 2;

	public final static String SEX_WOMEN_SYMBOL = "ж";
	public final static String SEX_MAN_SYMBOL = "м";
	
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
	private String _bday;
	/**
	 * sex 0-unknown; 1-women; 2-man; 
	 */
	private int _sex;
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
		_lame = lName.trim();
	}
	public String getFName() {
		return _fame;
	}
	public void setFName(String fName) {
		_fame = fName.trim();
	}
	public String getPName() {
		return _pame;
	}
	public void setPName(String pName) {
		_pame = pName.trim();
	}
	public String getBDay() {
		return _bday;
	}
	public void setBDay(String bDay) 
	{
		if (bDay != null)
			_bday = bDay.trim();
		else
			_bday = CC.STR_EMPTY;
	}
	public int getSex() {
		return _sex;
	}
	public void setSex(int sex) {
		_sex = sex;
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
    	_bday = aBDay;
    	_sex = aSex;
    	
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

 	public static tPerson LoadCipher(String aFileName, String aKey)
 	{
		tPerson ret = null;
    	try
    	{
    		InputStream in = new FileInputStream(new File(aFileName));
    		ByteArrayOutputStream out = new ByteArrayOutputStream();
    		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
    		cipher.init(Cipher.DECRYPT_MODE, Crypt.getSecretKey(aKey));
    		Crypt.Run(in, out, cipher);
    		in.close();
    		
	 		JAXBContext jc = JAXBContext.newInstance(tPerson.class);
	        Unmarshaller u = jc.createUnmarshaller();
	        StringBuffer xmlStr = new StringBuffer(new String(out.toByteArray(),"UTF-8"));
	        Object o = u.unmarshal( new StreamSource( new StringReader( xmlStr.toString() ) ) ); 		
    		ret = (tPerson) o;

    		out.close();
    	}
    	catch (Exception ex)
    	{
    		ex.printStackTrace();
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
	
	public String SaveCipher (String aFileName, String aKey)
	{
		String ret = null;
    	try
    	{
    		JAXBContext context = JAXBContext.newInstance(tPerson.class);
    		Marshaller m = context.createMarshaller();
    		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    		StringWriter wr = new StringWriter();
    		m.marshal( this, wr);

    		InputStream in = new ByteArrayInputStream(wr.toString().getBytes("UTF-8"));
    		OutputStream out = new FileOutputStream(new File(aFileName));
    		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
    		cipher.init(Cipher.ENCRYPT_MODE, Crypt.getSecretKey(aKey));
    		Crypt.Run(in, out, cipher);
    		wr.close();
    		in.close();
    		out.close();
    	}
    	catch (Exception ex)
    	{
    		ret = ex.getMessage();
    	}
    	
    	return ret;
	 }

	public java.util.Calendar getDBCalendar()
	{
		
		GregorianCalendar gc = null;
		try
		{
			if (_bday.length() == 8)
				gc = new GregorianCalendar(
						Integer.parseInt(_bday.substring(0, 4)),
						Integer.parseInt(_bday.substring(4, 6))-1, 
						Integer.parseInt(_bday.substring(6, 8))
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
			_bday = df.format(bdd);
		}
	}

	public void addTelephone()
	{
		
	}

	public String getFullName()
	{
		StringBuilder sb = new StringBuilder();

		if (_lame != null && _lame.length() > 0)
			sb.append(_lame);

		if (_fame != null && _fame.length() > 0)
		{
			sb.append(" ");
			sb.append(_lame);
			if (_pame != null && _pame.length() > 0)
			{
				sb.append(" ");
				sb.append(_pame);
			}
		}
		return sb.toString();
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
	
	public String getSexSymbol()
	{
		return _sex == SEX_WOMEN ? SEX_WOMEN_SYMBOL : (_sex == SEX_MAN ? SEX_MAN_SYMBOL : CC.STR_EMPTY);
	}
	
	public void ReplaceFirstLastName()
	{
		String ln = _lame;
		_lame = _fame;
		_fame = ln;	
	}
	
	public void ReplaceFirstLastNameIf()
	{
		if(_lame != null && _lame.length() > 0 && _fame != null && _fame.length() > 0)
		{
			ReplaceFirstLastName();
		}
	}
}
