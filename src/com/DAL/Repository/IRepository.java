package com.DAL.Repository;

import java.util.List;

import com.DAL.Repository.Exception.InvariantException;
import com.DAL.Repository.Exception.KeyConstraintException;

public interface IRepository<c> {
	public List<c> read();
	public boolean create(c item) throws KeyConstraintException;
	public boolean update(c item);
	public boolean delete(c item) throws InvariantException;
	
}
