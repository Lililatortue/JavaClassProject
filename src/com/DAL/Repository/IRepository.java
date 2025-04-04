package com.DAL.Repository;

import java.util.ArrayList;

import com.DAL.Repository.Exception.InvariantException;
import com.DAL.Repository.Exception.KeyConstraintException;

public interface IRepository<c> {
	public void create(c item) throws KeyConstraintException;
	public ArrayList<c> read();
	public void update(c item);
	public void delete(c item) throws InvariantException;
	
}
