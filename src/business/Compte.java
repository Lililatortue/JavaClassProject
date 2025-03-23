package business;

import java.io.Serializable;
import java.util.Date;

public abstract class Compte implements Serializable {

	
	private static final long serialVersionUID = 2300786707200308030L;
	
	
	protected String numero;
    protected double solde;
    protected Date dateOuverture;
    
    
    public Compte(String numero, double solde) {
        this.numero = numero;
        this.solde = solde;
        this.dateOuverture = new Date();
    }
    
    
    public String getNumero() {
		return numero;
	}
	public double getSolde() {
		return solde;
	}
	public Date getDateOuverture() {
		return dateOuverture;
	}
	
    
	public void deposer(double montant) {
        if (montant > 0) {
            solde += montant;
        }
    }
	
	// Méthodes abstraite pour chaque type de compte (Chèque, Épargne, Crédit)
	public abstract boolean retirer(double montant);
	public abstract void afficherDetails();


	@Override
	public String toString() {
		return "Compte [numero=" + numero + ", solde=" + solde + ", dateOuverture=" + dateOuverture + "]";
	}
}
