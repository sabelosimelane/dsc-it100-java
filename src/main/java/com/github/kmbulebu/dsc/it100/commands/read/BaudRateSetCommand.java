package com.github.kmbulebu.dsc.it100.commands.read;

import java.util.HashMap;
import java.util.Map;

import com.github.kmbulebu.dsc.it100.commands.ICommandHelp;

public class BaudRateSetCommand extends ReadCommand implements ICommandHelp {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2724596746922704371L;

	public static final String CODE = "580";
	
	private String baudRateCode = null;
	private static Map<String, Integer> baudRateMap = new HashMap<String, Integer>();
	private int baudRate = -1;
	
	static {
		baudRateMap.put("0", 9600);
		baudRateMap.put("1", 19200);
		baudRateMap.put("2", 38400);
		baudRateMap.put("3", 57600);
		baudRateMap.put("4", 115200);
	}

	@Override
	protected void parseData(String dataString)
			throws CommandDataParseException {
		if (dataString.length() != 1) {
			throw new CommandDataParseException("Expected data length of 1 bytes, received " + dataString.length());
		}
		baudRateCode = new String(dataString);
		baudRate = baudRateMap.get(baudRateCode);
		return;
	}
	
	public String getBaudRateCode() {
		return baudRateCode;
	}
	
	public int getBaudRate() {
		return baudRate;
	}

	public String getDescription() {
		return "The IT-100 sends the command in response to the following command sent by the application: Baud Change Rate (080)";
	}

}
