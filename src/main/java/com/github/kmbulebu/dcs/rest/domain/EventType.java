package com.github.kmbulebu.dcs.rest.domain;

public enum EventType {

	ZONE_OPEN, ZONE_RESTORE, ZONE_ALARM, ZONE_ALARM_RESTORE;
	
	public static EventType fromString(String eventType) {
		
		switch (eventType) {
		
			case "ZONE_OPEN":
				 return ZONE_OPEN;
				 
			case "ZONE_RESTORE":
				 return ZONE_RESTORE;
				 
			case "ZONE_ALARM":
				 return ZONE_ALARM;
				 
			case "ZONE_ALARM_RESTORE":
				 return ZONE_ALARM_RESTORE;
		}
		
		return ZONE_OPEN;
	}
}
