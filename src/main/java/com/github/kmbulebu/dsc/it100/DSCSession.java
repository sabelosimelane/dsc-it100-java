package com.github.kmbulebu.dsc.it100;

import javax.enterprise.context.ApplicationScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@ApplicationScoped
public class DSCSession {
	private final static Log log = LogFactory.getLog(DSCSession.class); 
	
	private IT100 instance;
	
	public void connect(String ipAddress) throws Exception {
		log.info(String.format("connecting to [%s]...", ipAddress));
		final IT100 it100 = new IT100(new ConfigurationBuilder()
				.withRemoteSocket(ipAddress, 4025)
				.withStatusPolling(15)
				.withEnvisalinkPassword("user").build());
		log.info("successfully connected!");
		instance = it100;
	}
	
	
	public void disconnect() throws Exception {
		instance.disconnect();
	}
}
