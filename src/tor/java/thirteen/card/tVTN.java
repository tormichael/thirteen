package tor.java.thirteen.card;

import JCommonTools.CC;

/**
 * Class - Value+Type+Note for present various simple value, so as:
 *   - phone number
 *   - email
 *   - web site
 *   - "nik" in various social networking 
 *   and etc.
 *   
 * @author M.Tor
 * @since 21.03.2013
 */
public class tVTN 
{
	private String _val;
	private int _type;
	private int _mode;
	private String _note;
	private int _nn;

	/**
	 * Get value
	 * @return Value string.
	 */
	public String getValue() {
		return _val;
	}
	/**
	 * Set value.
	 * @param value
	 */
	public void setValue(String value) {
		_val = value;
	}
	public int getType() {
		return _type;
	}
	public void setType(int type) {
		_type = type;
	}
	public int getMode() {
		return _mode;
	}
	public void setMode(int mode) {
		_mode = mode;
	}
	public String getNote() {
		return _note;
	}
	public void setNote(String note) {
		_note = note;
	}
	public int getNN() {
		return _nn;
	}
	public void setNN(int nN) {
		_nn = nN;
	}
	public tVTN(String aVal, int aType, int aMode, String aNote, int aNN)
	{
		_val = aVal;
		_type = aType;
		_mode = aMode;
		_note = aNote;
		_nn = aNN;
	}
	public  tVTN(String aVal, int aType, int aMode)
	{
		this(aVal, aType, aMode, CC.STR_EMPTY, 0);
	}
	public  tVTN()
	{
		this(CC.STR_EMPTY, 0, 0, CC.STR_EMPTY, 0);
	}

	
}
