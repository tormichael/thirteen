package tor.java.thirteen.parser;

import java.util.Date;

import tor.java.thirteen.card.tDoc;
import JCommonTools.CC;

public class PaPeDoc extends Parser 
{
	
	private tDoc _doc;
	
	
	/**
	 FORMAT symbols:
		S - series
		N - number
		P - place
		W - when
		T - various other word
		
		For examples:
		12 23 654321 выдан 12.12.2012 УФМС России по г. Москве по р-ну Восточный
		S S N T W PE - додумкать !!!!!
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
	protected boolean _run() 
	{
		boolean ret = super._run();
		
		if (mFmtAr != null && mFmtAr.length > 0)
		{
			int mm = mFmtAr.length < mPI.length ? mFmtAr.length : mPI.length;
			String series = CC.STR_EMPTY;
			String number = CC.STR_EMPTY;
			String place = CC.STR_EMPTY;
			for(int ii = 0; ii < mm; ii++)
			{
				if(mFmtAr[ii].equals("S"))
					series +=  mDelim+ getValue(ii);
				else if(mFmtAr[ii].equals("N"))
					number += mDelim+ getValue(ii);
				else if(mFmtAr[ii].equals("P"))
					place += mDelim+ getValue(ii);
				//else if(mFmtAr[ii].equals("D"))
				//	_date = mVal[ii];
			}
			_doc.setSeries(TrimFirstChar(series)); 
			_doc.setNumber(TrimFirstChar(number)); 
			_doc.setWho(TrimFirstChar(place)); 
			
			ret = true;
		}
		return ret;
	}
}

