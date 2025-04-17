package com.Bus.Model.Transaction.Validation;

/*
 * Classe utilitaire de validation pour les transactions bancaires.
 */
public class TransactionValidation {
	
	/**
 	 * Vérifie si une chaîne représente un montant valide supérieur à 0
 	 * 
 	 * @param amountStr - La chaîne représentant le montant à valider
 	 * @return true si la chaîne est un nombre positif, sinon false
 	 */
    public static boolean isValidAmount(String amountStr) {
        try {
            double amount = Double.parseDouble(amountStr);
            return amount > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    /**
     * Vérifie si un numéro de compte est valide
     * 
     * @param accountNumber - Le numéro de compte à valider
     * @return true si le format est correct, sinon false
     */
    public static boolean isValidAccountNumber(String accountNumber) {
    	return accountNumber != null && accountNumber.matches("C\\d+");
    }
    
    /**
     * Vérifie que les comptes source et destination sont différents
     * 
     * @param fromAccount - Le numéro du compte source
     * @param toAccount - Le numéro du compte destinataire
     * @return true si les comptes sont différents, sinon false
     */
    public static boolean areDifferentAccounts(String fromAccount, String toAccount) {
        return !fromAccount.equals(toAccount);
    }
}
