package com.buisness.validation;

public class TransactionValidation {
    public static boolean isValidAmount(String amountStr) {
        try {
            double amount = Double.parseDouble(amountStr);
            return amount > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public static boolean isValidAccountNumber(String accountNumber) {
    	return accountNumber != null && accountNumber.matches("C\\d+");
    }
    
    public static boolean areDifferentAccounts(String fromAccount, String toAccount) {
        return !fromAccount.equals(toAccount);
    }
}
