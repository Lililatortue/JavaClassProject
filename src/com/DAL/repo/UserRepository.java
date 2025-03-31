package com.DAL.repo;

import java.util.ArrayList;
import java.util.function.Predicate;

import com.DAL.repo.strategy.RecordStrategy;
import com.buisness.client.Utilisateur;

public class UserRepository implements IRepository<Utilisateur>{
	private ArrayList<Utilisateur> _user;
	private RecordStrategy<Utilisateur> _strategy;
	

	
	@SuppressWarnings("unchecked")
	public UserRepository(RecordStrategy<Utilisateur> strategy) {
		this.setStrategy(strategy);
		_user = _strategy.get((u)-> u.getId() >0 );//always return true
	}
	
	//setters
	public void setStrategy(RecordStrategy<Utilisateur> strategy) {
		this._strategy = strategy;
	}
	
	public Utilisateur findFirst(Predicate<Utilisateur> predicate) {
		for(var user : _user) {
			if(predicate.test(user)) {
				return user;
			}
		}
		return null;
	}
	
	
	@Override
	public void create(Utilisateur user) {
		_user.add(user);
		_strategy.set(_user);
	}

	@Override
	public ArrayList<Utilisateur> read() {	
		return _user;
	}
	
	@Override
	public void  update(Utilisateur user) {
		System.out.print("feature not provided");
	}
	@Override
	public void delete(Utilisateur user) {
		_user.remove(user);
		_strategy.set(_user);	
	}


}
