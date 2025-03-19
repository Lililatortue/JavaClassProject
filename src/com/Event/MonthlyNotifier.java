package com.Event;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import com.Observer.TimeObserver;

/*
 * une classe qui notify TimeObserver a toute les mois
 * */
public class MonthlyNotifier {
	private Month currentMonth;
	private ArrayList<TimeObserver> notifier= new ArrayList<TimeObserver>(); 
	
	public MonthlyNotifier() {
		this.currentMonth = LocalDate.now().getMonth();
	}
	public void attach(TimeObserver o) {
		notifier.add(o);
	}
	public void UpdateAccount() {
		for (var o : notifier) {
			if(o.getState()) {
				o.update(currentMonth);
			}
		}
	}
}
