package com.Bus.Model.Compte;

import java.time.LocalDate;

/*
 * Représente un compte d'épargne avec des intérêts mensuels.
 * 
 * Ce type de compte permet les dépôts et retraits avec une limite de retrait définie.
 * Les intérêts sont calculés mensuellement à partir du solde et du taux d'intérêt annuel.
 */

public class CompteEpargne extends CompteInteret  {
	
	private static final long serialVersionUID = -8110410012131872667L;
	
	// Limite d'achat ou de retrait disponible sur le compte
	private double limite;
	
	/**
	 * Constructeur de la classe CompteEpargne
	 * 
	 * @param clientId
	 * @param tauxInteret
	 * @param limite
	 */
	public CompteEpargne(int clientId, double tauxInteret, double limite) {
		super(clientId, tauxInteret,CompteType.EPRGN);
		this.limite = limite;
	}
	
	/**
	 * Constructeur de copie
	 * 
	 * @param compte
	 */
	protected CompteEpargne(CompteEpargne compte) {
		super(compte);
		this.limite = compte.limite;
		this.update();
	}
	
	/**
	 * Dépose un montant sur le compte d'épargne.
	 *
	 * @param montant - Montant à déposer
	 */
	@Override
	public void deposer(double montant) {
		if(montant>0)
			this.solde += montant;
		//else
			//throw new Exception("doit deposer plus que 0.");
	}
		
	/**
	 * Retire un montant du compte d'épargne en respectant la limite et le solde.
	 *
	 * @param montant - Montant à retirer
	 * @return le montant réellement retiré
	 * @throws Exception - Si le montant dépasse la limite ou le solde disponible
	 */
	@Override
	public double retirer(double montant) throws Exception  {
		if(montant > 0 && this.solde - montant > 0 && this.limite-montant > 0) {
			this.limite -= montant;
			this.solde -= montant;
			return montant;	
		} else {
			throw new Exception("doit deposer plus que 0.");}
	}
	
	// Met à jour le compte si le mois a changé, en appliquant les intérêts mensuels
	@Override
	public void update() {
		if(this.getMois().getValue() < LocalDate.now().getMonth().getValue())
			setInteretMensuelDu();
	}
	
	// Calcule les intérêts mensuels à partir du solde et les ajoute au compte
	@Override
	protected void setInteretMensuelDu() {
		this.solde+=(this.getTauxInteretAnnuel() / 12) * this.solde;
	}
	
	/**
	 * 
	 * @return une représentation textuelle du compte d'épargne
	 */
	@Override
	public String toString() {
		return super.toString()+"\n\tLimite d'achat: "+this.limite;
	}
}
