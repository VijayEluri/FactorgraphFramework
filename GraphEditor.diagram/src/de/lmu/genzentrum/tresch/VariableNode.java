package de.lmu.genzentrum.tresch;

/*
 * Unterklasse von Node
 */
public class VariableNode extends Node{
	private Object[] values;
	private boolean isKnown;
	//private String type;

	public VariableNode(String name, int id){
		super(name, id);
	}
	
	public VariableNode(String name, int id, Object[] values, boolean isKnown){
		super(name, id);	
		this.values=values;
		this.isKnown=isKnown;
	}

	public Object[] getValues() {
		return values;
	}

	public void setValues(Object[] values) {
		this.values = values;
	}

	public boolean isKnown() {
		return isKnown;
	}

	public void setKnown(boolean isKnown) {
		this.isKnown = isKnown;
	}

	
	
}
