package fr.watchnext.store.utils.payment.systempay;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

public class Helper {
	private static final String DATE_TRANS_FORMAT = "YYYYMMddHHmmss"; 
	
	public static String getParameterPrefix() {
		return DataDictionnary.PARAMETER_PREFIX;
	}
	
	public static String getSiteId() {
		return "94268958";
	}
	
	public static String getCtxMode() {
		return DataDictionnary.CTX_MODE_TEST;
	}
	
	public static String generateTransDate() {
		LocalDateTime nowInUtc = LocalDateTime.now(Clock.systemUTC());
		return nowInUtc.format(DateTimeFormatter.ofPattern(DATE_TRANS_FORMAT));
	}
	
	public static int getCurrencyEuroCode() {
		return DataDictionnary.CURRENCY_CODE_EURO;
	}
	
	public static String getInteractiveActionMode() {
		return DataDictionnary.ACTION_MODE_INTERACTIVE;
	}
	
	public static String getPaymentPageAction() {
		return DataDictionnary.PAGE_ACTION_PAYMENT;
	}
	
	public static String getCurrentVersion() {
		return DataDictionnary.CURRENT_VERSION;
	}
	
	public static String getSinglePaymentConfig() {
		return DataDictionnary.PAYMENT_CONFIG_SINGLE;
	}
	
	public static int getAutomaticValidationMode() {
		return DataDictionnary.AUTOMATIC_VALIDATION_MODE;
	}
	
	public static String generateSignature(PaymentForm form) {
		SortedSet<String> vadsFields = new TreeSet<String>();
		Iterator<String> paramNames = form.getParameterFullNames().iterator();
		while (paramNames.hasNext()) {
			String paramName = paramNames.next();
			if (paramName.startsWith( "vads_" )) {
				vadsFields.add(paramName);
			}
		}
		String sep = Sha.SEPARATOR;
		StringBuilder sb = new StringBuilder();
		for (String vadsParamName : vadsFields) {
			String vadsParamValue = form.getParameterValueAsString(vadsParamName);
			if (vadsParamValue != null) {
				sb.append(vadsParamValue);
			}
			sb.append(sep);
		}
		sb.append( "5240800751587805" );
		String c_sign = Sha.encode(sb.toString());
		return c_sign;
	}
}
