package controllers;

import models.Brand;
import fr.watchnext.utils.controllers.*;
import play.mvc.Controller;
import play.mvc.Security;

public class Brands extends Controller {
	public static Crud<Brand, Brand> crud = Crud.of(
			Brand.of(),
			null,
			views.html.admin.brand_form.ref(),
			views.html.admin.brands.ref());
}

