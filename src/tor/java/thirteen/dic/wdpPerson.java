package tor.java.thirteen.dic;

import java.util.ResourceBundle;

import tor.java.thirteen.RB13;
import tor.java.thirteen.Thirteen;

/**
 * Properties of person
 * @author M.Tor
 * @date 14.08.2015; 04.12.2015
 */
public enum wdpPerson 
{
	FIO(Thirteen.getRS("RB13.IT.Prs.FIO"), RB13.IT_PRS_FIO),
	LAST_NAME(Thirteen.getRS("RB13.IT.Prs.LastName"), RB13.IT_PRS_LAST_NAME),
	FIRST_NAME(Thirteen.getRS("RB13.IT.Prs.FirstName"), RB13.IT_PRS_FIRST_NAME),
	PATRONYMIC_NAME(Thirteen.getRS("RB13.IT.Prs.PatronymicName"), RB13.IT_PRS_PATRONYMIC_NAME),
	BIRTHDAY(Thirteen.getRS("RB13.IT.Prs.Birthday"), RB13.IT_PRS_BIRTHDAY),
	BORN_PLACE(Thirteen.getRS("RB13.IT.Prs.BornPlace"), RB13.IT_PRS_BORN_PLACE),
	GENDER(Thirteen.getRS("RB13.IT.Prs.Gender"), RB13.IT_PRS_GENDER);

	private String _title;
	private int _code;
	
	public String getTitle()
	{
		return _title;
	}
	
	public int getCode()
	{
		return _code;
	}
	
	private wdpPerson(String aTitle, int aCode)
	{
		_title = aTitle;
		_code = aCode;
	}
}
