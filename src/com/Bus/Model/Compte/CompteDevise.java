package com.Bus.Model.Compte;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.DAL.Repository.Exception.InvariantException;

/*
 * Représente un compte bancaire en devise étrangère.
 * 
 * Cette classe permet de gérer un compte dont le solde est exprimé dans une devise spécifique.
 * Elle inclut des fonctionnalités de dépôt et de retrait, avec des conversions basées sur le taux de change de la devise.
 * 
 * Le compte de devise utilise une instance de la classe Devise pour déterminer le taux de change applicable
 * lors des opérations financières.
 */

public class CompteDevise extends Compte {

	private static final long serialVersionUID = 826859753794501933L;
	
	// Devise associée à ce compte
	private Devise devise;
	
	
	/**
	 *  Constructeur pour initialiser un compte bancaire en devise étrangère.
	 * @param clientId
	 * @param solde
	 * @param devise
	 */
	public CompteDevise(int clientId, double solde,Devise devise) {
		super(clientId, solde,CompteType.DEVISE);
		this.devise = devise; // Associe la devise au compte
	}
	
	
	/**
	 *  Constructeur pour initialiser un compte bancaire en devise étrangère.
	 * @param clientId
	 * @param solde
	 * @param devise
	 * @throws SQLException 
	 */
	public CompteDevise(ResultSet rs) throws SQLException {
		super(rs);
		this.devise = Devise.valueOf(rs.getString("CPT_DEVISE")); // Associe la devise au compte
	}
	
	
	/**
	 * prototype
	 * @param compte
	 */
	public CompteDevise(CompteDevise compte) {
		super(compte);
		this.devise = compte.devise; // Associe la devise au compte
	}
	
	
	
	
	
	/*
	 * Dépôt d'une certaine somme sur le compte en devise.
	 * Le montant déposé est converti selon le taux de change de la devise avant d'être ajouté au solde.
	 */
	@Override
	public void deposer(double montant) throws InvariantException {
		this.solde +=montant * devise.exchangeRate;
	}
	/*
	 * Retrait d'une certaine somme du compte en devise.
	 * Le montant retiré est d'abord soustrait du solde, puis converti en devise de retrait en fonction du taux de change.
	 */
	@Override
	public double retirer(double montant) throws InvariantException {
		this.solde -=montant ;
		return montant / devise.exchangeRate;
	}
	
	
	//GETTERS
	public Devise getDevise() {
		return this.devise;
	}
}

