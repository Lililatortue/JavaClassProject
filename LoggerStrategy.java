package com.Bus.Service.Logger;

/*
 * Interface représentant une stratégie de journalisation (Logging Strategy).
 * 
 * Cette interface définit une méthode de log générique qui peut être implémentée
 * par différentes classes de journalisation, comme l'affichage en console, 
 * l'enregistrement dans un fichier ou une base de données.
 * 
 * Elle suit le principe Strategy, permettant de modifier
 * dynamiquement la méthode de journalisation sans changer la logique principale
 * du système.
 */

public interface LoggerStrategy {

	/**
	 * Enregistre un message de log en utilisant la stratégie définie
	 * 
	 * @param logger - Le logger appelant, contenant les données à enregistrer
	 */
	void log(Logger logger);
}
