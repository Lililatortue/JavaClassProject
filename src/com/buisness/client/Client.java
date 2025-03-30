package com.buisness.client;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.buisness.compte.*;

public class Client extends Utilisateur implements Serializable {
	
	private static final long serialVersionUID = 2445142944692786364L;
	
	 private String email;
	 private String telephone;
	 private List<Compte> comptes;
	
	 public Client(int id, String nom, String prenom, String adresse, String nip, String email, String telephone) {
		 super(id, nom, prenom, adresse, nip, "Client");
	     this.email = email;
	     this.telephone = telephone;
	     this.comptes = new ArrayList<>();
	     this.setComptePreRequis();
	 }
	 
	 
	// GETTERS
	public String getEmail() {
		return email;
	}

	public String getTelephone() {
		return telephone;
	}
	
	
	public List<Compte> getComptes() {
		return comptes;
	}

	
	// SETTERS
	public void setEmail(String email) {
		this.email = email;
	}


	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	private void setComptePreRequis() {
		CompteCheque c = new CompteCheque(this, 0.0, 5.0);
		this.comptes = new ArrayList<>();
		this.ajouterCompte(c);
	}
	
	// Ajouter un compte au client
    public void ajouterCompte(Compte compte) {
        comptes.add(compte);
    }
    
    // Consulter le solde total du client
    public double consulterSolde() {
        double soldeTotal = 0;
        for (Compte compte : comptes) {
            soldeTotal += compte.getSolde();
        }
        return soldeTotal;
    }
    
    
    @Override
    public void afficherDetails() {
        System.out.println("Client : " + nom + " " + prenom +
                           " | ID: " + id +
                           " | Adresse: " + adresse +
                           " | Email: " + email +
                           " | Téléphone: " + telephone);
        System.out.println("Comptes du client :");
        comptes.forEach(System.out::println);
    }
}
