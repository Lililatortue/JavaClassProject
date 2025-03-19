package com.William.Logger;

import java.util.function.Predicate;
/*
 * subclasse de Logger qui a pour but de debug et test des objets
 * 
 *Dependament de la strategy elle loggeras de maniere differente
 * 
 * */
public class LoggerInfo extends Logger {
	private String info;
	// constructor as to set strategy
	public LoggerInfo(LoggerStrategy strat) {
		super(strat);
	}
	
	/*
	 * Permet de log le state de l'objet,
	 * */
	public void logState(String state) {
		info=("["+LogLevel.INFO+"]"+" STATE: "+state);
		this.strategy.log(this);
	}
	
	public <T> void logTest(Predicate<T> pred,T object,String condition) {
		info="["+LogLevel.TEST+"] "+ "Condition: " + condition +
			 "\n\t\tResult:"+((pred.test(object)) ? "SUCCES ": "FAIL ")+
			 "\n\t\t[ State: {" + object.toString()+"} ]" ;
		this.strategy.log(this);
	};

	@Override
	public String toString() {	
		return info;
	}
	@Override
	public void log() {
		// TODO Auto-generated method stub
		
	};
}
