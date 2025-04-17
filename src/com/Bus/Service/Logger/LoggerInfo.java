package com.Bus.Service.Logger;

import java.util.function.Predicate;

/*
 * Logger spécialisé pour l'affichage d'informations et l'évaluation de conditions.
 * 
 * Cette classe permet de :
 *    Journaliser des états ou des informations générales via logState(String).
 *    Effectuer des tests conditionnels sur des objets avec un Predicate via logTest(Predicate, Object, String).
 * 
 * Elle utilise une stratégie de journalisation LoggerStrategy pour déléguer l'écriture réelle du log. 
 * Cette classe est utile pour les phases de débogage, de validation ou de tests unitaires légers.
 */
public class LoggerInfo extends Logger {
	
	// Message d'information stocké pour la journalisation
	private String info;
	
	/**
 	 * Constructeur de LoggerInfo
 	 * 
 	 * @param strat - La stratégie de journalisation à utiliser
 	 */
	public LoggerInfo(LoggerStrategy strat) {
		super(strat);
	}
	
	/**
 	 * Journalise un état ou une information simple
 	 * 
 	 * @param state - L'état ou l'information à journaliser
 	 */
	public void logState(String state) {
		info=("["+LogLevel.INFO+"]"+" STATE: "+state);
		this.strategy.log(this);
	}
	
	/**
 	 * Évalue une condition sur un objet et journalise le résultat du test
 	 * 
 	 * @param <T> - Le type de l'objet à tester
 	 * @param pred - Le prédicat représentant la condition à tester
 	 * @param object - L'objet à évaluer
 	 * @param condition - Description textuelle de la condition testée
 	 */
	public <T> void logTest(Predicate<T> pred,T object,String condition) {
		info="["+LogLevel.TEST+"] "+ "Condition: " + condition +
			 "\n\t\tResult:"+((pred.test(object)) ? "SUCCES ": "FAIL ")+
			 "\n\t\t[ State: {" + object.toString()+"} ]" ;
		this.strategy.log(this);
	};

	/**
     *
     * @return le message d'information formaté
     */
	@Override
	public String toString() {	
		return info;
	}
	
	// Implémentation vide de la méthode log(), car cette classe utilise des méthodes spécifiques
	@Override
	public void log() {
		// Non utilisé dans LoggerInfo
	};
}
