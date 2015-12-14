package tor.java.thirteen.parser;

import java.util.regex.Pattern;

import tor.java.thirteen.csv.CSVHeaderItem;
import JCommonTools.CC;

/***
 * Base class for parser various sequences various values. 
 * Like personal names, documents, address and so on.
 * 
 * @author M.Tor
 * date created 14.04.2015
 * last modified 15.10.2015
 */
public class Parser 
{
	public final static String SEPARATOR_SPASE = "[\\ ]";
	public final static String SEPARATOR_COMMA = "[\\,]";
	public final static String SEPARATOR_POINT = "[\\.]";
	public final static String SEPARATOR_SEMICOLON = "[;]";
	public final static String SEPARATOR_VERTICAL = "[\\|]";
	public final static String SEPARATOR_TABULATION = "[\\\t]";
	
	protected String 	mValSrc;
	protected String 	mDelim;
	protected Parser[] 	mPI;
	/***
	 * Status:
	 * 0 - initial not work, begin parser;
	 * 1 - did parser with good result - all right!
	 */
	protected int mStatus;
	//protected String mStatusText;
	
	protected String mFormat;
	protected String[] mFmtAr;

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

	public String getDelimiter()
	{
		return mDelim;
	}
	public void setDelimiter(String aDelim)
	{
		mDelim = aDelim;
		//mSepa = "\\" + aSepa; 
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
	
	public String getSource()
	{
		return mValSrc;
	}
	public void setSource(String aValSrv)
	{
		mValSrc = aValSrv;
	}
	
	public String getValue(int index)
	{
		if (mPI != null)
			return mPI[index].getSource();
		else
			return null;
	}
	
	//@XmlElementWrapper (name = "ArrPI")
	//@XmlElement (name = "PI")
	public Parser[] getParserItems()
	{
		return mPI;
	}
	public void setParserItems(Parser [] aPI)
	{
		mPI = aPI;
	}
	
	/**
	 * Default constructor.
	 */
	public Parser()
	{
		this(null);
	}
	public Parser(String aFmt)
	{
		mDelim = SEPARATOR_SPASE; // default separator !!!
		setFormat(aFmt);
		initial();
	}
	
	public void initial()
	{
		mStatus = 0;
		//mStatusText = null;
		mValSrc = null;
		mPI = null;
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
		
		if (mValSrc != null &&  mValSrc.length() > 0 && mDelim != null)
		{
			if (!_run ())
				initial();
		}
	}
	protected boolean _run()
	{
		boolean ret = false;
		//mVal = mValSrc.split(mSepa, -1);
		String [] ss  = Pattern.compile(mDelim).split(mValSrc, -1);
		if (ss.length > 0)
		{
			mPI = new Parser[ss.length];
			for (int ii =0; ii < mPI.length; ii++)
			{
				mPI[ii] = newParserItem();
				mPI[ii].setSource(ss[ii]);
			}
			ret = true;
		}
		return ret;
	}
	/**
	 * May be override in inheritable class
	 * @return Parser
	 */
	protected Parser newParserItem()
	{
		return new Parser();
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
	
	/**
	 * Count parser items.
	 * @param aPI - array items
	 * @param aIsAll - if true, calculate  all item, else only leaf (not node)
	 * @return
	 */
	public static int GetItemsCount(Parser [] aPI, boolean aIsAll)
	{
		int ret = 0;
		
		for (int ii=0; ii < aPI.length; ii++)
		{
			if (aPI[ii].getParserItems() != null && aPI[ii].getParserItems().length > 0)
			{
				ret += GetItemsCount(aPI[ii].getParserItems(), aIsAll);
				if (aIsAll)
					ret ++;
			}
			else
			{
				ret ++;
			}
		}
		
		return ret;
	}
}
