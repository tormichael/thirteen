package tor.java.thirteen.parser;

/**
 * 
 * @author tor
 * @date 07.08.2015
 */
public class wdItem 
{
	public enum wdiType 
	{
		UNDEFINED('u'),			//undefined
		
		dSPACE('s'),					//space
		dVERTICAL('v'),			//vertical line
		dPOINT('p'),					//point
		dCOMMA('c'),				//comma
		dSEMICOLON('s'),		//semicolon
		dTABULATION('t'),		//tabulation sing
		dBRACKET('b'),			//скобки ()[]{}<>
		dOTHERS('o'),				//другие символы пунктуации: !?:
		
		wLATINIAN('a'),			//латинские буквы
		wCYRILLIC('c'),			//буквы кириллицы
		wDECIMAL('d'),			//десятичные цифры (0,1,2,3,4,5,6,7,8,9)
		wHEXADECIMAL('h'),	//	шестнадцатеричные цифры (0,1,2,3,4,5,6,7,8,9,a,b,c,d,e,f)
		wROMAN('r')				//римские цифры (I,V,X,L,C,D,M)
		;
		
		private char _abbr;
		public char getAbbreviation()
		{
			return _abbr;
		}
		private wdiType(char aAbbr)
		{
			_abbr = aAbbr;
		}
	}
	
	private String _source; 
	private boolean _isWord;
	private int _indexOfBegin;
	private int _length; // for delimiter length = 1 !!!
	private wdiType _type;
	
	public boolean  isWord()
	{
		return _isWord;
	}
	public boolean isDelimiter()
	{
		return !_isWord;
	}
	public void setIsWord()
	{
		_isWord = true;
	}
	public void setIsDelimiter()
	{
		_isWord = false;
	}

	public wdItem(String aSrc)
	{
		_source = aSrc;
	}
}
