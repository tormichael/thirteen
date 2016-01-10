package tor.java.thirteen.card;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringBufferInputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.security.Key;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import JCommonTools.Crypt;
import tor.java.thirteen.Thirteen;

@XmlRootElement (name = "ObjRegister")
public class tRegister 
{
	public final static String FILE_EXTENTION = "ore";
	public final static String FILE_EXTENTION_CIPHER = "sre";
	
	private Date	_dateCreated;
	private Date _dateLastModified;
	
	private String _rbFN;
	private String _bpFN;

    private ArrayList<tObj> _objColl;
	
    public Date getDateCreated() 
    {
		return _dateCreated;
	}
	public void setDateCreated(Date _dateCreated) 
	{
		this._dateCreated = _dateCreated;
	}
	public Date getDateLastModified() 
	{
		return _dateLastModified;
	}
	public void setDateLastModified(Date _dateLastModified) 
	{
		this._dateLastModified = _dateLastModified;
	}
	
	public String getRefBookFileName() {
		return _rbFN;
	}
	public void setRefBookFileName(String _rbFN) {
		this._rbFN = _rbFN;
	}
	public String getBookParamFileName() {
		return _bpFN;
	}
	public void setBookParamFileName(String _bpFN) {
		this._bpFN = _bpFN;
	}
	
	@XmlElementWrapper (name = "ObjColl")
    @XmlElements ({
		@XmlElement (name = "Obj", type =tObj.class),
		@XmlElement (name = "Person", type = tPerson.class)
    })
    public ArrayList<tObj> getObjColl()
    {
    	return _objColl;
    }
    public void setObjColl(ArrayList<tObj> aObjColl)
    {
    	_objColl = aObjColl;
    }
	
    public tRegister()
    {
    	_rbFN = null;
    	_bpFN = null;
    	_dateCreated = Calendar.getInstance().getTime();
    	_dateLastModified = Calendar.getInstance().getTime();
    	_objColl = new ArrayList<tObj>();
    }
 
    public void ClearObjectsCollection()
    {
    	_objColl.clear();
    }
    
 	public static tRegister Load(String aFileName)
	{
		tRegister ret = null;

		if (aFileName != null && aFileName.length() > 0)
		{
	    	try
	    	{
	    		JAXBContext context = JAXBContext.newInstance(tRegister.class);
	    		Unmarshaller um = context.createUnmarshaller();
	    		Object obj = um.unmarshal(new File(aFileName));
	    		ret = (tRegister) obj;
	    	}
	    	catch (JAXBException ex)
	    	{
	    		ex.printStackTrace();
	    	}
		}
    	
    	return ret;
	}

 	public static tRegister LoadCipher(String aFileName, String aKey)
 	{
		tRegister ret = null;
    	try
    	{
    
    		InputStream in = new FileInputStream(new File(aFileName));
    		ByteArrayOutputStream out = new ByteArrayOutputStream();
    		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
    		//SecretKey kc = new SecretKeySpec(aKey.getBytes(), "AES");
    		cipher.init(Cipher.DECRYPT_MODE, Crypt.getSecretKey(aKey));
    		Crypt.Run(in, out, cipher);
    		in.close();
    		
	 		JAXBContext jc = JAXBContext.newInstance(tRegister.class);
	        Unmarshaller u = jc.createUnmarshaller();
	        StringBuffer xmlStr = new StringBuffer(new String(out.toByteArray(),"UTF-8"));
	        Object o = u.unmarshal( new StreamSource( new StringReader( xmlStr.toString() ) ) ); 		
    		ret = (tRegister) o;

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
	    		JAXBContext context = JAXBContext.newInstance(tRegister.class);
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
    		JAXBContext context = JAXBContext.newInstance(tRegister.class);
    		Marshaller m = context.createMarshaller();
    		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    		StringWriter wr = new StringWriter();
    		m.marshal( this, wr);

    		//InputStream in = new StringBufferInputStream(wr.toString());
    		InputStream in = new ByteArrayInputStream(wr.toString().getBytes("UTF-8"));
    		OutputStream out = new FileOutputStream(new File(aFileName));
    		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
    		//SecretKey kc = new SecretKeySpec(aKey.getBytes(), "AES");
    		//SecretKeyFactory kf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
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
	
	public void SortObjCollection()
	{
		Comparator<tObj> objComparator = new Comparator<tObj>() 
		{
			@Override
			public int compare(tObj o1, tObj o2) 
			{
				return o1.Name.compareToIgnoreCase(o2.Name);
			}
		};

		Collections.sort(_objColl, objComparator);
	}
	
	public boolean ReplaceObject (tObj objWas, tObj objNew)
	{
		boolean ret = false;
		
		int index = _objColl.indexOf(objWas);
		if (index >= 0)
		{
			_objColl.set(index, objNew);
			ret = true;
		}
		return ret;
	}
}
