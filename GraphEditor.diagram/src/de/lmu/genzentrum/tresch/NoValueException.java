package de.lmu.genzentrum.tresch;

public class NoValueException extends Exception {
	private String s;

	public NoValueException(String s) {
		super(s);
	}

}
