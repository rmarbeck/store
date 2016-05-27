package fr.watchnext.store.utils.payment.systempay;

import java.util.Optional;

public class PaymentFormParameter {
	public enum ParameterType {
	    STRING ("STRING"),
	    INTEGER ("INTEGER"),
	    RESERVED_1 ("RESERVED_1"),
	    RESERVED_2 ("RESERVED_2");
	    
		private String name = "";
		ParameterType(String name){
		    this.name = name;
		}
		public String toString(){
		    return name;
		}
		public int intValue() {
			return Integer.valueOf(name);
		}
		public static ParameterType fromString(String name) {
	        for (ParameterType type : ParameterType.values()) {
	            if (type.name.equals(name)) {
	                return type;
	            }
	        }
	        throw new IllegalArgumentException("Illegal type name: " + name);
	    }
	}
	
	private String shortName;
	private ParameterType type;
	private Optional<String> stringValue = Optional.empty();
	private Optional<Integer> intValue = Optional.empty();
	private boolean mandatory = false;
	
	private PaymentFormParameter(String shortName, Boolean mandatory) {
		this.shortName = shortName;
		this.mandatory = mandatory;
	}
	
	private PaymentFormParameter(String shortName, String value, Boolean mandatory) {
		this(shortName, mandatory);
		this.type = ParameterType.STRING;
		this.stringValue = Optional.of(value);
	}
	
	private PaymentFormParameter(String shortName, int value, Boolean mandatory) {
		this(shortName, mandatory);
		this.type = ParameterType.INTEGER;
		this.intValue = Optional.of(value);
	}
	
	public static PaymentFormParameter of(String shortName, String value, Boolean mandatory) {
		return new PaymentFormParameter(shortName, value, mandatory);
	}
	
	public static PaymentFormParameter of(String shortName, int value, Boolean mandatory) {
		return new PaymentFormParameter(shortName, value, mandatory);
	}
	
	public static PaymentFormParameter of(String shortName, String value) {
		return PaymentFormParameter.of(shortName, value, false);
	}
	
	public static PaymentFormParameter of(String shortName, int value) {
		return PaymentFormParameter.of(shortName, value, false);
	}
	
	public String getParameterName() {
		return Helper.getParameterPrefix() + shortName;
	}
	
	public String getValue() {
		switch(type) {
			case STRING:
				return stringValue.orElse("Error Missing Value");
			case INTEGER:
				if (intValue.isPresent())
					return intValue.get().toString();
				return "Error Missing Value";
			default:
				return "Not supported";
		}
	}
	
	public Boolean isMandatory() {
		return mandatory;
	}
}
