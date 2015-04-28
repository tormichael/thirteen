package tor.java.thirteen.parser;

import java.util.Date;

import JCommonTools.CC;

public class PaPeDoc extends Parser 
{
	
	private String _series;		// S
	private String _number;		// N
	private String _place;			// P
	private Date _when;			// W

	public String getSeries()
	{
		return _series;
	}
	public String getNumber()
	{
		return _number;
	}
	public String getPlace()
	{
		return _place;
	}
	public Date getWhen()
	{
		return _when;
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
		_series = CC.STR_EMPTY;
		_number = CC.STR_EMPTY;
		_place = CC.STR_EMPTY;
		_when = null;
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
			for(int ii = 0; ii < mm; ii++)
			{
				if(mFmtAr[ii].equals("S"))
					_series +=  mDelim+ ss[ii];
				else if(mFmtAr[ii].equals("N"))
					_number += mDelim+ ss[ii];
				else if(mFmtAr[ii].equals("P"))
					_place += mDelim+ ss[ii];
				//else if(mFmtAr[ii].equals("D"))
				//	_date = ss[ii];
			}
			_series = TrimFirstChar(_series); 
			_number = TrimFirstChar(_number); 
			_place = TrimFirstChar(_place); 
			
			ret = true;
		}
		return ret;
	}
}
