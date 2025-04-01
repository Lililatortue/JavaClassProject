package com.buisness.Logger;

/*
 * Implémentation de LoggerStrategy pour journaliser les messages dans une base de données.
 * 
 * !!! Actuellement, l'implémentation est incomplète et ne stocke pas encore les logs. !!!
 */

public class LoggerDatabase implements LoggerStrategy {
	
	private String connString;
	
	/*
     * Constructeur de LoggerDatabase.
     */
	public LoggerDatabase(String connectionString) {
		this.connString = connectionString;
	}
	
	/*
	 * Redéfinition de la méthode toString pour afficher une représentation textuelle du logger.
	 */
	@Override
	public void log(Logger logger) {
			logger.toString();
	}
	
	
}
