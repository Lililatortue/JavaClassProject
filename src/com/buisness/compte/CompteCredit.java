package com.buisness.compte;

import java.time.LocalDate;

import com.buisness.client.Client;

/*
 * Classe représentant un compte de crédit dans le système bancaire.
 * 
 * Un compte de crédit permet à un client d'emprunter de l'argent jusqu'à une certaine limite de crédit définie.
 * Cette classe étend la classe CompteInteret et permet de gérer les emprunts ainsi que l'accumulation des intérêts mensuels
 * sur le solde emprunté.
 */
public class CompteCredit extends CompteInteret {
	
	private static final long serialVersionUID = -4818229131330014012L;
		
	// Limite de crédit disponible pour ce compte
	private double limite;	
	
	/*
	 * Constructeur du compte de crédit.
	 * Initialise un compte de crédit avec un taux d'intérêt et une limite de crédit définis.
	 * Le compte est créé avec un solde initial de 0.0 et la date d'ouverture est définie sur la date actuelle.
	 */
	public CompteCredit(Client clientId, double tauxInteret, double limite){
		super(clientId, tauxInteret, LocalDate.now());
		this.setCompteId(clientId);
		this.setLimite(limite);
		this.interetMensuelDu = 0.0;
	}
	
	/*
	 * Constructeur du compte de crédit avec une date d'ouverture spécifiée.
	 * Ce constructeur permet de définir une date d'ouverture pour le compte et de mettre à jour les intérêts dus.
	 */
	protected CompteCredit(Client clientId, double tauxInteret, LocalDate localdate, double l){
		super(clientId, tauxInteret, localdate);
		this.setCompteId(clientId);
		this.setLimite(l);
		this.update();
	}
	
	// MÉTHODE DE GESTION DU COMPTE
	/*
	 * Définit l'identifiant du compte de crédit.
	 * L'identifiant est généré en concaténant "CMPTCRED" avec l'identifiant du client.
	 */
	@Override
	public void setCompteId(Client clientId) {
		this.compteId = "CMPTCRED" + clientId.getId();
	}
	
	// MÉTHODE D'EMPRUNT
	/*
	 * Permet à un client d'emprunter un montant d'argent à partir de son compte de crédit.
	 * Si le montant emprunté ne dépasse pas la limite de crédit, l'emprunt est effectué.
	 */
	public double emprunter(double montant) throws Exception {
		// Si le montant demandé ne dépasse pas la limite de crédit, procéder au retrait
		if(this.solde + montant > this.limite)
			return this.retirer(montant);
		else
			throw new Exception("ne peut emprunter de l'argent - limite atteinte"); // Si la limite est atteinte, lever une exception
	}
		
	// MÉTHODES DE GESTION DES INTÉRÊTS
	/*
	 * Calculer et appliquer l'intérêt mensuel dû en fonction du taux d'intérêt annuel et du solde emprunté.
	 */
	@Override
	protected void setInteretMensuelDu() {
		this.interetMensuelDu+=(this.getTauxInteretAnnuel() / 12) * this.solde;
	}
		
	/*
	 * Met à jour les intérêts dus sur le compte, si le mois a changé.
	 * 
	 * Cette méthode vérifie si le mois courant est supérieur à celui de l'ouverture du compte, et applique des intérêts mensuels si nécessaire.
	 */
	@Override
	public void update() {
		if(this.getMois().getValue() < LocalDate.now().getMonth().getValue())
			setInteretMensuelDu();
	}
		
	// SETTERS
	private void setLimite(Double l) {
		this.limite=l;
	}
	
	/*
	 * Redéfinition de la méthode toString pour afficher les détails du compte de crédit.
	 * Affiche la date d'ouverture, le solde et la limite de crédit disponible.
	 */
	@Override
	public String toString() {
		return super.toString()+"\n\tLimite sur le compte: "+ this.limite;
	}		
}
