package com.Bus.Service.Logger;

/*
 * Implémentation de l'interface LoggerStrategy pour journaliser les messages dans une base de données.
 * 
 * !!! Actuellement, l'implémentation est incomplète et ne stocke pas encore les logs. !!!
 */
public class LoggerDatabase implements LoggerStrategy {
	
	// Chaîne de connexion à la base de données
	private String connString;
	
	/**
 	 * Constructeur de la classe LoggerDatabase
 	 * 
 	 * @param connectionString - La chaîne de connexion à utiliser pour accéder à la base de données
 	 */
	public LoggerDatabase(String connectionString) {
		this.connString = connectionString;
	}
	
	/**
     * Méthode appelée pour journaliser un événement
     * 
     * @param logger - L'objet contenant les informations à journaliser
     */
	@Override
	public void log(Logger logger) {
			logger.toString(); // Implémentation incomplète
	}
	
	
}
