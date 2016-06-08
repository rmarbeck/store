import java.util.List;
import java.util.Map;

import models.Brand;

import com.avaje.ebean.Ebean;
import com.typesafe.config.ConfigFactory;

import play.*;
import play.libs.Yaml;

public class Global extends GlobalSettings {
	
	static class InitialData {
		@SuppressWarnings("unchecked")
		public static void insert(Application app) {
			if (app.isDev() || forceReload()) {
				if(Ebean.find(Brand.class).findRowCount() == 0) {
					Map<String,List<Object>> all = (Map<String,List<Object>>)Yaml.load("reference-data-default.yml");

					Ebean.save(all.get("brands"));
					
					Ebean.save(all.get("watches"));
				}
			}
		}

	}

    public void onStart(Application app) {
		if (!app.isTest()) {
			Logger.info("Application has started");
			InitialData.insert(app);
		} else {
			Logger.info("Application has started in test mode.");
		}
    }

    public void onStop(Application app) {
        Logger.info("Application shutdown...");
    }

    private static boolean forceReload() {
    	try {
    		return ConfigFactory.load().getBoolean("force.initial.data.reload");
    	} catch (Throwable t) {
    		return false;
    	}
    }
}