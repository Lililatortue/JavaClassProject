package com.Bus.Model.Compte;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.DAL.Repository.Exception.InvariantException;


public class LigneDeCredit extends CompteInteret {
	
	private static final long serialVersionUID = -3662972914243024057L;
	

	/**
	 * Constructeur principal d'une ligne de crédit.
     * Initialise le compte avec un taux d'intérêt donné et la date actuelle.
	 * @param clientId
	 * @param tauxInteret
	 */
	public LigneDeCredit(int clientId, double tauxInteret) {
		super(clientId, null, tauxInteret, CompteType.LGNCRED);
		this.interetMensuelDu=0.0;
	}
	
	/**
	 * Constructeur principal d'une ligne de crédit.
     * Initialise le compte avec un taux d'intérêt donné et la date actuelle.
	 * @param clientId
	 * @param tauxInteret
	 * @throws SQLException 
	 */
	public LigneDeCredit(ResultSet rs) throws SQLException {
		super(rs);
		this.interetMensuelDu=rs.getInt("interetMensuel");
	}
	
	/**
	 * Constructeur principal d'une ligne de crédit.
     * Initialise le compte avec un taux d'intérêt donné et la date actuelle.
	 * @param clientId
	 * @param tauxInteret
	 */
	public LigneDeCredit(LigneDeCredit credit) {
		super(credit);
		this.interetMensuelDu=0.0;
	}
	
	@Override
	public double retirer(double montant) throws InvariantException {
			this.solde += montant;
			return montant;	
	}
	

	/*
	 * Redéfinition de la méthode toString pour afficher les détails du compte de ligne de crédit
	 */
	@Override
	public String toString() {
		return super.toString();
	}

		
}
