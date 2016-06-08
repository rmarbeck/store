package controllers;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Optional;

import models.Brand;
import models.Watch;
import fr.watchnext.store.utils.aws.s3.S3Helper;
import play.*;
import play.core.Router;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {

    public Result index() {
        return ok(index.render());
    }
    
    public Result product(Long id) {
    	Optional<Watch> foundWatch = Watch.findById(id);
    	if (foundWatch.isPresent())
    		return ok(product.render(foundWatch.get()));
    	return badRequest();
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
