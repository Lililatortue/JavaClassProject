package com.Bus.Model.Compte;

import java.time.LocalDate;

import com.buisness.client.Client;

public class LigneDeCredit extends CompteInteret {
	
	private static final long serialVersionUID = -3662972914243024057L;
	
	/*
     * Constructeur principal d'une ligne de crédit.
     * Initialise le compte avec un taux d'intérêt donné et la date actuelle.
     */
	public LigneDeCredit(int clientId,double tauxInteret) {
		super(clientId, tauxInteret, CompteType.LGNCRED);
		this.interetMensuelDu=0.0;
	}
		
	/*
     * Constructeur alternatif permettant d'initialiser une ligne de crédit à une date donnée.
     */
	protected LigneDeCredit(LigneDeCredit compte) {
		super(compte);
		update();
	}
		

	@Override
	protected void setInteretMensuelDu() {
		this.interetMensuelDu+=(this.getTauxInteretAnnuel() / 12) * this.solde;
	} // Calcule et applique les intérêts mensuels dus sur le solde négatif du compte
		
	/*
     * Permet d'emprunter un montant spécifique à la ligne de crédit.
     * L'emprunt fonctionne comme un retrait de fonds.
     */ 
	public double emprunter(double montant) throws Exception {
			return this.retirer(montant);
	}
	
	/*
	 * Met à jour l'état du compte en vérifiant si un nouveau mois a commencé.
	 * Si oui, applique les intérêts mensuels.
	 */
	@Override
	public void update() {
		if(this.getMois().getValue() < LocalDate.now().getMonth().getValue())
			setInteretMensuelDu();
	}
	
	/*
	 * Redéfinition de la méthode toString pour afficher les détails du compte de ligne de crédit
	 */
	@Override
	public String toString() {
		return super.toString();
	}		
}
