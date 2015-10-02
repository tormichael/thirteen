package tor.java.thirteen.csv;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

/**
 * 
 * @author tor
 * 
 * created 01.10.2015
 * last modified 02.10.2015
 */
public class CSVHeaderItem 
{
	private String _iStr; 
	private String _type;
	
	private String _delim;
	private ArrayList<CSVHeaderItem> _hItems;

	public String getStrHI()
	{
		return _iStr;
	}
	public void setStrHI(String aStrHI)
	{
		_iStr = aStrHI;
	}
	
	public String getTypeHI()
	{
		return _type;
	}
	public void setTypeHI(String aTypeHI)
	{
		_type = aTypeHI;
	}

	public String getDelim()
	{
		return _delim;
	}
	public void setDelim(String aDelim)
	{
		_delim = aDelim;
	}
	
    @XmlElementWrapper (name = "ArrHI")
    @XmlElement (name = "HI")
	public ArrayList<CSVHeaderItem> getHItems()
	{
		if (_hItems == null)
			_hItems = new ArrayList<CSVHeaderItem>();
		
		return _hItems;
	}
	
	public CSVHeaderItem()
	{
		_iStr = null;
		_type = null;
		_delim = null;
		_hItems = null;
	}
}
