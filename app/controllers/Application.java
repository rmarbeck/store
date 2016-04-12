package controllers;

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
    
    public Result purchase_prepare() {
        return ok(purchase_step1.render());
    }
    
    public Result purchase_to_step2() {
        return ok(purchase_step2.render());
    }
    
    public Result purchase_to_step3() {
        return ok(purchase_step3.render());
    }
}
