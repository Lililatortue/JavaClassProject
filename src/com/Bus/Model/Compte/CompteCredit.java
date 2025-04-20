package com.Bus.Model.Compte;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.DAL.Repository.Exception.InvariantException;
 

/*
 * Classe représentant un compte de crédit dans le système bancaire.
 * Un compte de crédit permet à un client d'emprunter de l'argent jusqu'à une certaine limite de crédit définie.
 * Cette classe étend la classe CompteInteret et permet de gérer les emprunts ainsi que l'accumulation des intérêts mensuels
 * sur le solde emprunté.
 */
public class CompteCredit extends CompteInteret {
	
	private static final long serialVersionUID = -4818229131330014012L;
	/**
	 * constructeur par default
	 * @param clientId
	 * @param tauxInteret
	 * @param limite
	 */
	public CompteCredit(int clientId, Double limite, double tauxInteret){
		super(clientId, limite, tauxInteret,CompteType.CREDIT);
		this.interetMensuelDu = 0.0;
	}
	
	
	/**
	 * contructor de Oracle sql
	 * @param rs
	 * @throws SQLException
	 */
	public CompteCredit(ResultSet rs) throws SQLException {
		super(rs);
		this.update();
	}
	
	
	/**
	 * prototype
	 * @param compte
	 */
	public CompteCredit(CompteCredit compte){
		super(compte);
		this.update();
		
	}
	
	// MÉTHODE D'EMPRUNT
	@Override
	public double retirer(double montant) throws InvariantException {
		if(this.solde+montant<=limite) {
			this.solde += montant;
			return montant;	
		} else {
			throw new InvariantException("fonds insuffisant");
		}
	}
		
	/*
	 * Redéfinition de la méthode toString pour afficher les détails du compte de crédit.
	 * Affiche la date d'ouverture, le solde et la limite de crédit disponible.
	 */
	@Override
	public String toString() {
		return 	"\n\ttype de compte: "+ type + " Solde :"+super.solde+" limite: "+limite;
	}		
}
