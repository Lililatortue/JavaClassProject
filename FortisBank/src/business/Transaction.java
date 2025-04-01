package business;

import java.io.Serializable;
import java.util.Date;

public abstract class Transaction implements Serializable {

	private static final long serialVersionUID = -2728451611383323828L;
	protected int id;
	protected Date date;
	protected double montant;
	protected Compte compte;

	public Transaction(int id, double montant, Compte compte) {
		this.id = id;
		this.date = new Date();
		this.montant = montant;
		this.compte = compte;
	}

	public int getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}

	public double getMontant() {
		return montant;
	}

	public Compte getCompte() {
		return compte;
	}

	public abstract boolean executer();

	public abstract void afficherDetails();
	
	
	@Override
	public String toString() {
	    return this.getClass().getSimpleName() +
	           " [ID: " + id +
	           ", Montant: " + montant +
	           ", Compte: " + compte.getNumero() +
	           ", Date: " + date + "]";
	}
}
