package business;

import java.io.Serializable;

public class Notification implements Serializable {

	
	private static final long serialVersionUID = -4851025396913402381L;
	private String destinataire; // Client ou Gestionnaire
	private String message;

	public Notification(String destinataire, String message) {
		this.destinataire = destinataire;
		this.message = message;
	}

	public void envoyer() {
		System.out.println("[" + destinataire + "] " + message);
	}

	public String getDestinataire() {
		return destinataire;
	}

	public String getMessage() {
		return message;
	}

}
