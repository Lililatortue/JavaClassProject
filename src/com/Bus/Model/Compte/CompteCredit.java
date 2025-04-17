package com.Bus.Model.Compte;

import java.time.LocalDate;

/*
 * Classe représentant un compte de crédit dans le système bancaire.
 * 
 * Un compte de crédit permet à un client d'emprunter de l'argent jusqu'à une certaine limite de crédit définie.
 * Cette classe étend la classe CompteInteret et permet de gérer les emprunts
 * ainsi que l'accumulation des intérêts mensuels sur le solde emprunté.
 */
public class CompteCredit extends CompteInteret {
	
	private static final long serialVersionUID = -4818229131330014012L;
		
	// Limite maximale de crédit disponible pour le client
	private double limite;	
	
	/**
 	 * Constructeur de la classe CompteCredit
 	 * 
 	 * @param clientId
 	 * @param tauxInteret
 	 * @param limite
 	 */
	public CompteCredit(int clientId, double tauxInteret,double limite){
		super(clientId, tauxInteret,CompteType.CRED);
		this.setLimite(limite);
		this.interetMensuelDu = 0.0;
	}
	
	/**
 	 * Constructeur de copie
 	 * 
 	 * @param compte
 	 */
	public CompteCredit(CompteCredit compte){
		super(compte);
		this.limite = compte.limite;
		this.update();
	}
	
	/**
 	 * Permet au client d'emprunter un montant depuis le compte de crédit
 	 * Le montant peut être emprunté seulement si le nouveau solde ne dépasse pas la limite autorisée
 	 * 
 	 * @param montant - Montant souhaité à emprunter
 	 * @return le montant effectivement emprunté (retiré)
 	 * @throws Exception - Si la limite de crédit est dépassée
 	 */
	public double emprunter(double montant) throws Exception {
		if(this.solde + montant > this.limite)
			return this.retirer(montant);
		else
			throw new Exception("ne peut emprunter de l'argent - limite atteinte");
	}
		
	/*
 	 * Calcule et accumule les intérêts mensuels dus sur le solde emprunté
 	 * Le calcul est basé sur le taux annuel divisé par 12
 	 */
	@Override
	protected void setInteretMensuelDu() {
		this.interetMensuelDu+=(this.getTauxInteretAnnuel() / 12) * this.solde;
	}
		
	// Met à jour les intérêts mensuels si un nouveau mois est détecté depuis la dernière mise à jour
	@Override
	public void update() {
		if(this.getMois().getValue() < LocalDate.now().getMonth().getValue())
			setInteretMensuelDu();
	}
		
	/**
 	 * Définit la limite de crédit du compte
 	 * 
 	 * @param l - La limite à définir
 	 */
	private void setLimite(Double l) {
		this.limite=l;
	}
	
	/**
 	 * 
 	 * @return la limite de crédit du compte
 	 */
	public Double getLimite() {
		return this.limite;
	}
		
	/**
	 *
	 * @return une représentation textuelle du compte de crédit
	 */
	@Override
	public String toString() {
		return 	"\n\ttype de compte: "+ type + " Solde :"+super.solde+" limite: "+limite;
	}		
}
