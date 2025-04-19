package com.Bus.Model.Compte;

public enum CompteType{
	CHEQUE("CHEQUE"),
	CRED("CREDIT"),
	LGNCRED("LGNECRED"),
	DEV("DEVISE"),
	EPRGN("EPARGNE"),
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
