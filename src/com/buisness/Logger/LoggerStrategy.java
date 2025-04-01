package com.buisness.Logger;

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
	// Enregistre un log en utilisant la stratégie de journalisation définie
	void log(Logger logger);
}
