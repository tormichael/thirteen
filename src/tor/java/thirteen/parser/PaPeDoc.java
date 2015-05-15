package tor.java.thirteen.parser;

import java.util.Date;

import tor.java.thirteen.card.tDoc;
import JCommonTools.CC;

public class PaPeDoc extends Parser 
{
	
	private tDoc _doc;
	
	
	/**
	 
	series - S
	number;		// N
	private String _place;			// P
	private Date _when;			// W
	
	 */

	public tDoc getDoc()
	{
		return _doc;
	}
	
	public PaPeDoc()
	{
		super();
	}
	
	public PaPeDoc(String aFmt)
	{
		super(aFmt);
	}
	
	@Override
	public void initial() 
	{
		super.initial();
		_doc = new tDoc();
	}
	
	@Override
	protected boolean _run(String aText) 
	{
		boolean ret = false;
		
		String[] ss = aText.split(mDelim, -1);
		
		initial();
		
		if (mFmtAr != null && mFmtAr.length > 0)
		{
			int mm = mFmtAr.length < ss.length ? mFmtAr.length : ss.length;
			String series = CC.STR_EMPTY;
			String number = CC.STR_EMPTY;
			String place = CC.STR_EMPTY;
			for(int ii = 0; ii < mm; ii++)
			{
				if(mFmtAr[ii].equals("S"))
					series +=  mDelim+ ss[ii];
				else if(mFmtAr[ii].equals("N"))
					number += mDelim+ ss[ii];
				else if(mFmtAr[ii].equals("P"))
					place += mDelim+ ss[ii];
				//else if(mFmtAr[ii].equals("D"))
				//	_date = ss[ii];
			}
			_doc.setSeries(TrimFirstChar(series)); 
			_doc.setNumber(TrimFirstChar(number)); 
			_doc.setWho(TrimFirstChar(place)); 
			
			ret = true;
		}
		return ret;
	}
}

