package com.DAL.Repository.Connection;

import java.util.ArrayList;
import java.util.function.Predicate;

public interface RecordStrategy<c> {
	public ArrayList<c> get(Predicate<c> predicate);
	public void set(ArrayList<c> c);

}
