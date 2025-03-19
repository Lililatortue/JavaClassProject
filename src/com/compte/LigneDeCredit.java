package com.compte;

import java.time.Month;

import com.Observer.TimeObserver;

public class LigneDeCredit extends AbstractCompte implements TimeObserver {
	
	private final double tauxInteret=0.05;
	private Double plafond;
	
	public LigneDeCredit(String numero, Double solde, Double plafond) {
		super(numero, solde);
		this.plafond=plafond;
	}

	@Override
	public void update(Month currentMonth) {
		int month_passed=currentMonth.compareTo(this.month);
		this.solde= solde +(1 * this.tauxInteret)*month_passed;
	}

	@Override
	public Month getState() {
		return this.month;
	}

}
