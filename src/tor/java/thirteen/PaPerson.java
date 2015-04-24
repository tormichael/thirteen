package tor.java.thirteen;

public class PaPerson extends Parser 
{
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
		_lame = null;
		_fame = null;
		_pame = null;
	}
	
}
