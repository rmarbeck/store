package models;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.Logger;
import play.data.validation.Constraints;
import play.data.validation.ValidationError;

import com.avaje.ebean.Expr;
import com.avaje.ebean.Model;
import com.avaje.ebean.PagedList;

import fr.watchnext.utils.controllers.*;
import fr.watchnext.utils.usual.DateHelper;

/**
 * Definition of a Watch
 */
@Entity 
public class Watch extends Model implements CrudReady<Watch, Watch> {
	private static Watch singleton = null;
	
	public enum WatchOwnerStatus {
	    OWNED_BY_US ("OWNED_BY_US"),
	    OWNED_BY_CUSTOMER ("OWNED_BY_CUSTOMER"),
	    OWNED_BY_PARTNER ("OWNED_BY_PARTNER"),
	    UNKNOWN ("UNKNOWN"),
	    RESERVED_1 ("RESERVED_1"),
	    RESERVED_2 ("RESERVED_2");
	    
		private String name = "";
		    
		WatchOwnerStatus(String name){
		    this.name = name;
		}

		public String toString(){
		    return name;
		}
		
		public int intValue() {
			return Integer.valueOf(name);
		}
		
		public static WatchOwnerStatus fromString(String name) {
	        for (WatchOwnerStatus status : WatchOwnerStatus.values()) {
	            if (status.name.equals(name)) {
	                return status;
	            }
	        }
	        throw new IllegalArgumentException("Illegal type name: " + name);
	    }
	}
	
	public enum MovementType {
	    MECHANICAL_MANUAL ("MECHANICAL_MANUAL"),
	    MECHANICAL_AUTOMATIC ("MECHANICAL_AUTOMATIC"),
	    QUARTZ_STANDARD ("QUARTZ_STANDARD"),
	    QUARTZ_SPECIAL ("QUARTZ_SPECIAL"),
	    UNKNOWN ("UNKNOWN"),
	    RESERVED_1 ("RESERVED_1"),
	    RESERVED_2 ("RESERVED_2");
	    
		private String name = "";
		    
		MovementType(String name){
		    this.name = name;
		}

		public String toString(){
		    return name;
		}
		
		public int intValue() {
			return Integer.valueOf(name);
		}
		
		public static MovementType fromString(String name) {
	        for (MovementType status : MovementType.values()) {
	            if (status.name.equals(name)) {
	                return status;
	            }
	        }
	        throw new IllegalArgumentException("Illegal type name: " + name);
	    }
	}
	
	public enum WatchCondition {
		NEVER_WORN_0 ("NEVER_WORN_0"),
		EXCELLENT_1 ("EXCELLENT_1"),
	    GOOD_2 ("GOOD_2"),
	    USED_3 ("USED_3"),
	    NOT_WORKING_4 ("NOT_WORKING_4"),
	    FOR_PARTS_5 ("FOR_PARTS_5"),
	    UNKWOWN_6 ("UNKWOWN_6");
	    
		private String name = "";
		    
		WatchCondition(String name){
		    this.name = name;
		}

		public String toString(){
		    return name;
		}
		
		public int intValue() {
			return Integer.valueOf(name);
		}
		
		public static WatchCondition fromString(String name) {
	        for (WatchCondition status : WatchCondition.values()) {
	            if (status.name.equals(name)) {
	                return status;
	            }
	        }
	        throw new IllegalArgumentException("Illegal type name: " + name);
	    }
	}
	
	public enum WatchDeliveryDelay {
		IN_STOCK_0 ("IN_STOCK_0"),
		THREE_TO_FIVE_1 ("THREE_TO_FIVE_1"),
	    SIX_TO_TEN_2 ("SIX_TO_TEN_2"),
	    TO_ORDER_3 ("TO_ORDER_3"),
	    UNKWOWN ("UNKWOWN");
	    
		private String name = "";
		    
		WatchDeliveryDelay(String name){
		    this.name = name;
		}

		public String toString(){
		    return name;
		}
		
		public int intValue() {
			return Integer.valueOf(name);
		}
		
		public static WatchDeliveryDelay fromString(String name) {
	        for (WatchDeliveryDelay status : WatchDeliveryDelay.values()) {
	            if (status.name.equals(name)) {
	                return status;
	            }
	        }
	        throw new IllegalArgumentException("Illegal type name: " + name);
	    }
	}
	
	public enum WatchStatus {
	    TO_SELL ("TO_SELL"),
	    RESERVED_FOR_A_CUSTOMER ("RESERVED_FOR_A_CUSTOMER"),
	    SOLD ("SOLD"),
	    SELLING_CANCELED ("SELLING_CANCELED"),
	    UNKNOWN ("UNKNOWN"),
	    RESERVED_1 ("RESERVED_1"),
	    RESERVED_2 ("RESERVED_2");
	    
		private String name = "";
		    
		WatchStatus(String name){
		    this.name = name;
		}

		public String toString(){
		    return name;
		}
		
		public int intValue() {
			return Integer.valueOf(name);
		}
		
