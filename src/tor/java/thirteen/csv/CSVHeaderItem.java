package tor.java.thirteen.csv;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import tor.java.thirteen.parser.Parser;

/**
 * 
 * @author tor
 * 
 * created 01.10.2015
 * last modified 02.10.2015
 */
public class CSVHeaderItem extends Parser
{
	private int _type;
	
	public int getTypeHI()
	{
		return _type;
	}
	public void setTypeHI(int aTypeHI)
	{
		_type = aTypeHI;
	}

	
	public CSVHeaderItem()
	{
		_type = 0;
	}

	@Override
	protected Parser newParserItem()
	{
		return new CSVHeaderItem();
	}

}
