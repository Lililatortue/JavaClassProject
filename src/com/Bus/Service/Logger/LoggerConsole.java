package com.Bus.Service.Logger;

import java.io.IOException;

/*
 * Implémentation de LoggerStrategy pour journaliser les messages dans la console.
 */

public class LoggerConsole implements LoggerStrategy {

	/*
     * Journalise un message dans la console.
     */
    @Override
	public void log(Logger logger){
			System.out.print(logger.toString()+"\n");
	}
}
