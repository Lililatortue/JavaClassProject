package com.Bus.Service.GestionRapports;

/*
 * Enumération représentant les différentes extensions de fichiers 
 * supportées pour les formats d’exportation.
 * 
 * Chaque valeur d'énumération contient une chaîne représentant 
 * l'extension réelle du fichier, comme ".csv", ".txt", ou ".json".
 */
public enum extension {
	
	// Format CSV
	CSV(".csv"),
	
	// Format texte
	TXT(".txt"),
	
	// Format JSON
	JSON(".json");
	
	// Chaîne représentant l'extension de fichier
	private final String ext;
	
	/**
 	 * Constructeur de l’énumération
 	 * 
 	 * @param extension - Chaîne correspondant à l’extension du fichier
 	 */
	extension(String extension) {
		 this.ext = extension;
	}
	
	/**
 	 * 
 	 * @return la chaîne représentant l'extension de fichier
 	 */
	public String getExtension() {
        return ext;
    }
}
