package tor.java.thirteen.parser;

import tor.java.thirteen.card.tPerson;

public class PaPerson extends Parser 
{
	public final static String FMT_F_I_O = "F I O";
	public final static String FMT_I_O_F = "I O F";
	public final static String FMT_I_OC_F = "I O, F";
	public final static String FMT_F_IO = "F i.o.";
	
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
	protected boolean _run()
	{
		boolean ret = super._run();
		if (mPI.length == 3)
		{
			if (mFormat != null &&  mFormat.equals(FMT_F_I_O))
			{
				_prs.setLName(getValue(0));
				_prs.setFName(getValue(1));
				_prs.setPName(getValue(2));
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
