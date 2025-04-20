package com.Bus.Model.Compte;

public enum CompteType{
	CHEQUE("CHEQUE"),
	CREDIT("CREDIT"),
	LGNECRED("LGNECRED"),
	DEVISE("DEVISE"),
	EPARGNE("EPARGNE"),
	VRMNT("VIREMENT");
	private final String displayName;

    /**
     * contructeur par default
     * @param displayName
     */
    CompteType(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
