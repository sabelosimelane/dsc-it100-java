package com.oakcity.dsc.it100.commands.read;

import com.oakcity.dsc.it100.IZone;
import com.oakcity.dsc.it100.IZoneStateChangeEvent;
import com.oakcity.dsc.it100.commands.ICommandHelp;

public class ZoneFaultCommand extends BaseZoneCommand implements ICommandHelp, IZoneStateChangeEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4785978789855384966L;
	public static final String CODE = "605";
	
	public String getDescription() {
		return "This command indicates that a zone has a fault condition.";
	}

	@Override
	public String toString() {
		return "ZoneFaultCommand [toString()=" + super.toString() + "]";
	}

	@Override
	public boolean isZoneChanged() {
		return true;
	}

	@Override
	public void updateZone(IZone zone) {
		zone.setInFault(true);
	}
	
}
