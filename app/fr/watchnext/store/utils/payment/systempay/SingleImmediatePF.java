package fr.watchnext.store.utils.payment.systempay;

import static fr.watchnext.store.utils.payment.systempay.DataDictionnary.*;
import static fr.watchnext.store.utils.payment.systempay.Helper.*;

public class SingleImmediatePF extends PaymentForm {
	private SingleImmediatePF() {
		super();
	}
	
	protected static SingleImmediatePF of(String siteID,
										String ctxMode,
										String transId,
										String transDate,
										int amount,
										int currencyCode,
										String actionMode,
										String pageAction,
										String version,
										String paymenConfig,
										int captureDelay,
										int validationMode) {
		SingleImmediatePF instance = new SingleImmediatePF();
		instance.addParameter(PaymentFormParameter.of(SITE_ID, siteID, true));
		instance.addParameter(PaymentFormParameter.of(CTX_MODE, ctxMode, true));
		instance.addParameter(PaymentFormParameter.of(TRANS_ID, transId, true));
		instance.addParameter(PaymentFormParameter.of(TRANS_DATE, transDate, true));
		instance.addParameter(PaymentFormParameter.of(AMOUNT, amount, true));
		instance.addParameter(PaymentFormParameter.of(CURRENCY_CODE, currencyCode, true));
		instance.addParameter(PaymentFormParameter.of(ACTION_MODE, actionMode, true));
		instance.addParameter(PaymentFormParameter.of(PAGE_ACTION, pageAction, true));
		instance.addParameter(PaymentFormParameter.of(VERSION, version, true));
		instance.addParameter(PaymentFormParameter.of(PAYMENT_CONFIG, paymenConfig, true));
		instance.addParameter(PaymentFormParameter.of(CAPTURE_DELAY, captureDelay, true));
		instance.addParameter(PaymentFormParameter.of(VALIDATION_MODE, validationMode, true));
		return instance;
	}
	
	public static SingleImmediatePF of(String transId, int amount) {
		return SingleImmediatePF.of(getSiteId(),
									getCtxMode(),
									transId,
									generateTransDate(),
									amount,
									getCurrencyEuroCode(),
									getInteractiveActionMode(),
									getPaymentPageAction(),
									getCurrentVersion(),
									getSinglePaymentConfig(),
									0,
									getAutomaticValidationMode());
	}
}
