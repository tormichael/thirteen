package tor.java.thirteen.dic;

/**
 * Properties of person
 * @author M.Tor
 * @date 14.08.2015
 */
public enum wdpPerson 
{
	LAST_NAME('l'),					//last name
	FIRST_NAME('f'),					//first name
	PATRONYMIC_NAME('p'),	//patronymic name
	BIRTHDAY('b'),						//birthday
	BORN_PLACE('s'),					//place of born
	GENDER('g');						//gender

	private char _abbr;
	public char getAbbreviation()
	{
		return _abbr;
	}
	private wdpPerson(char aAbbr)
	{
		_abbr = aAbbr;
	}
}
