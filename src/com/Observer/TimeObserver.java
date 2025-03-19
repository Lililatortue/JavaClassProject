package com.Observer;

import java.time.Month;
;

public interface TimeObserver {
	public void update(Month currentMonth);
	public Boolean getState();
}
