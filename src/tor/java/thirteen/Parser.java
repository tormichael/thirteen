package tor.java.thirteen;

public class Parser 
{
	/***
	 * Status:
	 * 0 - initial not work, begin parser;
	 * 1 - did parser with good result - all right!
	 */
	protected int mStatus;

	protected void mStatusBegin()
	{
		mStatus = 0;
	}
	protected void mStatusFinishOkay()
	{
		mStatus = 1;
	}
	protected void mStatusFinishWarning(int aWarningCode)
	{
		mStatus = 100+aWarningCode;
	}
	protected void mStatusFinishError(int aErrorCode)
	{
		mStatus = 1000+aErrorCode;
	}
	
	public int getStatus()
	{
		return mStatus;
	}
	
	
	public Parser()
	{
	}
}
