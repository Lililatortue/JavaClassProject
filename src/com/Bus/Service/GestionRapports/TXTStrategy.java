package com.Bus.Service.GestionRapports;

/**
 * Implémentation de la stratégie de formatage pour générer des rapports au format texte
 * 
 * @param <c> - Le type d’objet à formater
 */
public class TXTStrategy<c> implements formatStrategy<c>{
	
	// En-tête du fichier TXT
	private String header;
	
	/**
 	 * Constructeur de la stratégie TXT
 	 * 
 	 * @param item - L’en-tête à utiliser
 	 */
	public TXTStrategy(String item){
		if(item==null)
			this.header = "";
		else
			this.header = item;
	}
	
	/**
     * Formatte un objet en chaîne de caractères en utilisant sa méthode toString()
     *
     * @param item - L’objet à formater
     * @return la représentation texte de l’objet
     */
	@Override
	public String write(c item) {
		return item.toString();
	}

	/**
     * 
     * @return l’en-tête du rapport texte
     */
	@Override
	public String getHeader() {
		return header;
	}
	
	/**
     *
     * @return l’extension de fichier associée à cette stratégie
     */
	@Override
	public String getExtension() {
		
		return ".txt";
	}

}
