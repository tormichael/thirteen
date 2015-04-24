package tor.java.thirteen;

public class PaPerson extends Parser 
{
	public final static String FMT_FIO = "F I O";
	public final static String FMT_IOF = "I O F";
	public final static String FMT_IO_F = "I O, F";
	
	private String _lame; 
	private String _fame; 
	private String _pame; 
	
	public String getLastName()
	{
		return _lame;
	}
	public String getFirstName()
	{
		return _fame;
	}
	public String getPatronymic()
	{
		return _pame;
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
		_lame = null;
		_fame = null;
		_pame = null;
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
				_lame = ss[0];
				_fame = ss[1];
				_pame = ss[2];
				ret = true;
			}
		}
		
		return ret;
	}
}
