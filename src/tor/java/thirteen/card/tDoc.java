package tor.java.thirteen.card;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import tor.java.thirteen.RecordHash;

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
		this (0, null, null, null, null);
	}

	public tDoc (int aType, String aSer, String aNum)
	{
		this (aType, aSer, aNum, null, null);
	}
	public tDoc (int aType, String aSer, String aNum, String aWho, Date aWhen)
	{
		_imgColl = new ArrayList<tBin>();
		_type = aType;
		_nn = 0;
		
		_series = aSer;
		_number = aNum;
		_who = aWho;
		_when = aWhen;
	}
	
	public static boolean FuzzyEquality(tDoc doc1, tDoc doc2)
	{
		if (doc1.getType() != doc2.getType())
			return false;
		
		if (!RecordHash.FuzzyEquality(doc1.getSeries(), doc2.getSeries()))
			return false;

		if (!RecordHash.FuzzyEquality(doc1.getNumber(), doc2.getNumber()))
			return false;

		//if (!RecordHash.FuzzyEquality(doc1.getWho(), doc2.getWho()))
		//	return false;
		
		//if ((doc1.getWhen() == null && doc2. )
		
		return true;
	}

	@Override
	public String toString() 
	{
		StringBuilder sb = new StringBuilder();
		sb.append(_series);
		sb.append(" ");
		sb.append(_number);
		
		if (_when != null)
		{
			DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, Locale.getDefault());
			sb.append(" [");
			sb.append(df.format(_when));
			sb.append("] ");
		}
		
		if (_who != null && _who.length() > 0)
		{
			sb.append(" ");
			sb.append(_who);
		}
		
		return sb.length() > 0 ? sb.toString() : "undefined";  
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException 
	{
		tDoc doc = new tDoc(_type, new String(_series), new String(_number), new String(_who), _when);
		if (_note != null)
			doc.setNote(new String(_note));
		if (_finished != null)
			doc.setFinished(_finished);
		for(tBin img : _imgColl)
		{
			doc.getImgColl().add((tBin) img.clone());
		}
		return doc;
	}
}
