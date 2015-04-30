package tor.java.thirteen.parser;

import tor.java.thirteen.card.tAdr;

public class PaAddress extends Parser 
{
	private tAdr _adr;

	public tAdr getAddress()
	{
		return _adr;
	}
	
	public PaAddress()
	{
		super();
	}
	public PaAddress(String aFmt)
	{
		super(aFmt);
	}
	
	@Override
	public void initial() 
	{
		super.initial();
		_adr = new tAdr();
	}

}
