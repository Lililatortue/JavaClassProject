package com.buisness.Logger;

public class LoggerDatabase implements LoggerStrategy {
	private String connString;
	
	public LoggerDatabase(String connectionString) {
		
	}
	@Override
	public void log(Logger logger) {
			logger.toString();
	}
	
	
}
