package com.buisness.transaction;

/*
 * Énumération représentant les types de transactions possibles dans le système bancaire.
 */

public enum Transaction_type {
	// Représente un dépôt d'argent sur un compte
	deposer,
	
	// Représente un retrait d'argent d'un compte
	retirer,
	
	// Représente un virement d'argent d'un compte à un autre
	virer
}
