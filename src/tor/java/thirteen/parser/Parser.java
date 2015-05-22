package tor.java.thirteen.parser;

import JCommonTools.CC;

/***
 * Base class for parser various sequences various values. 
 * Like personal names, documents, address and so on.
 * 
 * @author M.Tor
 * @created 14.04.2015
 */
public class Parser 
{
	public final static String SEPARATOR_SPASE = " ";
	public final static String SEPARATOR_COMMA = ",";
	public final static String SEPARATOR_POINT = ".";
	public final static String SEPARATOR_SEMICOLON = ";";
	public final static String SEPARATOR_VERTICAL = "|";
	public final static String SEPARATOR_TABULATION = "\t";
	
	protected String 	mValSrc;
	protected String[] 	mVal;
	protected Parser[] 	mValPa;
	
	/***
	 * Status:
	 * 0 - initial not work, begin parser;
	 * 1 - did parser with good result - all right!
	 */
	protected int mStatus;
	
	protected String mFormat;
	protected String[] mFmtAr;

	protected String mSepa;

	protected void mStatusBegin()
	{
		mStatus = 0;
	}
	protected void mStatusFinishOkay()
	{
		mStatus = 1;
	}
	protected void mStatusFinishWarning(int aWarningCode)
	{
		mStatus = 100+aWarningCode;
	}
	protected void mStatusFinishError(int aErrorCode)
	{
		mStatus = 1000+aErrorCode;
	}
	
	public int getStatus()
	{
		return mStatus;
	}
	
	public String getFormat()
	{
		return mFormat;
	}
	public String getFormat(String aPattern)
	{
		if (mFormat == null)
			FormatParser(aPattern);
		return mFormat;
	}
	public void setFormat(String aFmt)
	{
		mFormat = aFmt;
		if (aFmt != null && aFmt.length() > 0)
			mFmtAr = aFmt.split(mSepa, -1);
		else
			mFmtAr = null;
	}
	
	public String getSeparator()
	{
		return mSepa;
	}
	public void setSeparator(String aSepa)
	{
		mSepa = aSepa;
	}
	
	public String getSource()
	{
		return mValSrc;
	}
	public void setSource(String aValSrv)
	{
		mValSrc = aValSrv;
	}
	
	public String[] getValue()
	{
		return mVal;
	}
	
	public Parser[] getValueParser()
	{
		return mValPa;
	}
	
	public Parser()
	{
		this(null);
	}
	public Parser(String aFmt)
	{
		mSepa = SEPARATOR_SPASE; // default separator !!!
		setFormat(aFmt);
		initial();
	}
	
	public void initial()
	{
		mStatus = 0;
		mValSrc = null;
		mVal = null;
		mValPa = null;
	}
	
	protected void FormatParser(String aPattern)
	{
		mFormat = CC.STR_EMPTY;
	}

	public void Run(String aText)
	{
		initial();
		mValSrc = aText;
		Run();
	}
	public void Run()
	{
		mStatusBegin();
		
		if (mValSrc != null &&  mValSrc.length() > 0 && mSepa != null)
		{
			if (!_run ())
				initial();
		}
	}
	protected boolean _run()
	{
		mVal = mValSrc.split(mSepa, -1);
		if (mVal.length > 0)
		{
			mValPa = new Parser[mVal.length];
			for (int ii =0; ii < mValPa.length; ii++)
				mValPa[ii] = new Parser(mVal[ii]);
		}
		return mVal != null;
	}
	
	public String TrimFirstChar(String aSrc)
	{
		if (aSrc != null && aSrc.length() > 0)
		{
			return aSrc.substring(1);
		}
		else
		{
			return CC.STR_EMPTY;
		}
	}
}
