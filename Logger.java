package com.Bus.Service.Logger;

/*
 * Énumération représentant les différents niveaux de journalisation (log)
 * 
 * Ces niveaux permettent de classifier les événements enregistrés par le système :
 *    TEST - Utilisé pour les tests et le débogage.
 *    INFO - Informations générales sur le fonctionnement normal de l'application.
 *    WARNING - Signale un comportement anormal ou potentiellement problématique.
 *    ERROR - Indique une erreur critique bloquant ou affectant gravement le système.
 */
enum LogLevel {
    TEST,INFO, WARNING, ERROR;
} 

/*
 * Classe abstraite représentant un logger générique basé sur une stratégie.
 * 
 * Cette classe définit une interface commune pour tous les types de loggers,
 * déléguant la logique spécifique de journalisation à une stratégie LoggerStrategy.
 * 
 * Les sous-classes doivent implémenter la méthode log() pour définir le comportement du logger.
 */
public abstract class Logger {
	
	// Stratégie de journalisation utilisée pour formater et enregistrer les logs
	protected LoggerStrategy strategy;
	
	/**
	 * Constructeur permettant d'initialiser le logger avec une stratégie spécifique
	 * 
	 * @param strat - La stratégie de journalisation à utiliser
	 */
	public Logger(LoggerStrategy strat) {
		this.setStrategy(strat);
	}
	
	/**
	 * Définit une nouvelle stratégie de journalisation à utiliser
	 * 
	 * @param strat - La nouvelle stratégie à appliquer
	 */
	public void setStrategy(LoggerStrategy strat) {
		this.strategy=strat;
	};
	
	/*
     * Méthode abstraite que les sous-classes doivent implémenter pour gérer la journalisation
     * d'événements ou de messages selon la stratégie définie.
     */
	public abstract void log();
	
	/**
     *
     * @return une représentation textuelle du logger
     */
	@Override
	public String toString() {
		return "Logger MetaData: ";
	}
}
