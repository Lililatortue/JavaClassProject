package com.Bus.Event;

import java.time.LocalDate;
import java.time.Month;

/*
 * Interface représentant un événement déclencheur lié aux intérêts bancaires
 */
public interface IInterestEvent {
	
	// Méthode appelée lorsqu'un événement d'intérêt survient
	public void update();
}
