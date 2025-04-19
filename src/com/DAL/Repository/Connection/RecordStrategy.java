package com.DAL.Repository.Connection;

import java.util.ArrayList;
import java.util.function.Predicate;

public interface RecordStrategy<c> {
	public void create(Predicate<c> predicate);
	public void delete(c item);
	public void update(c item);
	public ArrayList<c>  read();
	public void commit(ArrayList<c> c);
}
