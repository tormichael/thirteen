package tor.java.thirteen.parser;

import tor.java.thirteen.card.tPerson;

public class PaPerson extends Parser 
{
	public final static String FMT_FIO = "F I O";
	public final static String FMT_IOF = "I O F";
	public final static String FMT_IO_F = "I O, F";
	
	private tPerson _prs ; 
	public tPerson getPerson()
	{
		return _prs;
	}

	public PaPerson ()
	{
		super();
	}

	public PaPerson (String aFmt)
	{
		super(aFmt);
	}
	
	@Override
	public void initial()
	{
		_prs = new tPerson();
	}
	
	@Override
	protected boolean _run(String aText)
	{
		boolean ret = false;
		String[] ss = aText.split(mDelim, -1);
		if (ss.length == 3)
		{
			if (mFormat.equals(FMT_FIO))
			{
				_prs.setLName(ss[0]);
				_prs.setFName(ss[1]);
				_prs.setPName(ss[2]);
				ret = true;
			}
		}
		
		return ret;
	}
	
	public void setSex(String aTxt)
	{
		String src= aTxt.toLowerCase().trim();
		if (src.equals("м") 
				||
				src.equals("m")
				||
				src.equals("м.")
				||
				src.equals("m.")
				||
				src.indexOf("муж") >= 0
				||
				src.indexOf("male") >= 0
				||
				src.indexOf("man") >= 0
		)
			_prs.setSex(tPerson.SEX_MAN);
		else
			_prs.setSex(tPerson.SEX_WOMEN);
	}
}
