package com.Bus.Model.Compte;

import java.time.LocalDate;



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
	
	// Limite d'achat ou de retrait sur le compte
	private double limite;
	
	/*
	 * Constructeur pour créer un compte d'épargne.
	 * Ce constructeur initialise le compte avec un client, un taux d'intérêt et une limite d'achat.
	 */
	public CompteEpargne(int clientId, double tauxInteret, double limite) {
		super(clientId, tauxInteret,CompteType.EPRGN);
		this.limite = limite;
	}
	
	/*
	 * Constructeur pour créer un compte d'épargne avec des paramètres supplémentaires.
	 * Ce constructeur initialise le compte avec une date et un type de compte spécifiés
	 */
	protected CompteEpargne(CompteEpargne compte) {
		super(compte);
		this.limite = compte.limite;
		this.update();
	}
	
	// MÉTHODES DE GESTION DU COMPTE
	/*
	 * Dépôt d'un montant sur le compte d'épargne.
	 * Le montant est ajouté au solde du compte si le montant est supérieur à zéro.
	 */
	@Override
	public void deposer(double montant) {
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
	protected double retirer(double montant) throws Exception  {
		if(montant > 0 && this.solde - montant > 0 && this.limite-montant > 0) {
			this.limite -= montant;
			this.solde -= montant;
			return montant;	
		} else {
			throw new Exception("doit deposer plus que 0.");}
	}
	
	// MÉTHODE DE MISE À JOUR
	/**
	 * Mise à jour de l'intérêt mensuel.
	 * Appelée pour ajuster les intérêts lorsque le mois change.
	 */
	@Override
	public void update() {
		if(this.getMois().getValue() < LocalDate.now().getMonth().getValue())
			setInteretMensuelDu();
	}
	
	// MÉTHODE D'INTÉRÊT
	/*
	 * Mise à jour du calcul des intérêts mensuels.
	 * Applique le taux d'intérêt annuel pour calculer l'intérêt du mois et l'ajouter au solde.
	 */
	@Override
	protected void setInteretMensuelDu() {
		this.solde+=(this.getTauxInteretAnnuel() / 12) * this.solde;
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
