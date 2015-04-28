package tor.java.thirteen.parser;

import JCommonTools.CC;

public class Parser 
{
	/***
	 * Status:
	 * 0 - initial not work, begin parser;
	 * 1 - did parser with good result - all right!
	 */
	protected int mStatus;
	
	protected String mFormat;
	protected String[] mFmtAr;

	protected String mDelim;

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
			mFmtAr = aFmt.split(mDelim, -1);
		else
			mFmtAr = null;
	}
	
	public String getDelimeter()
	{
		return mDelim;
	}
	public void setDelimeter(String aDelim)
	{
		mDelim = aDelim;
	}
	
	public Parser()
	{
		this(null);
	}
	public Parser(String aFmt)
	{
		mDelim = " ";
		setFormat(aFmt);
		initial();
	}
	
	public void initial()
	{
		mStatus = 0;
	}
	
	protected void FormatParser(String aPattern)
	{
		mFormat = CC.STR_EMPTY;
	}
	
	public void run(String aText)
	{
		mStatusBegin();
		
		if (!_run (aText))
			initial();
	}
	
	protected boolean _run(String aText)
	{
		return false;
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
