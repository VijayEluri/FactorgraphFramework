package de.lmu.genzentrum.tresch;

/*
 * Unterklasse von Node
 */
public class VariableNode extends Node {
	private double[] values;

	// private String type;

	public VariableNode(String name, int id) {
		super(name, id);
	}

	public VariableNode(String name, int id, double[] values) {
		super(name, id);
		this.values = values;
	}

	public double[] getValues() {
		return values;
	}

	public void setValues(double[] values) {
		this.values = values;
	}

}
