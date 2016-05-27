package controllers;

import fr.watchnext.store.utils.aws.s3.S3Helper;
import fr.watchnext.store.utils.payment.systempay.SingleImmediatePF;
import play.*;
import play.mvc.*;
import views.html.systempay.*;

public class SystemPay extends Controller {

    public Result index(String transactionId, int amount) {
        return ok(index.render(SingleImmediatePF.of(transactionId, amount)));
    }
}
