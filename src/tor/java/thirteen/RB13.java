package tor.java.thirteen;

public class RB13 
{
	private RB13(){}
	
	public final static int HEADER_ITEMS = 1000;
	
	
	/**
	 * Person names in format:
	 *  [LAST_NAME]{DELIMETER}[FIRST_NAME]{DELIMETER}[PATRONYMIC_NAME]
	 */
	public final static int IT_PRS_FIO = 1100;
	/**
	 * Person names in format:
	 *  [LAST_NAME]{DELIMETER}[FIRST_NAME_FIRST_LETTER]{DELIMETER}[PATRONYMIC_NAME_FIRST_LETTER]{DELIMETER}
	 */
	public final static int IT_PRS_FIO2 = 1102;
		/**
		 * Last name
		 */
		public final static int IT_PRS_LAST_NAME = 1110;
		/**
		 * First name
		 */
		public final static int IT_PRS_FIRST_NAME = 1120;
		/**
		 * Patronymic name
		 */
		public final static int IT_PRS_PATRONYMIC_NAME = 1130;
		/**
		 * Birthday
		 */
		public final static int IT_PRS_BIRTHDAY = 1140;
		/**
		 * Place of born
		 */
		public final static int IT_PRS_BORN_PLACE = 1150;
		/**
		 * Gender
		 */
		public final static int IT_PRS_GENDER = 1160;
		//public final static int IT_PRS_ = 1170;
		public final static int IT_PRS_DEATH_DAY = 1180;
		public final static int IT_PRS_DEATH_PLACE = 1190;


	/**
	 * 	personal document
	 */
	public final static int IT_PRS_DOC = 1200;
		/**
		 * Document serial
		 */
		public final static int IT_PRS_DOC_SER = 1210;
		/**
		 * Document number
		 */
		public final static int IT_PRS_DOC_NUM = 1220;
		/**
		 * When document issue
		 */
		public final static int IT_PRS_DOC_WHEN = 1230;
		/**
		 * Where document issue
		 */
		public final static int IT_PRS_DOC_WHERE = 1240;
		/**
		 * Department issue document
		 */
		public final static int IT_PRS_DOC_DEP = 1250;
	
	
	/**
	 * Address
	 */
	public final static int IT_ADDRESS = 1300;
		public final static int IT_ADDRESS_INDEX = 1310;
		public final static int IT_ADDRESS_COUNTRY = 1320;
		public final static int IT_ADDRESS_REGION = 1330;
		public final static int IT_ADDRESS_CITY = 1340;
		public final static int IT_ADDRESS_PLACE = 1350;
		
	/**
	 * Contacts
	 */
	public final static int IT_CONTACT = 1400;
		public final static int IT_CONTACT_PHONE = 1410;
		public final static int IT_CONTACT_EMAIL = 1420;
		public final static int IT_CONTACT_WWW = 1430;
//		public final static int IT_CONTACT_ = 1420;
//		public final static int IT_CONTACT_ = 1420;
//		public final static int IT_CONTACT_ = 1420;
//		public final static int IT_CONTACT_ = 1420;
		
	
	
}
