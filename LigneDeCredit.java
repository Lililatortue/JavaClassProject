package com.Bus.Model.Compte;

import java.time.LocalDate;

/*
 * Représente une ligne de crédit, un type de compte permettant à un client d’emprunter de l’argent
 * jusqu’à une certaine limite, avec intérêts mensuels appliqués sur le solde emprunté.
 */

public class LigneDeCredit extends CompteInteret {
	
	private static final long serialVersionUID = -3662972914243024057L;
	
	/**
	 * Constructeur de la classe LigneDeCredit
	 * 
	 * @param clientId
	 * @param tauxInteret
	 */
	public LigneDeCredit(int clientId,double tauxInteret) {
		super(clientId, tauxInteret, CompteType.LGNCRED);
		this.interetMensuelDu=0.0;
	}
		
	/**
	 * Constructeur de copie
	 * 
	 * @param compte
	 */
	protected LigneDeCredit(LigneDeCredit compte) {
		super(compte);
		update();
	}
		
	// Calcule les intérêts mensuels dus sur le solde actuel
	@Override
	protected void setInteretMensuelDu() {
		this.interetMensuelDu+=(this.getTauxInteretAnnuel() / 12) * this.solde;
	}
		
	/**
	 * Permet d’emprunter un montant à la ligne de crédit.
     * Cette opération est équivalente à un retrait.
	 *  
	 * @param montant - Le montant à emprunter
	 * @return le montant effectivement emprunté
	 * @throws Exception - Si le retrait n’est pas possible (dépasse la limite, solde, etc.)
	 */
	public double emprunter(double montant) throws Exception {
			return this.retirer(montant);
	}
	
	// Met à jour l’état du compte (intérêts dus) si un nouveau mois a commencé depuis la dernière mise à jour
	@Override
	public void update() {
		if(this.getMois().getValue() < LocalDate.now().getMonth().getValue())
			setInteretMensuelDu();
	}
	
	/**
	 * 
	 * @return une représentation textuelle de la ligne de crédit
	 */
	@Override
	public String toString() {
		return super.toString();
	}		
}
