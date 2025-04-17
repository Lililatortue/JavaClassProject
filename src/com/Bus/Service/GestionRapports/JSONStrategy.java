package com.Bus.Service.GestionRapports;

/**
 * Implémentation de la stratégie de formatage pour l’exportation en format JSON
 * 
 * @param <c> - Le type d’objet à exporter en JSON
 */
public class JSONStrategy<c> implements formatStrategy<c>{

	/**
 	 * Génère la représentation JSON de l’objet spécifié
 	 * 
 	 * @param item L’objet à convertir
 	 * @return une chaîne représentant l’objet en JSON
 	 */
	@Override
	public String write(c item) {
		return null;
	}

	/**
     * Retourne l’en-tête JSON
     * 
     * @return une chaîne représentant l’en-tête JSON
     */
	@Override
	public String getHeader() {
		return null;
	}

	/**
     * 
     * @return l’extension de fichier associée à ce format
     */
	@Override
	public String getExtension() {
		return ".json";
	}

}
