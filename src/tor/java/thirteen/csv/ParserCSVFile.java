package tor.java.thirteen.csv;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import tor.java.thirteen.parser.Parser;

/***
 * 	Parser comma separated value (CSV) files.
 * @author tor
 * created 07.10.2015
 * last modified 09.10.2015
 */
public class ParserCSVFile
{
	private String						_fileName;
	private CSVHeaderItem		_header;
	private int 							_minBufRow;
	private int 							_maxBufRow;
	private ArrayList<String>	_buf;
	private String						_charsetName;

	public CSVHeaderItem GetHeader()
	{
		return _header;
	}
	public String GetRow(int aRowIndex)
	{
		return _buf.get(aRowIndex);
	}
	public int GetRowCount()
	{
		return _buf.size();
	}
	
	public void setDeafultCharsetName(String aCharsetName)
	{
		_charsetName = aCharsetName;
	}
	
	public void SetCSVFile(String aFileName)
	{
		SetCSVFile(aFileName, _charsetName);
	}
	public void SetCSVFile(String aFileName, String aCharsetName)
	{
		_fileName = aFileName;
		InputStreamReader isr = null;
		try
		{
			if (aCharsetName != null && aCharsetName.length() > 0)
				isr = new InputStreamReader(new FileInputStream(_fileName), aCharsetName);
			else
				isr = new InputStreamReader(new FileInputStream(_fileName));
		}
		catch (Exception ex)
		{
			//mStatusText = ex.getMessage();
		}
		
		if (isr != null)
		{
			Scanner _inScan = new Scanner(isr);
			if (_inScan.hasNext())
			{
				// read first row as header
				_header.setSource(_inScan.nextLine());
			}
			

			int counter = 0;
			while (_inScan.hasNext())
			{
				if ((++counter) >= _minBufRow && counter <= _maxBufRow)
					_buf.add(_inScan.nextLine());
				else if (counter > _maxBufRow)
					break;
			}
			_inScan.close();
			try
			{
				isr.close();
				isr = null;
			}
			catch (IOException ex)
			{
				//mStatusText = ex.getMessage();
			}
		}
		
	}
	
	public ParserCSVFile()
	{
		_charsetName = null;
		_fileName = null;
		_header = new CSVHeaderItem();
		_minBufRow = 1;
		_maxBufRow = 100;
		_buf = new ArrayList<String>();
	}
}
