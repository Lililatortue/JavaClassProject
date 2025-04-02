package com.Bus.Model.Compte;

/*
 * Enumération représentant différentes devises monétaires.
 * Chaque devise est associée à un symbole et un taux de conversion.
 */

public enum  Devise{
	
	// Dollar américain avec son symbole et son taux de conversion
	USD( "$", 0.69),
	
	//  Euro avec son symbole et son taux de conversion
	EUR( "€", 0.64),
	
	// Livre sterling avec son symbole et son taux de conversion
	GBP( "£", 0.54);
	
	// Symbole de la devise
	public String signe;
	
	// Taux de conversion de la devise par rapport à la devise de référence
	public double exchangeRate;
	
	 /*
     * Constructeur de l'énumération Devise.
     */
	Devise(String signe, double exchangeRate) {
		this.signe = signe;
        this.exchangeRate = exchangeRate;
	}
	
	// GETTERS
	public String getSigne() {
        return signe;
    } // Obtient le symbole de la devise
	
	public double getExchangeRate() {
        return exchangeRate;
    } // Obtient le taux de conversion de la devise
}