		public static WatchStatus fromString(String name) {
	        for (WatchStatus status : WatchStatus.values()) {
	            if (status.name.equals(name)) {
	                return status;
	            }
	        }
	        throw new IllegalArgumentException("Illegal type name: " + name);
	    }
	}

	@Id
	public Long id;
	
	@Column(name="external_reference")
	public String externalReference;
	
	@Column(unique=true)
	public String key;
	
	@Column(name="creation_date")
	public Date creationDate;
	
	@Column(name="last_modification_date")
	public Date lastModificationDate;
	
	@ManyToOne
	public Brand brand;
	
	public String model;

	public String additionnalModelInfos;
	
	public String reference;
	
	public String serial;
	
	public String serial2;
	
	public String movement;
	
	@Constraints.Required
	@Enumerated(EnumType.STRING)
	public MovementType movementType;
	
	public Float size;
	
	public String strap;
	
	@Column(length = 10000)
	public String additionnalInfos;
	
	@Column(length = 100000)
	public String description;
	
	@Column(length = 10000)
	public String longDescription;
	
	@Column(length = 10000)
	public String privateInfos;
	
	public String year;
	
	public Boolean hasBox = false;
	
	public Boolean hasPapers = false;
	
	public Boolean isNew = true;

	@Constraints.Required
	@Enumerated(EnumType.STRING)
	public WatchOwnerStatus ownerStatus;
	
	@Constraints.Required
	@Enumerated(EnumType.STRING)
	public WatchStatus status;
	
	@Constraints.Required
	@Enumerated(EnumType.STRING)
	public WatchCondition condition;
	
	@Constraints.Required
	@Enumerated(EnumType.STRING)
	public WatchDeliveryDelay deliveryDelay;
	
	public long sellingPrice = 0;
	
	public long oldSellingPrice = 0;
	
	public long listPrice = 0;
	
	public Boolean shouldDisplay = true;
	
	public static Watch of() {
    	if (singleton == null)
    		singleton = new Watch();
    	return singleton;
    }
	
    // -- Special Accessors
	public Boolean isNewAddition() {
		return Instant.now().minus(Duration.ofDays(3)).isBefore(creationDate.toInstant());
	}
	
	public Boolean isDiscounted() {
		return oldSellingPrice != 0 && sellingPrice < oldSellingPrice;
	}
	
	public String getModel() {
		return model;
	}
	
	public String getDescription() {
		return description;
	}
	
	public int getBAndPFlag() {
		if (hasBox) {
			if (hasPapers) {
				return 0;
			} else {
				return 2;
			}
		} else {
			if (hasPapers) {
				return 1;
			} else {
				return 3;
			}
		}
	}
	
	public String getBrandName() {
		if (brand != null)
			return brand.getDisplayName();
		return null;
	}
	
    // -- Queries
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static Model.Finder<String,Watch> find = new Model.Finder(Watch.class);
    
    public static List<Watch> findAll() {
        return find.all();
    }

    public static Watch findBySeoName(String seoName) {
        return find.where().eq("seo_name", seoName).findUnique();
    }
    
    public static List<Watch> findDisplayable() {
    	List<Watch> displayableWatches = find.where().eq("should_display", true).orderBy("id ASC").findList();
    	if (displayableWatches == null)
    		return new ArrayList<Watch>();
        return displayableWatches;
    }

    public static List<Watch> findAvailable() {
    	List<Watch> avalaibleWatches = find.where().eq("is_available", true).eq("should_display", true).orderBy("id ASC").findList();
    	if (avalaibleWatches == null)
    		return new ArrayList<Watch>();
        return avalaibleWatches;
    }

    public static Optional<Watch> findById(Long id) {
    	Watch found = find.byId(id.toString());
    	if (found != null)
    		return Optional.of(found);
        return Optional.empty();
    }

    public static PagedList<Watch> page(int page, int pageSize, String sortBy, String order, String filter) {
        return 
            find.where().or(Expr.ilike("model", "%" + filter + "%"), Expr.ilike("brand", "%" + filter + "%"))
                .orderBy(sortBy + " " + order)
                .findPagedList(page, pageSize);
    }
    
	@Override
	public void save() {
		this.creationDate = DateHelper.toDate(Instant.now());
		checkBeforeSaving();
		super.save();
	}


	@Override
	public void update() {
		try {
			Logger.debug("Updating "+this.getClass().getName());
		} catch (Throwable t) {
			t.printStackTrace();
		}
		checkBeforeSaving();
		this.lastModificationDate = DateHelper.toDate(Instant.now());
		super.update();
	}
	
    public List<ValidationError> validate() {
    	List<ValidationError> errors = new ArrayList<ValidationError>();
        return errors.isEmpty() ? null : errors;
    }

	@Override
	public Finder<String, Watch> getFinder() {
		return find;
	}

	@Override
	public PagedList<Watch> getPage(int page, int pageSize, String sortBy, String order, String filter) {
		return page(page, pageSize, sortBy, order, filter);
	}
	
	private void checkBeforeSaving() {

	}
    
}

