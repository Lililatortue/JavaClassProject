package com.DAL.Repository;

import java.util.ArrayList;
import java.util.function.Predicate;

import com.Bus.Model.Compte.Compte;
import com.DAL.Repository.Connection.RecordStrategy;
import com.DAL.Repository.Exception.*;

public class CompteRepository implements IRepository<Compte> {
	private ArrayList<Compte> _compte;
	private RecordStrategy<Compte> _strategy;
	
	//default constructor
	@SuppressWarnings("unchecked")
	public CompteRepository(RecordStrategy<Compte> strategy) {
		this.setStrategy(strategy);
		_compte = _strategy.get((c)-> c == c );//always return true
	}
	
	//personnalise repository
	@SuppressWarnings("unchecked")
	public CompteRepository(RecordStrategy<Compte> strategy,Predicate<Compte> predicate) {
		this.setStrategy(strategy);
		_compte = _strategy.get(predicate);
	}
	
	
	public void setStrategy(RecordStrategy<Compte> strategy) {
		this._strategy = strategy;	
	}
	
	@Override
	public void create(Compte item) throws KeyConstraintException {
		for(var _item : _compte) {
			if(_item.getClientId()==item.getClientId() && _item.getType()==item.getType()) {
				throw new KeyConstraintException("Request already fullfill");
			}
		}
		_compte.add(item);
		_strategy.set(_compte);	
	}
	
	@Override
	public ArrayList<Compte> read() {
		return this._compte;
	}
	
	@Override
	public void update(Compte item) {
		System.out.print("update service not provided for this class");
		
	}
	@Override
	public void delete(Compte compte) throws InvariantException {
		if (_compte.contains(compte)) {
			_compte.remove(compte);
	        _strategy.set(_compte);
	    } else {
	    	throw new InvariantException("Request already fullfill");
	    }
		
	}
	
}
