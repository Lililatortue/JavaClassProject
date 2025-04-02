package com.DAL.Repository;

import java.util.ArrayList;

public interface IRepository<c> {
	public void create(c item);
	public ArrayList<c> read();
	public void update(c item);
	public void delete(c item);
	
}
