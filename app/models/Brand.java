package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import play.Logger;
import play.data.validation.ValidationError;

import com.avaje.ebean.Model;
import com.avaje.ebean.PagedList;

import fr.watchnext.utils.views.Selector;
import fr.watchnext.utils.controllers.CrudReady;
import static models.ModelHelper.toOptional;
import static models.ModelHelper.pageDisjunction;
import static models.ModelHelper.notNullList;

/**
 * Definition of a Brand
 */
@Entity 
public class Brand extends Model implements CrudReady<Brand, Brand> {
	private static Brand singleton = null;
	
	@Id
	public Long id;
	
	@Column(unique=true, name="internal_name")
	public String internalName;
	
	@Column(name="display_name")
	public String displayName;
	
	@Column(name="seo_name")
	public String seoName;

	@Column(name="logo_url")
	public String logoUrl;
	
	public String alt;
	
	public boolean active = true;
	
	@Column(length = 10000)
	public String description;
	
	@Column(length = 10000)
	public String remarks;
	
	public static Brand of() {
    	if (singleton == null)
    		singleton = new Brand();
    	return singleton;
    }
	
	// -- Special Accessors
    public static List<String> getDisplayNamesByNameAsc() {
    	List<String> displayNames = new ArrayList<String>();
    	for (Brand b : findAllByAscName())
    		displayNames.add(b.displayName);
    	return displayNames;
    }
    
    public static List<String> getInternalNamesByNameAsc() {
    	List<String> internalNames = new ArrayList<String>();
    	for (Brand b : findAllByAscName())
    		internalNames.add(b.internalName);
    	return internalNames;
    }
    
    public String getDisplayName() {
    	return displayName;
    }
    
    public String getInternalName() {
    	return internalName;
    }
    
    public String getDescription() {
    	return description;
    }
    
    public boolean isActive() {
    	return active;
    }
    
    // -- Queries
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static Model.Finder<String,Brand> find = new Model.Finder(Brand.class);
    
    public static List<Brand> findAll() {
        return notNullList(find.all());
    }
    
    public static List<Brand> findAllByAscName() {
        return find.orderBy("internal_name ASC").findList();
    }
    
    public static List<Brand> findAllByDisplayNameAsc() {
        return find.orderBy("display_name ASC").findList();
    }
    
    public static List<Brand> findAllByDisplayNameDesc() {
        return find.orderBy("display_name DESC").findList();
    }
    
    public static Optional<Brand> findById(Long id) {
        return toOptional(find.byId(id.toString()));
    }
    
    public static Optional<Brand> findByInternalName(String internalName) {
    	return toOptional(find.where().eq("internal_name", internalName).findUnique());
    }
    
    public static Optional<Brand> findBySeoName(String seoName) {
        return toOptional(find.where().eq("seo_name", seoName).findUnique());
    }
    
    public static List<Brand> findActiveOnly() {
        return notNullList(find.where().eq("active", true).orderBy(defaultOrdering()).findList());
    }
    
    private static String defaultOrdering() {
    	return "display_name ASC";
    }

    public static PagedList<Brand> page(int page, int pageSize, String sortBy, String order, String filter) {
    	return pageDisjunction(find, page, pageSize, sortBy, order, filter, Arrays.asList("display_name", "internal_name"), defaultOrdering());
    }
    
	@Override
	public void save() {
		super.save();
	}


	@Override
	public void update() {
		try {
			Logger.debug("Updating "+this.getClass().getName());
		} catch (Throwable t) {
			t.printStackTrace();
		}
		super.update();
	}
	
    public List<ValidationError> validate() {
    	List<ValidationError> errors = new ArrayList<ValidationError>();
        return errors.isEmpty() ? null : errors;
    }

	@Override
	public Finder<String, Brand> getFinder() {
		return find;
	}

	@Override
	public PagedList<Brand> getPage(int page, int pageSize, String sortBy, String order, String filter) {
		return page(page, pageSize, sortBy, order, filter);
	}
    
	public static Selector<Brand> getSelectorByDisplayNameAsc() {
		return getSelector(Brand::findAllByDisplayNameAsc);
	}
	
	public static Selector<Brand> getSelectorByDisplayNameDesc() {
		return getSelector(Brand::findAllByDisplayNameDesc);
	}
	
	public static Selector<Brand> getDefaultSelector() {
		return getSelector(Brand::findAll);
	}
	
	public static Selector<Brand> getSelector(Supplier<List<Brand>> brands) {
		return Selector.of(brands, b -> b.id, b -> b.getDisplayName());
	}
}

