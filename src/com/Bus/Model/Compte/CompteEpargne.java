package com.Bus.Model.Compte;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.DAL.Repository.Exception.InvariantException;



/*
 * Représente un compte d'épargne avec des intérêts mensuels.
 * 
 * Ce compte applique un taux d'intérêt mensuel sur le solde, et permet des opérations de dépôt et de retrait en fonction d'une limite définie. 
 * Les retraits sont soumis à des restrictions liées au solde et à la limite disponible sur le compte.
 * 
 * Le taux d'intérêt mensuel est appliqué à partir du solde, et un intérêt mensuel est ajouté au solde en fonction de ce taux.
 */

public class CompteEpargne extends CompteInteret  {
	
	private static final long serialVersionUID = -8110410012131872667L;
	


	/**
	 * Constructeur pour créer un compte d'épargne.
	 * Ce constructeur initialise le compte avec un client, un taux d'intérêt et une limite d'achat.
	 * @param clientId
	 * @param tauxInteret
	 * @param limite
	 */
	public CompteEpargne(int clientId, double limite, double tauxInteret) {
		super(clientId, limite, tauxInteret,CompteType.EPARGNE);
	}
	

	/**
	 * prototype
	 * @param compte
	 * @throws SQLException 
	 */
	public CompteEpargne(ResultSet rs) throws SQLException {
		super(rs);
		this.update();
	}	

	/**
	 * prototype
	 * @param compte
	 */
	protected CompteEpargne(CompteEpargne compte) {
		super(compte);
		this.update();
	}
	
	
	// MÉTHODES DE GESTION DU COMPTE
	/*
	 * Dépôt d'un montant sur le compte d'épargne.
	 * Le montant est ajouté au solde du compte si le montant est supérieur à zéro.
	 */
	@Override
	public void deposer(double montant) throws InvariantException {
		if(montant>0)
			this.solde += montant;
		//else
			//throw new Exception("doit deposer plus que 0.");
	}
		
	/*
	 * Retrait d'un montant du compte d'épargne.
	 * Les retraits sont soumis à la vérification du montant, du solde et de la limite disponible sur le compte.
	 */
	@Override
	public double retirer(double montant) throws InvariantException {
		if(montant > 0 && this.solde - montant > 0 && this.limite-montant > 0) {
			this.limite -= montant;
			this.solde -= montant;
			return montant;	
		} else {
			throw new InvariantException("doit deposer plus que 0.");}
	}
	
	
	
	/*
	 * Redéfinition de la méthode toString pour afficher les détails du compte d'épargne
	 * Affiche les détails du compte ainsi que la limite d'achat.
	 */
	@Override
	public String toString() {
		return super.toString()+"\n\tLimite d'achat: "+this.limite;
	}
}
