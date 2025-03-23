package com.William.Logger;


import java.io.IOException;

public class LoggerConsole implements LoggerStrategy {

	public void log(Logger logger){
			System.out.print(logger.toString()+"\n");
	}
}
