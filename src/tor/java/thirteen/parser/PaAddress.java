package tor.java.thirteen.parser;

import JCommonTools.CC;

public class PaAddress extends Parser 
{
	private String _index;
	private String _country;
	private String _region;
	private String _place;
	private String _build;
	private String _flat;


	
	
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
	}

}
