package com.Bus.Model.Compte;

/*
 * Énumération représentant les différents types de comptes bancaires disponibles dans l'application.
 * Chaque type possède un nom d'affichage plus lisible, utilisé pour l'affichage à l'utilisateur
 */

public enum CompteType{
	CHCK("CHECK"), // Compte chèque
	CRED("CREDIT"), // Compte crédit
	LGNCRED("LIGNE DE CREDIT"), // Ligne de crédit
	DEV("DEVISE"), // Compte en devise étrangère
	EPRGN("EPARGNE"), // Compte épargne
	VRMNT("VIREMENT"); // Compte de virement temporaire
	
	// Nom d'affichage lisible du type de compte
	private final String displayName;

	/**
	 * Constructeur de la classe CompteType
	 * 
	 * @param displayName
	 */
    CompteType(String displayName) {
        this.displayName = displayName;
    }

    /**
	 * 
	 * @return le nom d'affichage lisible du type de compte
	 */
    @Override
    public String toString() {
        return displayName;
    }
}
