package business;

public abstract class Utilisateur extends Personne {
	
	private static final long serialVersionUID = -655492106551936827L;
	protected String nip;
	
	public Utilisateur(int id, String nom, String prenom, String adresse, String nip) {
        super(id, nom, prenom, adresse);
        this.nip = nip;
    }

	public String getNip() {
		return nip;
	}
	
	public boolean verifierNIP(String inputNIP) {
        return this.nip.equals(inputNIP);
    }
}
