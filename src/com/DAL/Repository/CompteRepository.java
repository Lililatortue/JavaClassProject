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
	public CompteRepository(RecordStrategy<Compte> strategy) {
		this.setStrategy(strategy);
		_compte = _strategy.get((c)-> c!=null );//always return true
	}
	
	//personnalise repository
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
				throw new KeyConstraintException("account already exist");
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
	public void update(Compte compte) {
		for(var item : _compte) {
			if(item.getClientId()==compte.getClientId())
				item= compte;
		}
	}
	@Override
	public void delete(Compte compte) throws InvariantException {
		if(_compte.contains(compte)) {
			_compte.remove(compte);
		}
		else
			throw new InvariantException("compte does not exist");
	}
	public void commit() {
		_strategy.set(_compte);
	}
	
}
