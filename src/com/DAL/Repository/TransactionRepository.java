package com.DAL.Repository;
import java.util.ArrayList;
import java.util.function.Predicate;
import com.Bus.Model.Transaction.Transaction;
import com.DAL.Repository.Connection.RecordStrategy;
import com.DAL.Repository.Exception.InvariantException;

public class TransactionRepository implements IRepository<Transaction>{
	private ArrayList<Transaction> _transactions;
	private RecordStrategy<Transaction> _strategy;
	
    public TransactionRepository(RecordStrategy<Transaction> strategy) {
        this.setStrategy(strategy);
        _transactions = _strategy.get((c) -> c != null); // Toujours retourner vrai
      
    }
	
    public TransactionRepository(RecordStrategy<Transaction> strategy, Predicate<Transaction> predicate) {
        this.setStrategy(strategy);
        _transactions = _strategy.get(predicate);
    }
	
	public void setStrategy(RecordStrategy<Transaction> strategy) {
		this._strategy = strategy;
	}
	
	@Override
	public void create(Transaction item) {
		_transactions.add(item);
        // Sauvegarder la liste de transactions après ajout
        _strategy.set(_transactions);
	}

	@Override
	public ArrayList<Transaction> read() {
		return this._transactions;
	}
	
	@Override
	public void update(Transaction item) {
		//no implimentation
	}

	@Override
	public void delete(Transaction item) throws InvariantException {
		if(_transactions.contains(item)) {
			_transactions.remove(item);
		}
		else
			throw new InvariantException("transaction does not exist");
	}
	public void commit() {
		_strategy.set(_transactions);
	}
		
}
