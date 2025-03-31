package com.buisness.ConnectionHandler;

public class ConnectionHandler implements IConnectionHandler {
	private IConnectionHandler _next;
	
	
	public void setNext(IConnectionHandler next) {
		this._next = next;
	}
	
	public void Handle(Request request) {
		if(_next != null) {
			_next.Handle(request);
		}
	}
}
