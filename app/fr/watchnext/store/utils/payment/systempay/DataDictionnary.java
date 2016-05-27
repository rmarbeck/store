package fr.watchnext.store.utils.payment.systempay;

public class DataDictionnary {
	public static final String PARAMETER_PREFIX = "vads_";
	
	public static final String SITE_ID = "site_id";
	public static final String CTX_MODE = "ctx_mode";
	public static final String TRANS_ID = "trans_id";
	public static final String TRANS_DATE = "trans_date";
	public static final String AMOUNT = "amount";
	public static final String CURRENCY_CODE = "currency";
	public static final String ACTION_MODE = "action_mode";
	public static final String PAGE_ACTION = "page_action";
	public static final String VERSION = "version";
	public static final String PAYMENT_CONFIG = "payment_config";
	public static final String CAPTURE_DELAY = "capture_delay";
	public static final String VALIDATION_MODE = "validation_mode";
	
	
	public static final String CTX_MODE_TEST = "TEST";
	public static final String CTX_MODE_PRODUCTION = "PRODUCTION";
	
	public static final String ACTION_MODE_INTERACTIVE = "INTERACTIVE";
	
	public static final String PAGE_ACTION_PAYMENT = "PAYMENT";
	
	public static final String CURRENT_VERSION = "V2";
	
	public static final String PAYMENT_CONFIG_SINGLE = "SINGLE";
	
	public static final int CURRENCY_CODE_EURO = 978;
	
	public static final int AUTOMATIC_VALIDATION_MODE = 0;
}
