package tor.java.thirteen;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tor.java.thirteen.card.tDoc;
import JCommonTools.CC;

/**
 * Generate hash code for object array.
 * 
 * @author M.Tor
 *
 */
public class RecordHash 
{
	//private final int DELTA = 31;
	
	//private ArrayList<Object> _oa;
	private String _str;
	private DecimalFormat _decf;
	private SimpleDateFormat _datf;
	private SimpleDateFormat _dtmf;
	
	public RecordHash()
	{
		//_oa = new ArrayList<Object>();
		_decf = new DecimalFormat("0.0##");
		_datf = new SimpleDateFormat("yyyyMMdd");
		_dtmf = new SimpleDateFormat("yyyyMMddHHmmss");
		
		Init();
	}
	
	public void Init()
	{
		_str = CC.STR_EMPTY;
	}
	
 	public void Add(Object obj)
	{
 		if (obj == null)
 		{
 			return;
 		}
 		else if (obj.getClass() == String.class)
		{
			Add((String)obj);
		}
		else if (obj.getClass() == Byte.class)
		{
			Add(((Byte)obj).longValue());
		}
		else if (obj.getClass() == Short.class)
		{
			Add(((Short)obj).longValue());
		}
		else if (obj.getClass() == Integer.class)
		{
			Add(((Integer)obj).longValue());
		}
		else if (obj.getClass() == Long.class)
		{
			Add((Long)obj);
		}
		else if (obj.getClass() == Float.class)
		{
			Add((float)obj);
		}
		else if (obj.getClass() == Double.class)
		{
			Add((double)obj);
		}
		else if (obj.getClass() == Date.class)
		{
			Add((Date)obj);
		}
		else if (obj.getClass() == Calendar.class)
		{
			Add((Calendar)obj);
		}
		else if (obj.getClass() == GregorianCalendar.class)
		{
			Add((Calendar)obj);
		}
	}
	
 	public final String RUS_REPLACE_SYM = "аеёоpсх";
 	public final String ENG_REPLACE_SYM = "aeeopcx";
 	
	public void Add(String str)
	{
		_str += Sift(str);
	}
 	
	/**
	 * 1	из текста удаляются символы:
	 *		пробел, табуляция, возврат каретки, перевод каретки,
	 *		запятая, точка, восклицательный и вопросительный знак,
	 *		дефис, тире, №, {, }, [, ], (, ), “, ”, \, /, *, +, :, ;
	 * 2	все буквы переводятся в нижний регистр
	 * 3	для букв из кириллицы выполнять следующие замены на латиницу
	 *		а е ё о p с х
	 *		a e e o p c x
	 * @param str
	 */
	public static String Sift(String str)
	{
		if (str == null || str.length() == 0)
			return str;
		
		Pattern ptn = Pattern.compile("[\\s\\\\,.!?_\\|\\-№{}\\[\\]\\(\\)\"\'\\/*:;]");
		Matcher mat = ptn.matcher(str);
		String res = mat.replaceAll("").toLowerCase();
		
		String [] srcChar = {"а","е","ё","о","p","с","х","м","к"};
		String [] tgtChar = {"a","e","e","o","p","c","x","m","k"};
		
		for (int ii = 0;  ii < srcChar.length; ii++)
			res = res.replaceAll(srcChar[ii], tgtChar[ii]);

		return res;
	}
	
	public static Boolean FuzzyEquality(String st1, String st2)
	{
		return (st1 == null && st2 == null) || Sift(st1).equals(Sift(st2));
	}
	
	/**
	 * все цифры как есть
	 * @param ii
	 */
	public void Add(long ii)
	{
		_str += ii;
	}
	
	/**
	 * все цифры как есть разделитель дробной части – точка

	 * @param dbl
	 */
	public void Add(double dbl)
	{
		//String[] ss = _df.format(dbl).split(".", -1);
		//if (ss.length == 2)
		//	_str += ss[0]+ss[1];
		//else if (ss.length == 1)
		//	_str += ss[0];
		_str += _decf.format(dbl);
	}
	
	/**
	 * четыре цифры года + две цифры месяца + две цифры даты
	 * Если задано время, то плюс
	 * две цифры часа + две цифры минут + две цифры секунд
	 *	"yyyyMMdd"
	 *	"yyyyMMddHHmmss"
	 *
	 * @param dt
	 */
	public void Add(Date dt)
	{
		Calendar cd = new GregorianCalendar();
		cd.setTime(dt);
		Add(cd);
	}
	public void Add(Calendar dt)
	{
		if (dt.get(Calendar.HOUR_OF_DAY) > 0)
			_str += _dtmf.format(dt.getTime());
		else
			_str += _datf.format(dt.getTime());
	}
	
	@Override
	public int hashCode() 
	{
		//int ret = super.hashCode();
		//int len = _oa.size();
		//for (Object obj : _oa)
		//	ret = ret * DELTA + obj.hashCode(); 
		//return _oa.hashCode();
		return _str.hashCode();
	}
	
	@Override
	public String toString() 
	{
		return _str;
	}

}
