package com.buisness.Logger;

/*
 * Énumération représentant les différents niveaux de log 
 * -> permettent de classifier les événements enregistrés par le système.
 * 
 * TEST - Utilisé pour les tests et le débogage.
 * INFO - Indique une information générale sur le fonctionnement normal.
 * WARNING - Signale un problème potentiel qui pourrait nécessiter une attention.
 * ERROR - Indique une erreur qui empêche le bon fonctionnement du programme.
 */

enum LogLevel {
    TEST,INFO, WARNING, ERROR;
} 

/*
 * Classe abstraite définissant un logger générique.
 * 
 * Cette classe permet de journaliser des événements;
 * la logique spécifique du logging est déléguée à une classe implémentant LoggerStrategy.
 */
public abstract class Logger {
	
	// Stratégie utilisée pour enregistrer les logs
	protected LoggerStrategy strategy;
	
	/*
     * Constructeur permettant d'initialiser le logger avec une stratégie spécifique.
     */
	public Logger(LoggerStrategy strat) {
		this.setStrategy(strat);
	}
	
	// SETTERS
	public void setStrategy(LoggerStrategy strat) {
		this.strategy=strat;
	}; // Définit une nouvelle stratégie de journalisation
	
	// Méthode abstraite que les sous-classes doivent implémenter pour gérer la journalisation
	public abstract void log();
	
	/*
	 * Redéfinition de la méthode toString pour afficher une représentation textuelle du logger.
     */
	@Override
	public String toString() {
		return "Logger MetaData: ";
	}
}
