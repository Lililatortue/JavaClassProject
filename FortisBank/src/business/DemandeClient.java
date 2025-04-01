package business;

import java.io.Serializable;
import java.time.LocalDate;

public class DemandeClient implements Serializable {
	

	private static final long serialVersionUID = -2987861980347063439L;
	private int clientId;
    private String typeDemande;           // "AJOUT", "FERMETURE", "FERMETURE_TOTALE"
    private String typeCompteSouhaite;    // "CHEQUE", "EPARGNE", "CREDIT", "LIGNE", "DEVISE"
    private String compteCible;           // Numéro du compte à fermer
    private LocalDate dateDemande;        // Date de la demande
    
    
    // demande d’ajout
    public DemandeClient(int clientId, String typeDemande, String typeCompteSouhaite, LocalDate dateDemande) {
        this.clientId = clientId;
        this.typeDemande = typeDemande;
        this.typeCompteSouhaite = typeCompteSouhaite;
        this.dateDemande = dateDemande;
    }

    // fermeture d’un compte
    public DemandeClient(int clientId, String typeDemande, String compteCible, boolean fermetureTotale, LocalDate dateDemande) {
        this.clientId = clientId;
        this.typeDemande = fermetureTotale ? "FERMETURE_TOTALE" : "FERMETURE";
        this.compteCible = compteCible;
        this.dateDemande = dateDemande;
    }

    // Getters
    public int getClientId() {
        return clientId;
    }

    public String getTypeDemande() {
        return typeDemande;
    }

    public String getTypeCompteSouhaite() {
        return typeCompteSouhaite;
    }

    public String getCompteCible() {
        return compteCible;
    }

    public LocalDate getDateDemande() {
        return dateDemande;
    }

    @Override
    public String toString() {
        return "DemandeClient{" +
                "clientId=" + clientId +
                ", typeDemande='" + typeDemande + '\'' +
                ", typeCompteSouhaite='" + typeCompteSouhaite + '\'' +
                ", compteCible='" + compteCible + '\'' +
                ", dateDemande=" + dateDemande +
                '}';
    }
}
