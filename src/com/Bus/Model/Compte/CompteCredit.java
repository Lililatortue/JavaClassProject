package com.Bus.Model.Compte;

import java.time.LocalDate;

import com.Bus.Model.Client.Client;

/*
 * Classe représentant un compte de crédit dans le système bancaire.
 * Un compte de crédit permet à un client d'emprunter de l'argent jusqu'à une certaine limite de crédit définie.
 * Cette classe étend la classe CompteInteret et permet de gérer les emprunts ainsi que l'accumulation des intérêts mensuels
 * sur le solde emprunté.
 */
public class CompteCredit extends CompteInteret {
	
	private static final long serialVersionUID = -4818229131330014012L;
		
	// Limite de crédit disponible pour ce compte
	private double limite;	
	
	/*
	 *constructeur par default
	 */
	public CompteCredit(int clientId, double tauxInteret,double limite){
		super(clientId, tauxInteret,CompteType.CRED);
		this.setLimite(limite);
		this.interetMensuelDu = 0.0;
	}
	
	//prototype
	public CompteCredit(CompteCredit compte){
		super(compte);
		this.limite = compte.limite;
		this.update();
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
		return 	"\n\ttype de compte: "+ type + " Solde :"+super.solde+" limite: "+limite;
	}		
}
