package de.lmu.genzentrum.tresch;

/*
 * Unterklasse von Node
 */
public class VariableNode extends Node {
	private Object[] values;

	// private String type;

	public VariableNode(String name, int id) {
		super(name, id);
	}

	public VariableNode(String name, int id, Object[] values) {
		super(name, id);
		this.values = values;
	}

	public Object[] getValues() {
		return values;
	}

	public void setValues(Object[] values) {
		this.values = values;
	}

}
