package controllers;

import models.Watch;
import fr.watchnext.utils.controllers.*;
import play.mvc.Controller;
import play.mvc.Security;

public class Watches extends Controller {
	public static Crud<Watch, Watch> crud = Crud.of(
			Watch.of(),
			views.html.admin.watch.ref(),
			views.html.admin.watch_form.ref(),
			views.html.admin.watches.ref());
}

