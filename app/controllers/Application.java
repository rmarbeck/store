package controllers;

import fr.watchnext.store.utils.aws.s3.S3Helper;
import play.*;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {

    public Result index() {
        return ok(index.render());
    }
    
    public Result product() {
        return ok(product.render());
    }
    
    public Result category() {
        return ok(category.render());
    }

    public Result purchase_prepare() {
        return ok(purchase_step1.render());
    }
    
    public Result purchase_to_step2() {
        return ok(purchase_step2.render());
    }
    
    public Result purchase_to_step3() {
        return ok(purchase_step3.render());
    }
    
    public Result test_s3() {
        return ok(test_s3.render(S3Helper.getFiles()));
    }
}
