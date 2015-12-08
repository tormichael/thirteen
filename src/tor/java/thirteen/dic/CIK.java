package tor.java.thirteen.dic;

public class CIK 
{
	private int _code;
	private String _text;
	public int getCode() {
		return _code;
	}
	public void setCode(int _code) {
		this._code = _code;
	}
	public String getText() {
		return _text;
	}
	public void setText(String _text) {
		this._text = _text;
	}
	

	public CIK() 
	{
		this (0, null);
	}
	public CIK (int aCode, String aText)
	{
		_code = aCode;
		_text = aText;
	}
}
