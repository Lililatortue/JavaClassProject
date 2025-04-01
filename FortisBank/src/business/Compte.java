package business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public abstract class Compte implements Serializable {

	private static final long serialVersionUID = 2300786707200308030L;

	protected String numero;
	protected double solde;
	protected Date dateOuverture;

	protected List<Transaction> historique;

	public Compte(String numero, double solde) {
		this.numero = numero;
		this.solde = solde;
		this.dateOuverture = new Date();
		this.historique = new ArrayList<>();
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

	
	public abstract boolean retirer(double montant);

	public abstract void afficherDetails();

	public void ajouterTransaction(Transaction transaction) {
		historique.add(transaction);
	}

	public void afficherHistorique() {
		if (historique.isEmpty()) {
			System.out.println("Aucune transaction trouvée pour le compte " + numero);
		} else {
			System.out.println("Historique des transactions du compte " + numero + ":");
			for (Transaction t : historique) {
				t.afficherDetails();
			}
		}
	}
	
	
	public String afficherHistoriqueTexte() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("Historique des transactions du compte ").append(numero).append(":\n");
	    if (historique.isEmpty()) {
	        sb.append("Aucune transaction\n");
	    } else {
	        for (Transaction t : historique) {
	            sb.append(t).append("\n");
	        }
	    }
	    return sb.toString();
	}


	public void afficherHistoriqueMensuel(int mois, int annee) {
	    boolean vide = true;

	    System.out.println("Historique mensuel du compte " + numero + " pour " + mois + "/" + annee + " :");

	    for (Transaction t : historique) {
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(t.getDate());

	        int tMois = cal.get(Calendar.MONTH) + 1;
	        int tAnnee = cal.get(Calendar.YEAR);

	        if (tMois == mois && tAnnee == annee) {
	            t.afficherDetails();
	            vide = false;
	        }
	    }

	    if (vide) {
	        System.out.println("Aucune transaction ce mois");
	    }
	}


	@Override
	public String toString() {
		return "Compte [numero=" + numero + ", solde=" + solde + ", dateOuverture=" + dateOuverture + "]";
	}
}
