package com.DAL.repo;

import java.util.ArrayList;
import java.util.function.Predicate;
import com.DAL.repo.strategy.RecordStrategy;
import com.buisness.compte.Compte;

public class CompteRepository implements IRepository<Compte> {
	private ArrayList<Compte> _compte;
	private RecordStrategy<Compte> _strategy;
	
	
	@SuppressWarnings("unchecked")
	public CompteRepository(RecordStrategy<Compte> strategy) {
		this.setStrategy(strategy);
		_compte = _strategy.get((c)-> c == c );//always return true
	}
	
	@SuppressWarnings("unchecked")
	public CompteRepository(RecordStrategy<Compte> strategy,Predicate<Compte> predicate) {
		this.setStrategy(strategy);
		_compte = _strategy.get(predicate);
	}
	
	
	public void setStrategy(RecordStrategy<Compte> strategy) {
		this._strategy = strategy;	
	}
	
	@Override
	public void create(Compte item) {
		for(var _item : _compte) {
			if(_item.getClientId()==item.getClientId() && _item.getType()==item.getType()) {
				System.out.print("error unique constraint violated\n");
				//throw new UniqueConstraintViolation("Request already fullfill");
				return;
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
	public void delete(Compte item) {
		// TODO Auto-generated method stub
		
	}
	
}
