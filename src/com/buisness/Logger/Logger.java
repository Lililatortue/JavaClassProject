package com.buisness.Logger;
enum LogLevel {
    TEST,INFO, WARNING, ERROR;
} 
public abstract class Logger {
	protected LoggerStrategy strategy;
	
	public Logger(LoggerStrategy strat) {
		this.setStrategy(strat);
	}
	
	//setters
	public void setStrategy(LoggerStrategy strat) {
		this.strategy=strat;
	};
	public abstract void log();
	
	@Override
	public String toString() {
		return "Logger MetaData: ";
	}
}
