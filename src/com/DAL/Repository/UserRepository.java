package com.DAL.Repository;

import java.util.ArrayList;
import java.util.function.Predicate;
import com.Bus.Model.Client.Utilisateur;
import com.DAL.Repository.Connection.RecordStrategy;
import com.DAL.Repository.Exception.*;

public class UserRepository implements IRepository<Utilisateur>{
	private ArrayList<Utilisateur> _user;
	private RecordStrategy<Utilisateur> _strategy;
	

	public UserRepository(RecordStrategy<Utilisateur> strategy) {
		this.setStrategy(strategy);
		_user = _strategy.get((u)-> u.getId() >0 );//always return true
	}
	
	//setters
	public void setStrategy(RecordStrategy<Utilisateur> strategy) {
		this._strategy = strategy;
	}
	
	public Utilisateur findFirst(Predicate<Utilisateur> predicate) throws InvariantException {
		for(var user : _user) {
			if(predicate.test(user)) {
				return user;
			}
		}
		throw new InvariantException("User non existant");
	}
	
	
	@Override
	public void create(Utilisateur user) throws KeyConstraintException {
		//assurer integrite des donnes
		for(var item : _user) {
			if((item.getId()==user.getId())||(item.getEmail().equals(user.getEmail()))) {
				throw new KeyConstraintException("User id or email already exist");	
			}
		}
		
		_user.add(user);
		_strategy.set(_user);
	}

	@Override
	public ArrayList<Utilisateur> read() {	
		return _user;
	}
	
	@Override
	public void  update(Utilisateur user) {
		for(var  users : _user) {
	        if(user.getId()==users.getId()) {
	        	users=user;
	        }    	
	    }
	        _strategy.set(_user);
	}
	@Override
	public void delete(Utilisateur user) throws InvariantException {
		if (_user.contains(user)) {
	        _user.remove(user);
	       
	    } else {
	    	throw new InvariantException("User non existant");
	    }
	}

	public void commit() {
		 _strategy.set(_user);
	}
}
