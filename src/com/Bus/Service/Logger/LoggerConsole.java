package com.Bus.Service.Logger;

import java.io.IOException;

/*
 * Implémentation de l'interface LoggerStrategy qui journalise les messages dans la console.
 */
public class LoggerConsole implements LoggerStrategy {

	/**
     * Journalise un message dans la console
     *
     * @param logger - Le logger dont le message doit être affiché dans la console
     */
    @Override
	public void log(Logger logger){
			System.out.print(logger.toString()+"\n");
	}
}
