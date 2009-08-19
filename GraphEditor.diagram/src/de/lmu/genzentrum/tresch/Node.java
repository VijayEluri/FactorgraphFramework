package de.lmu.genzentrum.tresch;

/*
 * Node ist Überklasse von FactorNode und VariableNode
 */
public abstract class Node {
	private String name;
	private int id;
	
	public Node(String name, int id){
		this.name=name;
		this.id=id;		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
