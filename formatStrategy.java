package com.Bus.Service.GestionRapports;

/**
 * Interface définissant une stratégie de formatage pour l’exportation de données.
 * 
 * Chaque stratégie doit implémenter un en-tête, un format d’écriture et une extension
 * de fichier associée.
 * 
 * @param <c> - Le type d’objet à exporter
 */
public interface formatStrategy<c> {
	
	/**
	 * 
	 * @return une chaîne représentant l’en-tête du format
	 */
	public String getHeader();
	
	/**
	 * Formate un objet de type c en une ligne de texte correspondant 
     * au format choisi (CSV, TXT, etc.)
	 * 
	 * @param item - L’objet à formater
	 * @return la représentation textuelle de l’objet
	 */
	public String write(c item);
	
	/**
	 * 
	 * @return l’extension du fichier associée à ce format
	 */
	public String getExtension();
	
}
