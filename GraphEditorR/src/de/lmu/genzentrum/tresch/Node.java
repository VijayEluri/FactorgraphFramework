package de.lmu.genzentrum.tresch;

/*
 * Node ist ‹berklasse von FactorNode und VariableNode
 */
public abstract class Node {
	private String name;
	private int id;

	public Node(String name, int id) {
		this.name = name;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

}
