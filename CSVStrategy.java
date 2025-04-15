package com.Bus.Service.GestionRapports;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Implémentation de la stratégie de formatage en CSV pour sérialiser des objets génériques
 * 
 * @param <c> - Le type d'objet à sérialiser en CSV
 */
public class CSVStrategy<c> implements formatStrategy<c>{
	
	// Représentation en-tête des colonnes CSV
	private StringBuilder headerString = new StringBuilder();
	
	// Liste des champs primitifs de l'objet
	ArrayList<Field> primitifFields; 
	
	// Liste des champs objets (non-primitifs) de l'objet
	ArrayList<Field>objectFields; 
	
	/**
	 * Constructeur de la stratégie CSV
	 * 
	 * @param Header - Une instance du type c utilisée pour extraire les champs
	 */
	public CSVStrategy(c Header) {
		primitifFields = new ArrayList<Field>();
		objectFields = new ArrayList<Field>(); 
		
        for (Class<?> c = Header.getClass(); c != null; c = c.getSuperclass()) {
            for (Field field : c.getDeclaredFields()) {
                field.setAccessible(true);
                
                Class<?> type = field.getType();
                if (type.isPrimitive()) {
            	   primitifFields.add(field);
               } else {
            	   objectFields.add(field);
               }
            }
        }
        setHeader(Header);
	}
	
	/**
     * Génère une ligne CSV représentant l'objet donné
     *
     * @param item - L'objet à sérialiser en format CSV
     * @return une chaîne représentant les valeurs séparées par des virgules
     */
	@Override
	public String write(c item) {	
		//reflection
		StringBuilder contentString = new StringBuilder();
		Class<?> clazz = item.getClass();
		boolean first = true;
		for(Field field : primitifFields) {
			 contentString.append((first == true) ?  "": ",");
			 first=false;
			try {
				var itemValue=field.get(item);
				contentString.append(itemValue == null ? "" : itemValue);
			
			} catch(IllegalArgumentException | IllegalAccessException e) {
				System.out.print(e.getMessage());
			}
		}
		
		for(Field field : objectFields) {
			 contentString.append((first == true) ?  "": ",");
			 first=false;
			try {
				Object itemValue=field.get(item);
				contentString.append(itemValue == null ? "" : itemValue);
			
			} catch(IllegalArgumentException | IllegalAccessException e) {
				System.out.print(e.getMessage());
			}
		}
		return contentString.toString();	
	}
	
	/**
	 * Génère et stocke l’en-tête CSV à partir des noms des champs de l’objet
	 * 
	 * @param item - L'objet dont les noms de champs sont utilisés comme en-tête
	 */
	private void setHeader(c item) {
		boolean first = true;
		for(Field field : primitifFields) {
			headerString.append((first == true) ? "" : ",");	
			first=false;
			headerString.append(field.getName());
		}
		
		for(Field field : objectFields) {
			headerString.append((first == true) ? "" : ",");	
			first=false;
			headerString.append(field.getName());
		}
	}
	
	/**
	 * Retourne la ligne d’en-tête CSV, représentant les noms de champs
	 * 
	 * @return une chaîne représentant les noms des colonnes CSV
	 */
	@Override
	public String getHeader() {
		return headerString.toString();
	};

	/**
     * Retourne l’extension de fichier associée à cette stratégie
     *
     * @return l'extension ".csv"
     */
	@Override
	public String getExtension() {	
		return ".csv";
	}

}
