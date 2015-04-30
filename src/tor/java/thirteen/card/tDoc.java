package tor.java.thirteen.card;

import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class tDoc 
{
	private String _series;
	private String _number;
	private String _who;
	private Date _when;
	private Date _finished;
	private String _note;
	private int _type;
	private int _nn;

	private ArrayList<tBin> _imgColl;
	
    public String getSeries() 
    {
		return _series;
	}
	public void setSeries(String aSeries) 
	{
		_series = aSeries;
	}

    public String getNumber() 
    {
		return _number;
	}
	public void setNumber(String aNumber) 
	{
		_number = aNumber;
	}

    public String getWho() 
    {
		return _who;
	}
	public void setWho(String aWho) 
	{
		_who = aWho;
	}

    public Date getWhen() 
    {
		return _when;
	}
	public void setWhen(Date aWhen) 
	{
		_when = aWhen;
	}

    public Date getFinished() 
    {
		return _finished;
	}
	public void setFinished(Date aFinished) 
	{
		_finished = aFinished;
	}

    public String getNote() 
    {
		return _note;
	}
	public void setNote(String aNote) 
	{
		_note = aNote;
	}
    public int getType() 
    {
		return _type;
	}
	public void setType(int aType) 
	{
		_type = aType;
	}
    public int getNN() 
    {
		return _nn;
	}
	public void setNN(int aNN) 
	{
		_nn = aNN;
	}
	
    @XmlElementWrapper (name = "DocImgColl")
    @XmlElement (name = "tBin")
	public ArrayList<tBin> getImgColl()
	{
		return _imgColl;
	}
	
	public tDoc() 
	{
		_imgColl = new ArrayList<tBin>();
		_series = null;
		_number = null;
		_who = null;
		_when = null;
		
	}

}
