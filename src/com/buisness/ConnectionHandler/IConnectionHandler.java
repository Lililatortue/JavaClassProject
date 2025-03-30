package com.buisness.ConnectionHandler;

public interface IConnectionHandler {
	public void setNext(IConnectionHandler n);
	public void Handle(Request request);
}
