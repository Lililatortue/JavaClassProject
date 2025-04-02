package com.Bus.Service.Logger;

import java.util.function.Predicate;

/*
 * Logger spécialisé pour l'affichage d'informations et les tests de conditions.
 * 
 * Cette classe permet d'enregistrer des logs d'état et des tests conditionnels 
 * en fonction d'une stratégie de journalisation définie via LoggerStrategy.
 * 
 * Elle peut être utilisée pour afficher des informations générales 
 * ou pour exécuter des tests sur des objets avec un retour détaillé.
 */

public class LoggerInfo extends Logger {
	
	// Message d'information stocké pour la journalisation
	private String info;
	
	/*
     * Constructeur permettant d'initialiser la stratégie de journalisation.
     */
	public LoggerInfo(LoggerStrategy strat) {
		super(strat);
	}
	
	/*
     * Enregistre l'état actuel d'un objet ou d'une variable.
     */
	public void logState(String state) {
		info=("["+LogLevel.INFO+"]"+" STATE: "+state);
		this.strategy.log(this);
	}
	
	/*
     * Effectue un test conditionnel sur un objet et journalise le résultat.
     * 
     * Le test est basé sur un Predicate qui est évalué sur l'objet donné.
     */
	public <T> void logTest(Predicate<T> pred,T object,String condition) {
		info="["+LogLevel.TEST+"] "+ "Condition: " + condition +
			 "\n\t\tResult:"+((pred.test(object)) ? "SUCCES ": "FAIL ")+
			 "\n\t\t[ State: {" + object.toString()+"} ]" ;
		this.strategy.log(this);
	};

	/*
     * Redéfinition de la méthode toString pour afficher la chaîne de log actuelle.
     */
	@Override
	public String toString() {	
		return info;
	}
	
	/*
     * Implémentation vide de la méthode log(), car cette classe utilise des méthodes spécifiques.
     */
	@Override
	public void log() {
		// Implémentation non nécessaire ici, car logState et logTest gèrent les logs directement
	};
}
