package de.lmu.genzentrum.tresch;

/*
 * Unterklasse von Node
 */
public class FactorNode extends Node {
	private Object[][] function;
	private String[] nodeArray;

	public FactorNode(String name, int id) {
		super(name, id);
	}

	public FactorNode(String name, int id, Object[][] function) {
		super(name, id);
		this.function = function;
	}

	public FactorNode(String name, int id, Object[][] function,
			String[] nodeArray) {
		super(name, id);
		this.function = function;
		this.nodeArray = nodeArray;
	}

	public Object[][] getFunction() {
		return function;
	}

	public String[] getNodeArray() {
		return nodeArray;
	}

	public void setFunction(Object[][] function) {
		this.function = function;
	}

	public void setNodeArray(String[] nodeArray) {
		this.nodeArray = nodeArray;
	}

}
